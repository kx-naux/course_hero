package controller;

import JPAEntity.Accounts;
import JPAEntity.BillingAddress;
import JPAEntity.TablesRecordCounter;
import JPAEntity.Users;
import entity.LoginFormData;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@WebServlet(name = "Sign Up Page", urlPatterns = {"/sign-up"})
public class SignUpPage extends HttpServlet {
    @PersistenceContext EntityManager em;
    @Resource UserTransaction utx;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // check is user login, if yes goto home page else sign up page
        
        
        // Forward the request to login.jsp
        request.getRequestDispatcher("/WEB-INF/Client/SignUp.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // get all of the data first
        LoginFormData formData = new LoginFormData();
        
        formData.setEmail(request.getParameter("email"));
        formData.setUsername(request.getParameter("username"));
        formData.setDisplayName(request.getParameter("name"));
        formData.setGender(request.getParameter("gender"));
        formData.setAddressLine1(request.getParameter("address1"));
        formData.setAddressLine2(request.getParameter("address2"));
        formData.setCity(request.getParameter("city"));
        formData.setPostalCode(request.getParameter("postalCode"));
        formData.setState(request.getParameter("state"));
        formData.setCountry(request.getParameter("country"));
        formData.setDob(new Date());
        String password = request.getParameter("password");
        
        //set empty value for address to prevent store null value in databases
        if(formData.getAddressLine1() == null){
            formData.setAddressLine1("");
        }
        if(formData.getAddressLine2() == null){
            formData.setAddressLine2("");
        }
        if(formData.getCity() == null){
            formData.setCity("");
        }
        if(formData.getPostalCode() == null){
            formData.setPostalCode("");
        }
        if(formData.getState() == null){
            formData.setState("");
        }
        if(formData.getCountry() == null){
             formData.setCountry("");
        }
        
        boolean duplicatedUsername = checkForDuplicatedUsername(formData.getUsername());
        boolean duplicatedEmail = checkForDuplicatedEmail(formData.getEmail());
        if(duplicatedUsername && duplicatedEmail){
            request.setAttribute("loginFormData",formData);
            forwardAndShowErrorOnSignUpPage(request,response,"1","email&username","Email and Username already exists, please use another.");
        }else if(duplicatedUsername){
            request.setAttribute("loginFormData",formData);
            forwardAndShowErrorOnSignUpPage(request,response,"1","email","Email already exists, please use another.");
        }else if(duplicatedEmail){
            request.setAttribute("loginFormData",formData);
            forwardAndShowErrorOnSignUpPage(request,response,"1","username","Username already exist, please use another username");
        }
       
        //create new accounts objct into databases
        Accounts newAcc = new Accounts();
        try{
            newAcc = new Accounts(formData.getUsername(),formData.getEmail());
            newAcc.setHashedPassword(password);
            
        }catch(NoSuchAlgorithmException ex){
            ErrorPage.forwardToServerErrorPage(request,response,"Server Passoword Hashing Error. Please Try Again Later");
        }
        
        //create new billing address obj
        BillingAddress newBillAddress = new BillingAddress(formData.getAddressLine1(),formData.getCity(),formData.getState(),formData.getPostalCode(),formData.getCountry());
        newBillAddress.setLine2(formData.getAddressLine2());
        
        // create new user object
        Users newUser = new Users(formData.getDisplayName(),formData.getDob(),"Customer",new Date(),formData.getGender(),newAcc,newBillAddress);
        
        //set id for each of the entity so that they can be stored in databases
            //get all of the current counts
        TablesRecordCounter accountCounter = em.find(TablesRecordCounter.class,"ACCOUNTS");
        TablesRecordCounter billAddCounter = em.find(TablesRecordCounter.class,"BILLING_ADDRESS");
        TablesRecordCounter userCounter = em.find(TablesRecordCounter.class,"USERS");
            //set id
        newAcc.setAccountId(accountCounter.getCounter() + 1);
        newBillAddress.setAddressId(billAddCounter.getCounter()+1);
        newUser.setUserId(userCounter.getCounter()+1);
        
            //add one to each counter
        accountCounter.counterIncrementByOne();
        billAddCounter.counterIncrementByOne();
        userCounter.counterIncrementByOne();
        
        List<TablesRecordCounter> recordsCounterList = new ArrayList<TablesRecordCounter>();
        recordsCounterList.add(accountCounter);
        recordsCounterList.add(billAddCounter);
        recordsCounterList.add(userCounter);
        
        //persist the records
        saveDataToDatabases(request, response, newAcc,newBillAddress, newUser,recordsCounterList);
                  
        // forward the request to SignUp.jsp complete section
        request.setAttribute("pageNumber", "4");
        request.getRequestDispatcher("/WEB-INF/Client/SignUp.jsp").forward(request, response);
    }
    
    private boolean checkForDuplicatedEmail(String email){
        Query query = em.createNamedQuery("Accounts.findByEmail").setParameter("email", email);
        List<Accounts> record = (List<Accounts>)query.getResultList();
        if(record.isEmpty()){ //means is not duplicated
            return false;
        }else{
            return true;
        }
    }
    
    private boolean checkForDuplicatedUsername(String username){
        Query query = em.createNamedQuery("Accounts.findByUsername").setParameter("username", username);
        List<Accounts> record = (List<Accounts>)query.getResultList();
        if(record.isEmpty()){ //means is not duplicated
            return false;
        }else{
            return true;
        }
    }
        
    
    private void forwardAndShowErrorOnSignUpPage(HttpServletRequest request, HttpServletResponse response,String pageNumber,String errField, String errMsg)
        throws ServletException, IOException{
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("errField",errField);
        request.setAttribute("errMsg", errMsg);
        
        request.getRequestDispatcher("/WEB-INF/Client/SignUp.jsp").forward(request, response);
    }
    
    private void saveDataToDatabases(HttpServletRequest request, HttpServletResponse response, Accounts newAcc, BillingAddress newBillAddress, Users newUser, List<TablesRecordCounter> tablesRecordCounterList) throws ServletException, IOException{      
        try{
            utx.begin();
            em.persist(newAcc);
            em.persist(newBillAddress);
            em.persist(newUser);
            for(TablesRecordCounter data: tablesRecordCounterList){
                em.merge(data);
            }
            utx.commit();
        }catch(Exception ex){
            try{
            if (utx.getStatus() == Status.STATUS_ACTIVE) {
                try {
                    utx.rollback();
                }catch (SystemException ex2) {
                    //server error page
                    ErrorPage.forwardToServerErrorPage(request,response,"Database Server Error. Please Try Again Later");
                }
            }
            }catch (SystemException ex2){
                ErrorPage.forwardToServerErrorPage(request,response,"Database Server Error. Please Try Again Later");
            }
            ErrorPage.forwardToServerErrorPage(request,response,"Database Server Error. Please Try Again Later");
        }
    }
    
}
