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
        <table>
            <tr>
                <td>

                </td>
                <td>
                    <div class="day">
                        <div>
                            Kwota z dopłatą
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Kwota bez dopłaty
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
                </tr>
            </c:forEach>
            <tr>
                <td>
                    <div class="day">
                        <div>
                            Podsumowanie:
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            ${sumOfDepartmentDiscountPrice} zł
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            ${sumOfDepartmentFullPrice} zł
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</div>

<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
