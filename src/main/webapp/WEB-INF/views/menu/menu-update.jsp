<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <jsp:include page="../fragments/title.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_menu_update.css">
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu-admin.jsp"/>

<div class="main-block">
    <div class="day-block">
        <h3>Nowe menu na tydzień KW: ${date}</h3>
    </div>
</div>
<form:form method="post" modelAttribute="newMenu">
    <div class="main-block">
        <div class="login-row">
            <label for="mealNo">Numer potrawy:
                <form:input path="mealNo"/>
            </label>
        </div>
        <div class="login-row">
            <label for="mealPrice">Cena:
                <input type="number" name="mealPrice" placeholder="np. 14.5" step="0.01"/> zł
            </label>
        </div>
        <div class="login-row">
            <label for="dayId">Dzień (id):
                <select name="dayId">
                    <option value="1" name="Poniedziałek">Poniedziałek</option>
                    <option value="2" name="Wtorek">Wtorek</option>
                    <option value="3" name="Środe">Środe</option>
                    <option value="4" name="Czwartek">Czwartek</option>
                    <option value="5" name="Piątek">Piątek</option>
                </select>
            </label>
        </div>
        <div class="login-row">
            <label for="mealName">Nazwa:
                <input name="mealName" type="text" size="100"/>
            </label>
        </div>
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
                                <li> ${mealMonday.mealNo} ${mealMonday.mealName} ${mealMonday.mealPrice} zł
                                    <a href="/menu/delete?mealNo=${mealMonday.mealNo}">Usuń</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </td>
                <td class="day">
                    <a href="/menu/deleteDay?dayId=1">Usuń dzień</a></li>
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
                                <li> ${mealTuesday.mealNo} ${mealTuesday.mealName} ${mealTuesday.mealPrice} zł
                                    <a href="/menu/delete?mealNo=${mealTuesday.mealNo}">Usuń</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </td>
                <td class="day">
                    <a href="/menu/deleteDay?dayId=2">Usuń dzień</a></li>
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
                                <li>${mealWednesday.mealNo} ${mealWednesday.mealName} ${mealWednesday.mealPrice} zł
                                    <a href="/menu/delete?mealNo=${mealWednesday.mealNo}">Usuń</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </td>
                <td class="day">
                    <a href="/menu/deleteDay?dayId=3">Usuń dzień</a></li>
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
                                <li>${mealThursday.mealNo} ${mealThursday.mealName} ${mealThursday.mealPrice} zł
                                    <a href="/menu/delete?mealNo=${mealThursday.mealNo}">Usuń</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </td>
                <td class="day">
                    <a href="/menu/deleteDay?dayId=4">Usuń dzień</a></li>
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
                                <li>${mealFriday.mealNo} ${mealFriday.mealName} ${mealFriday.mealPrice} zł
                                    <a href="/menu/delete?mealNo=${mealFriday.mealNo}">Usuń</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </td>
                <td class="day">
                    <a href="/menu/deleteDay?dayId=5">Usuń dzień</a></li>
                </td>
            </tr>
        </table>
    </div>
</div>


<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
