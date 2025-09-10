package vn.iostar.controllers.admin;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.RequestDispatcher;
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

@WebServlet(urlPatterns = { "/admin/category/findall" })
public class CategoryFindAllController extends HttpServlet {
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
        
        List<Category> allCategories = cateService.findAll();
        List<Category> adminCategories = allCategories.stream()
            .filter(cat -> cat.getUser() != null && cat.getUser().getRoleid() == 3)
            .collect(Collectors.toList());
        req.setAttribute("allCategories", adminCategories);
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/findall-category.jsp");
        dispatcher.forward(req, resp);
    }
}
