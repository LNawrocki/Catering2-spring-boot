<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <title>Catering</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_user_auth.css">
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu.jsp"/>

<form method="post">
    <div class="main-block">
        <div class="login-row">
            <label for="login">Login:
                <input type="text" name="login" placeholder="login"> admin  / user
            </label>
        </div>
        <div class="login-row">
            <label for="password">Hasło:
                <input type="password" name="password" placeholder="hasło"> admin  / user
            </label>
        </div>
        <div class="login-row">
            <button type="submit">Dalej</button>
        </div>
    </div>
</form>

<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
