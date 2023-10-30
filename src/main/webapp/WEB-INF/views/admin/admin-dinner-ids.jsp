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
                            Dzień:
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day" style="color: #C73EE0FF; font-weight: bold;">
                        <div>
                            Nr Dania:
                        </div>
                    </div>
                </td>
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
                            Suma zamówień :
                        </div>
                    </div>
                </td>
            </tr>
            <c:forEach var="dinner" items="${actualMenuList}">
                <tr>
                    <td>
                        <div class="day" style="justify-content: center">
                            <div>
                                    ${dinner.dayId}
                            </div>
                        </div>

                    </td>
                    <td>
                        <div class="day" style="justify-content: right; color: #3a7c25; font-weight: bold;">
                            <div>
                                    ${dinner.mealNo}
                            </div>
                        </div>
                        <div class="day" style="justify-content: left">
                            <div>
                                Zmiana 1:
                            </div>
                        </div>
                        <div class="day" style="justify-content: left">
                            <div>
                                Zmiana 2:
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day" style="justify-content: left; color: #3a7c25; font-weight: bold;">
                            <div>
                                    ${dinner.mealName}
                            </div>
                        </div>
                        <div class="day" style="justify-content: left">
                            <div>
                               ID: ${dinner.firstShiftUsersId}
                            </div>
                        </div>
                        <div class="day" style="justify-content: left">
                            <div>
                               ID: ${dinner.secondShiftUsersId}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day" style="justify-content: left">
                            Suma:
                        </div>
                        <div class="day" style="justify-content: left">
                            <div>
                                    ${dinner.firstShiftQuantity}
                            </div>
                        </div>
                        <div class="day" style="justify-content: left">
                            <div>
                                    ${dinner.secondShiftQuantity}
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
