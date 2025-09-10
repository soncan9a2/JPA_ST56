package vn.iostar.controllers.manager;

import java.io.IOException;
import java.util.List;

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

@WebServlet(urlPatterns = {"/manager/home"})
public class ManagerHomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		// Manager chỉ được xem Category của chính mình
		List<Category> categories = categoryService.findByUserId(user.getUserId());
		request.setAttribute("categories", categories);
		request.setAttribute("userRole", "manager");
		request.getRequestDispatcher("/views/manager/home.jsp").forward(request, response);
	}
}
