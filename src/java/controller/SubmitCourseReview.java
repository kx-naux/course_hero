/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import JPAEntity.Accounts;
import JPAEntity.BillingAddress;
import JPAEntity.Courses;
import JPAEntity.Product;
import JPAEntity.Ratings;
import JPAEntity.TablesRecordCounter;
import JPAEntity.Users;
import entity.UserRatingStatistic;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

//url ('/submit-course-review')
public class SubmitCourseReview extends HttpServlet {  
    @PersistenceContext EntityManager em;
    @Resource UserTransaction utx;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get the data
        int score = Integer.parseInt(request.getParameter("rate"));
        String reviewStr = request.getParameter("comment");
        
        //get product entity 
        String productIdToReview = request.getParameter("productIdToReview");
        Product prodEntity = em.find(Product.class, productIdToReview);
        
        //get the user data
        HttpSession session = request.getSession();
        Users userData = (Users) session.getAttribute("userData");
        
        //get prev rating id to store the review
        TablesRecordCounter ratingsCounter = em.find(TablesRecordCounter.class,"RATINGS");
        
        //store the review
        Ratings newRateData = new Ratings(score,reviewStr,new Date(),0,prodEntity,userData);
        newRateData.setRatingId(ratingsCounter.getCounter() + 1);
        
        //increase the count of counter
        ratingsCounter.counterIncrementByOne();
       
        
        //get rating statistic
        List<Long> starCountsAsc = new ArrayList<>();
        Query getRatingStatsBasedOnStarQuery = em.createNamedQuery("Ratings.findRatingCountByProdIdAndScore");
        getRatingStatsBasedOnStarQuery.setParameter("productId", prodEntity);
        
        Long result = null;
        for(int i = 1;i<6;i++){
            getRatingStatsBasedOnStarQuery.setParameter("score", i);
            getRatingStatsBasedOnStarQuery.getSingleResult();
            result = new Long((long)getRatingStatsBasedOnStarQuery.getSingleResult());
            starCountsAsc.add(result);
        }  
        
        UserRatingStatistic rateStats = new UserRatingStatistic(starCountsAsc.get(4),starCountsAsc.get(3),starCountsAsc.get(2),starCountsAsc.get(1),starCountsAsc.get(0));
        
        //update rating 
        prodEntity.setAvgRating((rateStats.getAvgRating()*5 + score)/(rateStats.getAllStarCounts()+1));
        
        
        //save the rating data
        if(saveDataToDatabases(request,response,newRateData,prodEntity,ratingsCounter)){
            //success
            //get course id
            response.sendRedirect("course?id=" + request.getParameter("courseIdToReview"));
        }else{
            request.setAttribute("reviewError","Server Error, Failed to submit review. Please Try again later");
            response.sendRedirect("course?id=" + request.getParameter("courseIdToReview"));
        }
        
       
    }
    
    private boolean saveDataToDatabases(HttpServletRequest request, HttpServletResponse response, Ratings rateData, Product product, TablesRecordCounter newCounterData) throws ServletException, IOException{      
        try{
            utx.begin();
            em.persist(rateData);
            em.merge(product);
            em.merge(newCounterData);
            utx.commit();
            return true;
        }catch(Exception ex){
            try{
            if (utx.getStatus() == Status.STATUS_ACTIVE) {
                try {
                    utx.rollback();
                }catch (SystemException ex2) {
                    //server error page
                    return false;
                }
            }
            }catch (SystemException ex2){
                return false;
            }
            return false;
        }
    }


}
