package controller;

import JPAEntity.Accounts;
import JPAEntity.Users;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@WebServlet(name = "Admin Delete Staff", urlPatterns = {"/admin/delete-staff"})
public class AdminDeleteCustomer extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("id");
        Users selectedUser = em.find(Users.class , userId);
        
        deleteDataInDatabase(selectedUser,request,response);
        
        request.setAttribute("successMsg", "Record Deleted");

       List<Users> customersList;
        Query qry = em.createNamedQuery("Users.findAllCustomer");
        customersList = qry.getResultList();

        request.setAttribute("customersSize", customersList.size());
        request.setAttribute("customers", customersList);

        // Forward the request to home.jsp
        request.getRequestDispatcher("/WEB-INF/Admin/AdminManageCustomer.jsp").forward(request, response);
    }

    private void deleteDataInDatabase(Users newUserData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
           utx.begin();
            Users userData = em.find(Users.class, newUserData.getUserId());
            em.remove(userData);
            utx.commit();
        } catch (Exception ex) {
            try {
                if (utx.getStatus() == Status.STATUS_ACTIVE) {
                    try {
                        utx.rollback();
                    } catch (SystemException ex2) {
                        //server error page
                        ErrorPage.forwardToServerErrorPage(request, response, "Database Server Error. Please Try Again Later");
                    }
                }
            } catch (SystemException ex2) {
                ErrorPage.forwardToServerErrorPage(request, response, "Database Server Error. Please Try Again Later");
            }
            ErrorPage.forwardToServerErrorPage(request, response, "Database Server Error. Please Try Again Later");
        }
    }

}
