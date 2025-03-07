<%--
  Created by IntelliJ IDEA.
  User: idriss
  Date: 05/03/2025
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter Offre d'Emploi</title>
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
        input[type="text"], input[type="date"] {
            width: 200px;
            padding: 5px;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h1>Ajouter une nouvelle Offre</h1>
    <form action="${pageContext.request.contextPath}/offre_emploi/insert" method="post">
        <label>Titre :</label>
        <input type="text" name="titre" required><br>
        <label>Description :</label>
        <input type="text" name="description" required><br>
        <label>Date :</label>
        <input type="date" name="date" required><br>
        <input type="submit" value="Ajouter">
        <a href="${pageContext.request.contextPath}/offre_emploi/list">Annuler</a>
    </form>
</div>
</body>
</html>