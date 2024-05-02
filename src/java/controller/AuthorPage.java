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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;
import javax.servlet.ServletConfig;

@WebServlet(name = "Author Page", urlPatterns = {"/author"})
public class AuthorPage extends HttpServlet {
    @PersistenceContext EntityManager em;
    
    private List<AuthorContribution> authorNCourses;
    private List<Authors> authors;
    
    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        Query authorsQuery = em.createNamedQuery("Authors.findAll");
        authors = authorsQuery.getResultList();
        
        Query authContriQuery = em.createNamedQuery("AuthorContribution.findAll");
        authorNCourses = authContriQuery.getResultList();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authorId = request.getParameter("id");
        HttpSession session = request.getSession();
        Authors author = null;
        // check is course id input exist
        if (authorId == null) {
           goToErrorPage(request,response);
        }
        else{
            author = findMatchingAuthorId(authorId);
            if(author == null){
                goToErrorPage(request,response);
            }else{
                //give author result get author and it's courses
                request.setAttribute("authorData",author);
                //Query query = em.createNamedQuery("AuthorContribution.findByAuthorId").setParameter("authorId", author);
                //List<AuthorContribution> authorNCourses = query.getResultList();
                List<Courses> authCourses = getAuthorCourses(author);
                //if(!authorNCourses.isEmpty()){  
                //   for(AuthorContribution authContri:authorNCourses){
                //        authCourses.add(authContri.getCourseId());
                //    }
                //}
                request.setAttribute("authorCourses",authCourses);
            }
        }
        //

        // Forward the request to Course.jsp
        request.getRequestDispatcher("/WEB-INF/Client/Author.jsp").forward(request, response);
    }
    
    private Authors findMatchingAuthorId(String authId){
        for(Authors author:authors){
            if(author.getAuthorId().equals(authId)){
                return author;
            }
        }
        return null;
    }
    
    private List<Courses> getAuthorCourses(Authors auth){
        List<Courses> authCourses = new ArrayList<>();
        for(AuthorContribution authContri:authorNCourses){
            if(!authContri.getCourseId().getProductId().getStatus().equals("Active")){
                continue;
            }
            //get author courses
            if(authContri.getAuthorId().getAuthorId().equals(auth.getAuthorId())){
                authCourses.add(authContri.getCourseId());
            }
        }
        return authCourses;
    }
    
    private void goToErrorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        session.setAttribute("type", "Author");
        session.setAttribute("param", "Author ID");
        session.setAttribute("errorDetail", "Your search has ventured beyond the known universe.");
            
        // Forward the request to  error page
        request.getRequestDispatcher("/WEB-INF/Client/NotFoundError.jsp").forward(request, response);
    }

}
