package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.InvalidResumeException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.SectionType;
import ru.javawebinar.basejava.model.content.Section;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static ru.javawebinar.basejava.util.JsonParser.read;
import static ru.javawebinar.basejava.util.JsonParser.write;

public class SqlStorage implements Storage {

    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(connection -> {
            try (PreparedStatement ps = connection.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ? ")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                if (ps.executeUpdate() != 1) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }
            deleteContacts(connection, resume);
            deleteSections(connection, resume);
            insertContacts(connection, resume);
            insertSections(connection, resume);
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        if (resume == null || resume.getUuid().isEmpty()) {
            throw new InvalidResumeException("Invalid UUID: ", resume);
        }

        sqlHelper.transactionalExecute(connection -> {
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO resume (uuid, full_name) values (?,?) ")) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.execute();
            }

            insertContacts(connection, resume);
            insertSections(connection, resume);
            return null;
        });

    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(conn -> {
            Resume r;
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume WHERE uuid =?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                r = new Resume(uuid, rs.getString("full_name"));
            }

            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact WHERE resume_uuid =?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addContact(rs, r);
                }
            }

            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section WHERE resume_uuid =?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addSection(rs, r);
                }
            }

            return r;
        });
    }

//    @Override
//    public Resume get(String uuid) {
//        return sqlHelper.execute("SELECT * FROM resume r " +
//                        "JOIN contact c " +
//                        "on r.uuid=c.resume_uuid " +
//                        "WHERE r.uuid = ?",
//                ps -> {
//                    ps.setString(1, uuid);
//                    ResultSet rs = ps.executeQuery();
//                    if (!rs.next()) {
//                        throw new NotExistStorageException(uuid);
//                    }
//                    Resume resume = new Resume(uuid, rs.getString("full_name"));
//                    do {
//                        setContact(rs, resume);
//                    } while (rs.next());
//
//                    return resume;
//                });
//    }

    @Override
    public void delete(String uuid) {
        sqlHelper.<Void>execute(
                "DELETE FROM resume " +
                        "WHERE uuid = ? ",
                ps -> {
                    ps.setString(1, uuid);
                    if (ps.executeUpdate() == 0) {
                        throw new NotExistStorageException(uuid);
                    }
                    ;
                    return null;
                });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(conn -> {
            Map<String, Resume> resumes = new LinkedHashMap<>();

            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume ORDER BY full_name, uuid")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    resumes.put(uuid, new Resume(uuid, rs.getString("full_name")));
                }
            }

            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Resume r = resumes.get(rs.getString("resume_uuid"));
                    addContact(rs, r);
                }
            }

            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Resume r = resumes.get(rs.getString("resume_uuid"));
                    addSection(rs, r);
                }
            }

            return new ArrayList<>(resumes.values());
        });
    }

    /*
    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("SELECT * FROM resume r " +
                "LEFT JOIN contact c " +
                "on r.uuid=c.resume_uuid " +
                "ORDER BY r.full_name, uuid ", ps -> {
            ResultSet rs = ps.executeQuery();
            Map<String, Resume> resumesMap = new LinkedHashMap<>();
            while (rs.next()) {
                String uuid = rs.getString("uuid");
                Resume resume = resumesMap.get(uuid);
                if(resume == null){
                    resume = new Resume(uuid, rs.getString("full_name"));
                    resumesMap.put(uuid, resume);
                }
                 setContact(rs, resume);
            }

            return new ArrayList<>(resumesMap.values());
        });
    }
*/
    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM resume ", st -> {
            ResultSet rs = st.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private void addContact(ResultSet rs, Resume resume) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            resume.setContact(ContactType.valueOf(rs.getString("type")), value);
        }
    }

    private void insertContacts(Connection connection, Resume resume) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO contact (resume_uuid, type, value) values (?,?,?)")) {
            for (Map.Entry<ContactType, String> c : resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, c.getKey().name());
                ps.setString(3, c.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertSections(Connection connection, Resume resume) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO section (resume_uuid, type, content) values (?,?,?)")) {
            for (Map.Entry<SectionType, Section> c : resume.getSections().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, c.getKey().name());
                Section section = c.getValue();

                ps.setString(3, write(section, Section.class));
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteContacts(Connection connection, Resume resume) throws SQLException {
        deleteAttributes(connection, resume, "DELETE FROM contact where resume_uuid = ?");
    }

    private void deleteSections(Connection connection, Resume resume) throws SQLException {
        deleteAttributes(connection, resume, "DELETE FROM section where resume_uuid = ?");
    }

    private void deleteAttributes(Connection connection, Resume resume, String sqlRequest) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(sqlRequest)) {
            ps.setString(1, resume.getUuid());
            ps.execute();
        }
    }

    private void addSection(ResultSet rs, Resume resume) throws SQLException {
        String content = rs.getString("content");
        if (content != null) {
            SectionType type = SectionType.valueOf(rs.getString("type"));
            resume.setSection(type, read(content, Section.class));
        }
    }
}
