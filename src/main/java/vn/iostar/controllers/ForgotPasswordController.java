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

@WebServlet(urlPatterns = {"/forgot-password"})
public class ForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		String alertMsg = "";
		
		if (username == null || email == null || phone == null || newPassword == null ||
			username.trim().isEmpty() || email.trim().isEmpty() || phone.trim().isEmpty() || newPassword.trim().isEmpty()) {
			alertMsg = "Vui lòng điền đầy đủ thông tin";
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
			return;
		}
		
		if (!newPassword.equals(confirmPassword)) {
			alertMsg = "Mật khẩu xác nhận không khớp";
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
			return;
		}
		
		if (newPassword.length() < 6) {
			alertMsg = "Mật khẩu phải có ít nhất 6 ký tự";
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
			return;
		}
		
		User user = userService.findByUsername(username.trim());
		if (user == null) {
			alertMsg = "Tên đăng nhập không tồn tại";
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
			return;
		}
		
		if (!email.trim().equals(user.getEmail()) || !phone.trim().equals(user.getPhone())) {
			alertMsg = "Email hoặc số điện thoại không đúng với tài khoản này";
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
			return;
		}
		
		try {
			user.setPassword(newPassword.trim());
			userService.update(user);
			
			request.setAttribute("success", "Đổi mật khẩu thành công! Bạn có thể đăng nhập với mật khẩu mới.");
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
		} catch (Exception e) {
			alertMsg = "Có lỗi xảy ra khi đổi mật khẩu: " + e.getMessage();
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
		}
	}
}
