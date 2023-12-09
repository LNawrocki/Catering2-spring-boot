<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <jsp:include page="../fragments/title.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/style_user_list_delete_info.css">
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu-admin.jsp"/>


<div class="main-block">
    <div class="day-block">
        <table>
            <tr>
                <form action="/admin/list/searchId" method="post">
                    <td>
                        ID: <input type="number" name="searchId">
                        <button type="submit">Wyszukaj</button>
                    </td>
                </form>
                <form action="/admin/list/searchLogin" method="post">
                    <td>
                        Login: <input type="text" name="searchLogin">
                        <button type="submit">Wyszukaj</button>
                    </td>
                </form>
                <form action="/admin/list/searchDepartment" method="post">
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

                <form action="/admin/list/searchClean" method="post">
                    <td>
                        <button type="submit">Wyczyść</button>
                    </td>
                </form>
                <td></td>
            </tr>
        </table>
    </div>
</div>

<form action="/admin/deleteUser/confirm" method="post">
    <div class="main-block">
        <div class="day-block">
            <p>
                Usuwasz użytkownika. Wraz z użytkownikiem zostaną usunięte jego bierzące i nowe zamówienia.
                Czy potwierdzasz?
            </p>
            <input name="deleteUserId" value="${deleteUserId}" hidden="hidden">
            <input name="confirm" value="true" hidden="hidden">

        </div>
        <div class="day-block">
            <div class="list-row">
                <button type="submit">Potwierdź</button>
            </div>
            <div class="list-row">
                <a href="/admin/list">Wróć</a>
            </div>
        </div>
    </div>
</form>

<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
