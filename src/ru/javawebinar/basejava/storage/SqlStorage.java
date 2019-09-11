package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.InvalidResumeException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {

    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(connection -> {
            try (PreparedStatement ps = connection.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ? ")){
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                if (ps.executeUpdate()!=1) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }
            deleteContacts(connection, resume);
            insertContact(connection, resume);
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        if (resume == null || resume.getUuid().isEmpty()){
            throw new InvalidResumeException("Invalid UUID: ", resume);
        }

        sqlHelper.transactionalExecute(connection -> {
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO resume (uuid, full_name) values (?,?) ")) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.execute();
            }

            insertContact(connection, resume);

            return null;

        });

    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("SELECT * FROM resume r " +
                        "JOIN contact c " +
                        "on r.uuid=c.resume_uuid " +
                        "WHERE r.uuid = ?",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume resume = new Resume(uuid, rs.getString("full_name"));
                    do {
                        addContact(rs, resume);
                    }while (rs.next());

                    return resume;
                });
    }

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
        return sqlHelper.execute("SELECT * FROM resume r " +
                "LEFT JOIN contact c " +
                "on r.uuid=c.resume_uuid " +
                "ORDER BY r.full_name, uuid ", ps -> {
            ResultSet rs = ps.executeQuery();
            Map<String, Resume> resumesMap = new HashMap<>();
            while (rs.next()) {
                String uuid = rs.getString("uuid");
                Resume resume = resumesMap.get(uuid);
                if(resume == null){
                    resume = new Resume(uuid, rs.getString("full_name"));
                    resumesMap.put(uuid, resume);
                }
                 addContact(rs, resume);
            }

            return new ArrayList<>(resumesMap.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM resume ", st -> {
            ResultSet rs = st.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private void addContact(ResultSet rs, Resume resume) throws SQLException {
        resume.addContact(ContactType.valueOf(rs.getString("type")), rs.getString("value"));
    }

    private void insertContact(Connection connection, Resume resume) throws SQLException {
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

    private void deleteContacts(Connection connection, Resume resume) {
        sqlHelper.execute("DELETE FROM contact where resume_uuid = ?", ps ->{
            ps.setString(1, resume.getUuid());
            ps.execute();
            return null;
        });
    }
}
