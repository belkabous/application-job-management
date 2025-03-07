<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<h1>Please log in</h1>
<form action="login" method="post">
    <label>Email : </label><br>
    <input type="email" id="fname" name="email" ><br>
    <label>Password : </label><br>
    <input type="password"  name="password" ><br><br>

    <input type="submit" value="Submit">
</form>
<c:if test="${param.error}">
    password or username invalide :(
</c:if>
</body>
</html>