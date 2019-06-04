package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Period;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.SectionType;
import ru.javawebinar.basejava.model.content.*;

import static java.time.LocalDate.now;
import static java.time.LocalDate.of;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("01", "Григорий Кислин");
        resume.addContact(ContactType.LANDLINE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/gkislin");
        resume.addContact(ContactType.HOME_PAGE, "http://gkislin.ru/");

        Section jobPosition = new SingleLine(new OneLine("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        Section personalQuality = new SingleLine(new OneLine("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры. "));

        //achievement
        Content achievement1 = new OneLine("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников");
        Content achievement2 = new OneLine("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk. ");
        Content achievement3 = new OneLine("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера. ");
        Content achievement4 = new OneLine("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга. ");
        Content achievement5 = new OneLine("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django). ");
        Content achievement6 = new OneLine("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");

        Section achievement = new MultiLine();
        achievement.addRow(achievement1);
        achievement.addRow(achievement2);
        achievement.addRow(achievement3);
        achievement.addRow(achievement4);
        achievement.addRow(achievement5);
        achievement.addRow(achievement6);

        //qualifications
        Section qualifications = new MultiLine();
        qualifications.addRow(new OneLine("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2 "));
        qualifications.addRow(new OneLine("Version control: Subversion, Git, Mercury, ClearCase, Perforce "));
        qualifications.addRow(new OneLine("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB  "));
        qualifications.addRow(new OneLine("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,\n" +
                "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,\n" +
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).   "));
        qualifications.addRow(new OneLine("Python: Django"));

        //experience
        Section experience = new MultiLine();
        experience.addRow(new Job("Java Online Projects", new Period(of(2013, 10, 1), now()), "Создание, организация и проведение Java онлайн проектов и стажировок.", "Автор проекта."));
        experience.addRow(new Job("Wrike", new Period(of(2014, 10, 1), of(2016, 1, 1)), "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.", "Старший разработчик (backend)"));
        experience.addRow(new Job("RIT Center", new Period(of(2012, 4, 1), of(2014, 10, 1)), "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python", " Java архитектор"));

        //
        Section education = new MultiLine();
        education.addRow(new Education("Coursera", new Period(of(2013, 3, 1), of(2013, 5, 1)), "\"Functional Programming Principles in Scala\" by Martin Odersky"));
        education.addRow(new Education("Luxoft", new Period(of(2011, 3, 1), of(2011, 4, 1)), "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\""));
        education.addRow(new Education("Заочная физико-техническая школа при МФТИ", new Period(of(1984, 9, 1), of(1987, 6, 1)), "Закончил с отличием"));

        resume.addSection(SectionType.OBJECTIVE, jobPosition);
        resume.addSection(SectionType.PERSONAL, personalQuality);
        resume.addSection(SectionType.ACHIEVEMENT, achievement);
        resume.addSection(SectionType.QUALIFICATIONS, qualifications);
        resume.addSection(SectionType.EXPERIENCE, experience);
        resume.addSection(SectionType.EDUCATION, education);
    }
}
