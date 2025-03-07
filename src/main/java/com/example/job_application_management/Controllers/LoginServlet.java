// LoginServlet.java
package com.example.job_application_management.Controllers;

import com.example.job_application_management.DAO.UserDao;
import com.example.job_application_management.Models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Afficher la page de login
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Views/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDao.authenticate(email, password);

        if (user != null) {
            // Authentification réussie, créer la session
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getNom());
            session.setAttribute("email", email);
            session.setAttribute("role", user.getRole());
            session.setAttribute("userId", user.getId());

            // Rediriger en fonction du rôle
            if ("admin".equals(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            } else if ("recruteur".equals(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/offre_emploi/list");
            } else {
                response.sendRedirect(request.getContextPath() + "/candidat/dashboard");
            }
        } else {
            // Authentification échouée
            request.setAttribute("errorMessage", "Email ou mot de passe incorrect");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Auth/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}

