<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <jsp:include page="../fragments/title.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_admin_financial.css">
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
                            Dział:
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day" style="color: #C73EE0FF; font-weight: bold;">
                        <div>
                            Łączna kwota za obiady z dofinansowaniem
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day" style="color: #C73EE0FF; font-weight: bold;">
                        <div>
                            Łączna kwota za obiady bez dofinansowania
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day" style="color: #C73EE0FF; font-weight: bold;">
                        <div>
                            Ilość nieopłaconych obiadów
                        </div>
                    </div>
                </td>
            </tr>
            <c:forEach var="financialDepartmentSummary" items="${financialDepartmentSummaryList}">
                <tr>
                    <td>
                        <div class="day">
                            <div>
                                    ${financialDepartmentSummary.departmentName}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${financialDepartmentSummary.departmentSummaryDiscountPrice} zł
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${financialDepartmentSummary.departmentSummaryFullPrice} zł
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day" style="color: #FF0000; font-weight: bold;">
                            <div>
                                    ${financialDepartmentSummary.notPaidOrders}
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td>
                    <div class="day" style="color: #C73EE0FF; font-weight: bold;">
                        <div>
                            Podsumowanie:
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day" style="color: #C73EE0FF; font-weight: bold;">
                        <div>
                            Od pracowników: ${sumOfDepartmentDiscountPrice} zł
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day" style="color: #C73EE0FF; font-weight: bold;">
                        <div>
                            Dla dostawcy: ${sumOfDepartmentFullPrice} zł
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day" style="color: #C73EE0FF; font-weight: bold;">
                        <div>
                            Kwota dofinansowania: ${refundation} zł
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                </td>

            </tr>
        </table>
    </div>
</div>

<div class="main-block">
    <form action="/admin/financial/saveNewAndActualOrdersToCsv" method="post">
        <div class="day-block">
            <div class="day">
                <button>Wygeneruj listy aktualnych i nowych zamówień</button>
            </div>
        </div>
    </form>
    <form action="/admin/financial/closeWeek" method="post">
        <div class="day-block">
            <div class="day">
                <button>Zamknij tydzień</button>
            </div>
        </div>
    </form>
    <form action="#" method="post">
        <div class="day-block">
            <div class="day">
                <button>Wyślij podsumowanie finansowe do pliku csv</button>
            </div>
        </div>
    </form>
</div>



<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
