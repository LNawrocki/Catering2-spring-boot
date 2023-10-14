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
            <tr><td></td>

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


<div class="main-block">
    <div class="day-block">
        <table>
            <tr>
                <td>
                    <div class="day">
                        <div>
                            ID
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Login
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Imię
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Nazwisko
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Dział
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
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
                        <div class="day">
                            <div>
                                <a href="/admin/update?userId=${user.userId}">Edytuj</a><br/>
                                <a href="/admin/delete?userId=${user.userId}">Usuń</a>
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
