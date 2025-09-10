package vn.iostar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iostar.entity.User;
import vn.iostar.services.UserService;
import vn.iostar.services.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action.equals("/logout")) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			redirectByRole(user, request, response);
			return;
		}
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String alertMsg = "";
		
		if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
			alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			return;
		}
		
		User user = userService.login(username.trim(), password.trim());
		if (user != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			redirectByRole(user, request, response);
		} else {
			alertMsg = "Tài khoản hoặc mật khẩu không đúng. <a href='" + request.getContextPath() + "/init-data'>Tạo tài khoản demo</a>";
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
		}
	}
	
	private void redirectByRole(User user, HttpServletRequest request, HttpServletResponse response) 
			throws IOException {
		String contextPath = request.getContextPath();
		switch (user.getRoleid()) {
			case 1: // User
				response.sendRedirect(contextPath + "/user/home");
				break;
			case 2: // Manager
				response.sendRedirect(contextPath + "/manager/home");
				break;
			case 3: // Admin
				response.sendRedirect(contextPath + "/admin/home");
				break;
			default:
				response.sendRedirect(contextPath + "/login");
				break;
		}
	}
}
