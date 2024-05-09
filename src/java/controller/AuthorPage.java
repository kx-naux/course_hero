package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import JPAEntity.AuthorContribution;
import JPAEntity.Authors;
import JPAEntity.Courses;
import JPAEntity.SocialMediaLinks;
import JPAEntity.Users;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;
import javax.servlet.ServletConfig;

@WebServlet(name = "Author Page", urlPatterns = {"/author"})
public class AuthorPage extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    private List<Authors> authors;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        Query authorsQuery = em.createNamedQuery("Authors.findAll");
        authors = authorsQuery.getResultList();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authorId = request.getParameter("id");
        HttpSession session = request.getSession();
        Authors author = null;

        Users userDataSession = (Users) request.getSession().getAttribute("userData");
        Users userData = Login.checkRmbMeToken(request, em);

        if (userData != null) {
            Login.getUserWishlist(request, em, userData);
            Login.getUserCart(request, em, userData);
        }
        if (userDataSession != null) {
            Login.getUserWishlist(request, em, userDataSession);
            Login.getUserCart(request, em, userDataSession);
        }

        // check is course id input exist
        if (authorId == null) {
            goToNotFoundErrorPage(request, response);
        } else {
            author = findMatchingAuthorId(authorId);
            if (author == null) {
                goToNotFoundErrorPage(request, response);
            } else {
                //get number of courses recorded
                Query getCountQuery = em.createNamedQuery("AuthorContribution.countAllAvailabeCoursesByAuthor").setParameter("authorId", author);
                long numberOfCoursesRecord = (long) getCountQuery.getSingleResult();
                request.setAttribute("numberOfCourses", numberOfCoursesRecord);

                //do pagination for courses
                int reqPage = 1;
                int maxDataInPage = 4;
                long lastPage = (numberOfCoursesRecord - 1) / Long.parseLong(String.valueOf(maxDataInPage)) + 1;
                if (request.getParameter("p") != null) {
                    reqPage = Integer.parseInt(request.getParameter("p"));
                    if (reqPage > lastPage) {
                        goToCustomErrorPage(request, response, "Invalid URL");
                    }
                }

                int offset = (reqPage - 1) * maxDataInPage;

                //give author result get author and it's courses
                request.setAttribute("authorData", author);

                Query query = em.createNamedQuery("AuthorContribution.findByAuthorIdWhereCourseIsAvailable").setParameter("authorId", author);
                query.setFirstResult(offset);
                query.setMaxResults(maxDataInPage);
                List<AuthorContribution> authorNCourses = query.getResultList();
                List<Courses> authCourses = new ArrayList<>();
                if (!authorNCourses.isEmpty()) {
                    for (AuthorContribution authContri : authorNCourses) {
                        authCourses.add(authContri.getCourseId());
                    }
                }
                request.setAttribute("authorCourses", authCourses);

                //find social media link of author and pass it to jsp
                List<SocialMediaLinks> smLinks = getAuthorSocialMedia(author);
                request.setAttribute("socialMediaLinks", smLinks);
            }
        }
        //

        // Forward the request to Course.jsp
        request.getRequestDispatcher("/WEB-INF/Client/Author.jsp").forward(request, response);
    }

    private List<SocialMediaLinks> getAuthorSocialMedia(Authors author) {
        Query query = em.createNamedQuery("SocialMediaLinks.findByAuthorId").setParameter("authorId", author);
        List<SocialMediaLinks> resultList = query.getResultList();
        return resultList;
    }

    private Authors findMatchingAuthorId(String authId) {
        for (Authors author : authors) {
            if (author.getAuthorId().equals(authId)) {
                return author;
            }
        }
        return null;
    }

    private List<Courses> getAuthorCourses(List<AuthorContribution> authorNCourses, Authors auth) {
        List<Courses> authCourses = new ArrayList<>();
        for (AuthorContribution authContri : authorNCourses) {
            if (!authContri.getCourseId().getProductId().getStatus().equals("Active")) {
                continue;
            }
            //get author courses
            if (authContri.getAuthorId().getAuthorId().equals(auth.getAuthorId())) {
                authCourses.add(authContri.getCourseId());
            }
        }
        return authCourses;
    }

    private void goToNotFoundErrorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("type", "Author");
        session.setAttribute("param", "Author ID");
        session.setAttribute("errorDetail", "Your search has ventured beyond the known universe.");

        // Forward the request to  error page
        request.getRequestDispatcher("/WEB-INF/Client/NotFoundError.jsp").forward(request, response);
    }

    private void goToCustomErrorPage(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("errorMessage", errorMessage);
        session.setAttribute("errorDetail", "Your search has ventured beyond the known universe.");

        // Forward the request to  error page
        request.getRequestDispatcher("/WEB-INF/Client/CustomError.jsp").forward(request, response);
    }

}
