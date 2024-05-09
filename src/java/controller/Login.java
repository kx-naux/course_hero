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
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
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
        HttpSession session = request.getSession();
        session.removeAttribute("pageToGoAfterLogin");
        // Forward the request to home.jsp
        //request.getRequestDispatcher("/WEB-INF/Client/Login.jsp").forward(request, response);
        request.getRequestDispatcher("/WEB-INF/Client/Login.jsp").forward(request, response);
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
            //redirect to login
            session.setAttribute("loginErrorMsg","Invalid Login Credentials");
            response.sendRedirect("login");
            return;
        }else{
            //always will only has one result as the username and email must be unique in databases
            accountRetrieved = accounts.get(0);
        }
        
        //hash the password input
        try{
            hashedInputPassword = Accounts.hashPassword(password,accountRetrieved.getSalt());
        }catch(Exception e){
            //redirect to server error message
        }
        
        //check is password valid onot
        if(hashedInputPassword.equals(accountRetrieved.getSaltedpassword())){
            // valid password
            userData = getUserData(accountRetrieved);
            session.setAttribute("userData",userData);
            session.setAttribute("loginStatus","logined");
            //if at the same session second user login, we must clear the 1st user checkingOutCartItems
            session.removeAttribute("checkingOutCartItemList");
            String pageToGo = (String) session.getAttribute("pageToGoAfterLogin");
            //check remember me
            if(rememberMe!=null){
                setRmbMeToken(userData,response);
            }else{
                //remove token in cookie if exist
                removeRmbMeTokenInCookie(request,response);
            }
            
            if(pageToGo != null){
                response.sendRedirect(pageToGo);
            }else{
                response.sendRedirect("home");
                return;
            }

        }else{
            session.setAttribute("loginErrorMsg","Invalid Login Credentials");
            response.sendRedirect("login");
            //redirect to login page
        }
        
        
        // Forward the request to home.jsp
        //request.getRequestDispatcher("/WEB-INF/Client/Login.jsp").forward(request, response);
    }
    
    public static void removeRmbMeTokenInCookie(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("rmbMeToken")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }
    
    public static Users checkRmbMeToken(HttpServletRequest request,EntityManager em){
        String token = getRmbMeCookie(request);
        if(token == null){
            return null;
        }
        
        RememberMeToken obj = em.find(RememberMeToken.class, token);
        //token not found in databases
        if(obj == null){
            return null;
        }else
        {
            return obj.getUserId();
        }
    }
    
    private static String getRmbMeCookie(HttpServletRequest request){
        String rmbMeToken = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("rmbMeToken")) {
                    rmbMeToken = cookie.getValue();
                    break;
                }
            }
        }
        
        return rmbMeToken;
    }
    
    private boolean isEmail(String usernameInput){
        Matcher matcher = pattern.matcher(usernameInput);
        return matcher.matches();
    }
   
        
    
    private boolean isPasswordValid(String hashedInputPassword,String saltUserPassword){
        return hashedInputPassword.equals(saltUserPassword);
    }
    
    private Users getUserData(Accounts accountID){
        Query query = em.createNamedQuery("Users.findByAccountId").setParameter("accountId",accountID);
        List<Users> users = query.getResultList();     
            return users.get(0);
    }
    
    private void setRmbMeToken(Users user,HttpServletResponse response){
        String token = generateRmbMeToken();
        Cookie tokenCookie = new Cookie("rmbMeToken",token);
        tokenCookie.setHttpOnly(true);
        tokenCookie.setSecure(true);
        tokenCookie.setMaxAge(28*24*60*60); // set cookie exipre in 28 days
        response.addCookie(tokenCookie);
        
        RememberMeToken rmbMeToken = new RememberMeToken(token,new Date(System.currentTimeMillis()),user);
        //save to databases
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
