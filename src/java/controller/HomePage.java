package controller;

import JPAEntity.AuthorContribution;
import JPAEntity.Authors;
import JPAEntity.CartItems;
import JPAEntity.CourseCategory;
import JPAEntity.Courses;
import JPAEntity.Product;
import JPAEntity.Users;
import JPAEntity.Wishlist;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletConfig;

public class HomePage extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Users userDataSession = (Users) request.getSession().getAttribute("userData");
        Users userData = Login.checkRmbMeToken(request, em);

        Users currentUser = null;

        //check has rmb token onot
        if (userData != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userData", userData);
            Login.getUserWishlist(request, em, userData);
            Login.getUserCart(request, em, userData);
            currentUser = userData;
            //check has user logged in
        }

        if (userDataSession != null) {
            Login.getUserWishlist(request, em, userDataSession);
            Login.getUserCart(request, em, userDataSession);
            currentUser = userDataSession;
        }

        CourseCategory programmingCat = null;
        CourseCategory designCat = null;

        // To get all of the course category
        List<CourseCategory> courseCatList = em.createNamedQuery("CourseCategory.findAll").getResultList();
        request.setAttribute("courseCategoryList", courseCatList);

        // To get total courses registered for each category
        List<Integer> eachCourseCatRegistered = new ArrayList<>();

        for (CourseCategory course : courseCatList) {
            List<Courses> registeredCourse = em.createNamedQuery("Courses.findByCategoryId").setParameter("coursecatId", course).getResultList();
            Integer currentActiveCourse = 0;
            for (Courses eachCatCourse : registeredCourse) {
                if ("Active".equals(eachCatCourse.getProductId().getStatus())) {
                    currentActiveCourse++;
                }
            }
            eachCourseCatRegistered.add(currentActiveCourse);

            // Used for programming and design courses
            if ("CC0000001".equals(course.getCoursecatId())) {
                programmingCat = course;
            }
            if ("CC0000003".equals(course.getCoursecatId())) {
                designCat = course;
            }
        }
        request.setAttribute("eachCourseCatRegistered", eachCourseCatRegistered);

        // To get one of top courses in Programming CC0000001
        List<Courses> programmingCourses = em.createNamedQuery("Courses.findByCategoryId").setParameter("coursecatId", programmingCat).getResultList();
        request.setAttribute("programmingCourse", programmingCourses);

        // To get the associated authors on each of the programming course & cart + wishlist status
        Map<String, List<Authors>> authorContribution1 = new HashMap<>();
        Map<String, List<Integer>> programmingItemStatus = new HashMap<>();
        for (Courses eachProgrammingCourse : programmingCourses) {
            List<AuthorContribution> rawAuthorContribution1 = em.createNamedQuery("AuthorContribution.findByCourseId").setParameter("courseId", eachProgrammingCourse).getResultList();
            List<Authors> author = new ArrayList<>();
            for (AuthorContribution eachRawAuthorContribution1 : rawAuthorContribution1) {
                List<Authors> rawAuthor = em.createNamedQuery("Authors.findByAuthorId").setParameter("authorId", eachRawAuthorContribution1.getAuthorId().getAuthorId()).getResultList();
                author.add(rawAuthor.get(0));
            }
            authorContribution1.put(eachProgrammingCourse.getCourseId(), author);

            List<Integer> currentCourseStatus = new ArrayList<>();
            List<CartItems> cartExist = em.createNamedQuery("CartItems.findByUserIdProductId").setParameter("userId", currentUser).setParameter("productId", eachProgrammingCourse.getProductId()).getResultList();
            if (!cartExist.isEmpty()) {
                currentCourseStatus.add(1);
            } else {
                currentCourseStatus.add(0);
            }

            List<Wishlist> wishlistExist = em.createNamedQuery("Wishlist.findByUserIdProductId").setParameter("userId", currentUser).setParameter("productId", eachProgrammingCourse.getProductId()).getResultList();
            if (!wishlistExist.isEmpty()) {
                currentCourseStatus.add(1);
            } else {
                currentCourseStatus.add(0);
            }

            programmingItemStatus.put(eachProgrammingCourse.getCourseId(), currentCourseStatus);
        }
        request.setAttribute("programmingAuthorContribution", authorContribution1);
        request.setAttribute("programmingItemStatus", programmingItemStatus);

        // To get one of top courses in Design CC0000003
        List<Courses> designCourses = em.createNamedQuery("Courses.findByCategoryId").setParameter("coursecatId", designCat).getResultList();
        request.setAttribute("designCourse", designCourses);

        // To get the associated authors on each of the design course & cart + wishlist status
        Map<String, List<Authors>> authorContribution2 = new HashMap<>();
        Map<String, List<Integer>> designItemStatus = new HashMap<>();
        for (Courses eachDesignCourses : designCourses) {
            List<AuthorContribution> rawAuthorContribution2 = em.createNamedQuery("AuthorContribution.findByCourseId").setParameter("courseId", eachDesignCourses).getResultList();
            List<Authors> author = new ArrayList<>();
            for (AuthorContribution eachRawAuthorContribution2 : rawAuthorContribution2) {
                List<Authors> rawAuthor = em.createNamedQuery("Authors.findByAuthorId").setParameter("authorId", eachRawAuthorContribution2.getAuthorId().getAuthorId()).getResultList();
                author.add(rawAuthor.get(0));
            }
            authorContribution2.put(eachDesignCourses.getCourseId(), author);

            List<Integer> currentMerchStatus = new ArrayList<>();
            List<CartItems> cartExist = em.createNamedQuery("CartItems.findByUserIdProductId").setParameter("userId", currentUser).setParameter("productId", eachDesignCourses.getProductId()).getResultList();
            if (!cartExist.isEmpty()) {
                currentMerchStatus.add(1);
            } else {
                currentMerchStatus.add(0);
            }

            List<Wishlist> wishlistExist = em.createNamedQuery("Wishlist.findByUserIdProductId").setParameter("userId", currentUser).setParameter("productId", eachDesignCourses.getProductId()).getResultList();
            if (!wishlistExist.isEmpty()) {
                currentMerchStatus.add(1);
            } else {
                currentMerchStatus.add(0);
            }

            designItemStatus.put(eachDesignCourses.getCourseId(), currentMerchStatus);
        }
        request.setAttribute("designAuthorContribution", authorContribution2);
        request.setAttribute("designItemStatus", designItemStatus);

        // To get the all of courses 
        List<Courses> allCourses = em.createNamedQuery("Courses.findAll").getResultList();
        request.setAttribute("allCourse", allCourses);

        // To get all of the associated authors
        Map<String, List<Authors>> authorContribution3 = new HashMap<>();
        for (Courses eachMainCourse : allCourses) {
            List<AuthorContribution> rawAuthorContribution3 = em.createNamedQuery("AuthorContribution.findByCourseId").setParameter("courseId", eachMainCourse).getResultList();
            List<Authors> author = new ArrayList<>();
            for (AuthorContribution eachRawAuthorContribution3 : rawAuthorContribution3) {
                List<Authors> rawAuthor = em.createNamedQuery("Authors.findByAuthorId").setParameter("authorId", eachRawAuthorContribution3.getAuthorId().getAuthorId()).getResultList();
                author.add(rawAuthor.get(0));
            }
            authorContribution3.put(eachMainCourse.getCourseId(), author);
        }
        request.setAttribute("allAuthorContribution", authorContribution3);

        // Forward the request to home.jsp
        request.getRequestDispatcher("/WEB-INF/Client/Home.jsp").forward(request, response);
    }
}
