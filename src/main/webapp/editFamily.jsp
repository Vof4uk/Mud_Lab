<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Микитенко
  Date: 21.10.2016
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="jspSnippets/header.jsp"/>

<main class="main">
    <link rel="stylesheet" href="css/sampleform.css">
    <div class="container cf">
        <div class="before-table">

        </div>
        <div>
            <table class="samples">
                <tr class="samples-header-row">
                    <th>Назва</th>
                    <th>init ID</th>
                </tr>
                <c:forEach items="${families}" var="family">
                    <jsp:useBean id="family" type="ua.mykytenko.entities.samples.SampleFamily" scope="request"/>
                    <tr class="samples-body-row">
                        <td>${family.name}</td>
                        <td>${family.initialId}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="samples">
            <form action="samples?action=saveFamily" method="post">
                <input name="id" value="${family.id}" type="hidden">
                <dl class="samples">
                    <dd>Initial Id</dd>
                    <dt><input name="initialId" value="${initialId}" type="text"></dt>

                    <dd>Назва</dd>
                    <dt><input name="name" value="${familyName}" type="text"></dt>
                </dl>
                <button type="submit">Save</button>
                <button onclick="window.history.back()">Cancel</button>
            </form>
        </div>
    </div>
</main>

<jsp:include page="jspSnippets/footer.jsp"/>


