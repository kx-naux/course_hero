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
        // Forward the request to home.jsp
        request.getRequestDispatcher("/WEB-INF/Admin/AdminAddStaff.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        HttpSession session = request.getSession();

        // validation check
        validateData(request, response);

        request.getRequestDispatcher("/WEB-INF/Admin/AdminAddStaff.jsp").forward(request, response);
    }

    private void validateData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get all of the data first
        LoginFormData formData = getFormData(request, response);
        
        HttpSession session = request.getSession();

        session.setAttribute("signUpFormDetails", formData);

        boolean duplicatedUsername = checkForDuplicatedUsername(formData.getUsername());
        boolean duplicatedEmail = checkForDuplicatedEmail(formData.getEmail());
        if (duplicatedUsername && duplicatedEmail) {
            request.setAttribute("signUpFormDetails", formData);
            forwardAndShowErrorOnSignUpPage(request, response, "Email and Username already exists, please use another.");
        } else if (duplicatedEmail) {
            request.setAttribute("signUpFormDetails", formData);
            forwardAndShowErrorOnSignUpPage(request, response, "Email already exists, please use another.");
        } else if (duplicatedUsername) {
            request.setAttribute("signUpFormDetails", formData);
            forwardAndShowErrorOnSignUpPage(request, response, "Staff ID already exist, please use another username");
        }

        processDataForDatabaseAndSaveIt(request, response);
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

    private void forwardAndShowErrorOnSignUpPage(HttpServletRequest request, HttpServletResponse response, String errMsg)
            throws ServletException, IOException {
        request.setAttribute("errorMsg", errMsg);

        request.getRequestDispatcher("/WEB-INF/Client/AdminAddStaff.jsp").forward(request, response);
    }

    private LoginFormData getFormData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");

        // get all of the data first
        LoginFormData formData = new LoginFormData();

        formData.setEmail(request.getParameter("email"));
        formData.setUsername(request.getParameter("username"));
        formData.setDisplayName(request.getParameter("name"));
        formData.setGender("");
        formData.setAddressLine1("");
        formData.setAddressLine2("");
        formData.setCity("");
        formData.setPostalCode("");
        formData.setState("");
        formData.setCountry("");
        formData.setPassword("coursehero");

        String dobStr = "2000-01-01";
        formData.setDobStr(dobStr);

        //get Date
        Date dob = null;
        if (dobStr != null) {
            try {
                dob = dateFormat.parse(dobStr);
            } catch (java.text.ParseException e) {
                ErrorPage.forwardToServerErrorPage(request, response, "Server Error - Pass Date data type error. Please Try Again Later");
            }
        }
        formData.setDob(dob);

        return formData;
    }

    private void processDataForDatabaseAndSaveIt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //create new accounts objct into databases
        HttpSession session = request.getSession();
        LoginFormData formData = (LoginFormData) session.getAttribute("signUpFormDetails");
        String password = "coursehero";
        session.removeAttribute("SignUpPassword");
        Accounts newAcc = new Accounts();
        try {
            newAcc = new Accounts(formData.getUsername(), formData.getEmail());
            newAcc.setHashedPassword(password);

        } catch (NoSuchAlgorithmException ex) {
            ErrorPage.forwardToServerErrorPage(request, response, "Server Passoword Hashing Error. Please Try Again Later");
        }

        //create new billing address obj
        BillingAddress newBillAddress = new BillingAddress(formData.getAddressLine1(), formData.getCity(), formData.getState(), formData.getPostalCode(), formData.getCountry());
        newBillAddress.setLine2(formData.getAddressLine2());

        // create new user object
        Users newUser = new Users(formData.getDisplayName(), formData.getDob(), "Customer", new Date(), formData.getGender(), newAcc, newBillAddress);

        //set id for each of the entity so that they can be stored in databases
        //get all of the current counts
        TablesRecordCounter accountCounter = em.find(TablesRecordCounter.class, "ACCOUNTS");
        TablesRecordCounter billAddCounter = em.find(TablesRecordCounter.class, "BILLING_ADDRESS");
        TablesRecordCounter userCounter = em.find(TablesRecordCounter.class, "USERS");
        //set id
        newAcc.setAccountId(accountCounter.getCounter() + 1);
        newBillAddress.setAddressId(billAddCounter.getCounter() + 1);
        newUser.setUserId(userCounter.getCounter() + 1);

        //add one to each counter
        accountCounter.counterIncrementByOne();
        billAddCounter.counterIncrementByOne();
        userCounter.counterIncrementByOne();

        List<TablesRecordCounter> recordsCounterList = new ArrayList<TablesRecordCounter>();
        recordsCounterList.add(accountCounter);
        recordsCounterList.add(billAddCounter);
        recordsCounterList.add(userCounter);

        //persist the records
        saveDataToDatabases(request, response, newAcc, newBillAddress, newUser, recordsCounterList);
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
