package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Organization;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.SectionType;
import ru.javawebinar.basejava.model.content.ListSection;
import ru.javawebinar.basejava.model.content.OrganizationSection;
import ru.javawebinar.basejava.model.content.Section;
import ru.javawebinar.basejava.model.content.TextSection;

import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.now;
import static java.time.LocalDate.of;

public class KislinResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("01", "Григорий Кислин");
        resume.setContact(ContactType.LANDLINE, "+7(921) 855-0482");
        resume.setContact(ContactType.SKYPE, "grigory.kislin");
        resume.setContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.setContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/gkislin");
        resume.setContact(ContactType.HOME_PAGE, "http://gkislin.ru/");

        resume.setSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));

        Section personalQuality = new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры. ");
        resume.setSection(SectionType.PERSONAL, personalQuality);
        //achievement
        List<String> achievementList = new ArrayList<>();
        achievementList.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников");
        achievementList.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk. ");
        achievementList.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера. ");
        achievementList.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга. ");
        achievementList.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django). ");
        achievementList.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");

        resume.setSection(SectionType.ACHIEVEMENT, new ListSection(achievementList));
        //qualifications
        ArrayList qualificationsList = new ArrayList();
        qualificationsList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2 ");
        qualificationsList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce ");
        qualificationsList.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB  ");
        qualificationsList.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,\n" +
                "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,\n" +
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).   ");
        qualificationsList.add("Python: Django");

        resume.setSection(SectionType.QUALIFICATIONS, new ListSection(qualificationsList));
        //experience
        List<Organization> experienceList = new ArrayList<>();
        experienceList.add(new Organization("Java Online Projects", "javaops.ru", new Organization.Position(of(2013, 10, 1), now(), "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.")));
        experienceList.add(new Organization("Wrike","https://www.wrike.com/", new Organization.Position(of(2014, 10, 1), of(2016, 1, 1), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")));
        experienceList.add(new Organization("RIT Center",null, new Organization.Position(of(2012, 4, 1), of(2014, 10, 1), "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python")));
        Section experience = new OrganizationSection(experienceList);
        resume.setSection(SectionType.EXPERIENCE, experience);

        //education
        List<Organization> educationList = new ArrayList<>();
        educationList.add(new Organization("Coursera","https://www.coursera.org/course/progfun", new Organization.Position(of(2013, 3, 1), of(2013, 5, 1), "Trainee","\"Functional Programming Principles in Scala\" by Martin Odersky" )));
        educationList.add(new Organization("Luxoft","www.luxoft-training.ru/training/catalog/course.html?ID=22366", new Organization.Position(of(2011, 3, 1), of(2011, 4, 1), "Trainee","Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"")));
        educationList.add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики","http://www.ifmo.ru/ru/", new Organization.Position(of(1993, 9, 1), of(1996, 7, 1), "Postgraduate","(программист С, С++)"),new Organization.Position(of(1987, 9, 1), of(1993, 7, 1), "Student","(программист Fortran, C)")));
        educationList.add(new Organization("Заочная физико-техническая школа при МФТИ","http://www.school.mipt.ru/", new Organization.Position(of(1984, 9, 1), of(1987, 6, 1), "Pupil" ,"Закончил с отличием")));

        Section education = new OrganizationSection(educationList);
        resume.setSection(SectionType.EDUCATION, education);


    }


}
