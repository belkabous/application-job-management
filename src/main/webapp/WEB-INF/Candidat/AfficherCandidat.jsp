<%--
  Created by IntelliJ IDEA.
  User: idriss
  Date: 05/03/2025
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Liste des Candidats</title>
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
<h1>Liste des Candidats</h1>
<a href="${pageContext.request.contextPath}/candidat/NewCandidat">Ajouter un nouveau candidat</a>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Nom d'utilisateur</th>
        <th>Email</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${candidatList}" var="candidat">
        <tr>
            <td>${candidat.id}</td>
            <td>${candidat.username}</td>
            <td>${candidat.email}</td>
            <td>
                <a href="${pageContext.request.contextPath}/candidat/edit?id=${candidat.id}">Modifier</a>
                <a href="${pageContext.request.contextPath}/candidat/delete?id=${candidat.id}"
                   onclick="return confirm('Voulez-vous vraiment supprimer ce candidat?');">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>