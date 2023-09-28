<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="main-menu-block">
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/">Nowe Menu</a>
    </div>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/user/auth">Zamów obiad</a>
    </div>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/user/list">Lista użytkowników</a>
    </div>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/user/add">Dodaj użytkownika</a>
    </div>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/admin/department">Edycja działów</a>
    </div>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/menu/update">Edycja menu</a>
    </div>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/menu/order/list">Lista zamówień</a>
    </div>

</div>
