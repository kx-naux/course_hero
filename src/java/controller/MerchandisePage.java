package controller;

import JPAEntity.AuthorContribution;
import JPAEntity.Courses;
import JPAEntity.Merchandise;
import JPAEntity.Ratings;
import entity.UserRatingStatistic;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MerchandisePage extends HttpServlet {
   @PersistenceContext EntityManager em;
    
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null) {
            goToNotFoundErrorPage(request,response);
        }
        // get merch data
        Merchandise merchData = em.find(Merchandise.class, id);
        request.setAttribute("merchData", merchData);
        if (merchData == null) {
            goToNotFoundErrorPage(request,response);
        }
        String stockStatus = "";
        if(merchData.getProductId().getStatus().equalsIgnoreCase("Discontinued")){
            stockStatus = "Discontinued";
        }else if(merchData.getStockBalance()>0){
            stockStatus = "In Stock";
        }else if(merchData.getStockBalance()<10){
            stockStatus = "Low Stock Balance";
        }else if(merchData.getStockBalance()<1){
            stockStatus = "Out of Stock";
        }
        
        request.setAttribute("stockStatus",stockStatus);
        
        
        //get number of ratings
        Query getRateCountQuery = em.createNamedQuery("Ratings.findRatingCountByProdId").setParameter("productId",merchData.getProductId());
        long ratingCount = (long)getRateCountQuery.getSingleResult();
        request.setAttribute("ratingCount",ratingCount);
        
        //pass rating data to jsp based on pagination
        //get number of courses recorded
        int reqPage = 1;
        int maxDataInPage=2;
        long lastPage = (ratingCount-1)/Long.parseLong(String.valueOf(maxDataInPage)) + 1;
        if (request.getParameter("p") != null) {
            reqPage = Integer.parseInt(request.getParameter("p"));
            if(reqPage > lastPage){
                goToCustomErrorPage(request,response,"Invalid URL");
                }
            }

        int offset = (reqPage-1)*maxDataInPage;

        Query getRatings = em.createNamedQuery("Ratings.findRatingByProdIdSortDescPriKey").setParameter("productId",merchData.getProductId());
        getRatings.setFirstResult(offset);
        getRatings.setMaxResults(maxDataInPage);
        List<Ratings> ratingsList = getRatings.getResultList();
        request.setAttribute("ratingList", ratingsList);
        //Query getRatings = em.createNamedQuery("Ratings.findRatingByProdIdSortDescPriKey").setParameter("productId",merchData.getProductId());
        //List<Ratings> ratingsList = getRatings.getResultList();
        //request.setAttribute("ratingList", ratingsList);
        
        //get rating statistic
        List<Long> starCountsAsc = new ArrayList<>();
        Query getRatingStatsBasedOnStarQuery = em.createNamedQuery("Ratings.findRatingCountByProdIdAndScore");
        getRatingStatsBasedOnStarQuery.setParameter("productId", merchData.getProductId());
        
        Long result = null;
        for(int i = 1;i<6;i++){
            getRatingStatsBasedOnStarQuery.setParameter("score", i);
            getRatingStatsBasedOnStarQuery.getSingleResult();
            result = new Long((long)getRatingStatsBasedOnStarQuery.getSingleResult());
            starCountsAsc.add(result);
        }  
        
        UserRatingStatistic rateStats = new UserRatingStatistic(starCountsAsc.get(4),starCountsAsc.get(3),starCountsAsc.get(2),starCountsAsc.get(1),starCountsAsc.get(0));
        request.setAttribute("rateStats", rateStats);
        
        //get number of sold
        Query getTtlSoldQuery = em.createNamedQuery("Orders.findCountByProductId").setParameter("productId",merchData.getProductId().getProductId());
        long ttlSold = (long)getTtlSoldQuery.getSingleResult();
        request.setAttribute("totalSold", ttlSold);
        
        
        // Forward the request to Course.jsp
        request.getRequestDispatcher("/WEB-INF/Client/Merchandise.jsp").forward(request, response);
    }
    
    private void goToNotFoundErrorPage(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        HttpSession session = request.getSession();
        session.setAttribute("type", "Merchandise");
        session.setAttribute("param", "Merchandise ID");
        session.setAttribute("errorDetail", "Your search has ventured beyond the known universe.");
            
        // Forward the request to error page
        request.getRequestDispatcher("/WEB-INF/Client/NotFoundError.jsp").forward(request, response);
    }
    
    private void goToCustomErrorPage(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException{
        HttpSession session = request.getSession();
        session.setAttribute("errorMessage", errorMessage);
        session.setAttribute("errorDetail", "Your search has ventured beyond the known universe.");    
        
        // Forward the request to  error page
        request.getRequestDispatcher("/WEB-INF/Client/CustomError.jsp").forward(request, response);
    }
}
