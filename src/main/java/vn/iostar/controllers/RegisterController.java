package vn.iostar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.entity.User;
import vn.iostar.services.UserService;
import vn.iostar.services.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/register.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String alertMsg = "";
		
		if (username == null || password == null || fullname == null || email == null ||
			username.trim().isEmpty() || password.trim().isEmpty() || fullname.trim().isEmpty() || email.trim().isEmpty()) {
			alertMsg = "Vui lòng điền đầy đủ thông tin bắt buộc";
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("/views/register.jsp").forward(request, response);
			return;
		}
		
		if (!password.equals(confirmPassword)) {
			alertMsg = "Mật khẩu xác nhận không khớp";
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("/views/register.jsp").forward(request, response);
			return;
		}
		
		if (password.length() < 6) {
			alertMsg = "Mật khẩu phải có ít nhất 6 ký tự";
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("/views/register.jsp").forward(request, response);
			return;
		}
		
		User existingUser = userService.findByUsername(username.trim());
		if (existingUser != null) {
			alertMsg = "Tên đăng nhập đã tồn tại";
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("/views/register.jsp").forward(request, response);
			return;
		}
		
		try {
			User newUser = new User(username.trim(), password.trim(), fullname.trim(), 
									email.trim(), phone != null ? phone.trim() : "", 1);
			userService.insert(newUser);
			
			request.setAttribute("success", "Đăng ký thành công! Bạn có thể đăng nhập ngay bây giờ.");
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
		} catch (Exception e) {
			alertMsg = "Có lỗi xảy ra khi đăng ký: " + e.getMessage();
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("/views/register.jsp").forward(request, response);
		}
	}
}
