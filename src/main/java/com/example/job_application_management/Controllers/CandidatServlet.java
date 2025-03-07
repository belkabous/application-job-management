package com.example.job_application_management.Controllers;


import com.example.job_application_management.DAO.CandidatDao;
import com.example.job_application_management.Models.Candidat;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/candidat/*")
public class CandidatServlet extends HttpServlet {
    private CandidatDao candidatDao;
    public void init() throws ServletException {
      candidatDao = new CandidatDao();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String action = request.getRequestURI();
            System.out.println(action);
            try {
                if (action == null || action.equals("/")) {
                AfficherCandidat(request,response);
                } else {
                    switch (action) {
                        case "/candidat/NewCandidat":
                            afficherNewForm(request, response);
                            break;
                        case "/candidat/Ajoutercandidat":
                        Ajoutercandidat(request, response);
                            break;
                        case "/candidat/Affichercandidat":
                        AfficherCandidat(request, response);
                            break;
                        case "/candidat/edit":
                            afficherModifierFormulaire(request, response);
                            break;
                        case "/candidat/update":
                        ModifierCandidat(request, response);
                            break;
                        case "/candidat/delete":
                        DeleteCandidat(request, response);
                            break;
                        default:
                            response.sendError(HttpServletResponse.SC_NOT_FOUND);
                            break;
                    }
                }
            } catch (Exception ex) {
                throw new ServletException(ex);
            }
        }
    private void Ajoutercandidat(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String username = request.getParameter("username");
        String Email = request.getParameter("email");
        Candidat newCandidat = new Candidat(username, Email);
        candidatDao.AjouterCandidat(newCandidat);
        response.sendRedirect("Affichercandidat");
    }
    private void afficherNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Candidat/AjouterCandidat.jsp").forward(request, response);
    }


    private  void  AfficherCandidat(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        List<Candidat> candidatList = candidatDao.AfficherCandidat();
        request.setAttribute("candidatList", candidatList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Candidat/AfficherCandidat.jsp");
    }
    private void ModifierCandidat(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String Email = request.getParameter("email");
        Candidat newCandidat = new Candidat(id,username, Email);
        candidatDao.ModiffierCandidat(newCandidat);
        response.sendRedirect("Affichercandidat");

    }


    private  void  afficherModifierFormulaire(HttpServletRequest request, HttpServletResponse response)
        throws  ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Candidat candidat = candidatDao.selectCandidatById(id);
        request.setAttribute("candidat", candidat);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Candidat/ModifierCandidat.jsp");
        dispatcher.forward(request, response);
    }
    private void DeleteCandidat(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        candidatDao.deleterCandidat(id);
        response.sendRedirect("Affichercandidat");
    }
}