<%--
  Created by IntelliJ IDEA.
  User: idriss
  Date: 05/03/2025
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifier Offre d'Emploi</title>
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
    <h1>${offre == null ? 'Ajouter une nouvelle offre' : 'Modifier l\'offre'}</h1>
    <form action="${pageContext.request.contextPath}/offre_emploi/${offre == null ? 'insert' : 'update'}" method="post">
        <c:if test="${offre != null}">
            <input type="hidden" name="id" value="${offre.id}">
        </c:if>
        <label>Titre :</label>
        <input type="text" name="titre" value="${offre.titre}" required><br>
        <label>Description :</label>
        <input type="text" name="description" value="${offre.description}" required><br>
        <label>Date :</label>
        <input type="date" name="date" value="${offre.date}" required><br>
        <input type="submit" value="${offre == null ? 'Ajouter' : 'Mettre à jour'}">
        <a href="${pageContext.request.contextPath}/offre_emploi/list">Annuler</a>
    </form>
</div>
</body>
</html>