/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import JPAEntity.Users;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author User
 */
//url "/update-user-pfp"
@MultipartConfig
public class UpdateUserPFP extends HttpServlet {

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
        //check has user logged in
        }else if(userDataSession.getUserId() == null){
            HttpSession session = request.getSession();
            session.setAttribute("pageToGoAfterLogin","update-user-pfp");
            response.sendRedirect("login");
            return;
        }

        // Forward the request to Profile.jsp edit basic profile section
        request.setAttribute("profilePageNumber", "3");
        request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("profilePic");
        //String fileName = filePart.getSubmittedFileName();
        
        //get the user data
        HttpSession session = request.getSession();
        Users userData = (Users) session.getAttribute("userData");
        byte[] imgData = null;
        if (filePart.getSize()!= 0) {      
            imgData = readFileData(filePart);
            userData.setImgData(imgData);
            
            updateDataInDatabase(userData,request,response);
        
            //update userdata in session
            session.setAttribute("userData", userData);
        
            //show success
            request.setAttribute("successMsg", "User Profile Picture Updated");
            request.setAttribute("profilePageNumber","3");
            request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
        } else {
            //fail to save data
            request.setAttribute("errMsg","Server fails to upload your files. Please try again.");
            request.setAttribute("profilePageNumber","3");
            request.getRequestDispatcher("/WEB-INF/Client/Profile.jsp").forward(request, response);
        }
        
        
    }
    
    private byte[] readFileData(Part filePart) throws IOException {
        try (InputStream inputStream = filePart.getInputStream()) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[1096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            return outputStream.toByteArray();
        }
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
