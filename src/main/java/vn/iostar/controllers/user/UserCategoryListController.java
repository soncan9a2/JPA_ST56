package vn.iostar.controllers.user;

import java.io.IOException;
import java.util.List;

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

@WebServlet(urlPatterns = { "/user/category/list" })
public class UserCategoryListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null || user.getRoleid() != 1) { // Chỉ user mới được truy cập
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        
        List<Category> cateList = cateService.findByUserId(user.getUserId());
        req.setAttribute("cateList", cateList);
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/list-category.jsp");
        dispatcher.forward(req, resp);
    }
}
