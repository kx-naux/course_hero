package controller;

import JPAEntity.AuthorContribution;
import JPAEntity.Authors;
import JPAEntity.CartItems;
import JPAEntity.CourseSubscriptions;
import JPAEntity.Courses;
import JPAEntity.Ratings;
import JPAEntity.Users;
import entity.UserRatingStatistic;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SingleCoursePage", urlPatterns = {"/course"})
public class SingleCoursePage extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String courseId = request.getParameter("id");
        Courses courseRetrieved = new Courses();
        HttpSession session = request.getSession();
        // check is course id exist
        if (courseId == null) {
            session.setAttribute("type", "Course");
            session.setAttribute("param", "Course ID");
            session.setAttribute("errorDetail", "Your search has ventured beyond the known universe.");

            // Forward the request to error page
            request.getRequestDispatcher("/WEB-INF/Client/NotFoundError.jsp").forward(request, response);
            return;
        } else {
            //check course id in databases onot
            courseRetrieved = em.find(Courses.class, courseId);
            if (courseRetrieved == null) {
                session.setAttribute("type", "Course");
                session.setAttribute("param", "Course ID");
                session.setAttribute("errorDetail", "Your search has ventured beyond the known universe.");

                // Forward the request to error page
                request.getRequestDispatcher("/WEB-INF/Client/NotFoundError.jsp").forward(request, response);
                return;
            }
        }

        //if got pass data to jsp 
        request.setAttribute("courseData", courseRetrieved);

        //check can add to cart onot
        if (courseRetrieved.getProductId().getStatus().equalsIgnoreCase("active")) {
            request.setAttribute("canAddToCart", "true");
        } else {
            request.setAttribute("canAddToCart", "false");
        }

        //get author and pass the data
        Query authorsQuery = em.createNamedQuery("AuthorContribution.findByCourseId");
        authorsQuery.setParameter("courseId", courseRetrieved);
        List<AuthorContribution> authorsList = authorsQuery.getResultList();
        //get author list
        List<Authors> authorList = new ArrayList<>();
        if (!authorsList.isEmpty()) {
            for (AuthorContribution authContri : authorsList) {
                authorList.add(authContri.getAuthorId());
            }
        }
        request.setAttribute("authorList", authorList);

        //pass rating to jsp
        Query ratingQuery = em.createNamedQuery("Ratings.findRatingCountByProdId");
        ratingQuery.setParameter("productId", courseRetrieved.getProductId());
        long ratingCount = (long) ratingQuery.getSingleResult();
        request.setAttribute("ratingCount", ratingCount);

        Query getRatings = em.createNamedQuery("Ratings.findRatingByProdIdSortDescPriKey").setParameter("productId", courseRetrieved.getProductId());
        getRatings.setMaxResults(4);
        List<Ratings> ratingsList = getRatings.getResultList();
        request.setAttribute("ratingList", ratingsList);

        //get total learners
        request.setAttribute("totalLearners", courseRetrieved.getCourseSubscriptionsList().size());

        Users userData = (Users) session.getAttribute("userData");
        if (userData == null) {
            //request.setAttribute("isOwn", false);
            //request.setAttribute("inCart",false);
            //request.setAttribute("inWishlist",false);
        } else {
            //check is in cart
            Query qry = em.createNamedQuery("CartItems.findByUserIdProductId");
            qry.setParameter("userId", userData);
            qry.setParameter("productId", courseRetrieved.getProductId());

            List<CartItems> cartItemList = qry.getResultList();
            if (cartItemList.isEmpty()) {
                request.setAttribute("inCart", false);
            } else {
                request.setAttribute("inCart", true);
            }
            //check is isOwn
            Query qryOwn = em.createNamedQuery("CourseSubscriptions.findByUserIdAndCourseId");
            qryOwn.setParameter("userId", userData);
            qryOwn.setParameter("courseId", courseRetrieved);
            List<CourseSubscriptions> courseSub = qryOwn.getResultList();
            if (courseSub.isEmpty()) {
                request.setAttribute("isOwn", false);
            } else {
                request.setAttribute("isOwn", true);
            }
            //check is inWhishlist
            request.setAttribute("inWishlist", false);

            //chek is isReviewed
            Query qryReview = em.createNamedQuery("Ratings.findByProdIdAndUser");
            qryReview.setParameter("productId", courseRetrieved.getProductId());
            qryReview.setParameter("userId", userData);
            List<Ratings> ratings = qryReview.getResultList();
            if (ratings.isEmpty()) {
                request.setAttribute("isReviewed", false);
            } else {
                request.setAttribute("isReviewed", true);
            }
        }

        Users userDataSession = (Users) request.getSession().getAttribute("userData");

        if (userData != null) {
            Login.getUserWishlist(request, em, userData);
            Login.getUserCart(request, em, userData);
        }
        if (userDataSession != null) {
            Login.getUserWishlist(request, em, userDataSession);
            Login.getUserCart(request, em, userDataSession);
        }

        //get rating statistic
        List<Long> starCountsAsc = new ArrayList<>();
        Query getRatingStatsBasedOnStarQuery = em.createNamedQuery("Ratings.findRatingCountByProdIdAndScore");
        getRatingStatsBasedOnStarQuery.setParameter("productId", courseRetrieved.getProductId());

        Long result = null;
        for (int i = 1; i < 6; i++) {
            getRatingStatsBasedOnStarQuery.setParameter("score", i);
            getRatingStatsBasedOnStarQuery.getSingleResult();
            result = new Long((long) getRatingStatsBasedOnStarQuery.getSingleResult());
            starCountsAsc.add(result);
        }

        UserRatingStatistic rateStats = new UserRatingStatistic(starCountsAsc.get(4), starCountsAsc.get(3), starCountsAsc.get(2), starCountsAsc.get(1), starCountsAsc.get(0));
        request.setAttribute("rateStats", rateStats);
        // Forward the request to Course.jsp
        request.getRequestDispatcher("/WEB-INF/Client/Course.jsp").forward(request, response);
    }

}
