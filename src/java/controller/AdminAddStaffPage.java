package controller;

import JPAEntity.Accounts;
import JPAEntity.BillingAddress;
import JPAEntity.TablesRecordCounter;
import JPAEntity.Users;
import entity.LoginFormData;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@WebServlet(name = "Admin Add Staff", urlPatterns = {"/admin/add-staff"})
public class AdminAddStaffPage extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users userDataSession = (Users) request.getSession().getAttribute("userData");
        Users userData = Login.checkRmbMeToken(request, em);
        //check has rmb token onot
        if(userData != null){
            HttpSession session = request.getSession();
            session.setAttribute("userData",userData);
            Login.getUserWishlist(request, em, userData);
            Login.getUserCart(request, em, userData);
        //check has user logged in
        }else if(userDataSession.getUserId() == null){
            HttpSession session = request.getSession();
            session.setAttribute("pageToGoAfterLogin","admin/add-staff");
            response.sendRedirect("../login");
            return;
        }
        
        if (userDataSession != null) {
            Login.getUserWishlist(request, em, userDataSession);
            Login.getUserCart(request, em, userDataSession);
        }
        
        //get userData from session as the user can login thru the rmbMe
        Users checkUserAccess = (Users) request.getSession().getAttribute("userData");
        if(!checkUserAccess.getUsertype().equals("Manager")){
            ErrorPage.forwardToServerErrorPage(request, response, "Authorized Access Only ! ! !");
        }
        // Forward the request to home.jsp
        request.getRequestDispatcher("/WEB-INF/Admin/AdminAddStaff.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        HttpSession session = request.getSession();

        // validation check
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String defaultPassword = "coursehero";

        //create new 
        Accounts newAcc = new Accounts(username, email);
        try {
            newAcc.setHashedPassword(defaultPassword);
        } catch (NoSuchAlgorithmException ex) {
            ErrorPage.forwardToServerErrorPage(request, response, "Server Passoword Hashing Error. Please Try Again Later");
        }

        //create new billing address obj
        BillingAddress newBillAddress = new BillingAddress("", "", "", "", "");
        newBillAddress.setLine2("");

        // create new user
        Users newUser = new Users(name, new Date(), "Staff", new Date(), "-", newAcc, newBillAddress);

        // validation
        if (checkForDuplicatedUsername(username)) {
            request.setAttribute("username", username);
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("errorMsg", "Staff ID already exists, please use another.");
            request.getRequestDispatcher("/WEB-INF/Admin/AdminAddStaff.jsp").forward(request, response);
        }

        if (checkForDuplicatedEmail(email)) {
            request.setAttribute("username", username);
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("errorMsg", "Email already exists, please use another.");
            request.getRequestDispatcher("/WEB-INF/Admin/AdminAddStaff.jsp").forward(request, response);
        }

        // set new id
        TablesRecordCounter accountCounter = em.find(TablesRecordCounter.class, "ACCOUNTS");
        TablesRecordCounter billAddCounter = em.find(TablesRecordCounter.class, "BILLING_ADDRESS");
        TablesRecordCounter userCounter = em.find(TablesRecordCounter.class, "USERS");
        //set id
        newAcc.setAccountId(accountCounter.getCounter() + 1);
        newBillAddress.setAddressId(billAddCounter.getCounter() + 1);
        newUser.setUserId(userCounter.getCounter() + 1);

        accountCounter.counterIncrementByOne();
        billAddCounter.counterIncrementByOne();
        userCounter.counterIncrementByOne();

        List<TablesRecordCounter> recordsCounterList = new ArrayList<TablesRecordCounter>();
        recordsCounterList.add(accountCounter);
        recordsCounterList.add(billAddCounter);
        recordsCounterList.add(userCounter);

        //persist the records
        saveDataToDatabases(request, response, newAcc, newBillAddress, newUser, recordsCounterList);

        request.setAttribute("successMsg", "Staff ID: " + newAcc.getUsername());
        request.getRequestDispatcher("/WEB-INF/Admin/AdminAddStaff.jsp").forward(request, response);
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

    private void saveDataToDatabases(HttpServletRequest request, HttpServletResponse response, Accounts newAcc, BillingAddress newBillAddress, Users newUser, List<TablesRecordCounter> tablesRecordCounterList) throws ServletException, IOException {
        try {
            utx.begin();
            em.persist(newAcc);
            em.persist(newBillAddress);
            em.persist(newUser);
            for (TablesRecordCounter data : tablesRecordCounterList) {
                em.merge(data);
            }
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
