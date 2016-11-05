<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Микитенко
  Date: 21.10.2016
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />

<main class="main">
    <div class="container cf">
        <div class="before-table">

        </div>
        <div class="samples">
            <table class="samples">
                <tr class="samples-header-row">
                    <th>Номер</th>
                    <th>Назва</th>
                    <th class="td-no-borders"></th>
                    <th class="td-no-borders"></th>
                </tr>
                <jsp:useBean id="family" class="ua.mykytenko.entities.samples.SampleFamily"/>
                <c:forEach items="${families}" var="family">
                    <tr class="samples-body-row">
                        <td>${family.id}</td>
                        <td>${family.name}</td>
                        <td class="td-no-borders"><a href="samples?action=updateFamily&id=${family.id}">Update</a></td>
                        <td class="td-no-borders"><a href="samples?action=updateFamily&id=${family.id}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</main>

<jsp:include page="footer.jsp" />


