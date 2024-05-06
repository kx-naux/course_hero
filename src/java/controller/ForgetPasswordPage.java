package controller;

import JPAEntity.Accounts;
import JPAEntity.BillingAddress;
import JPAEntity.Users;
import entity.OtpSender;
import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

//url '/forget-pw'
public class ForgetPasswordPage extends HttpServlet {
    @PersistenceContext EntityManager em;
    @Resource UserTransaction utx;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Forward the request to home.jsp
        request.getRequestDispatcher("/WEB-INF/Client/ForgetPassword.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //check email valid
        String formType = request.getParameter("formType");
        if(formType.equals("emailForm")){
            String emailInput = request.getParameter("email");
            if(checkForExistEmail(emailInput)){
                OtpSender otpSender = new OtpSender();
                try{
                    if(otpSender.send(emailInput)){
                        //success send OTP
                        HttpSession session = request.getSession();
                        session.setAttribute("email",emailInput);
                        session.setAttribute("otp",otpSender.getOtp());
                        session.setAttribute("expTime",otpSender.getExpiryDate());
                        request.setAttribute("formPageNumber","2");
                        request.getRequestDispatcher("/WEB-INF/Client/ForgetPassword.jsp").forward(request, response);
                    }
                }catch(Exception err){
                    request.setAttribute("errType", "email");
                    request.setAttribute("errMsg", "Server Failed to send OTP. Please try again later");
                    request.setAttribute("formPageNumber","1");
                    request.getRequestDispatcher("/WEB-INF/Client/ForgetPassword.jsp").forward(request, response);
                }

            }else{
                request.setAttribute("errType", "email");
                request.setAttribute("errMsg", "This email does not exist in our databases");
                request.setAttribute("formPageNumber","1");
                request.getRequestDispatcher("/WEB-INF/Client/ForgetPassword.jsp").forward(request, response);
            }
        }else if(formType.equals("OTPForm")){
            HttpSession session = request.getSession();
            String otpInput = getOtp(request);
            String otp = (String)session.getAttribute("otp");
            if(otp.matches(otpInput)){
                request.setAttribute("formPageNumber","3");
                request.getRequestDispatcher("/WEB-INF/Client/ForgetPassword.jsp").forward(request, response);
            }else{
                request.setAttribute("errType", "otp");
                request.setAttribute("errMsg", "OTP code does not match.");
                request.setAttribute("formPageNumber","2");
                request.getRequestDispatcher("/WEB-INF/Client/ForgetPassword.jsp").forward(request, response);
            }
        }else if(formType.equals("resendOTP")){
            OtpSender otpSender = new OtpSender();
            String emailInput = (String) request.getSession().getAttribute("email");
                try{
                    if(otpSender.send(emailInput)){
                        //success send OTP
                        HttpSession session = request.getSession();
                        session.setAttribute("otp",otpSender.getOtp());
                        session.setAttribute("expTime",otpSender.getExpiryDate());
                        request.setAttribute("formPageNumber","2");
                        request.getRequestDispatcher("/WEB-INF/Client/ForgetPassword.jsp").forward(request, response);
                        return;
                    }
                }catch(Exception err){
                    request.setAttribute("errType", "otp");
                    request.setAttribute("errMsg", "Server Failed to send OTP. Please try again later");
                    request.setAttribute("formPageNumber","2");
                    request.getRequestDispatcher("/WEB-INF/Client/ForgetPassword.jsp").forward(request, response);
                }
        }else if(formType.equals("getPassword")){
            String passwordInput = request.getParameter("password");
            //retrieve account
            String emailInput = (String) request.getSession().getAttribute("email");
            Query query = em.createNamedQuery("Accounts.findByEmail").setParameter("email", emailInput);
            List<Accounts> record = (List<Accounts>)query.getResultList();
            Accounts destAcc = record.get(0);
            //set acc
            try{
                destAcc.setHashedPassword(passwordInput);
            }catch(Exception ex){
                request.setAttribute("errType","password");
                request.setAttribute("errMsg", "Server Failed to Change password. Please Try again");
                request.setAttribute("formPageNumber","3");
                request.getRequestDispatcher("/WEB-INF/Client/ForgetPassword.jsp").forward(request, response);
            }
            
            
            //save to databases
            updateDataInDatabase(destAcc,request,response);
            
            request.setAttribute("formPageNumber","4");
            request.getRequestDispatcher("/WEB-INF/Client/ForgetPassword.jsp").forward(request, response);
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
    
    private String getOtp(HttpServletRequest request){
        String otp1 = request.getParameter("otp1");
        String otp2 = request.getParameter("otp2");
        String otp3 = request.getParameter("otp3");
        String otp4 = request.getParameter("otp4");
        String otp5 = request.getParameter("otp5");
        String otp6 = request.getParameter("otp6");

        // Combine the values into one string
        return otp1 + otp2 + otp3 + otp4 + otp5 + otp6;
    }
    
    private boolean checkForExistEmail(String email){
        Query query = em.createNamedQuery("Accounts.findByEmail").setParameter("email", email);
        List<Accounts> record = (List<Accounts>)query.getResultList();
        if(record.isEmpty()){ //means is not duplicated
            return false;
        }else{
            return true;
        }
    }
}
