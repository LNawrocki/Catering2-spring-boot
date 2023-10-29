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
        <h3>Podsumowanie zamówień na tydzień KW: ${kw} ( ${weekStart} - ${weekEnd} )</h3>
    </div>
</div>
<div class="main-block">
    <div class="day-block">
        <table>
            <tr>
                <td>
                    <div class="day" style="color: #C73EE0FF; font-weight: bold;">
                        <div>
                            Danie:
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day" style="color: #C73EE0FF; font-weight: bold;">
                        <div>
                            Użytkownicy (ID):
                        </div>
                    </div>
                </td>
            </tr>
            <c:forEach var="dinnerUser" items="${dinnerUsersMap}">
                <tr>
                    <td>
                        <div class="day" style="justify-content: left">
                            <div>
                                    ${dinnerUser.key}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day" style="justify-content: left">
                            <div>
                                    ${dinnerUser.value}
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

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
