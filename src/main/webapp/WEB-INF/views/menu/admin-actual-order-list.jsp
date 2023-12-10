<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <jsp:include page="../fragments/title.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/style_admin_actual_order_list.css">
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu-admin.jsp"/>
<div class="main-block">
    <div class="day-block">
        <form action="/admin/financial/saveActualOrdersToCsv" method="post">
            <div class="day-block">
                <div class="day">
                    <button>Wyślij do pliku csv</button>
                </div>
            </div>
        </form>
    </div>
</div>


<div class="main-block">
    <div class="day-block">
        <table>
            <tr>
                <td>
                    <div class="day">
                        <div>
                            ID
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Dzień
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Dania
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Cena
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Zmiana
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Do zapłaty
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Zapłacono
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Dane osoby
                        </div>
                    </div>
                </td>
            </tr>
            <c:forEach var="actualOrder" items="${actualOrders}">
                <tr>
                    <td>
                        <div class="day">
                            <div>
                                    ${actualOrder.id}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                Paniedziałek<br>
                                Wtorek<br>
                                Środa<br>
                                Czwartek<br>
                                Piątek<br>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${actualOrder.mealMon}. ${actualOrder.mealMonName}<br>
                                    ${actualOrder.mealTue}. ${actualOrder.mealTueName}<br>
                                    ${actualOrder.mealWed}. ${actualOrder.mealWedName}<br>
                                    ${actualOrder.mealThu}. ${actualOrder.mealThuName}<br>
                                    ${actualOrder.mealFri}. ${actualOrder.mealFriName}<br>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${actualOrder.priceMon} zł<br>
                                    ${actualOrder.priceTue} zł<br>
                                    ${actualOrder.priceWed} zł<br>
                                    ${actualOrder.priceThu} zł<br>
                                    ${actualOrder.priceFri} zł<br>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${actualOrder.shiftMon}<br>
                                    ${actualOrder.shiftTue}<br>
                                    ${actualOrder.shiftWed}<br>
                                    ${actualOrder.shiftThu}<br>
                                    ${actualOrder.shiftFri}<br>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${actualOrder.toPay} zł
                            </div>
                        </div>
                    </td>
                    <td>
                        <form method="post" action="/admin/order/list/paid">
                            <c:if test="${actualOrder.isPaid == false}">
                            <div class="day" style="box-shadow: 0px 0px 15px rgb(229,62,62) inset;">
                                </c:if>
                                <c:if test="${actualOrder.isPaid == true}">
                                <div class="day" style="box-shadow: 0px 0px 15px rgb(166,229,135) inset;">
                                    </c:if>
                                    <div>
                                        <c:choose>
                                            <c:when test="${actualOrder.isPaid eq true}">
                                                TAK
                                            </c:when>
                                            <c:when test="${actualOrder.isPaid eq false}">
                                                NIE
                                            </c:when>
                                        </c:choose>

                                    </div>
                                </div>
                        </form>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                Login: ${actualOrder.user.login}<br>
                                ID: ${actualOrder.user.userId}<br>
                                    ${actualOrder.user.name}
                                    ${actualOrder.user.lastName}<br>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>


<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
