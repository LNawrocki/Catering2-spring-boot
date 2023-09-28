<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <title>Catering - Lightnet</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_user_list.css">
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu.jsp"/>

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
            <c:forEach var="newOrder" items="${newOrders}">
                <tr>
                    <td>
                        <div class="day">
                            <div>
                                    ${newOrder.id}
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
                                    ${newOrder.userMealMon}<br>
                                    ${newOrder.userMealTue}<br>
                                    ${newOrder.userMealWed}<br>
                                    ${newOrder.userMealThu}<br>
                                    ${newOrder.userMealFri}<br>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${newOrder.userPriceMon} zł<br>
                                    ${newOrder.userPriceTue} zł<br>
                                    ${newOrder.userPriceWed} zł<br>
                                    ${newOrder.userPriceThu} zł<br>
                                    ${newOrder.userPriceFri} zł<br>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${newOrder.userShiftMon}<br>
                                    ${newOrder.userShiftTue}<br>
                                    ${newOrder.userShiftWed}<br>
                                    ${newOrder.userShiftThu}<br>
                                    ${newOrder.userShiftFri}<br>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${newOrder.toPay} zł
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${newOrder.isPaid}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                   ID: ${newOrder.user.userId}<br>
                                   ${newOrder.user.name}
                                   ${newOrder.user.lastName}<br>
                                   Login: ${newOrder.user.login}
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
