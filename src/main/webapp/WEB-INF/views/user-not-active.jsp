<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <jsp:include page="fragments/title.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_home.css">
</head>

<body>
<jsp:include page="fragments/header.jsp"/>
<jsp:include page="fragments/menu.jsp"/>


<div class="main-block">
    <div class="day-block">
        <div class="day" style="color: red; font-size: larger; font-weight: bold">
            Konto "${login}" zablokowane - skontaktuj się z administratorem.
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
