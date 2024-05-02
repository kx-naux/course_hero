import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MerchandisePage extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // check is course id exist
        if (request.getParameter("id") == null) {
            HttpSession session = request.getSession();
            session.setAttribute("type", "Merchandise");
            session.setAttribute("param", "Merchandise ID");
            session.setAttribute("errorDetail", "Your search has ventured beyond the known universe.");
            
            // Forward the request to error page
            request.getRequestDispatcher("/WEB-INF/Client/NotFoundError.jsp").forward(request, response);
        }

        // Forward the request to Course.jsp
        request.getRequestDispatcher("/WEB-INF/Client/Merchandise.jsp").forward(request, response);
    }
}
