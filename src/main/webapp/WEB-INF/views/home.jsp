<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <title>Order-dinner</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_home.css">
</head>

<body>
<jsp:include page="fragments/header.jsp"/>
<jsp:include page="fragments/menu.jsp"/>

<div class="main-block">
    <div class="day-block">
        <h3>Nowe menu na tydzień KW: ${date}</h3>
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
                        <ol>
                            <c:forEach var="mealMonday" items="${mealsMonday}">
                                <li>${mealMonday.mealName}</li>
                            </c:forEach>
                        </ol>
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
                        <ol>
                            <c:forEach var="mealTuesday" items="${mealsTuesday}">
                                <li>${mealTuesday.mealName}</li>
                            </c:forEach>
                        </ol>
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
                        <ol>
                            <c:forEach var="mealWednesday" items="${mealsWednesday}">
                                <li>${mealWednesday.mealName}</li>
                            </c:forEach>
                        </ol>
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
                        <ol>
                            <c:forEach var="mealThursday" items="${mealsThursday}">
                                <li>${mealThursday.mealName}</li>
                            </c:forEach>
                        </ol>
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
                        <ol>
                            <c:forEach var="mealFriday" items="${mealsFriday}">
                                <li>${mealFriday.mealName}</li>
                            </c:forEach>
                        </ol>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</div>


<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
