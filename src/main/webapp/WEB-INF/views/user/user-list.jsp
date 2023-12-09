<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <jsp:include page="../fragments/title.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_user_list.css">
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu-admin.jsp"/>


<div class="main-block">
    <div class="day-block">
        <table>
            <tr>
                <form action="/admin/userList/searchId" method="post">
                    <td>
                        ID: <input type="number" name="searchId">
                        <button type="submit">Wyszukaj</button>
                    </td>
                </form>
                <form action="/admin/userList/searchLogin" method="post">
                    <td>
                        Login: <input type="text" name="searchLogin">
                        <button type="submit">Wyszukaj</button>
                    </td>
                </form>
                <form action="/admin/userList/searchDepartment" method="post">
                    <td>
                        Dział: <select name="searchDepartmentId">
                        <c:forEach var="department" items="${departments}">
                            <option value="${department.id}">${department.name}</option>
                        </c:forEach>
                    </select>
                        <button type="submit">Wyszukaj</button>
                    </td>
                </form>
            </tr>
            <tr>
                <td></td>
                <form action="/admin/userList/searchClean" method="post">
                    <td>
                        <button type="submit">Wyczyść</button>
                    </td>
                </form>
                <td></td>
            </tr>
        </table>
    </div>
</div>
<div class="main-block">
    <div class="day-block">
        <table>
            <tr>
                <td>
                    <div class="day" style="color: darkorange; font-weight: bold;">
                        <div>
                            ID
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day" style="color: darkorange; font-weight: bold;">
                        <div>
                            Login
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day" style="color: darkorange; font-weight: bold;">
                        <div>
                            Imię
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day" style="color: darkorange; font-weight: bold;">
                        <div>
                            Nazwisko
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day" style="color: darkorange; font-weight: bold;">
                        <div>
                            Dział
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day" style="color: darkorange; font-weight: bold;">
                        <div>
                            Konto
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day" style="color: darkorange; font-weight: bold;">
                        <div>
                            Akcja
                        </div>
                    </div>
                </td>
            </tr>
            <c:forEach var="user" items="${usersList}">
                <tr>
                    <td>
                        <div class="day">
                            <div>
                                    ${user.userId}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${user.login}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${user.name}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${user.lastName}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                    ${user.department.name}
                            </div>
                        </div>
                    </td>
                    <td>
                        <c:if test="${user.active == false}">
                        <div class="day" style="box-shadow: 0px 0px 15px rgb(229,62,62) inset;">
                            </c:if>
                            <c:if test="${user.active == true}">
                            <div class="day" style="box-shadow: 0px 0px 15px rgb(166,229,135) inset;">
                                </c:if>
                            <div>
                                <c:choose>
                                    <c:when test="${user.active eq true}">
                                        Aktywne
                                    </c:when>
                                    <c:when test="${user.active eq false}">
                                        Nieaktywne
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day" >
                            <div >
                                <form action="/admin/userUpdate" method="get" style="padding: 1px; margin: 1px">
                                    <button name="editUserId" value="${user.userId}" style="font-size: small; border-radius: 5px; border-width: 1px; color: limegreen">Edytuj</button>
                                </form>
                                <form action="/admin/deleteUser/confirm" method="post" style="padding: 1px; margin: 1px">
                                    <button name="deleteUserId" value="${user.userId}" style="font-size: small; border-radius: 5px; border-width: 1px; color: red">Usuń</button>
                                </form>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
