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
    <div class="container cf">
        <div class="before-table">

        </div>
        <div class="samples">
            <table class="samples">
                <tr class="samples-header-row">
                    <th>Номер</th>
                    <th>Назва</th>
                    <th>Маса зразка, кг</th>
                    <th>Дата прибуття</th>
                    <th>Виробник</th>
                    <th>Дистрибютор</th>
                    <th>Призначення</th>
                    <th>Склад</th>
                    <th class="td-no-borders"></th>
                    <th class="td-no-borders"></th>
                </tr>
                <jsp:useBean id="sample" type="ua.mykytenko.entities.samples.Sample" scope="request"/>
                <tr class="samples-body-row">
                    <td>${sample.id}</td>
                    <td>${sample.name}</td>
                    <td>${sample.weight}</td>
                    <td>${sample.arrived}</td>
                    <td>${sample.vendor}</td>
                    <td>${sample.manufacturer}</td>
                    <td>
                        <c:forEach items="${sample.applications}" var="application">
                            ${application};
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${sample.composition}" var="entry">
                            ${entry.key} - ${entry.value}%<br>
                        </c:forEach>
                    </td>
                    <td class="td-no-borders"><a href="samples?action=update&id=${sample.id}">Update</a></td>
                    <td class="td-no-borders"><a href="samples?action=delete&id=${sample.id}">Delete</a></td>
                </tr>
            </table>

            <div>
                <c:forEach items="${toList}" var="to">
                    <jsp:useBean id="to" class="ua.mykytenko.to.TestingTo" />
                    <h2>${to.entityName}</h2>
                    <table class="samples">
                        <tr class="samples-header-row">
                            <c:forEach items="${to.keySet()}" var="key">
                                <th>${key}</th>
                            </c:forEach>
                        </tr>
                        <tr class="samples-body-row">
                            <c:forEach items="${to.values()}" var="value">
                                <td>${value}</td>
                            </c:forEach>
                        </tr>
                    </table>

                </c:forEach>
            </div>


        </div>
    </div>
</main>

<jsp:include page="jspSnippets/footer.jsp"/>


