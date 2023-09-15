<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 9/15/2023
  Time: 10:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sandwich condiments</title>
</head>
<body>
<h1>Sandwich Condiments</h1>
<form action="/listCondiments" method="post">
    <label for="lettuce" >Lettuce</label>
    <input value="lettuce" type="checkbox" id="lettuce" name="lettuce">
    <label for="tomato">Tomato</label>
    <input value="tomato" type="checkbox" id="tomato" name="tomato">
    <label for="mustard">Mustard</label>
    <input value="mustard" type="checkbox" id="mustard" name="mustard">
    <label for="sprouts">Sprouts</label>
    <input value="sprouts" type="checkbox" id="sprouts" name="sprouts">
    <button type="submit">Choose</button>
    <p>${condiments}</p>
</form>
</body>
</html>
