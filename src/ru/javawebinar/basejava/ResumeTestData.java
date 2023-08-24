package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {

    private static final boolean USE_TOSTRING = true;

    public static void main(String[] args) {
        Resume resume = createFilledResume("uuid1", "Григорий Кислин");

        if (USE_TOSTRING) {
            System.out.println(resume);
        } else {
            printResume(resume);
        }
    }

    private static List<Period> fillPeriodList(LocalDate startDate, LocalDate endDate, String position, String description) {
        List<Period> periodList = new ArrayList<>();
        periodList.add(new Period(startDate, endDate, position, description));
        return periodList;
    }

    public static Resume createFilledResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        resume.setContacts(ContactType.PHONE_NUMBER, "+7(921) 855-0482");
        resume.setContacts(ContactType.SKYPE, "skype:grigory.kislin");
        resume.setContacts(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.setContacts(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin/");
        resume.setContacts(ContactType.GITHUB, "https://github.com/gkislin");
        resume.setContacts(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        resume.setContacts(ContactType.HOME_PAGE, "http://gkislin.ru/");

        TextSection textSectionObjective = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        resume.setSections(SectionType.OBJECTIVE, textSectionObjective);

        TextSection textSectionPersonal = new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        resume.setSections(SectionType.PERSONAL, textSectionPersonal);

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
/*
        Company company1 = new Company(fillPeriodList(LocalDate.of(2013, 10, 1), LocalDate.now(), "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок."), "Java Online Projects", "https://javaops.ru/");
        Company company2 = new Company(fillPeriodList(LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."), "Wrike", "https://www.wrike.com/");
        Company company3 = new Company(fillPeriodList(LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 1), "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"), "RIT Center", "");
        Company company4 = new Company(fillPeriodList(LocalDate.of(2010, 12, 1), LocalDate.of(2012, 4, 1), "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."), "Luxoft (Deutsche Bank)", "http://www.luxoft.ru/");
        Company company5 = new Company(fillPeriodList(LocalDate.of(2008, 6, 1), LocalDate.of(2010, 12, 1), "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"), "Yota", "https://www.yota.ru/?utm_source=cityads&utm_medium=cpa&utm_campaign=2Keh&utm_term=8y6Z204JHXZt7YU&utm_content=8y6Z204JHXZt7YU&click_id=8y6Z204JHXZt7YU");
        Company company6 = new Company(fillPeriodList(LocalDate.of(2007, 3, 1), LocalDate.of(2008, 6, 1), "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)."), "Enkata", "http://enkata.com/");
        Company company7 = new Company(fillPeriodList(LocalDate.of(2005, 1, 1), LocalDate.of(2007, 2, 1), "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."), "Siemens AG", "https://www.siemens.com/global/en.html");
        Company company8 = new Company(fillPeriodList(LocalDate.of(1997, 9, 1), LocalDate.of(2005, 1, 1), "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."), "Alcatel", "http://www.alcatel.ru/");
        List<Company> companyList = new ArrayList<>();
        companyList.add(company1);
        companyList.add(company2);
        companyList.add(company3);
        companyList.add(company4);
        companyList.add(company5);
        companyList.add(company6);
        companyList.add(company7);
        companyList.add(company8);
        resume.setSections(SectionType.EXPERIENCE, new CompanySection(companyList));

        Company education1 = new Company(fillPeriodList(LocalDate.of(2013, 3, 1), LocalDate.of(2013, 5, 1), "'Functional Programming Principles in Scala' by Martin Odersky", ""), "Coursera", "https://www.coursera.org/?irclickid=3Ky1XPVyPxyNTOQ2vSxwRRtOUkFwnRwPeUDdXA0&irgwc=1&utm_medium=partners&utm_source=impact&utm_campaign=1301558&utm_content=b2c");
        Company education2 = new Company(fillPeriodList(LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4, 1), "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'", ""), "Luxoft", "http://www.luxoft.ru/");
        Company education3 = new Company(fillPeriodList(LocalDate.of(2005, 1, 1), LocalDate.of(2005, 4, 1), "3 месяца обучения мобильным IN сетям (Берлин)", ""), "Siemens AG", "https://www.siemens.com/global/en.html");
        Company education4 = new Company(fillPeriodList(LocalDate.of(1997, 9, 1), LocalDate.of(1998, 3, 1), "6 месяцев обучения цифровым телефонным сетям (Москва)", ""), "Alcatel", "http://www.alcatel.ru/");
        Company education5 = new Company(fillPeriodList(LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7, 1), "Аспирантура (программист С, С++)", ""), "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "https://itmo.ru/");
        Company education6 = new Company(fillPeriodList(LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1), "Инженер (программист Fortran, C)", ""), "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "https://itmo.ru/");
        Company education7 = new Company(fillPeriodList(LocalDate.of(1984, 9, 1), LocalDate.of(1987, 6, 1), "Закончил с отличием", ""), "Заочная физико-техническая школа при МФТИ", "https://mipt.ru/");
        companyList = new ArrayList<>();
        companyList.add(education1);
        companyList.add(education2);
        companyList.add(education3);
        companyList.add(education4);
        companyList.add(education5);
        companyList.add(education6);
        companyList.add(education7);
        resume.setSections(SectionType.EDUCATION, new CompanySection(companyList));
*/
        return resume;
    }

    private static void printResume(Resume resume) {
        System.out.println(resume.getFullName());

        for (ContactType type : ContactType.values()) {
            System.out.println(type.getTitle() + ": " + resume.getContact(type));
        }
        System.out.println();

        System.out.println(SectionType.OBJECTIVE.getTitle() + ": " + "\n" + ((TextSection) resume.getSection(SectionType.OBJECTIVE)).getDescription() + "\n");
        System.out.println(SectionType.PERSONAL.getTitle() + ": " + "\n" + ((TextSection) resume.getSection(SectionType.PERSONAL)).getDescription() + "\n");

        List<String> listAchievement = ((ListTextSection) resume.getSection(SectionType.ACHIEVEMENT)).getStrings();
        System.out.println(SectionType.ACHIEVEMENT.getTitle() + ": ");
        for (String line : listAchievement) {
            System.out.println("• " + line);
        }
        System.out.println();

        List<String> listQualification = ((ListTextSection) resume.getSection(SectionType.QUALIFICATIONS)).getStrings();
        System.out.println(SectionType.QUALIFICATIONS.getTitle() + ": ");
        for (String line : listQualification) {
            System.out.println("• " + line);
        }
        System.out.println();

        List<Company> listExperience = ((CompanySection) resume.getSection(SectionType.EXPERIENCE)).getCompanies();
        System.out.println(SectionType.EXPERIENCE.getTitle() + ": ");
        for (Company organization : listExperience) {
            for (Period period : organization.getPeriods()) {
                System.out.println(
                        period.getStartDate().getMonthValue() + "/"
                                + period.getStartDate().getYear()
                                + "-" + period.getEndDate().getMonthValue() + "/"
                                + period.getEndDate().getYear() +
                                " " + organization.getName() +
                                " " + organization.getWebsite() + " " +
                                period.getPosition() + " " +
                                period.getDescription());
            }
        }
        System.out.println();

        List<Company> listEducation = ((CompanySection) resume.getSection(SectionType.EDUCATION)).getCompanies();
        System.out.println(SectionType.EDUCATION.getTitle() + ": ");
        for (Company organization : listEducation) {
            for (Period period : organization.getPeriods()) {
                System.out.println(
                        period.getStartDate().getMonthValue() + "/"
                                + period.getStartDate().getYear()
                                + "-" + period.getEndDate().getMonthValue() + "/"
                                + period.getEndDate().getYear() +
                                " " + organization.getName() +
                                " " + organization.getWebsite() + " " +
                                period.getPosition() + " " +
                                period.getDescription());
            }
        }
    }
}
