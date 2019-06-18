package ru.javawebinar.basejava;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.MapStorage;
import ru.javawebinar.basejava.storage.Storage;

/**
 * Test for your ArrayStorage implementation
 */
class MainTestMapStorage {
    private static final Storage ARRAY_STORAGE = new MapStorage();

    public static void main(String[] args) {
        final Resume r1 = new Resume("uuid1");
        final Resume r2 = new Resume("uuid2");
        final Resume r3 = new Resume("uuid3");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        try {
            System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        } catch (NotExistStorageException e) {
            System.out.println("Get dummy: NOT exist");
        }

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    private static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }

    public static class ResumeTestData {

        public static void main(String[] args) {
            Resume resume = new Resume("01", "Григорий Кислин");
            resume.addContact(ContactType.LANDLINE, "+7(921) 855-0482");
            resume.addContact(ContactType.SKYPE, "grigory.kislin");
            resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
            resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
            resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
            resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/gkislin");
            resume.addContact(ContactType.HOME_PAGE, "http://gkislin.ru/");
/*
            Section jobPosition = new TextSection(new OneLineContent("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
            resume.addSection(SectionType.OBJECTIVE, jobPosition);

            Section personalQuality = new TextSection(new OneLineContent("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры. "));
            resume.addSection(SectionType.PERSONAL, personalQuality);
            //achievement
            List<Content> achievementList = new ArrayList<>();
            achievementList.add(new OneLineContent("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников"));
            achievementList.add(new OneLineContent("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk. "));
            achievementList.add(new OneLineContent("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера. "));
            achievementList.add(new OneLineContent("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга. "));
            achievementList.add(new OneLineContent("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django). "));
            achievementList.add(new OneLineContent("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."));

            resume.addSection(SectionType.ACHIEVEMENT, new ListSection(achievementList));
            //qualifications
            List<Content> qualificationsList = new ArrayList();

            qualificationsList.add(new OneLineContent("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2 "));
            qualificationsList.add(new OneLineContent("Version control: Subversion, Git, Mercury, ClearCase, Perforce "));
            qualificationsList.add(new OneLineContent("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB  "));
            qualificationsList.add(new OneLineContent("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,\n" +
                    "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,\n" +
                    "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).   "));
            qualificationsList.add(new OneLineContent("Python: Django"));

            resume.addSection(SectionType.QUALIFICATIONS, new ListSection(qualificationsList));
            //experience
            List<Content> experienceList = new ArrayList<>();
            experienceList.add(new JobContent("Java Online Projects","javaops.ru", of(2013, 10, 1), now(), "Создание, организация и проведение Java онлайн проектов и стажировок.", "Автор проекта."));
            experienceList.add(new JobContent("Wrike","https://www.wrike.com/", of(2014, 10, 1), of(2016, 1, 1), "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.", "Старший разработчик (backend)"));
            experienceList.add(new JobContent("RIT Center",null, of(2012, 4, 1), of(2014, 10, 1), "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python", " Java архитектор"));
            Section experience = new ListSection(experienceList);
            resume.addSection(SectionType.EXPERIENCE, experience);

            //
            List<Content> educationList = new ArrayList<>();
            educationList.add(new EducationContent("Coursera","https://www.coursera.org/course/progfun", of(2013, 3, 1), of(2013, 5, 1), "\"Functional Programming Principles in Scala\" by Martin Odersky"));
            educationList.add(new EducationContent("Luxoft","www.luxoft-training.ru/training/catalog/course.html?ID=22366", of(2011, 3, 1), of(2011, 4, 1), "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\""));
            educationList.add(new EducationContent("Заочная физико-техническая школа при МФТИ","http://www.school.mipt.ru/", of(1984, 9, 1), of(1987, 6, 1), "Закончил с отличием"));

            Section education = new ListSection(educationList);
            resume.addSection(SectionType.EDUCATION, education);*/
        }
    }
}
