package vn.iostar.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.entity.User;
import vn.iostar.entity.Category;
import vn.iostar.services.UserService;
import vn.iostar.services.CategoryService;
import vn.iostar.services.impl.UserServiceImpl;
import vn.iostar.services.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = {"/init-data"})
public class InitDataController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		UserService userService = new UserServiceImpl();
		CategoryService categoryService = new CategoryServiceImpl();
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><body>");
		out.println("<h2>Khởi tạo dữ liệu demo</h2>");
		
		try {
			boolean hasUser = userService.findByUsername("user") != null;
			boolean hasManager = userService.findByUsername("manager") != null;
			boolean hasAdmin = userService.findByUsername("admin") != null;
			
			if (hasUser && hasManager && hasAdmin) {
				out.println("<p style='color: green;'> Tất cả tài khoản test đã tồn tại. Không cần tạo lại.</p>");
			} else {
				out.println("<h3>Đang kiểm tra và tạo tài khoản test...</h3>");
				if (!hasUser) {
					User user1 = new User("user", "password", "Nguyễn Văn User", "user@example.com", "0123456789", 1);
					userService.insert(user1);
					out.println("<p>✅ Đã tạo user: " + user1.getUsername() + "</p>");
				} else {
					out.println("<p style='color: orange;'>⚠️ User 'user' đã tồn tại</p>");
				}
				if (!hasManager) {
					User user2 = new User("manager", "password", "Trần Thị Manager", "manager@example.com", "0987654321", 2);
					userService.insert(user2);
					out.println("<p> Đã tạo manager: " + user2.getUsername() + "</p>");
				} else {
					out.println("<p style='color: orange;'>⚠️ User 'manager' đã tồn tại</p>");
				}
				if (!hasAdmin) {
					User user3 = new User("admin", "password", "Lê Văn Admin", "admin@example.com", "0555666777", 3);
					userService.insert(user3);
					out.println("<p> Đã tạo admin: " + user3.getUsername() + "</p>");
				} else {
					out.println("<p style='color: orange;'>⚠️ User 'admin' đã tồn tại</p>");
				}
				if (categoryService.count() == 0) {
					out.println("<h3>Đang tạo category demo...</h3>");
					
					User user = userService.findByUsername("user");
					User manager = userService.findByUsername("manager");
					User admin = userService.findByUsername("admin");
					if (user != null) {
						Category cat1 = new Category("User Category 1", "");
						cat1.setUser(user);
						categoryService.insert(cat1);
						out.println("<p> Đã tạo category: " + cat1.getCateName() + " (User)</p>");
					}
					if (manager != null) {
						Category cat2 = new Category("Manager Category 1", "");
						cat2.setUser(manager);
						categoryService.insert(cat2);
						out.println("<p> Đã tạo category: " + cat2.getCateName() + " (Manager)</p>");
					}
					if (admin != null) {
						Category cat3 = new Category("Admin Category 1", "");
						cat3.setUser(admin);
						categoryService.insert(cat3);
						out.println("<p> Đã tạo category: " + cat3.getCateName() + " (Admin)</p>");
					}
				} else {
					out.println("<p style='color: orange;'> Đã có " + categoryService.count() + " category trong database</p>");
				}
				
				out.println("<h3 style='color: green;'> KIỂM TRA VÀ KHỞI TẠO HOÀN TẤT!</h3>");
			}
			
			out.println("<hr>");
			out.println("<h3>Tài khoản demo đã được tạo:</h3>");
			out.println("<ul>");
			out.println("<li><strong>Username:</strong> user, <strong>Password:</strong> password (Role: User)</li>");
			out.println("<li><strong>Username:</strong> manager, <strong>Password:</strong> password (Role: Manager)</li>");
			out.println("<li><strong>Username:</strong> admin, <strong>Password:</strong> password (Role: Admin)</li>");
			out.println("</ul>");
			
			out.println("<p><a href='" + request.getContextPath() + "/login' style='color: #007bff; font-size: 18px;'>🔑 Đến trang đăng nhập</a></p>");
			out.println("<hr>");
			out.println("<h4>Quick Test URLs:</h4>");
			out.println("<p>📊 <a href='" + request.getContextPath() + "/user/home'>User Home</a> (cần login trước)</p>");
			out.println("<p>📋 <a href='" + request.getContextPath() + "/manager/home'>Manager Home</a> (cần login trước)</p>");
			out.println("<p>⚙️ <a href='" + request.getContextPath() + "/admin/home'>Admin Home</a> (cần login trước)</p>");
			
		} catch (Exception e) {
			out.println("<h3 style='color: red;'>❌ LỖI KHI KHỞI TẠO DỮ LIỆU:</h3>");
			out.println("<pre>" + e.getMessage() + "</pre>");
			e.printStackTrace(out);
		}
		
		out.println("</body></html>");
	}
}
