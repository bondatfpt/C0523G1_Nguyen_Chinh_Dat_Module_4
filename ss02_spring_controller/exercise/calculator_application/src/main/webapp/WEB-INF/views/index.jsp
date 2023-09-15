<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Calculator</title>
</head>
<body>
<h1>Calculator</h1>
<form action="/calculate" method="post">
    <input value="${firstNum}" type="number" name="firstNum" required>
    <input value="${secondNum}" type="number" name="secondNum" required> <br>
    <button type="submit" name="operator" value="+">+</button>
    <button type="submit" name="operator" value="-">-</button>
    <button type="submit" name="operator" value="*">*</button>
    <button type="submit"  name="operator" value="/">/</button>
</form>
<p>Result: ${result}</p>
</body>
</html>