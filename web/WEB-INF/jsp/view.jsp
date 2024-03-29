<%@ page import="ru.javawebinar.basejava.model.TextSection" %>
<%@ page import="ru.javawebinar.basejava.model.ListTextSection" %>
<%@ page import="ru.javawebinar.basejava.model.CompanySection" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="ru.javawebinar.basejava.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h1>${resume.fullName}&nbsp;
        <a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a>
        <a href="resume?uuid=${resume.uuid}&action=delete"><img src="img/delete.png"></a>
    </h1>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.ContactType, java.lang.String>"/>
            <contacts><%=contactEntry.getKey().toHtml(contactEntry.getValue())%></contacts>
            <br/>
        </c:forEach>
    <p>
    <hr>
    <table cellpadding="2">
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.SectionType, ru.javawebinar.basejava.model.Section>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean id="section" type="ru.javawebinar.basejava.model.Section"/>
            <% if (section != null) { %>
                <tr>
                    <td colspan="2"><h2><a name="type.name">${type.title}</a></h2></td>
                </tr>
                <c:choose>
                    <c:when test="${type=='OBJECTIVE'}">
                        <tr>
                            <td colspan="2">
                                <h3><%=((TextSection) section).getDescription()%>
                                </h3>
                            </td>
                        </tr>
                    </c:when>
                    <c:when test="${type=='PERSONAL'}">
                        <tr>
                            <td colspan="2">
                                <%=((TextSection) section).getDescription()%>
                            </td>
                        </tr>
                    </c:when>
                    <c:when test="${type=='ACHIEVEMENT' || type=='QUALIFICATIONS'}">
                        <tr>
                            <td colspan="2">
                                <ul>
                                    <c:forEach var="item" items="<%=((ListTextSection) section).getStrings()%>">
                                        <li>${item}</li>
                                    </c:forEach>
                                </ul>
                            </td>
                        </tr>
                    </c:when>
                    <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                        <c:forEach var="company" items="<%=((CompanySection) section).getCompanies()%>">
                            <tr>
                                <td colspan="2">
                                    <c:choose>
                                        <c:when test="${empty company.website}">
                                            <h3><job-name>${company.name}</job-name></h3>
                                        </c:when>
                                        <c:otherwise>
                                            <h3><job-name><a href="${company.website}">${company.name}</a></job-name></h3>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <c:forEach var="period" items="${company.periods}">
                                <jsp:useBean id="period" type="ru.javawebinar.basejava.model.Period"/>

                                <tr>
                                    <td width="15%" style="vertical-align: top"><%=DateUtil.format(period.getStartDate()) + " - " + DateUtil.format(period.getEndDate())%>
                                    </td>
                                    <td><b>${period.position}</b><br>${period.description}</td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                    </c:when>
                </c:choose>
            <% } %>
        </c:forEach>
    </table>
    <br/>
    <button onclick="window.history.back()">ОК</button>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
