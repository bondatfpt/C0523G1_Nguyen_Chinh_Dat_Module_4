<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
  <title>Setting Email Box</title>
</head>
<body>
<h1>Setting</h1>
<form:form modelAttribute="settingEmailBox">
  <table>
    <tr>
      <td><label>Languages</label></td>
      <td><form:select path="languages">
        <form:option value="Vietnamese" label="Vietnamese"></form:option>
        <form:option value="English" label="English"></form:option>
        <form:option value="Japanese" label="Japanese"></form:option>
        <form:option value="Chinese" label="Chinese"></form:option>
      </form:select>
      </td>
      <td><label>Page Size</label></td>
      <td></td>
    </tr>
  </table>
</form:form>
</body>
</html>