<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<form:form method="post" action="/admin/config/editMode" modelAttribute="config">
    <div class="main-block">
        <div class="login-row">
            <div class="day">
                <label>Tryb edycji:
                        <form:radiobutton path="editMode" value="true"/>Włączony
                        <form:radiobutton path="editMode" value="false"/>Wyłączony
                    <form:button>Potwierdź</form:button>
                </label>
            </div>
        </div>
    </div>
</form:form>

<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
