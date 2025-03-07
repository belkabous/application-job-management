<%--
  Created by IntelliJ IDEA.
  User: idriss
  Date: 05/03/2025
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Liste des Offres d'Emploi</title>
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h1>Liste des Offres d'Emploi</h1>
<a href="${pageContext.request.contextPath}/offre_emploi/new">Ajouter une nouvelle offre</a>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Titre</th>
        <th>Description</th>
        <th>Date</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${offreEmplois}" var="offre">
        <tr>
            <td>${offre.id}</td>
            <td>${offre.titre}</td>
            <td>${offre.description}</td>
            <td>${offre.date}</td>
            <td>
                <a href="${pageContext.request.contextPath}/offre_emploi/edit?id=${offre.id}">Modifier</a>
                <a href="${pageContext.request.contextPath}/offre_emploi/delete?id=${offre.id}"
                   onclick="return confirm('Voulez-vous vraiment supprimer cette offre?');">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>