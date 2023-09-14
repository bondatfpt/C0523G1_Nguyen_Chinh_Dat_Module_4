<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 9/14/2023
  Time: 2:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Currency Conversion</title>
</head>
<body>
<form action="/currency-conversion/vnd" method="post">
  <lable for="vnd">Enter Vnd</lable>
  <input type="number" name="vnd" id="vnd" value="${vnd}">
  <button type="submit">Convert</button>
  <p>Result: ${resultVnd}</p>
</form>

<form action="/currency-conversion/usd" method="post">
  <lable>Enter Usd</lable>
  <input type="number" name="usd" id="usd" value="${usd}">
  <button type="submit">Convert</button>
  <p>Result: ${resultUsd}</p>
</form>

</body>
</html>
