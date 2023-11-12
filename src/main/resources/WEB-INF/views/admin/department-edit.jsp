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

<form:form method="post" modelAttribute="department">
    <div class="main-block">
        <div class="login-row">
            <label>ID:
<%--                <form:input path="id"/>--%>
                <input type="number" name="id" value="${nextId}">
            </label>
        </div>
        <div class="login-row">
            <label for="name">Nazwa:
                <form:input path="name"/>
            </label>
        </div>
        <div class="login-row">
            <label for="paymentPerc">Płatność (%):
                <form:input path="paymentPerc"/><br>
                <form:errors path="paymentPerc" cssStyle="color: red"/>
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
                            ID
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Nazwa
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Płatność (%)
                        </div>
                    </div>
                </td>
                <td>
                    Akcja
                </td>
            </tr>
            <c:forEach items="${departments}" var="dish">
            <tr>
                <td>
                    <div class="day">
                        <div>
                            <c:out value="${dish.id}"/>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            <c:out value="${dish.name}"/>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            <c:out value="${dish.paymentPerc}"/>
                        </div>
                    </div>
                </td>
                <td>
                    Do zrobienia :)
                </td>
            </tr>
            </c:forEach>
        </table>
    </div>
</div>


<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>
