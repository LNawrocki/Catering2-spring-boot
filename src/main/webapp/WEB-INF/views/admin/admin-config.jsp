<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <jsp:include page="../fragments/title.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_admin_config.css">
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu-admin.jsp"/>

<form method="post" action="/admin/config/editMenu">
    <div class="main-block">
        <div class="login-row">
            <label for="editMode">Tryb edycji:
                <input type="radio" name="editMode" value="true"/>Włączony
                <input type="radio" name="editMode" value="falsy" checked/>Wyłączony
                <button type="submit">Potwierdź</button>
            </label>
        </div>
    </div>
</form>
<form method="post" action="/admin/config/newMenuAvaliabe">
    <div class="main-block">
        <div class="login-row">
            <label for="newMenuAvaliable">Nowe MENU - podgląd i zamawianie:
                <input type="radio" name="newMenuAvaliable" value="true"/>Nowe menu dostępne
                <input type="radio" name="newMenuAvaliable" value="falsy" checked/>Nowe menu niedostępne
                <button type="submit">Potwierdź</button>
            </label>
        </div>
    </div>
</form>

<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
