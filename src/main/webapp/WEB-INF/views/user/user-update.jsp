<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <jsp:include page="../fragments/title.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_admin_update.css">
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu-user.jsp"/>

<form:form action="/user/update" method="post" modelAttribute="user">
    <div class="main-block">
        <div class="login-row">
            <label for="userId">ID użytkownika:
                <form:input path="userId" readonly="true"/>
            </label>
        </div>
        <div class="login-row">
            <label for="name">Imię:
                <form:input path="name" readonly="true"/>
            </label>
        </div>
        <div class="login-row">
            <label for="lastName">Nazwisko:
                <form:input path="lastName" readonly="true"/>
            </label>
        </div>
        <div class="login-row">
            <label for="department">Dział:
                <input name="department" value="${user.department.id}"  readonly="true" hidden="hidden">${user.department.name}</label>
            </label>
        </div>
        <div class="login-row">
            <label for="login">Login:
                <form:input path="login" readonly="true"/>
            </label>
        </div>
        <div class="login-row">
            <label for="password">Hasło:
                <form:input path="password"/>
            </label>
        </div>
        <div class="login-row">
            <input name="superAdmin" value="${user.superAdmin}" hidden="hidden"/>
            <input name="active" value="${user.active}" hidden="hidden"/>
            <form:button>Edytuj</form:button>
        </div>
    </div>
</form:form>

<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
