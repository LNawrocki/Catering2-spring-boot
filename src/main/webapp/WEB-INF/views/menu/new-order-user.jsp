<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <title>Catering</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_menu_order.css">
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu-user.jsp"/>

<div class="main-block">
    <div class="login-row">
        <div class="day">
            Witaj ${name} ${lastName}
        </div>
        <c:if test="${receivables != 0}">
        <div class="day" style="color: red">
            </c:if>
            <c:if test="${receivables == 0}">
            <div class="day">
                </c:if>
                Należności: ${receivables} zł
            </div>
        </div>
    </div>
    <div class="main-block">
        <div class="day-block">
            <h3>Nowe menu na tydzień KW: ${date}</h3>
        </div>
    </div>
<
<form:form action="/menu/newOrder" method="post" modelAttribute="newOrder">
    <div class="main-block">
        <div class="day-block">
            <form:hidden path="kw"/>
            <form:hidden path="user"/>
            <form:hidden path="qtyMon"/>
            <form:hidden path="qtyTue"/>
            <form:hidden path="qtyWed"/>
            <form:hidden path="qtyThu" />
            <form:hidden path="qtyFri"/>
            <form:hidden path="priceMon"/>
            <form:hidden path="priceTue"/>
            <form:hidden path="priceWed"/>
            <form:hidden path="priceThu"/>
            <form:hidden path="priceFri"/>
            <form:hidden path="toPay"/>
            <form:hidden path="isPaid"/>

        </div>
    </div>
    <div class="main-block">
        <div class="day-block">
            <table>
                <tr style="font-weight: bold">
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
                                Danie
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
                </tr>
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
                            <div>
                                <form:select path="mealMon" items="${newMenuMonday}" itemLabel="mealName"
                                             itemValue="mealNo"/>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="meal">
                            <div>
                                <form:radiobutton path="shiftMon" value="1"/>1
                                <form:radiobutton path="shiftMon" value="2"/>2
                                <input type="radio" name="shiftMon" value="0" checked hidden/>
                            </div>
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
                            <div>
                                <form:select path="mealTue" items="${newMenuTuesday}" itemLabel="mealName"
                                             itemValue="mealNo"/>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="meal">
                            <div>
                                <form:radiobutton path="shiftTue" value="1" />1
                                <form:radiobutton path="shiftTue" value="2"/>2
                                <input type="radio" name="shiftTue" value="0" checked hidden/>
                            </div>
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
                            <div>
                                <form:select path="mealWed" items="${newMenuWednesday}" itemLabel="mealName"
                                             itemValue="mealNo"/>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="meal">
                            <div>
                                <form:radiobutton path="shiftWed" value="1"/>1
                                <form:radiobutton path="shiftWed" value="2"/>2
                                <input type="radio" name="shiftWed" value="0" checked hidden/>
                            </div>
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
                            <div>
                                <form:select path="mealThu" items="${newMenuThursday}" itemLabel="mealName"
                                             itemValue="mealNo"/>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="meal">
                            <div>
                                <form:radiobutton path="shiftThu" value="1"/>1
                                <form:radiobutton path="shiftThu" value="2"/>2
                                <input type="radio" name="shiftThu" value="0" checked hidden/>
                            </div>
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
                            <div>
                                <form:select path="mealFri" items="${newMenuFriday}" itemLabel="mealName"
                                             itemValue="mealNo"/>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="meal">
                            <div>
                                <form:radiobutton path="shiftFri" value="1"/>1
                                <form:radiobutton path="shiftFri" value="2"/>2
                                <input type="radio" name="shiftFri" value="0" checked hidden/>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div class="main-block">
        <div class="day-block">
            <button type="submit">Zapisz</button>
            <button type="reset">reset</button>
        </div>
    </div>
</form:form>


<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>