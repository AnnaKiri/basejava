package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        resume.setContacts(ContactType.PHONE_NUMBER, "+7(921) 855-0482");
        resume.setContacts(ContactType.SKYPE, "skype:grigory.kislin");
        resume.setContacts(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.setContacts(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin/");
        resume.setContacts(ContactType.GITHUB, "https://github.com/gkislin");
        resume.setContacts(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        resume.setContacts(ContactType.HOME_PAGE, "http://gkislin.ru/");

        TextSection textSection = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        resume.setSections(SectionType.OBJECTIVE, textSection);

        List<String> listTextSectionPersonal = new ArrayList<>();
        listTextSectionPersonal.add("Аналитический склад ума, сильная логика, креативность, инициативность.");
        listTextSectionPersonal.add("Пурист кода и архитектуры");
        resume.setSections(SectionType.PERSONAL, new ListTextSection(listTextSectionPersonal));

        List<String> listTextSectionAchievement = new ArrayList<>();
        listTextSectionAchievement.add("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет");
        listTextSectionAchievement.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.");
        listTextSectionAchievement.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        listTextSectionAchievement.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        listTextSectionAchievement.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        listTextSectionAchievement.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        listTextSectionAchievement.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        resume.setSections(SectionType.ACHIEVEMENT, new ListTextSection(listTextSectionAchievement));

        List<String> listTextSectionQualifications = new ArrayList<>();
        listTextSectionQualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        listTextSectionQualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        listTextSectionQualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
        listTextSectionQualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        listTextSectionQualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        listTextSectionQualifications.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements)");
        listTextSectionQualifications.add("Python: Django");
        listTextSectionQualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        listTextSectionQualifications.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        listTextSectionQualifications.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT");
        listTextSectionQualifications.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix");
        listTextSectionQualifications.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer");
        listTextSectionQualifications.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        listTextSectionQualifications.add("Родной русский, английский \"upper intermediate\"");
        resume.setSections(SectionType.QUALIFICATIONS, new ListTextSection(listTextSectionQualifications));

        List<Organization> organizationList = new ArrayList<>();
        Organization organization1 = new Organization("10/2013", "Сейчас", "Java Online Projects", "https://javaops.ru/", "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок.");
        Organization organization2 = new Organization("10/2014", "01/2016", "Wrike", "https://www.wrike.com/", "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        Organization organization3 = new Organization("04/2012", "10/2014", "RIT Center", "", "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        Organization organization4 = new Organization("12/2010", "04/2012", "Luxoft (Deutsche Bank)", "http://www.luxoft.ru/", "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        Organization organization5 = new Organization("06/2008", "12/2010", "Yota", "https://www.yota.ru/?utm_source=cityads&utm_medium=cpa&utm_campaign=2Keh&utm_term=8y6Z204JHXZt7YU&utm_content=8y6Z204JHXZt7YU&click_id=8y6Z204JHXZt7YU", "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
        Organization organization6 = new Organization("03/2007", "06/2008", "Enkata", "http://enkata.com/", "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        Organization organization7 = new Organization("01/2005", "02/2007", "Siemens AG", "https://www.siemens.com/global/en.html", "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        Organization organization8 = new Organization("09/1997", "01/2005", "Alcatel", "http://www.alcatel.ru/", "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");
        organizationList.add(organization1);
        organizationList.add(organization2);
        organizationList.add(organization3);
        organizationList.add(organization4);
        organizationList.add(organization5);
        organizationList.add(organization6);
        organizationList.add(organization7);
        organizationList.add(organization8);
        resume.setSections(SectionType.EXPERIENCE, new ListOrganizationSection(organizationList));

        List<Organization> educationList = new ArrayList<>();
        Organization education1 = new Organization("03/2013", "05/2013", "Coursera", "https://www.coursera.org/?irclickid=3Ky1XPVyPxyNTOQ2vSxwRRtOUkFwnRwPeUDdXA0&irgwc=1&utm_medium=partners&utm_source=impact&utm_campaign=1301558&utm_content=b2c", "'Functional Programming Principles in Scala' by Martin Odersky", "");
        Organization education2 = new Organization("03/2011", "04/2011", "Luxoft", "http://www.luxoft.ru/", "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'", "");
        Organization education3 = new Organization("01/2005", "04/2005", "Siemens AG", "https://www.siemens.com/global/en.html", "3 месяца обучения мобильным IN сетям (Берлин)", "");
        Organization education4 = new Organization("09/1997", "03/1998", "Alcatel", "http://www.alcatel.ru/", "6 месяцев обучения цифровым телефонным сетям (Москва)", "");
        Organization education5 = new Organization("09/1993", "07/1996", "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "https://itmo.ru/", "Аспирантура (программист С, С++)", "");
        Organization education6 = new Organization("09/1987", "07/1993", "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "https://itmo.ru/", "Инженер (программист Fortran, C)", "");
        Organization education7 = new Organization("09/1984", "06/1987", "Заочная физико-техническая школа при МФТИ", "https://mipt.ru/", "Закончил с отличием", "");
        educationList.add(education1);
        educationList.add(education2);
        educationList.add(education3);
        educationList.add(education4);
        educationList.add(education5);
        educationList.add(education6);
        educationList.add(education7);
        resume.setSections(SectionType.EDUCATION, new ListOrganizationSection(educationList));

        System.out.println(resume);
    }
}
