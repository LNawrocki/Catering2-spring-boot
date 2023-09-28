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
                            Pn nr
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Pn cena
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Pn zm
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Wt nr
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Wt cena
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Wt zm
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Śr nr
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Śr cena
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Śr zm
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Czw nr
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Czw cena
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Czw zm
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Pt nr
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Pt cena
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Pt zm
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
                            Osoba
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
                                    ${newOrder.userMealMon}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${newOrder.userPriceMon}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${newOrder.userShiftMon}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${newOrder.userMealTue}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${newOrder.userPriceTue}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${newOrder.userShiftTue}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${newOrder.userMealWed}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${newOrder.userPriceWed}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${newOrder.userShiftWed}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${newOrder.userMealThu}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${newOrder.userPriceThu}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${newOrder.userShiftThu}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${newOrder.userMealFri}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${newOrder.userPriceFri}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${newOrder.userShiftFri}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${newOrder.toPay}
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
                                    ${newOrder.user.userId}
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
