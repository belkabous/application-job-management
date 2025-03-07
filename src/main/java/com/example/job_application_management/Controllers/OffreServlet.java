package com.example.job_application_management.Controllers;

import com.example.job_application_management.DAO.OffreDao;
import com.example.job_application_management.Models.Offre_emploi;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/offre_emploi/*")
public class OffreServlet extends HttpServlet {
    private OffreDao offreDao;

    public void init() throws ServletException {
        offreDao = new OffreDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();
        if (action == null) {
            action = "/list";
        }

        try {
            switch (action) {
                case "/new":
                    showNewForm(req, resp);
                    break;
                case "/insert":
                    AjouterOffre(req, resp);
                    break;
                case "/delete":
                    DeleteOffre(req, resp);
                    break;
                case "/edit":
                    showEditForm(req, resp);
                    break;
                case "/update":
                    UpdateOffre(req, resp);
                    break;
                default:
                    listOffres(req, resp);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void listOffres(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Offre_emploi> offreEmplois = offreDao.AfficherOffer();
        req.setAttribute("offreEmplois", offreEmplois);
        req.getRequestDispatcher("/list-offres.jsp").forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/Emploi/Ajouter-offre.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Offre_emploi existingOffre = offreDao.SelectByIdOffre(id);
        req.setAttribute("offre", existingOffre);
        req.getRequestDispatcher("/WEB-INF/Emploi/offre-form.jsp").forward(req, resp);
    }

    public void AjouterOffre(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        String titre = req.getParameter("titre");
        String description = req.getParameter("description");
        String dateStr = req.getParameter("date");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateStr);

        Offre_emploi newoffreEmploi = new Offre_emploi(titre, date, description);
        offreDao.AjouterOffer(newoffreEmploi);
        resp.sendRedirect("list");
    }

    public void UpdateOffre(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        int id = Integer.parseInt(req.getParameter("id"));
        String titre = req.getParameter("titre");
        String description = req.getParameter("description");
        String dateStr = req.getParameter("date");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateStr);

        Offre_emploi offreEmploi = new Offre_emploi(id, titre, description, date);
        offreDao.ModiffierOffer(offreEmploi);
        resp.sendRedirect("list");
    }

    public void DeleteOffre(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        offreDao.DeleteOffre(id);
        resp.sendRedirect("list");
    }
}