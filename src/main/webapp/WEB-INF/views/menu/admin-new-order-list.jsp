<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <jsp:include page="../fragments/title.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_admin_new_order_list.css">
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu-admin.jsp"/>

<div class="main-block">
    <div class="day-block">
        <h3>Lista zamówień na tydzień KW: ${kw} ( ${weekStart} - ${weekEnd} )</h3>
    </div>
</div>
<div class="main-block">
    <div class="day-block">
        <form action="/admin/financial/saveNewOrdersToCsv" method="post">
            <div>
                <button>Wyślij do pliku csv</button>
            </div>
        </form>
    </div>
</div>

<div class="main-block">
    <div class="day-block">
        <table>
            <tr>
                <form action="/admin/newOrder/searchNewOrderId" method="post">
                    <td style="margin-right: 50px; text-align: center">
                        <div class="day">
                            <input type="number" name="searchNewOrderId" placeholder="ID zamówienia"
                                   style="width: 170px; text-align: center"> </br>
                            <button type="submit">Wyszukaj</button>
                        </div>
                    </td>
                </form>
                <form action="/admin/newOrder/searchIsPaid" method="post">
                    <td>
                        <div class="day">
                            <select name="searchIsPaid" style="width: 200px; text-align: center">
                                <option value="">Status</option>
                                <option value="false">Nie zapłacono</option>
                                <option value="true">Zapłacono</option>
                            </select>
                            <button type="submit">Wyszukaj</button>
                        </div>
                    </td>
                </form>
                <form action="/admin/newOrder/searchDepartment" method="post">
                    <td>
                        <div class="day">
                            <select name="searchDepartmentId" style="text-align: center">
                                <c:forEach var="department" items="${departments}">
                                    <option value="${department.id}">${department.name}</option>
                                </c:forEach>
                            </select>
                            <button type="submit">Wyszukaj</button>
                        </div>
                    </td>
                </form>
                <form action="/admin/newOrder/searchLogin" method="post">
                    <td>
                        <div class="day">
                            <input type="text" name="searchLogin" placeholder="Login:"
                                   style="width: 150px; text-align: center">
                            <button type="submit">Wyszukaj</button>
                        </div>
                    </td>
                </form>
                <form action="/admin/newOrder/searchUserId" method="post">
                    <td>
                        <div class="day">
                            <input type="number" name="searchUserId" placeholder="ID użytkownika"
                                   style="width: 170px; text-align: center">
                            <button type="submit">Wyszukaj</button>
                        </div>
                    </td>
                </form>
                <form action="/admin/newOrder/searchClean" method="post">
                    <td>
                        <button type="submit" style="background-color: green">Wyczyść</button>
                    </td>
                </form>
            </tr>
        </table>
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
                <td>
                    <div class="day">
                        <div>
                            Akcja
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
                                    ${newOrder.mealMon}. ${newOrder.mealMonName}<br>
                                    ${newOrder.mealTue}. ${newOrder.mealTueName}<br>
                                    ${newOrder.mealWed}. ${newOrder.mealWedName}<br>
                                    ${newOrder.mealThu}. ${newOrder.mealThuName}<br>
                                    ${newOrder.mealFri}. ${newOrder.mealFriName}<br>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${newOrder.priceMon} zł<br>
                                    ${newOrder.priceTue} zł<br>
                                    ${newOrder.priceWed} zł<br>
                                    ${newOrder.priceThu} zł<br>
                                    ${newOrder.priceFri} zł<br>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${newOrder.shiftMon}<br>
                                    ${newOrder.shiftTue}<br>
                                    ${newOrder.shiftWed}<br>
                                    ${newOrder.shiftThu}<br>
                                    ${newOrder.shiftFri}<br>
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
                        <form method="post" action="/admin/newOrder/list/paid">
                            <c:if test="${newOrder.isPaid == false}">
                            <div class="day" style="box-shadow: 0px 0px 15px rgb(229,62,62) inset;">
                                </c:if>
                                <c:if test="${newOrder.isPaid == true}">
                                <div class="day" style="box-shadow: 0px 0px 15px rgb(166,229,135) inset;">
                                    </c:if>
                                    <div>
                                        <c:choose>
                                            <c:when test="${newOrder.isPaid eq true}">
                                                TAK
                                            </c:when>
                                            <c:when test="${newOrder.isPaid eq false}">
                                                NIE
                                            </c:when>
                                        </c:choose>

                                        <input name="userIdUpdate" value="${newOrder.user.userId}" hidden>
                                        <div class="day">
                                            <button type="submit" name="paid" value="true" style="border-radius: 10px">
                                                wpłata
                                            </button>
                                            <br>
                                        </div>
                                        <div class="day">
                                            <button type="submit" name="paid" value="false" style="border-radius: 10px">
                                                nie zpałacono
                                            </button>
                                        </div>
                                    </div>
                                </div>
                        </form>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                Login: ${newOrder.user.login}<br>
                                ID: ${newOrder.user.userId}<br>
                                    ${newOrder.user.name}
                                    ${newOrder.user.lastName}<br>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                <a href="/user/newOrder/delete?id=${newOrder.id}" style="color: red">Usuń</a>
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
