<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <jsp:include page="../fragments/title.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_user_auth.css">
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu.jsp"/>

<form method="post">
    <div class="main-block">
        <div class="login-row">
            <label>Login:
                <input type="text" name="login" placeholder="login" value="admin"> admin / biuro1
            </label>
        </div>
        <div class="login-row">
            <label>Hasło:
                <input type="password" name="password" placeholder="hasło" value="Administrator"> Administrator / biuro1
            </label>
        </div>
        <c:if test="${msg != null}">
            <div class="login-row" style="color: red">
                    ${msg}
            </div>
        </c:if>
        <div class="login-row">
            <button type="submit">Dalej</button>
        </div>
    </div>
</form>

<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
