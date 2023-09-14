<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 9/14/2023
  Time: 4:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dictionary English-Vietnamese</title>
</head>
<body>
<h2>Dictionary</h2>
<form action="/translate" method="post">
    <lable>Enter an english word</lable>
    <input type="text" name="word" id="word" required>
    <button type="submit">Translate</button>
    <p>Result: ${result}</p>
</form>
</body>
</html>
