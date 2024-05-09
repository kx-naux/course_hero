package controller;


import JPAEntity.MerchCategory;
import JPAEntity.Product;
import JPAEntity.Merchandise;
import JPAEntity.Users;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Merchandises", urlPatterns = {"/merchandises"})
public class Merchandises extends HttpServlet {
    @PersistenceContext EntityManager em;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                Users userDataSession = (Users) request.getSession().getAttribute("userData");
        Users userData = Login.checkRmbMeToken(request, em);
        if (userData != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userData", userData);
            Login.getUserWishlist(request, em, userData);
            Login.getUserCart(request, em, userData);
        }
        if (userDataSession != null) {
            Login.getUserWishlist(request, em, userDataSession);
            Login.getUserCart(request, em, userDataSession);
        }
        
            // Retrieve all merchandise categories
            List<MerchCategory> merchCategories = em.createNamedQuery("MerchCategory.findAll").getResultList();

            // Map to store merchandise items for each category
            Map<String, List<Merchandise>> merchItemsByCategory = new HashMap<>();

            // Iterate through each merchandise category
            for (MerchCategory eachMerchcategory : merchCategories) {
            // Retrieve merchandise items associated with the current category
            List<Merchandise> merchItems = em.createNamedQuery("Merchandise.findByMerchCatId")
                                           .setParameter("merchcatId", eachMerchcategory)
                                           .getResultList();
            // Add the list of merchandise items to the map
            merchItemsByCategory.put(eachMerchcategory.getMerchcatId(), merchItems);
            }

            // Set the map as a request attribute to make it available in the JSP
            request.setAttribute("merchItemsByCategory", merchItemsByCategory);
             
//              List<String> merchCatIds = new ArrayList<>();
//                merchCatIds.add("MC0000001");
//                merchCatIds.add("MC0000002");
//                merchCatIds.add("MC0000003");
//                merchCatIds.add("MC0000004");
//                merchCatIds.add("MC0000005");
//                
//                Map<String, List<Merchandises>> merchandiseByCategoryMap = new HashMap<>();
//                
//                for (String merchCatId : merchCatIds) {
//                    List<Merchandises> merchandiseListByCategory = em.createNamedQuery("Merchandise.findByMerchCatId")
//                            .setParameter("merchCatId", merchCatId)
//                            .getResultList();
//                    merchandiseByCategoryMap.put(merchCatId, merchandiseListByCategory);
//                }
//                request.setAttribute("merchandiseByCategoryMap", merchandiseByCategoryMap); 
                

       

        request.getRequestDispatcher("/WEB-INF/Client/Merchandises.jsp").forward(request, response);
    }
}
