package controller;

import JPAEntity.Users;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

@WebServlet(name = "Admin Manage Contract Entries", urlPatterns = {"/admin/manage-contract-entries"})
public class AdminManageContractEntriesPage extends HttpServlet {
    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        Users userDataSession = (Users) request.getSession().getAttribute("userData");
        Users userData = Login.checkRmbMeToken(request, em);
        //check has rmb token onot
        if(userData != null){
            HttpSession session = request.getSession();
            session.setAttribute("userData",userData);
            Login.getUserWishlist(request, em, userData);
            Login.getUserCart(request, em, userData);
        //check has user logged in
        }else if(userDataSession == null){
            HttpSession session = request.getSession();
            session.setAttribute("pageToGoAfterLogin","admin/manage-contract-entries");
            response.sendRedirect("../login");
            return;
        }
        
        if (userDataSession != null) {
            Login.getUserWishlist(request, em, userDataSession);
            Login.getUserCart(request, em, userDataSession);
        }
        
        //get userData from session as the user can login thru the rmbMe
        Users checkUserAccess = (Users) request.getSession().getAttribute("userData");
        if(!checkUserAccess.getUsertype().equals("Manager")){
            ErrorPage.forwardToServerErrorPage(request, response, "Authorized Access Only ! ! !");
        }
        
        // Forward the request to home.jsp
        request.getRequestDispatcher("/WEB-INF/Admin/AdminManageContractEntries.jsp").forward(request, response);
    }
}

