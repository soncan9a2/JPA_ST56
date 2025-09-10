package vn.iostar.controllers.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iostar.entity.Category;
import vn.iostar.entity.User;
import vn.iostar.services.CategoryService;
import vn.iostar.services.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = { "/admin/category/delete" })
public class CategoryDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null || user.getRoleid() != 3) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        
        int cateid = Integer.parseInt(req.getParameter("id"));
        
        try {
            Category category = cateService.findById(cateid);
            
            if (category != null && category.getUser() != null && category.getUser().getUserId() == user.getUserId()) {
                cateService.delete(cateid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
    }
}
