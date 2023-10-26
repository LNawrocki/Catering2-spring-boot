<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <jsp:include page="fragments/title.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_home.css">
</head>

<body>
<jsp:include page="fragments/header.jsp"/>
<jsp:include page="fragments/menu.jsp"/>

<div class="main-block">
    <div class="day-block">
        <h3>Nowe menu na tydzień KW: ${kw} ( ${weekStart} - ${weekEnd} )</h3>
    </div>
</div>
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
                                <li> ${mealMonday.mealNo} ${mealMonday.mealName}</li>
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
                                <li> ${mealTuesday.mealNo} ${mealTuesday.mealName}</li>
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
                                <li>${mealWednesday.mealNo} ${mealWednesday.mealName}</li>
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
                                <li>${mealThursday.mealNo} ${mealThursday.mealName}</li>
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
                                <li>${mealFriday.mealNo} ${mealFriday.mealName}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</div>


<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
