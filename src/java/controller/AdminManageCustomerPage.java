package controller;

import JPAEntity.Users;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@WebServlet(name = "Admin Manage Customer", urlPatterns = {"/admin/manage-customer"})
public class AdminManageCustomerPage extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users userDataSession = (Users) request.getSession().getAttribute("userData");
        Users userData = Login.checkRmbMeToken(request, em);
        //check has rmb token onot
        if (userData != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userData", userData);
            Login.getUserWishlist(request, em, userData);
            Login.getUserCart(request, em, userData);
            //check has user logged in
        } else if (userDataSession == null) {
            HttpSession session = request.getSession();
            session.setAttribute("pageToGoAfterLogin", "admin/manage-customer");
            response.sendRedirect("../login");
            return;
        }

        if (userDataSession != null) {
            Login.getUserWishlist(request, em, userDataSession);
            Login.getUserCart(request, em, userDataSession);
        }

        //get userData from session as the user can login thru the rmbMe
        Users checkUserAccess = (Users) request.getSession().getAttribute("userData");
        if (checkUserAccess.getUsertype().equals("Customer")) {
            ErrorPage.forwardToServerErrorPage(request, response, "Authorized Access Only ! ! !");
        }

        List<Users> customersList;
        Query qry = em.createNamedQuery("Users.findAllCustomer");
        customersList = qry.getResultList();

        request.setAttribute("customersSize", customersList.size());
        request.setAttribute("customers", customersList);

        // Forward the request to home.jsp
        request.getRequestDispatcher("/WEB-INF/Admin/AdminManageCustomer.jsp").forward(request, response);
    }
}
