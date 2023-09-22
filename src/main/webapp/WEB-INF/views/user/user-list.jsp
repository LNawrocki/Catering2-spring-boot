<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <title>Catering - Lightnet</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_user_list.css">
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu.jsp"/>

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
                                <a href="/user/update?userId=${user.userId}">Edytuj</a><br/>
                                <a href="/user/delete?userId=${user.userId}">Usuń</a>
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
