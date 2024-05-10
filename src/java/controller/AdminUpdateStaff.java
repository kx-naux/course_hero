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

@WebServlet(name = "Admin Update Staff", urlPatterns = {"/admin/update-staff"})
public class AdminUpdateStaff extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("userId");
        Users recordToUpdate = em.find(Users.class, userId);

        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String userType = request.getParameter("role");
        String email = request.getParameter("email");

        // validation
        if (checkForDuplicatedUsername(username)) {
            List<Users> staffs;
            Query qry = em.createNamedQuery("Users.findAllStaff");
            staffs = qry.getResultList();

            request.setAttribute("staffSize", staffs.size());
            request.setAttribute("staffs", staffs);
            request.setAttribute("errorMsg", "Staff ID already exists, please use another.");
            request.getRequestDispatcher("/WEB-INF/Admin/AdminManageStaff.jsp").forward(request, response);
        }

        if (checkForDuplicatedEmail(email)) {
            List<Users> staffs;
            Query qry = em.createNamedQuery("Users.findAllStaff");
            staffs = qry.getResultList();

            request.setAttribute("staffSize", staffs.size());
            request.setAttribute("staffs", staffs);
            request.setAttribute("errorMsg", "Email already exists, please use another.");
            request.getRequestDispatcher("/WEB-INF/Admin/AdminManageStaff.jsp").forward(request, response);
        }

        Accounts newAcc = recordToUpdate.getAccountId();
        newAcc.setUsername(username);
        newAcc.setEmail(email);

        udpateDataInDatabaseAcc(newAcc, request, response);

        recordToUpdate.setAccountId(newAcc);
        recordToUpdate.setUsertype(userType);
        recordToUpdate.setDisplayName(name);

        udpateDataInDatabase(recordToUpdate, request, response);

        request.setAttribute("successMsg", "Record Updated");

        List<Users> staffs;
        Query qry = em.createNamedQuery("Users.findAllStaff");
        staffs = qry.getResultList();

        request.setAttribute("staffSize", staffs.size());
        request.setAttribute("staffs", staffs);

        // Forward the request to home.jsp
        request.getRequestDispatcher("/WEB-INF/Admin/AdminManageStaff.jsp").forward(request, response);
    }

    private boolean checkForDuplicatedEmail(String email) {
        Query query = em.createNamedQuery("Accounts.findByEmail").setParameter("email", email);
        List<Accounts> record = (List<Accounts>) query.getResultList();
        if (record.isEmpty()) { //means is not duplicated
            return false;
        } else {
            return true;
        }
    }

    private boolean checkForDuplicatedUsername(String username) {
        Query query = em.createNamedQuery("Accounts.findByUsername").setParameter("username", username);
        List<Accounts> record = (List<Accounts>) query.getResultList();
        if (record.isEmpty()) { //means is not duplicated
            return false;
        } else {
            return true;
        }
    }

    private void udpateDataInDatabase(Users newUserData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            utx.begin();
            em.merge(newUserData);
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

    private void udpateDataInDatabaseAcc(Accounts newUserData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            utx.begin();
            em.merge(newUserData);
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
