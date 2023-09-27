<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <title>Catering</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_user_add.css">
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
            <label for="department">Dział:
                <form:select path="department" items="${departments}" itemLabel="name" itemValue="id"/>
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
                <form:radiobutton path="superAdmin" value="true"/>TAK
                <form:radiobutton path="superAdmin" value="false"/>NIE
            </label>
        </div>
        <div class="login-row">
            <label>
                <form:radiobutton path="active" value="true"/>Użytkownik aktywny</br>
                <form:radiobutton path="active" value="false"/>Użytkownik nieaktywny
            </label>
        </div>

        <div class="login-row">
            <form:button>Dodaj</form:button>
        </div>
    </div>
</form:form>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
