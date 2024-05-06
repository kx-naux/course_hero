package controller;

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

public class EditProfilePage extends HttpServlet {
    @PersistenceContext EntityManager em;
    @Resource UserTransaction utx;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users userData = Login.checkRmbMeToken(request, em);
        if(userData != null){
            HttpSession session = request.getSession();
            session.setAttribute("userData",userData);
        }else{
            //Statements
        }
        // Forward the request to Profile.jsp edit basic profile section
        request.setAttribute("profilePageNumber", "1");
        request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String newName = (String) request.getParameter("name");
        String newGender = (String) request.getParameter("gender");
        String dobStr = (String) request.getParameter("dob");
        
    //get Date in Date type
        Date newDob = null;
        if(dobStr != null){
            try {
                newDob = dateFormat.parse(dobStr);
            } catch (java.text.ParseException e) {
                ErrorPage.forwardToServerErrorPage(request,response,"Server Error - Pass Date data type error. Please Try Again Later");           
            }
        }
        
        //get user object
        HttpSession session = request.getSession();
        Users userData = (Users) session.getAttribute("userData");
        
        userData.setDisplayName(newName);
        userData.setGender(newGender);
        userData.setDob(newDob);
        
        updateDataInDatabase(userData,request,response);
        
        //update userdata in session
        session.setAttribute("userData", userData);
        
        //show success
        request.setAttribute("successMsg", "User Profile Updated");
        request.setAttribute("profilePageNumber","1");
        request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
    }
    
    private void updateDataInDatabase(Users newUserData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            utx.begin();
            em.merge(newUserData);
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

