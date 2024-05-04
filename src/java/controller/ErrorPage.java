package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Error Page", urlPatterns = {"/Error"})
public class ErrorPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();

        String errorCode = request.getParameter("code");
        String errorDesc = "";
        String errorDetail = "";
        
        if (errorCode.equals("404")) {
            errorDesc = "Page Not Found";
            errorDetail = "Your search has ventured beyond the known universe.";
        }
                
        session.setAttribute("errorDesc", errorDesc);
        session.setAttribute("errorDetail", errorDetail);
        
        // Forward the request to error page
        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
    }
    
    public static void forwardToServerErrorPage(HttpServletRequest request, HttpServletResponse response, String errMsg)
        throws ServletException, IOException{
        
        HttpSession session = request.getSession();
        String errorDesc = errMsg;
        String errorDetail = "If issuse still presists, please contact customer support [contact number: +6012-3456 7890]";
        
        session.setAttribute("errorDesc", errorDesc);
        session.setAttribute("errorDetail", errorDetail);
        
        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
    }

}
