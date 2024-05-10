package controller;

import JPAEntity.AuthorContribution;
import JPAEntity.Authors;
import JPAEntity.CourseCategory;
import JPAEntity.Courses;
import JPAEntity.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchResultPage extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Getting all of the parameters
        String query = request.getParameter("query");
        String sort = request.getParameter("sort");
        String ratings = request.getParameter("ratings");
        String[] duration = request.getParameterValues("duration");
        String[] level = request.getParameterValues("level");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        String[] category = request.getParameterValues("category");

        /*
        highest-rated = avg score
        reviewed = rating weight
        price = price
        relevance = string match
         */
        //Get all of the course category
        List<CourseCategory> allCategory = em.createNamedQuery("CourseCategory.findAll").getResultList();
        request.setAttribute("allCategory", allCategory);

        //Get "Count" for Rating, Duration, Level, Category
        // Count for Rating
        Map<Double, Long> ratingMap = new HashMap<>();
        double[] ratingArray = {3.0, 3.5, 4.0, 4.5};
        for (double eachRating : ratingArray) {
            List<Product> currentRatingProducts = em.createNamedQuery("Product.findByAvgRatingMoreThan").setParameter("avgRating", eachRating).getResultList();
            long totalEachRating = 0;
            for (Product crProduct : currentRatingProducts) {
                List<Courses> currentCourses = em.createNamedQuery("Courses.findByProductId").setParameter("productId", crProduct).getResultList();
                if (!currentCourses.isEmpty()) {
                    totalEachRating++;
                }
            }
            ratingMap.put(eachRating, totalEachRating);
        }

        request.setAttribute("ratingMap", ratingMap);

        // Count for Duration
        Map<Integer, Long> durationMap = new HashMap<>();
        int[] durationArray = {0, 1, 3, 6, 17};
        for (int i = 0; i < 4; i++) {
            long totalEachDuration = (long) em.createNamedQuery("Courses.countByTwoLengthHour").setParameter("minLengthHour", (double) durationArray[i]).setParameter("maxLengthHour", (double) durationArray[i + 1]).getSingleResult();
            durationMap.put((Integer) durationArray[i], totalEachDuration);
            if (i == 3) {
                long AddtotalEachDuration = (long) em.createNamedQuery("Courses.countByTwoLengthHour").setParameter("minLengthHour", (double) durationArray[i + 1]).setParameter("maxLengthHour", Double.MAX_VALUE).getSingleResult();
                durationMap.put((Integer) durationArray[i + 1], AddtotalEachDuration);
            }
        }

        request.setAttribute("durationMap", durationMap);

        // Count for Level
        Map<String, Long> levelMap = new HashMap<>();
        String[] rawLevelList = {"All Levels", "Beginner", "Intermediate", "Expert"};
        List<String> allLevelList = Arrays.asList(rawLevelList);

        for (String eachAllLevelList : allLevelList) {
            long AddtotalEachDuration = (long) em.createNamedQuery("Courses.countByCourseLevel").setParameter("courseLevel", eachAllLevelList).getSingleResult();
            levelMap.put(eachAllLevelList, AddtotalEachDuration);
        }

        request.setAttribute("levelMap", levelMap);

        // Count for Course Category
        Map<String, Long> categoryMap = new HashMap<>();
        for (CourseCategory eachCourseCategory : allCategory) {
            Long totalEeachCategory = (Long) em.createNamedQuery("Courses.countByCategoryId").setParameter("coursecatId", eachCourseCategory).getSingleResult();
            categoryMap.put(eachCourseCategory.getCategoryName(), totalEeachCategory);
        }

        request.setAttribute("categoryMap", categoryMap);

        //Getting filtered result by using query
        List<Product> filteredProduct = new ArrayList<>();
        List<Courses> filteredCourses = new ArrayList<>();

        if (query.length() > 0) {
            if (sort != null) {
                switch (sort) {
                    case "highest-rated":
                        filteredProduct = em.createNamedQuery("Product.findByProdNameFilterOrderByAvgRatingDesc").setParameter("prodName", query).getResultList();
                        break;
                    case "reviewed":
                        filteredProduct = em.createNamedQuery("Product.findByProdNameFilterOrderByRateWeightageDesc").setParameter("prodName", query).getResultList();
                        break;
                    case "price":
                        filteredProduct = em.createNamedQuery("Product.findByProdNameFilterOrderByPriceDesc").setParameter("prodName", query).getResultList();
                        break;
                    default:
                        break;
                }
            } else {
                filteredProduct = em.createNamedQuery("Product.findByProdNameFilter").setParameter("prodName", query).getResultList();
            }
        } else {
            if (sort != null) {
                switch (sort) {
                    case "highest-rated":
                        filteredProduct = em.createNamedQuery("Product.findAllOrderByAvgRatingDesc").getResultList();
                        break;
                    case "reviewed":
                        filteredProduct = em.createNamedQuery("Product.findAllOrderByRateWeightageDesc").getResultList();
                        break;
                    case "price":
                        filteredProduct = em.createNamedQuery("Product.findAllOrderByPriceDesc").getResultList();
                        break;
                    default:
                        filteredProduct = em.createNamedQuery("Product.findAll").getResultList();
                        break;
                }
            } else {
                filteredProduct = em.createNamedQuery("Product.findByProdNameFilter").setParameter("prodName", query).getResultList();
            }
        }

        //First filtering #Rating
        if (ratings != null) {
            List<Product> temporaryFilter = new ArrayList<>();
            double ratingDouble = Double.parseDouble(ratings);

            for (Product eachProd : filteredProduct) {
                if (eachProd.getAvgRating() >= ratingDouble) {
                    temporaryFilter.add(eachProd);
                }
            }

            filteredProduct = temporaryFilter;
        }

        //Second filtering #Price Range
        if (minPrice != null || maxPrice != null) {
            if (minPrice.length() > 0 || maxPrice.length() > 0) {
                List<Product> temporaryFilter = new ArrayList<>();
                double minPriceDouble = Double.MIN_VALUE;
                double maxPriceDouble = Double.MAX_VALUE;

                if (minPrice != null) {
                    minPriceDouble = Double.parseDouble(minPrice);
                }
                if (maxPrice != null) {
                    maxPriceDouble = Double.parseDouble(maxPrice);
                }

                for (Product eachProd : filteredProduct) {

                    double productPrice = (eachProd.getDiscount() != 0) ? ((100 - eachProd.getDiscount()) / 100 * eachProd.getPrice()) : eachProd.getPrice();
                    if (productPrice >= minPriceDouble && productPrice <= maxPriceDouble) {
                        temporaryFilter.add(eachProd);
                    }
                }

                filteredProduct = temporaryFilter;
            }
        }

        //Converting everything into course entity
        for (Product eachProd : filteredProduct) {
            List<Courses> courseExistList = em.createNamedQuery("Courses.findByProductId").setParameter("productId", eachProd).getResultList();
            if (!courseExistList.isEmpty()) {
                filteredCourses.add(courseExistList.get(0));
            }
        }

        //Third filtering #Video Duration
        if (duration != null) {
            List<Courses> temporaryFilter = new ArrayList<>();
            List<String> durationList = Arrays.asList(duration);

            for (Courses eachCourse : filteredCourses) {
                double courseLearningHour = eachCourse.getLengthHour();
                String durationCategory = getDurationCategory(courseLearningHour);
                if (durationList.contains(durationCategory)) {
                    temporaryFilter.add(eachCourse);
                }
            }

            filteredCourses = temporaryFilter;
        }

        //Fourth filtering #Level
        if (level != null) {
            List<Courses> temporaryFilter = new ArrayList<>();
            List<String> levelList = Arrays.asList(level);

            for (Courses eachCourse : filteredCourses) {
                if (levelList.contains(eachCourse.getCourseLevel().toLowerCase())) {
                    temporaryFilter.add(eachCourse);
                }
            }

            filteredCourses = temporaryFilter;
        }

        //Fifth filtering #Category
        if (category != null) {
            List<Courses> temporaryFilter = new ArrayList<>();
            List<String> categoryList = Arrays.asList(category);

            for (Courses eachCourse : filteredCourses) {
                if (categoryList.contains(eachCourse.getCoursecatId().getCategoryName())) {
                    temporaryFilter.add(eachCourse);
                }
            }

            filteredCourses = temporaryFilter;
        }

        //Getting the authors contribution
        Map<String, List<Authors>> filteredAuthors = new HashMap<>();
        for (Courses eachFilteredCourses : filteredCourses) {
            List<Authors> author = new ArrayList<>();
            List<AuthorContribution> rawAuthorCon = em.createNamedQuery("AuthorContribution.findByCourseId").setParameter("courseId", eachFilteredCourses).getResultList();
            for (AuthorContribution eachRawAuthor : rawAuthorCon) {
                author.add(eachRawAuthor.getAuthorId());
            }
            filteredAuthors.put(eachFilteredCourses.getCourseId(), author);
        }

        //Pagination
        int reqPage = 1;
        int maxDataInPage = 4;
        long lastPage = (filteredCourses.size() - 1) / Long.parseLong(String.valueOf(maxDataInPage)) + 1;
        if (request.getParameter("p") != null) {
            reqPage = Integer.parseInt(request.getParameter("p"));
            if (reqPage > lastPage) {
                goToCustomErrorPage(request, response, "Invalid URL");
            }
        }

        // Calculate the end index based on the offset and maxDataPerPage
        int offset = (reqPage - 1) * maxDataInPage;
        int endIndex = Math.min(offset + maxDataInPage, filteredCourses.size());

        // Get the sublist of courses to show on the current page
        List<Courses> coursesToShow = filteredCourses.subList(offset, endIndex);

        //Offset
        /* assuming 0 - 80
        query.setFirstResult(offset);
        query.setMaxResults(maxDataInPage);
         */
        
        request.setAttribute("coursesToShow", coursesToShow);
        request.setAttribute("filteredCourses", filteredCourses);
        request.setAttribute("filteredAuthors", filteredAuthors);

        // Forward the request to home.jsp
        request.getRequestDispatcher("/WEB-INF/Client/SearchResult.jsp").forward(request, response);
    }

    private void goToCustomErrorPage(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("errorMessage", errorMessage);
        session.setAttribute("errorDetail", "Your search has ventured beyond the known universe.");

        // Forward the request to  error page
        request.getRequestDispatcher("/WEB-INF/Client/CustomError.jsp").forward(request, response);
    }

    public String getDurationCategory(double duration) {
        if (duration >= 0 && duration <= 1) {
            return "extrashort";
        } else if (duration > 1 && duration <= 3) {
            return "short";
        } else if (duration > 3 && duration <= 6) {
            return "medium";
        } else if (duration > 6 && duration <= 17) {
            return "long";
        } else {
            return "extralong";
        }
    }
}
