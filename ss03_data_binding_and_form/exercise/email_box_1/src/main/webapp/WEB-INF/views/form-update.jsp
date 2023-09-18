<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 9/18/2023
  Time: 5:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Setting</h1>
<form:form modelAttribute="settingEmailBox" action="/update" method="post">
    <form:input readonly="true" path="id" cssStyle="display: none"></form:input>
    <table>
        <tr>
            <td><label>Languages</label></td>
            <td><form:select path="languages">
                <form:options items="${languageList}"></form:options>
            </form:select>
            </td>
        </tr>
        <tr>
            <td><label>Page Size</label></td>
            <td>
                <form:select path="pages">
                    <form:options items="${pageList}"></form:options>
                </form:select>
            </td>
        </tr>
        <tr>
            <td><label>Spams filter:</label></td>
            <td>
                <form:checkbox path="spamsFilter"></form:checkbox>
            </td>
        </tr>
        <tr>
            <td><label>Signature</label></td>
            <td><form:textarea path="signature"></form:textarea></td>
        </tr>
        <tr>
            <td> <form:button>Update</form:button> </td>
        </tr>
    </table>
</form:form>
</body>
</html>
