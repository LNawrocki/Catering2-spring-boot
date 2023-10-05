<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="main-menu-block">
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/admin/home">Nowe Menu</a>
    </div>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/menu/newOrder">Zamów</a>
    </div>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/user/actualOrder">Twój obiad</a>
    </div>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/logout">Wyloguj</a>
    </div>
</div>
<div class="main-menu-block">
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/admin/list">Lista użytkowników</a>
    </div>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/admin/add">Dodaj użytkownika</a>
    </div>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/admin/department">Edycja działów</a>
    </div>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/admin/menu/update">Edycja menu</a>
    </div>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/admin/order/list">Lista zamówień</a>
    </div>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/admin/config">Ustawienia</a>
    </div>
</div>
