/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import JPAEntity.Accounts;
import JPAEntity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
// url '/user-delete-account'
public class UserDeleteAccount extends HttpServlet {
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
            session.setAttribute("pageToGoAfterLogin","user-delete-account");
            response.sendRedirect("login");
            return;
        }
        
        if(userDataSession.getUsertype().equals("Manager")||userDataSession.getUsertype().equals("Staff")){
            request.setAttribute("toastErrMsg", "Manager Unable to Perform Account Deletion");
            request.setAttribute("profilePageNumber", "1");
            request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
            return;
        }

        if (userDataSession != null) {
            Login.getUserWishlist(request, em, userDataSession);
            Login.getUserCart(request, em, userDataSession);
        }
        
        // Forward the request to Profile.jsp edit basic profile section
        request.setAttribute("profilePageNumber", "5");
        request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String password = request.getParameter("password");
        Users userData = (Users) request.getSession().getAttribute("userData");
        String hashedInputPassword = "";
        try{
            hashedInputPassword = Accounts.hashPassword(password,userData.getAccountId().getSalt());
        }catch(Exception ex){
            request.setAttribute("deleteAccErrMsg","Server Error Please Try Again");
            request.setAttribute("profilePageNumber", "5");
            request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
        }
        
        if(hashedInputPassword.equals(userData.getAccountId().getSaltedpassword())){
            //proceed delete
            request.getSession().removeAttribute("userData");
            deleteDataInDatabase(userData,request,response);
            request.setAttribute("successMsg","Account Deleted.");
            request.setAttribute("profilePageNumber", "5");
            request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
        }else{
            request.setAttribute("deleteAccErrMsg","Password does not match.");
            request.setAttribute("profilePageNumber", "5");
            request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
        }
    }
    
    private void deleteDataInDatabase(Users newUserData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            utx.begin();
            Users userData = em.find(Users.class, newUserData.getUserId());
            em.remove(userData);
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
