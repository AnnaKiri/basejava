<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page import="ru.javawebinar.basejava.model.ListTextSection" %>
<%@ page import="ru.javawebinar.basejava.model.CompanySection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>${param.uuid == null ? 'Новое резюме' : resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <h1>Имя:</h1>
        <dl>
            <input type="text" name="fullName" size=55 value="${resume.fullName}" required>
        </dl>
        <h2>Контакты:</h2>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <hr>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(type)}"/>
            <jsp:useBean id="section" type="ru.javawebinar.basejava.model.Section"/>
            <h2><a>${type.title}</a></h2>
            <c:choose>
                <c:when test="${type.name() != 'EXPERIENCE' && type.name() != 'EDUCATION'}">
                    <dl>
                        <dt>${type.title}</dt>
                        <dd><textarea rows="5" name="${type.name()}"
                                      cols="100">${ resume.getSection(type) != null ? resume.getSectionByType(type) : null }</textarea>
                        </dd>
                    </dl>
                </c:when>
                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <dl>
                        <dt style="margin-left: 10px">Название компании:</dt>
                        <dd><input type="text" name='${type}' size="75"></dd>
                    </dl>
                    <dl>
                        <dt style="margin-left: 10px">Сайт:</dt>
                        <dd><input type="text" name='${type}url' size="75"></dd>
                    </dl>
                    <dl>
                        <dt style="margin-left: 30px">Дата начала:</dt>
                        <dd><input type="text" name="${type}0startDate" size="30"></dd>
                    </dl>
                    <dl>
                        <dt style="margin-left: 30px">Дата окончания:</dt>
                        <dd><input type="text" name="${type}0endDate" size="30"></dd>
                    </dl>
                    <dl>
                        <dt style="margin-left: 30px">Должность:</dt>
                        <dd><input type="text" name="${type}0position" size="30"></dd>
                    </dl>
                    <dl>
                        <dt style="margin-left: 30px">Описание:</dt>
                        <dd><textarea rows="3" name="${type}0description" cols="70"></textarea></dd>
                    </dl>
                    <c:forEach var="company" items="<%=((CompanySection) section).getCompanies()%>"
                               varStatus="counter">
                        <dl>
                            <dt style="margin-left: 10px">Название компании:</dt>
                            <dd><input type="text" name='${type}' size=75 value="${company.getName()}"></dd>
                        </dl>
                        <dl>
                            <dt style="margin-left: 10px">Сайт:</dt>
                            <dd><input type="text" name='${type}url' size=75 value="${company.getWebsite()}"></dd>
                        </dl>
                        <br>
                        <div style="margin-left: 30px">
                            <c:forEach var="period" items="${company.periods}">
                                <jsp:useBean id="period" type="ru.javawebinar.basejava.model.Period"/>
                                <dl>
                                    <dt style="margin-left: 30px">Дата начала:</dt>
                                    <dd><input type="text" name="${type}${counter.index+1}startDate" size="30"
                                               placeholder="MM/yyyy"></dd>
                                </dl>
                                <dl>
                                    <dt style="margin-left: 30px">Дата окончания:</dt>
                                    <dd><input type="text" name="${type}${counter.index+1}endDate" size="30"
                                               placeholder="MM/yyyy"></dd>
                                </dl>
                                <dl>
                                    <dt style="margin-left: 30px">Должность:</dt>
                                    <dd><input type="text" name="${type}${counter.index+1}position" size="30"></dd>
                                </dl>
                                <dl>
                                    <dt style="margin-left: 30px">Описание:</dt>
                                    <dd><textarea rows="3" name="${type}${counter.index+1}description" cols="70"></textarea></dd>
                                </dl>
                                <dl>
                                    <dt style="margin-left: 30px">Дата начала:</dt>
                                    <dd>
                                        <input type="text" name="${type}${counter.index+1}startDate" size=15
                                               value="${period.startDate}" placeholder="MM/yyyy">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt style="margin-left: 30px">Дата окончания:</dt>
                                    <dd>
                                        <input type="text" name="${type}${counter.index+1}endDate" size=15
                                               value="${period.endDate}" placeholder="MM/yyyy">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt style="margin-left: 30px">Должность:</dt>
                                    <dd><input type="text" name='${type}${counter.index+1}position' size=75
                                               value="${period.position}">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt style="margin-left: 30px">Описание:</dt>
                                    <dd><textarea name="${type}${counter.index+1}description" rows=5
                                                  cols=75>${period.description}</textarea></dd>
                                </dl>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button type="button" onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
