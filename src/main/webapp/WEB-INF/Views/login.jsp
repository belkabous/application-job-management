<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier une Offre d'Emploi</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">
<div class="container max-w-2xl mx-auto p-6 bg-white rounded-lg shadow-md my-6">
    <h1 class="text-2xl font-bold text-gray-800 mb-6 text-center">Modifier une Offre d'Emploi</h1>

    <form action="<c:url value='/offre_emploi/update'/>" method="post" class="space-y-6">
        <input type="hidden" name="id" value="${offre.id}">

        <div>
            <label for="titre" class="block text-sm font-medium text-gray-700 mb-1">Titre:</label>
            <input type="text"
                   id="titre"
                   name="titre"
                   value="${offre.titre}"
                   required
                   class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent">
        </div>

        <div>
            <label for="date" class="block text-sm font-medium text-gray-700 mb-1">Date de publication:</label>
            <input type="date"
                   id="date"
                   name="date"
                   value="<fmt:formatDate value='${offre.date}' pattern='yyyy-MM-dd'/>"
                   required
                   class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent">
        </div>

        <div>
            <label for="description" class="block text-sm font-medium text-gray-700 mb-1">Description:</label>
            <textarea id="description"
                      name="description"
                      rows="5"
                      required
                      class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent">${offre.description}</textarea>
        </div>

        <div class="flex justify-end space-x-4">
            <button type="submit"
                    class="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 transition-colors duration-200">
                Mettre à jour
            </button>
            <a href="<c:url value='/offre_emploi/list'/>"
               class="px-4 py-2 bg-gray-500 text-white rounded-md hover:bg-gray-600 transition-colors duration-200">
                Annuler
            </a>
        </div>
    </form>
</div>
</body>
</html>