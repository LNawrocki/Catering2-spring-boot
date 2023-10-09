<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <title>Catering - Lightnet</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_user_list.css">
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu-admin.jsp"/>

<form action="/admin/list/search" method="post">
    <div class="main-block">
        <div class="day-block">
            <table>
                <tr>
                    <td>
                        Wyszukaj:
                    </td>
                    <td>
                        <select name="item">
                            <option name="findLogin" value="findLogin" >Login</option>
                            <option name="findUserId" value="findUserId" selected>Id</option>
                            <option name="findDepartment" value="findDepartment">Dział</option>
                        </select>
                    </td>
                    <td>
                        <input type="text" name="search" placeholder="szukana wartość">
                    </td>
                    <td>
                        <button type="submit">Wyszukaj</button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</form>

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
