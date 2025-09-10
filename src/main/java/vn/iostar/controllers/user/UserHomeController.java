package vn.iostar.controllers.user;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.entity.Category;
import vn.iostar.services.CategoryService;
import vn.iostar.services.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = {"/user/home"})
public class UserHomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// User và Admin được xem tất cả Category
		List<Category> categories = categoryService.findAll();
		request.setAttribute("categories", categories);
		request.setAttribute("userRole", "user");
		request.getRequestDispatcher("/views/user/home.jsp").forward(request, response);
	}
}
