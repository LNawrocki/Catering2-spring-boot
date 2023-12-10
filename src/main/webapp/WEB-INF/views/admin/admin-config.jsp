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

<div class="main-block">
    <div class="login-row">
        <form:form method="post" action="/admin/config/editMode" modelAttribute="config">
        <c:if test="${config.editMode eq false}">
            <div class="day">
                <label>Tryb edycji:
                    <button name="editMode" value="true" style="box-shadow: 0px 0px 15px rgb(166,229,135) inset;"/>
                    Włącz tryb edycji administratora
                </label>
            </div>
            <div class="day">
                Mowe menu i możliwość zamawiania są dostepne.
            </div>
        </c:if>
        <c:if test="${config.editMode eq true}">
            <div class="day">
                <label>Tryb edycji:
                    <button name="editMode" value="false" style="box-shadow: 0px 0px 15px rgb(229,62,62) inset;"/>
                    Wyłącz tryb edycji administratora
                </label>
            </div>
            <div class="day">
                Mowe menu i możliwość zamawiania są zablokowane.
            </div>
        </c:if>
    </div>
    </form:form>
</div>

<%--<div class="main-block">--%>
<%--    <div class="login-row">--%>
<%--        <form:form method="post" action="/admin/config/clearActualMenu" modelAttribute="config">--%>
<%--            <button name="editMode" style="box-shadow: 0px 0px 15px rgb(229,62,62) inset;"/>--%>
<%--            <div>--%>
<%--                Wyczyść Listę zamówień--%>
<%--            </div>--%>
<%--        </form:form>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<div class="main-block">--%>
<%--    <div class="login-row">--%>
<%--        <form:form method="post" action="/admin/config/clearNewOrders" modelAttribute="config">--%>
<%--            <button name="editMode" style="box-shadow: 0px 0px 15px rgb(229,62,62) inset;"/>--%>
<%--            <div>--%>
<%--                Wyczyść Listę nowych zamówień złożonych prze użytkowników na przyszły tydzień--%>
<%--            </div>--%>
<%--        </form:form>--%>
<%--    </div>--%>
<%--</div>--%>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
