<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <title>Catering</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_department-edit.css">
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu.jsp"/>

<form:form method="post" modelAttribute="department">
    <div class="main-block">
        <div class="login-row">
            <label for="id">ID:
                <form:input path="id"/>
            </label>
        </div>
        <div class="login-row">
            <label for="name">Nazwa:
                <form:input path="name"/>
            </label>
        </div>
        <div class="login-row">
            <label for="paymentPerc">Płatność (%):
                <form:input path="paymentPerc"/>
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
            </tr>
            <c:forEach items="${departments}" var="depart">
            <tr>
                <td>
                    <div class="day">
                        <div>
                            <c:out value="${depart.id}"/>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            <c:out value="${depart.name}"/>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            <c:out value="${depart.paymentPerc}"/>
                        </div>
                    </div>
                </td>
            </tr>
            </c:forEach>
        </table>
    </div>
</div>


<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>
