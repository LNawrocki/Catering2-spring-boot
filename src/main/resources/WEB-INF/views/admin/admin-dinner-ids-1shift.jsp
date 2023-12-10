<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <jsp:include page="../fragments/title.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_admin_dinner_ids.css">
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu-admin.jsp"/>

<div class="main-block">
    <div class="day-block">
        <h3>Podsumowanie zamówień na tydzień KW: ${kw} </h3>
    </div>
</div>
<form action="/admin/orderSummary" method="get">
    <div class="main-block">
        <div class="day-block">
            <div class="day">
                <button name="shift" value="1" style="font-weight:  bold">Zmiana 1</button>
            </div>
            <div class="day">
                <button name="shift" value="2" style="color: #dfdfdf">Zmiana 2</button>
            </div>
        </div>
        <div class="day-block">
            <div class="day" style="font-size: large; font-weight: bold;">
                Podsumowanie:
            </div>
            <div class="day" style="font-size: large; font-weight: bold;">
                Poniedziałek: ${mealsQtyPerDayFirstShift[0]} dań
            </div>
            <div class="day" style="font-size: large; font-weight: bold;">
                Wtorek: ${mealsQtyPerDayFirstShift[1]} dań
            </div>
            <div class="day" style="font-size: large; font-weight: bold;">
                Środa: ${mealsQtyPerDayFirstShift[2]} dań
            </div>
            <div class="day" style="font-size: large; font-weight: bold;">
                Czwatrek: ${mealsQtyPerDayFirstShift[3]} dań
            </div>
            <div class="day" style="font-size: large; font-weight: bold;">
                Piątek: ${mealsQtyPerDayFirstShift[4]} dań
            </div>
        </div>
        <div class="day-block">
            <table>
                <tr>
                    <td>
                        <div class="day" style="font-weight: bold;">
                            <div>
                                Dzień:
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day" style="font-weight: bold;">
                            <div>
                                Nr Dania:
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day" style="font-weight: bold;">
                            <div>
                                Danie:
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day" style="font-weight: bold;">
                            <div>
                                Suma zamówień :
                            </div>
                        </div>
                    </td>
                </tr>
                <c:forEach var="meal" items="${orderSummaryList}">
                    <c:if test="${meal.mealName != 'Brak'}">
                        <c:if test="${meal.firstShiftQuantity != 0 }">
                            <tr>
                                <td>
                                    <div class="day" style="justify-content: center">
                                        <div>
                                            <c:choose>
                                                <c:when test="${meal.dayId eq 1}">Poniedziałek</c:when>
                                                <c:when test="${meal.dayId eq 2}">Wtorek</c:when>
                                                <c:when test="${meal.dayId eq 3}">Środa</c:when>
                                                <c:when test="${meal.dayId eq 4}">Czwartek</c:when>
                                                <c:when test="${meal.dayId eq 5}">Piątek</c:when>
                                            </c:choose>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="day" style="justify-content: right; font-weight: bold;">
                                        <div>
                                            nr ${meal.mealNo}
                                        </div>
                                    </div>

                                    <div class="day" style="justify-content: left">
                                        <div>
                                            Zmiana 1:
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="day" style="justify-content: left; font-weight: bold;">
                                        <div>
                                                ${meal.mealName}
                                        </div>
                                    </div>
                                    <div class="day" style="justify-content: left">
                                        <div>
                                            ID: ${meal.firstShiftUsersId}
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="day" style="justify-content: left">
                                        <div>
                                                ${meal.firstShiftQuantity}
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td>
                                    <div>
                                        -
                                    </div>
                                </td>
                            </tr>
                        </c:if>
                    </c:if>
                </c:forEach>
            </table>
        </div>
    </div>
</form>
<div class="main-block">
    <div class="day-block">
        <div class="day">
            <button>Wyślij do pliku csv (jeszcze nie działa)</button>
        </div>
    </div>
</div>

<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
