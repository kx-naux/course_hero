/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import JPAEntity.Authors;
import JPAEntity.CourseSubscriptions;
import JPAEntity.Courses;
import entity.AboutUsStats;
import entity.CourseCategory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
public class AboutUs extends HttpServlet {
    @PersistenceContext EntityManager em;
    
    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        ServletContext context = getServletContext();
        AboutUsStats aboutUsStats = getAboutUsStats();
        context.setAttribute("aboutUsStats", aboutUsStats);
    }

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
        
        // Forward the request to AboutUs.jsp
        request.getRequestDispatcher("/WEB-INF/Client/AboutUs.jsp").forward(request, response);
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
        
    }
    
    private AboutUsStats getAboutUsStats(){
        List<CourseSubscriptions> courseSubsList = em.createNamedQuery("CourseSubscriptions.findAll").getResultList();
        List<Authors> authorsList = em.createNamedQuery("Authors.findAll").getResultList();
        List<Courses> coursesList = em.createNamedQuery("Courses.findAll").getResultList();
        List<CourseCategory> categoryList = em.createNamedQuery("CourseCategory.findAll").getResultList();
        
        AboutUsStats stats = new AboutUsStats();
        
        stats.addStatistic("Learners",courseSubsList.size());
        stats.addStatistic("Authors",authorsList.size());
        stats.addStatistic("Courses",coursesList.size());
        stats.addStatistic("Categories",categoryList.size());
        
        return stats;
    }

   

}
