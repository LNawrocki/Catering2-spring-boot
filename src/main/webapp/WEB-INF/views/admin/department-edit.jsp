<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <jsp:include page="../fragments/title.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_department-edit.css">
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>
<jsp:include page="../fragments/menu-admin.jsp"/>

<form:form method="post" modelAttribute="department">
    <div class="main-block">
        <div class="login-row">
            <label>ID:
                <form:input type="number" path="id"/>
            </label>
        </div>
        <div class="login-row">
            <label for="name">Nazwa:
                <form:input path="name"/>
            </label>
        </div>
        <div class="login-row">
            <label for="paymentPerc">Płatność (%):
                <form:input path="paymentPerc"/><br>
                <form:errors path="paymentPerc" cssStyle="color: red"/>
            </label>
        </div>
        <div class="login-row">
            <form:button>Dodaj / Zastąp</form:button>
        </div>
    </div>
</form:form>

<c:if test="${msg ne null && msg ne ''}">
<div class="main-block">
    <div class="login-row" style="color: red; font-size: larger; font-weight: bold">
        ${msg}
    </div>
</div>
</c:if>

<div class="main-block">
    <div class="login-row">
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
                            Nazwa
                        </div>
                    </div>
                </td>
                <td>
                    <div class="day">
                        <div>
                            Płatność (%)
                        </div>
                    </div>
                </td>
                <td>
                    Akcja
                </td>
            </tr>
            <c:forEach items="${departments}" var="department">
                <tr>
                    <td>
                        <div class="day">
                            <div>
                                <c:out value="${department.id}"/>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                <c:out value="${department.name}"/>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="day">
                            <div>
                                <c:out value="${department.paymentPerc}"/>
                            </div>
                        </div>
                    </td>
                    <td>
                        <form action="/admin/department/delete" method="post">
                            <button name="deleteDepartmentId" value="${department.id}"
                                    style="font-size: small; border-radius: 5px; border-width: 1px; color: red">Usuń
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>


<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>
