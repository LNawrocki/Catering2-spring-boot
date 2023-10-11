<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <title>Catering</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_admin_financial.css">
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu-admin.jsp"/>


<div class="main-block">
    <div class="login-row">
        Łączna kwota dla Hutnika:
    </div>
    <div class="login-row">

    </div>
    <div class="login-row">
        <label for="lastName">Nazwisko:

        </label>
    </div>
    <div class="login-row">
        <label for="department">Dział:

        </label>
    </div>
    <div class="login-row">
        <label for="login">Login:

        </label>
    </div>
    <div class="login-row">
        <label for="password">Hasło:

        </label>
    </div>
    <div class="login-row">
        <label>Admin:

        </label>
    </div>
    <div class="login-row">
        <label>

        </label>
    </div>

    <div class="login-row">

    </div>
</div>

<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
