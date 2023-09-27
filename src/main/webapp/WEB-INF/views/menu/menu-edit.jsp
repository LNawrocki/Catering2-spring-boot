<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <title>Catering - Lightnet</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_home.css">
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu.jsp"/>

<div class="main-block">
    <div class="day-block">
        <h3>Nowe menu na tydzień KW: ${date}</h3>
    </div>
</div>
<form:form method="post" modelAttribute="newMenu">
    <div class="main-block">
        <div class="day-block">
            <label for="mealNo">numer dania:
                <form:input path="mealNo"/>
            </label>
            <label for="mealName">Nazwa dania:
                <form:input path="mealName"/>
            </label>
            <label for="mealPrice">Cena dania:
                <form:input path="mealPrice"/>
            </label>
            <label for="dayId">Numer dnia:
                <form:input path="dayId"/>
            </label>
            <div class="login-row">
                <form:button>Dodaj</form:button>
            </div>
        </div>
    </div>
</form:form>


<div class="main-block">
    <div class="day-block">
        <table>
            <tr>
                <td>
                    <div class="day">
                        <div>
                            Poniedziałek
                        </div>
                    </div>
                </td>
                <td>
                    <div class="meal">
                        <ul>
                            <c:forEach var="mealMonday" items="${mealsMonday}">
                                <li> ${mealMonday.mealNo} ${mealMonday.mealName}<a href="/menu/delete?mealNo=${mealMonday.mealNo}">Usuń</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="day">
                        <div>
                            Wtorek
                        </div>
                    </div>
                </td>
                <td>
                    <div class="meal">
                        <ul>
                            <c:forEach var="mealTuesday" items="${mealsTuesday}">
                                <li> ${mealTuesday.mealNo} ${mealTuesday.mealName}<a href="/menu/delete?mealNo=${mealTuesday.mealNo}">Usuń</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="day">
                        <div>
                            Środa
                        </div>
                    </div>
                </td>
                <td>
                    <div class="meal">
                        <ul>
                            <c:forEach var="mealWednesday" items="${mealsWednesday}">
                                <li>${mealWednesday.mealNo} ${mealWednesday.mealName}<a href="/menu/delete?mealNo=${mealWednesday.mealNo}">Usuń</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="day">
                        <div>
                            Czwartek
                        </div>
                    </div>
                </td>
                <td>
                    <div class="meal">
                        <ul>
                            <c:forEach var="mealThursday" items="${mealsThursday}">
                                <li>${mealThursday.mealNo} ${mealThursday.mealName}<a href="/menu/delete?mealNo=${mealThursday.mealNo}">Usuń</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="day">
                        <div>
                            Piątek
                        </div>
                    </div>
                </td>
                <td>
                    <div class="meal">
                        <ul>
                            <c:forEach var="mealFriday" items="${mealsFriday}">
                                <li>${mealFriday.mealNo} ${mealFriday.mealName}<a href="/menu/delete?mealNo=${mealFriday.mealNo}">Usuń</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</div>


<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
