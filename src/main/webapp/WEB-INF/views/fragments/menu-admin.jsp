<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="main-menu-block">
    <div class="menu-links">
    <a href="${pageContext.request.contextPath}/user/orderDetail">BIERZĄCE MENU</a>
</div>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/order/add">ZAMÓW OBIAD</a>
    </div>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}#">PŁATNOŚCI</a>
    </div>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/user/list">LISTA OSÓB</a>
    </div>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}#">EDYCJA MENU</a>
    </div>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}#">EDYCJA OSÓB</a>
    </div>
<%--    <div class="menu-links">--%>
<%--        <a href="${pageContext.request.contextPath}#">ROZLICZENIA</a>--%>
<%--    </div>--%>
    <div class="menu-links">
        <a href="${pageContext.request.contextPath}/user/logout">Wyloguj</a>
    </div>
</div>
