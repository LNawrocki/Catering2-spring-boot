<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <jsp:include page="../fragments/title.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_new_menu_edit.css">
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu-admin.jsp"/>

<div class="main-block">
    <div class="day-block">
        <h3>Nowe menu na tydzień KW: ${kw} ( ${weekStart} - ${weekEnd} )</h3>
    </div>
</div>
<form:form action="/admin/newMenu/addMeal" method="post" modelAttribute="newMenu">
    <div class="main-block">
        <div class="login-row">
            <label for="mealNo">Numer potrawy:
                <form:input path="mealNo"/>
            </label>
        </div>
        <div class="login-row">
            <label>Cena:
                <select name="mealPrice">
                    <c:forEach var="price" items="${prices}">
                        <option value="${price.price}">${price.price} zł</option>
                    </c:forEach>
                </select>
            </label>
        </div>
        <div class="login-row">
            <label>Dzień:
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
            <label>Nazwa:
                <select name="mealName">
                    <option value="Brak" name="mealName">Pierwsza pozycja dnia musi się nazywać "Brak".</option>
                    <c:forEach var="dish" items="${dishes}">
                        <option value="${dish.name}" placeholder="Pierwsza pozycja dnia musi się nazywać 'Brak'.">${dish.name}</option>
                    </c:forEach>
                </select>
            </label>
        </div>
        <div class="login-row">
            <form:button>Dodaj</form:button>
            <input name="kw" value="${kw}" hidden="hidden"/>
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
                                <c:if test="${deleteButtonVisible == true}">
                                    <form action="/admin/newMenu/deleteMeal" method="post">
                                        <input name="mealNo" value="${mealMonday.mealNo}" hidden="hidden"/>
                                        <button>Usuń</button>
                                    </form>
                                </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </td>
                <c:if test="${deleteButtonVisible == true}">
                    <td>
                        <form action="/admin/newMenu/deleteDayMeals" method="post">
                            <input name="dayId" value="1" hidden="hidden"/>
                            <button>Usuń dzień</button>
                        </form>
                    </td>
                </c:if>
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
                                <c:if test="${deleteButtonVisible == true}">
                                    <form action="/admin/newMenu/deleteMeal" method="post">
                                        <input name="mealNo" value="${mealTuesday.mealNo}" hidden="hidden"/>
                                        <button>Usuń</button>
                                    </form>
                                </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </td>
                <c:if test="${deleteButtonVisible == true}">
                    <td>
                        <form action="/admin/newMenu/deleteDayMeals" method="post">
                            <input name="dayId" value="2" hidden="hidden"/>
                            <button>Usuń dzień</button>
                        </form>
                    </td>
                </c:if>
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
                                <c:if test="${deleteButtonVisible == true}">
                                    <form action="/admin/newMenu/deleteMeal" method="post">
                                        <input name="mealNo" value="${mealWednesday.mealNo}" hidden="hidden"/>
                                        <button>Usuń</button>
                                    </form>
                                </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </td>
                <c:if test="${deleteButtonVisible == true}">
                    <td>
                        <form action="/admin/newMenu/deleteDayMeals" method="post">
                            <input name="dayId" value="3" hidden="hidden"/>
                            <button>Usuń dzień</button>
                        </form>
                    </td>
                </c:if>
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
                                <c:if test="${deleteButtonVisible == true}">
                                    <form action="/admin/newMenu/deleteMeal" method="post">
                                        <input name="mealNo" value="${mealThursday.mealNo}" hidden="hidden"/>
                                        <button>Usuń</button>
                                    </form>
                                </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </td>
                <c:if test="${deleteButtonVisible == true}">
                    <td>
                        <form action="/admin/newMenu/deleteDayMeals" method="post">
                            <input name="dayId" value="4" hidden="hidden"/>
                            <button>Usuń dzień</button>
                        </form>
                    </td>
                </c:if>
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
                                <c:if test="${deleteButtonVisible == true}">
                                    <form action="/admin/newMenu/deleteMeal" method="post">
                                        <input name="mealNo" value="${mealFriday.mealNo}" hidden="hidden"/>
                                        <button>Usuń</button>
                                    </form>
                                </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </td>
                <c:if test="${deleteButtonVisible == true}">
                    <td>
                        <form action="/admin/newMenu/deleteDayMeals" method="post">
                            <input name="dayId" value="5" hidden="hidden"/>
                            <button>Usuń dzień</button>
                        </form>
                    </td>
                </c:if>
            </tr>
        </table>
    </div>
</div>


<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
