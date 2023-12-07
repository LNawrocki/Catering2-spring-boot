<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <jsp:include page="../fragments/title.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_department-edit.css">
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu-admin.jsp"/>

<form:form method="post" modelAttribute="price">
    <div class="main-block">
        <div class="login-row">
            <label>Nr ceny:
                <form:input type="number" path="priceId" value="${priceId}"/>
            </label>
        </div>
        <div class="login-row">
            <label for="price">Wartość:
                <form:input path="price"/>
            </label>
        </div>
        <div class="login-row">
            <form:button>Dodaj</form:button>
        </div>
    </div>
</form:form>

<div class="main-block">
    <div class="login-row">
        <table>
            <tr>
                <td>
                    <div class="day">
                        <div>
                            Nr ceny
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Wartość
                        </div>
                    </div>
                </td>
                <td>
                    Akcja
                </td>
            </tr>
            <c:forEach items="${prices}" var="price">
            <tr>
                <td>
                    <div class="day">
                        <div>
                            <c:out value="${price.priceId}"/>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            <c:out value="${price.price}"/>
                        </div>
                    </div>
                </td>
                <td>
                    <form action="/admin/price/delete" method="post" style="padding: 1px; margin: 1px">
                        <button name="deletePriceId" value="${price.priceId}" style="font-size: small; border-radius: 5px; border-width: 1px; color: red">Usuń</button>
                    </form>
                </td>
            </tr>
            </c:forEach>
        </table>
    </div>
</div>


<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>
