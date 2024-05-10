package controller;

import JPAEntity.AuthorContribution;
import JPAEntity.MerchCategory;
import JPAEntity.Merchandise;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@WebServlet(name = "Admin Manage Merch", urlPatterns = {"/admin/manage-merch"})
public class AdminManageMerchPage extends HttpServlet {
    
    @PersistenceContext
    EntityManager em;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //To get all Merchandise Record
        Query merchandiseListQry = em.createNamedQuery("Merchandise.findAll");
        List<Merchandise> merchandiseList = merchandiseListQry.getResultList();
        request.setAttribute("merchandiseList",  merchandiseList);
       
        
        //To get all Merchandise Category
        Query merchandiseCatListQry = em.createNamedQuery("MerchCategory.findAll");
        List<MerchCategory> merchandiseCatList = merchandiseCatListQry.getResultList();
        request.setAttribute("merchandiseCatList", merchandiseCatList);
        
        /*
        long noOfMerchandises = (long)merchandiseList.size();
        request.setAttribute("noOfMerchandise", noOfMerchandises);
        
        //pagination
        int reqPage = 1;
        int maxDataInPage = 2;
        long lastPage = (noOfMerchandises-1)/Long.parseLong(String.valueOf(maxDataInPage)) + 1;
                if (request.getParameter("p") != null) {
                    reqPage = Integer.parseInt(request.getParameter("p"));
                    if(reqPage > lastPage){
                    }
                }

                int offset = (reqPage-1)*maxDataInPage;

                Query query = em.createNamedQuery("AuthorContribution.findByAuthorIdWhereCourseIsAvailable").setParameter("authorId", author);
                query.setFirstResult(offset);
                query.setMaxResults(maxDataInPage);
                List<AuthorContribution> authorNCourses = query.getResultList();
                List<JPAEntity.Merchandise> merchandises = new ArrayList<>();
                if(!authorNCourses.isEmpty()){  
                    for(AuthorContribution authContri:authorNCourses){
                        authCourses.add(authContri.getCourseId());
                    }
                }
        */
        // Forward the request to AdminManageMerch.jsp
        request.getRequestDispatcher("/WEB-INF/Admin/AdminManageMerch.jsp").forward(request, response);
        
    }
}

