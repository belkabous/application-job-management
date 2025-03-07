<%--
  Created by IntelliJ IDEA.
  User: idriss
  Date: 05/03/2025
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter Candidat</title>
    <style>
        .form-container {
            width: 50%;
            margin: 20px auto;
        }
        label {
            display: inline-block;
            width: 120px;
            margin: 10px 0;
        }
        input[type="text"], input[type="email"] {
            width: 200px;
            padding: 5px;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h1>Ajouter un Candidat</h1>
    <form action="${pageContext.request.contextPath}/candidat/Ajoutercandidat" method="post">
        <label>Username :</label>
        <input type="text" name="username" required><br>
        <label>Email :</label>
        <input type="email" name="email" required><br>
        <input type="submit" value="Ajouter">
        <a href="${pageContext.request.contextPath}/candidat/Affichercandidat">Annuler</a>
    </form>
</div>
</body>
</html>