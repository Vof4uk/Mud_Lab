<%--
  Created by IntelliJ IDEA.
  User: Микитенко
  Date: 21.10.2016
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="jspSnippets/header.jsp" />

<main class="main">
<div>
    <form action="samples?" method="get">
        <input type="hidden" name="action" value="logIn">
        <select name="userId">
            <option selected value="1">unauthorised</option>
            <option value="2">Laboratory assistant</option>
            <option value="3">Chief of lab</option>
            <option value="4">Boss</option>
            <option value="5">sysAdmin</option>
        </select>
        <input type="submit" value="log in">
    </form>
</div>
</main>

<jsp:include page="jspSnippets/footer.jsp" />


