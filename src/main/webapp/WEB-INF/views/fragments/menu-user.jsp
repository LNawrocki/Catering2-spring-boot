<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="main-menu-block">
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/user/home">Nowe Menu</a>
    </div>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/menu/newOrder">Zamów</a>
    </div>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/user/actualOrder">Twój obiad</a>
    </div>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/">Zmień hasło</a>
    </div>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/logout">Wyloguj</a>
    </div>
</div>
