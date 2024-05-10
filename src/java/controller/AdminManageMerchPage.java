package controller;

import JPAEntity.AuthorContribution;
import JPAEntity.MerchCategory;
import JPAEntity.Merchandise;
import JPAEntity.Users;
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
        Users userDataSession = (Users) request.getSession().getAttribute("userData");
        Users userData = Login.checkRmbMeToken(request, em);
        //check has rmb token onot
        if(userData != null){
            HttpSession session = request.getSession();
            session.setAttribute("userData",userData);
            Login.getUserWishlist(request, em, userData);
            Login.getUserCart(request, em, userData);
        //check has user logged in
        }else if(userDataSession == null){
            HttpSession session = request.getSession();
            session.setAttribute("pageToGoAfterLogin","admin/manage-merch");
            response.sendRedirect("../login");
            return;
        }
        
        if (userDataSession != null) {
            Login.getUserWishlist(request, em, userDataSession);
            Login.getUserCart(request, em, userDataSession);
        }
        
        //get userData from session as the user can login thru the rmbMe
        Users checkUserAccess = (Users) request.getSession().getAttribute("userData");
        if(checkUserAccess.getUsertype().equals("Customer")){
            ErrorPage.forwardToServerErrorPage(request, response, "Authorized Access Only ! ! !");
        }
        
        
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

