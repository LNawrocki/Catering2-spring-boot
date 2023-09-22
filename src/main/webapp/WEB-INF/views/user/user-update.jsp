<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <title>Order-dinner</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_user_update.css">
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu.jsp"/>

<form:form method="post" modelAttribute="user">
    <div class="main-block">
        <div class="login-row">
            <label for="userId">ID użytkownika:
                <form:input path="userId"/>
            </label>
        </div>
        <div class="login-row">
            <label for="name">Imię:
                <form:input path="name"/>
            </label>
        </div>
        <div class="login-row">
            <label for="lastName">Nazwisko:
                <form:input path="lastName"/>
            </label>
        </div>
        <div class="login-row">
            <label for="department.name">Dział:
                <form:input path="department.name"/>
            </label>
        </div>
        <div class="login-row">
            <label for="login">Login:
                <form:input path="login"/>
            </label>
        </div>
        <div class="login-row">
            <label for="password">Hasło:
                <form:input path="password"/>
            </label>
        </div>
        <div class="login-row">
            <label>Admin:
                <input type="radio" name="superAdmin" value="true">TAK</input>
                <input type="radio" name="superAdmin" value="false">NIE</input>
            </label>
        </div>

        <div class="login-row">
                <%--        <form:hidden path="userId"/>--%>
            <form:button>Edytuj</form:button>
        </div>
    </div>
</form:form>

<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
