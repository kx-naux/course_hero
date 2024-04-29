/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import JPAEntity.Accounts;
import JPAEntity.RememberMeToken;
import JPAEntity.Users;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author User
 */
public class Login extends HttpServlet {
    @PersistenceContext EntityManager em;
    @Resource UserTransaction utx;

    //Email Regex Patter to check the input is email or username
    String emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    Pattern pattern = Pattern.compile(emailPattern);
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        
        // Forward the request to home.jsp
        //request.getRequestDispatcher("/WEB-INF/Client/Login.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        
        // check is user login, if yes goto home page else login page
        List<Accounts> accounts; //to store result
        Accounts accountRetrieved = null;
        String hashedInputPassword = "";
        Users userData;
        HttpSession session = request.getSession();
        
        //get data from forms
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        
        //get the account record based on username/email
        if(isEmail(username)){
            Query qry = em.createNamedQuery("Accounts.findByEmail").setParameter("email",username);
            accounts = qry.getResultList();
        }else{  
            Query qry = em.createNamedQuery("Accounts.findByUsername").setParameter("username",username);
            accounts = qry.getResultList();
        }
        
        //check is account record found
        if(accounts.isEmpty()){
            //redirect to error
            response.sendRedirect("LoginError.jsp");
            return;
        }else{
            //always will only has one result as the username and email must be unique in databases
            accountRetrieved = accounts.get(0);
            response.sendRedirect("LoginError.jsp");
        }
        
        //hash the password input
        try{
            hashedInputPassword = Accounts.hashPassword(password,accountRetrieved.getSalt());
        }catch(Exception e){
            //redirect to server error message
        }
        
        //check is password valid onot
        if(hashedInputPassword == accountRetrieved.getSaltedpassword()){
            userData = getUserData(accountRetrieved.getAccountId());
            session.setAttribute("userData",userData);
            //check remember me
            if(rememberMe == "rememberMe"){
                setRmbMeToken(userData);
            }
            response.sendRedirect("Home.jsp");
        }else{
            response.sendRedirect("LoginError.jsp");

            //redirect to login page
        }
        
        
        // Forward the request to home.jsp
        //request.getRequestDispatcher("/WEB-INF/Client/Login.jsp").forward(request, response);
    }

        private boolean isEmail(String usernameInput){
        Matcher matcher = pattern.matcher(usernameInput);
        return matcher.matches();
    }
    
    private boolean isPasswordValid(String hashedInputPassword,String saltUserPassword){
        return hashedInputPassword.equals(saltUserPassword);
    }
    
    private Users getUserData(String accountID){
        Query query = em.createNamedQuery("Users.findByAccountId").setParameter("accountId",accountID);
        List<Users> users = query.getResultList();     
        return users.get(0);
    }
    
    private void setRmbMeToken(Users user){
        String token = generateRmbMeToken();
        Cookie cookie = new Cookie("rmbMeToken",token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(28*24*60*60); // set cookie exipre in 28 days
        RememberMeToken rmbMeToken = new RememberMeToken(token,new Date(System.currentTimeMillis()),user);
        
        //safe to databases
        try{
            utx.begin();
            em.persist(rmbMeToken);
            utx.commit();
        }catch(Exception e){
            try{
                if (utx.getStatus() == Status.STATUS_ACTIVE) {
                try {
                    utx.rollback();
                } catch (SystemException ex) {
                    // Handle rollback exception
                    System.err.println("Error during rollback: " + ex.getMessage());
                }
            }
            }catch (SystemException ex){
                
            }
            
        }
        
    }
    
    private String generateRmbMeToken(){
        SecureRandom secureRandom = new SecureRandom();
        
        byte[] tokenBytes = new byte[16];
        secureRandom.nextBytes(tokenBytes);
        
        String token = Base64.getEncoder().encodeToString(tokenBytes);
        
        return token;
    }
}
