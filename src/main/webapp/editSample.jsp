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
        <div class="samples">
            <form action="samples?action=saveSample" method="post">
                <jsp:useBean id="sample" type="ua.mykytenko.entities.samples.Sample" scope="request"/>
                    <input name="id" value="${sample.id}" type="hidden">
                <dl class="samples">
                    <dd>Назва</dd>
                    <dt><input name="name" value="${sample.name}" type="text"></dt>

                    <dd>Testing Family</dd>
                    <dt>
                        <select name="testingFamily" value="${sample.family}" type="text">
                            <c:forEach items="${families}" var="family">
                                <option value="${family}" ${sample.family == family ? 'selected' : ''}  >${family}</option>
                            </c:forEach>
                        </select>
                    </dt>

                    <dd>Маса зразка, кг</dd>
                    <dt><input name="weight" value="${sample.weight}" type="text"></dt>

                    <dd>Дата прибуття</dd>
                    <dt><input name="arrived" value="${sample.arrived}" type="date"></dt>

                    <dd>Дистрибютор</dd>
                    <dt><input name="vendor" value="${sample.vendor}" type="text"></dt>

                    <dd>Виробник</dd>
                    <dt><input name="manufacturer" value="${sample.manufacturer}" type="text"></dt>

                    <dd>Призначення</dd>
                    <dt><input class="big-input" name="applications" value="${sample.applications}" type="text"></dt>

                    <dd>Склад</dd>
                    <dt><input class="big-input" name="composition" value="${sample.composition}" type="text"></dt>
                </dl>
                <button type="submit">Save</button>
                <button onclick="window.history.back()">Cancel</button>
            </form>
        </div>
    </div>
</main>

<jsp:include page="jspSnippets/footer.jsp"/>


