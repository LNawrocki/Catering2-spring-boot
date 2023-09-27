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
<jsp:include page="../fragments/menu.jsp"/>


<div class="main-block">
    <div class="day-block">
        <h3>Cześć ${name} ${lastName}</h3>
    </div>
</div>
<div class="main-block">
<div class="day-block">
<%--    <h3>Nowe menu na tydzień KW: ${kw}</h3>--%>
</div>
</div>
<form:form action="/menu/newOrder" method="post" modelAttribute="newOrder">
    <div class="main-block">
        <div class="day-block">
            <form:hidden path="kw"/>
            <form:hidden path="user"/>
            <form:hidden path="userQtyMon"/>
            <form:hidden path="userQtyTue"/>
            <form:hidden path="userQtyWed"/>
            <form:hidden path="userQtyThu" />
            <form:hidden path="userQtyFri"/>
            <form:hidden path="userPriceMon"/>
            <form:hidden path="userPriceTue"/>
            <form:hidden path="userPriceWed"/>
            <form:hidden path="userPriceThu"/>
            <form:hidden path="userPriceFri"/>
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
                                <form:select path="userMealMon" items="${newMenuMonday}" itemLabel="mealName"
                                             itemValue="mealNo"/>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="meal">
                            <div>
                                <form:radiobutton path="userShiftMon" value="1"/>1
                                <form:radiobutton path="userShiftMon" value="2"/>2
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
                                <form:select path="userMealTue" items="${newMenuTuesday}" itemLabel="mealName"
                                             itemValue="mealNo"/>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="meal">
                            <div>
                                <form:radiobutton path="userShiftTue" value="1"/>1
                                <form:radiobutton path="userShiftTue" value="2"/>2
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
                                <form:select path="userMealWed" items="${newMenuWednesday}" itemLabel="mealName"
                                             itemValue="mealNo"/>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="meal">
                            <div>
                                <form:radiobutton path="userShiftWed" value="1"/>1
                                <form:radiobutton path="userShiftWed" value="2"/>2
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
                                <form:select path="userMealThu" items="${newMenuThursday}" itemLabel="mealName"
                                             itemValue="mealNo"/>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="meal">
                            <div>
                                <form:radiobutton path="userShiftThu" value="1"/>1
                                <form:radiobutton path="userShiftThu" value="2"/>2
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
                                <form:select path="userMealFri" items="${newMenuFriday}" itemLabel="mealName"
                                             itemValue="mealNo"/>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="meal">
                            <div>
                                <form:radiobutton path="userShiftFri" value="1"/>1
                                <form:radiobutton path="userShiftFri" value="2"/>2
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