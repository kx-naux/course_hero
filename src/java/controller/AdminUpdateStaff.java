package controller;

import JPAEntity.Accounts;
import JPAEntity.Users;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@WebServlet(name = "Admin Update Staff", urlPatterns = {"/admin/update-staff"})
public class AdminUpdateStaff extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("userId");
        System.out.println(userId);

        List<Users> staffs;
        Query qry = em.createNamedQuery("Users.findAllStaff");
        staffs = qry.getResultList();

        request.setAttribute("staffSize", staffs.size());
        request.setAttribute("staffs", staffs);

        // Forward the request to home.jsp
        request.getRequestDispatcher("/WEB-INF/Admin/AdminManageStaff.jsp").forward(request, response);
    }
}
