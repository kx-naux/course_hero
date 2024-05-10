/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import JPAEntity.Accounts;
import JPAEntity.Users;
import entity.OtpSender;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author User
 */
// url 'edit-security'
public class EditUserSecurity extends HttpServlet {

    @PersistenceContext EntityManager em;
    @Resource UserTransaction utx;

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
            session.setAttribute("pageToGoAfterLogin","edit-security");
            response.sendRedirect("login");
            return;
        }
        
        if (userDataSession != null) {
            Login.getUserWishlist(request, em, userDataSession);
            Login.getUserCart(request, em, userDataSession);
        }
        
        // Forward the request to Profile.jsp edit basic profile section
        request.setAttribute("profilePageNumber", "4");
        request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get which form submission
        HttpSession session = request.getSession();
        
        String formType = request.getParameter("formType");
        String test = "";
        if(formType.equals("ChangeEmail")){
            String newEmail = request.getParameter("email");
            
            //check duplicated email
            if(checkForDuplicatedEmail(newEmail)){
                request.setAttribute("emailInput",newEmail);
                request.setAttribute("emailErrMsg","This email already exists in our databases");
                request.setAttribute("profilePageNumber", "4");
                request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
            }
            
            //send OTP
            OtpSender otpSender = new OtpSender();
            try{
                if(otpSender.send(newEmail)){ //if success
                    //store OTP and data in session scope
                    session.setAttribute("newEmailToChangeBuffer",newEmail);
                    session.setAttribute("changeUserDataOtp", otpSender.getOtp());
                    session.setAttribute("changeUserDataOtpExpTime",otpSender.getExpiryDate());
                    //direct to OTP page
                    request.setAttribute("otpForWhichForm","ChangeEmail");
                    request.setAttribute("showOTPForm","1");
                    request.setAttribute("profilePageNumber", "4");
                    request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
                }else{
                    //show error
                    request.setAttribute("emailInput",newEmail);
                    request.setAttribute("emailErrMsg","Server Error Fail To Send OTP Please Try Again");
                    request.setAttribute("profilePageNumber", "4");
                    request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
                }
            }catch(InterruptedException otpEx){
                request.setAttribute("emailInput",newEmail);
                request.setAttribute("emailErrMsg","Server Error Fail To Send OTP Please Try Again");
                request.setAttribute("profilePageNumber", "4");
                request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
            }catch(IOException otpIOExp){
                request.setAttribute("emailInput",newEmail);
                request.setAttribute("emailErrMsg","Server Error Fail To Send OTP Please Try Again");
                request.setAttribute("profilePageNumber", "4");
                request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
                return;
            }
            
        }else if(formType.equals("ChangePassword")){
            String password = request.getParameter("password");
            Users userData = (Users) session.getAttribute("userData");
            //send OTP
            OtpSender otpSender = new OtpSender();
            try{
                if(otpSender.send(userData.getAccountId().getEmail())){ //if success
                    //store OTP and data in session scope
                    session.setAttribute("newPasswordToChangeBuffer",password);
                    session.setAttribute("changeUserDataOtp", otpSender.getOtp());
                    session.setAttribute("changeUserDataOtpExpTime",otpSender.getExpiryDate());
                    //direct to OTP page
                    request.setAttribute("otpForWhichForm","ChangePassword");
                    request.setAttribute("showOTPForm","1");
                    request.setAttribute("profilePageNumber", "4");
                    request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
                }else{
                    //show error
                    //request.setAttribute("emailInput",newEmail);
                    request.setAttribute("passErrMsg","Server Error Fail To Send OTP Please Try Again");
                    request.setAttribute("profilePageNumber", "4");
                    request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
                }
            }catch(InterruptedException otpEx){
                //request.setAttribute("emailInput",newEmail);
                request.setAttribute("passErrMsg","Server Error Fail To Send OTP Please Try Again");
                request.setAttribute("profilePageNumber", "4");
                request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
            }catch(IOException otpIOExp){
                //request.setAttribute("emailInput",newEmail);
                request.setAttribute("passErrMsg","Server Error Fail To Send OTP Please Try Again");
                request.setAttribute("profilePageNumber", "4");
                request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
                return;
            }
            
        }else if(formType.equals("OTPSubmission")){
            //get the opt and expiry date of the otp
            String otpForWhichForm = (String) request.getParameter("otpForWhichForm");
            String otp = (String) session.getAttribute("changeUserDataOtp");
            Date otpExpTime = (Date) session.getAttribute("changeUserDataOtpExpTime");
            String otpInput = request.getParameter("otp");
            boolean isOtpExpired = false;
            
            if(otp.equals(otpInput) && !isOtpExpired){
                if(otpForWhichForm.equals("ChangeEmail")){
                    String newEmail = (String) session.getAttribute("newEmailToChangeBuffer");
                    Users userData = (Users) session.getAttribute("userData");
                    userData.getAccountId().setEmail(newEmail);
                    updateDataInDatabase(userData.getAccountId(),request,response);
                
                    //update userdata in session
                    session.setAttribute("userData", userData);
                    session.removeAttribute("newEmailToChangeBuffer");
                    request.setAttribute("successMsg", "Email Changed");
                    request.setAttribute("showOTPForm","0");
                    request.setAttribute("profilePageNumber","4");
                    request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
                    return;
                }else if(otpForWhichForm.equals("ChangePassword")){
                    String password = (String) session.getAttribute("newPasswordToChangeBuffer");
                    Users userData = (Users) session.getAttribute("userData");
                    try{
                        userData.getAccountId().setHashedPassword(password);
                    }catch(Exception ex){
                        request.setAttribute("passErrMsg","Server Error in hashing the variable");
                        request.setAttribute("profilePageNumber", "4");
                        request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
                        return;
                    }
                    
                    updateDataInDatabase(userData.getAccountId(),request,response);
                    
                    //update userdata in session
                    session.setAttribute("userData", userData);
                    session.removeAttribute("newEmailToChangeBuffer");
                    request.setAttribute("successMsg", "Password Changed");
                    request.setAttribute("showOTPForm","0");
                    request.setAttribute("profilePageNumber","4");
                    request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
                    return;
                }
            }else{
                request.setAttribute("otpForWhichForm",otpForWhichForm);
                request.setAttribute("otpErrMsg", "Invalid OTP");
                request.setAttribute("showOTPForm","1");
                request.setAttribute("profilePageNumber", "4");
                request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
            }
            
                
      
            
            
        }
        
        
        
        
    }
    
    private void updateDataInDatabase(Accounts newAccData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            utx.begin();
            em.merge(newAccData);
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
    
    private boolean checkForDuplicatedEmail(String email){
        Query query = em.createNamedQuery("Accounts.findByEmail").setParameter("email", email);
        List<Accounts> record = (List<Accounts>)query.getResultList();
        if(record.isEmpty()){ //means is not duplicated
            return false;
        }else{
            return true;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
