package com.example.job_application_management.Controllers;

import com.example.job_application_management.DAO.CandidatureDao;
import com.example.job_application_management.Models.Candidature;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/candidature/*")
public class CandidatureServlet extends HttpServlet {
    private CandidatureDao candidatureDao;

    public void init() throws ServletException {
        candidatureDao = new CandidatureDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getRequestURI();
        System.out.println("Action: " + action);

        try {
            if (action == null || action.equals("/candidature")) {
                listCandidatures(request, response);
            } else {
                switch (action) {
                    case "/candidature/new":
                        showNewForm(request, response);
                        break;
                    case "/candidature/list":
                        listCandidatures(request, response);
                        break;
                    case "/candidature/byoffre":
                        listCandidaturesByOffre(request, response);
                        break;
                    case "/candidature/bycandidat":
                        listCandidaturesByCandidat(request, response);
                        break;
                    case "/candidature/delete":
                        deleteCandidature(request, response);
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getRequestURI();

        try {
            if ("/candidature/add".equals(action)) {
                addCandidature(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'id de l'offre si fourni
        String offreIdParam = request.getParameter("offreId");
        if (offreIdParam != null && !offreIdParam.isEmpty()) {
            int offreId = Integer.parseInt(offreIdParam);
            request.setAttribute("offreId", offreId);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Candidature/NewCandidature.jsp");
        dispatcher.forward(request, response);
    }

    private void addCandidature(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int candidatId = Integer.parseInt(request.getParameter("candidatId"));
        int offreId = Integer.parseInt(request.getParameter("offreId"));

        boolean success = candidatureDao.ajouterCandidature(candidatId, offreId);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/candidature/list");
        } else {
            request.setAttribute("error", "Échec de l'ajout de la candidature");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Candidature/NewCandidature.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void listCandidatures(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Cette méthode afficherait toutes les candidatures, mais généralement on les filtre par offre ou candidat
        // Rediriger vers une page qui permet de choisir entre voir par offre ou par candidat
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Candidature/SelectListType.jsp");
        dispatcher.forward(request, response);
    }

    private void listCandidaturesByOffre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int offreId = Integer.parseInt(request.getParameter("offreId"));
        List<Candidature> candidatures = candidatureDao.getCandidaturesForOffre(offreId);

        request.setAttribute("candidatures", candidatures);
        request.setAttribute("offreId", offreId);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Candidature/ListCandidaturesByOffre.jsp");
        dispatcher.forward(request, response);
    }

    private void listCandidaturesByCandidat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int candidatId = Integer.parseInt(request.getParameter("candidatId"));
        List<Candidature> candidatures = candidatureDao.getCandidaturesForCandidat(candidatId);

        request.setAttribute("candidatures", candidatures);
        request.setAttribute("candidatId", candidatId);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Candidature/ListCandidaturesByCandidat.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteCandidature(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int candidatId = Integer.parseInt(request.getParameter("candidatId"));
        int offreId = Integer.parseInt(request.getParameter("offreId"));

        boolean success = candidatureDao.deleteCandidature(candidatId, offreId);

        // Rediriger en fonction de la source de la demande (depuis la liste des offres ou des candidats)
        String source = request.getParameter("source");
        if ("offre".equals(source)) {
            response.sendRedirect(request.getContextPath() + "/candidature/byoffre?offreId=" + offreId);
        } else {
            response.sendRedirect(request.getContextPath() + "/candidature/bycandidat?candidatId=" + candidatId);
        }
    }
}