package controller;

import JPAEntity.AuthorContribution;
import JPAEntity.Authors;
import JPAEntity.Courses;
import JPAEntity.Merchandise;
import JPAEntity.Product;
import JPAEntity.Users;
import JPAEntity.Wishlist;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

public class WishlistPage extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users userData = Login.checkRmbMeToken(request, em);
        if (userData != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userData", userData);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("pageToGoAfterLogin", "wishlist");
            response.sendRedirect("login");
            return;
        }

        // Get number of items in the wishlist
        long totalWishlistItems = (long) em.createNamedQuery("Wishlist.countAllByUserId").setParameter("userId", userData).getSingleResult();
        List<Wishlist> userWishlist = em.createNamedQuery("Wishlist.findByUserId").setParameter("userId", userData).getResultList();

        List<Courses> allCourseWishlist = new ArrayList<>();

        // To get all of the courses user wished
        for (Wishlist eachWishlist : userWishlist) {
            List<Courses> courseExistList = em.createNamedQuery("Courses.findByProductId").setParameter("productId", eachWishlist.getProductId()).getResultList();
            allCourseWishlist.add(courseExistList.get(0));
        }

        // To get author contribution for each course
        Map<String, List<Authors>> authorContribution = new HashMap<>();
        for (Courses eachCourse : allCourseWishlist) {
            List<AuthorContribution> rawAuthorContribution = em.createNamedQuery("AuthorContribution.findByCourseId").setParameter("courseId", eachCourse).getResultList();
            List<Authors> author = new ArrayList<>();
            for (AuthorContribution eachRawAuthorContribution : rawAuthorContribution) {
                Authors rawAuthor = em.find(Authors.class, eachRawAuthorContribution.getAuthorId().getAuthorId());
                author.add(rawAuthor);
            }
            authorContribution.put(eachCourse.getCourseId(), author);
        }

        request.setAttribute("totalWishlistItems", totalWishlistItems);
        request.setAttribute("allCourseWishlist", allCourseWishlist);
        request.setAttribute("authorContribution", authorContribution);

        // Forward the request to Merchandises.jsp
        request.getRequestDispatcher("/WEB-INF/Client/Wishlist.jsp").forward(request, response);

    }

}
