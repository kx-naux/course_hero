package controller;

import JPAEntity.Promotions;
import JPAEntity.Users;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Promotion", urlPatterns = {"/promotion"})

public class Promotion extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Users userData = Login.checkRmbMeToken(request, em);
        if (userData != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userData", userData);
            Login.getUserWishlist(request, em, userData);
            Login.getUserCart(request, em, userData);
        } else {

        }
        
        Users userDataSession = (Users) request.getSession().getAttribute("userData");
        if (userDataSession != null) {
            Login.getUserWishlist(request, em, userDataSession);
            Login.getUserCart(request, em, userDataSession);
        }

        
        Query query = em.createNamedQuery("Promotions.countAllActive", Long.class);
        query.setParameter("active", "Active");
        Long count = (long) query.getSingleResult();
        request.setAttribute("numberOfPromotions", count);

        int reqPage = 1;
        int maxDataInPage = 4;
        long lastPage = (count - 1) / Long.parseLong(String.valueOf(maxDataInPage)) + 1;
        if (request.getParameter("p") != null) {
            reqPage = Integer.parseInt(request.getParameter("p"));
            if (reqPage > lastPage) {
                goToCustomErrorPage(request, response, "Invalid URL");
            }
        }

        int offset = (reqPage - 1) * maxDataInPage;

        query = em.createNamedQuery("Promotions.findAllSortByEndTimeAscActive");
        query.setParameter("active", "Active");
        query.setFirstResult(offset);
        query.setMaxResults(maxDataInPage);
        List<Promotions> promotions = query.getResultList();
        request.setAttribute("promotions", promotions);

        // Forward the request to Promotion.jsp
        request.getRequestDispatcher("/WEB-INF/Client/Promotion.jsp").forward(request, response);
    }

    private void goToCustomErrorPage(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("errorMessage", errorMessage);
        session.setAttribute("errorDetail", "Your search has ventured beyond the known universe.");

        // Forward the request to  error page
        request.getRequestDispatcher("/WEB-INF/Client/CustomError.jsp").forward(request, response);
    }
}
