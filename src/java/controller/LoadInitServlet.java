/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import JPAEntity.Keyword;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wooyu
 */
public class LoadInitServlet extends HttpServlet {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();

        String companyName = "Missing Company Name";
        String copyright = "Missing Copyright";
        String contactLocation = "Missing Location";
        String contactNo = "Missing Contact Number";
        String contactEmail = "Missing Email";
        String companyLogo = "Missing Company Logo";
        String companyIcon = "Missing Company Icon";

        if (context.getInitParameter("companyName") != null) {
            companyName = context.getInitParameter("companyName");
        }
        if (context.getInitParameter("copyright") != null) {
            copyright = context.getInitParameter("copyright");
        }
        if (context.getInitParameter("contactLocation") != null) {
            contactLocation = context.getInitParameter("contactLocation");
        }
        if (context.getInitParameter("contactNo") != null) {
            contactNo = context.getInitParameter("contactNo");
        }
        if (context.getInitParameter("contactEmail") != null) {
            contactEmail = context.getInitParameter("contactEmail");
        }
        if (context.getInitParameter("companyLogo") != null) {
            companyLogo = context.getInitParameter("companyLogo");
        }
        if (context.getInitParameter("companyIcon") != null) {
            companyIcon = context.getInitParameter("companyIcon");
        }
        
        Query query = em.createNamedQuery("Keyword.findAll");
        query.setMaxResults(5);
        List<Keyword> allKeywords = query.getResultList();

        context.setAttribute("companyName", companyName);
        context.setAttribute("copyright", copyright);
        context.setAttribute("contactLocation", contactLocation);
        context.setAttribute("contactNo", contactNo);
        context.setAttribute("contactEmail", contactEmail);
        context.setAttribute("companyLogo", companyLogo);
        context.setAttribute("companyIcon", companyLogo);
        context.setAttribute("allKeywords", allKeywords);
    }

}
