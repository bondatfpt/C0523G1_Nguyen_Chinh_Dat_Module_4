<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<h1>List Settings</h1>
<table class="table">
    <thead>
    <tr>
        <th scope="col">STT</th>
        <th scope="col">Language</th>
        <th scope="col">Page size</th>
        <th scope="col">Spams filter</th>
        <th scope="col">Signature</th>
        <th scope="col">Action</th>

    </tr>
    </thead>
    <tbody>
    <tr>
        <c:forEach var="setting" items="${settingEmailBoxList}" varStatus="STT">
    <tr>
        <td>${STT.count}</td>
        <td>${setting.languages}</td>
        <td>${setting.pages}</td>
        <td><input disabled type="checkbox" ${setting.spamsFilter ? 'checked': ''} ></td>
        <td>${setting.signature}</td>
        <td><a href="/showFormUpdate?id=${setting.id}">Update</a></td>
    </tr>
    </c:forEach>
    </tr>
    </tbody>
</table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>
</html>