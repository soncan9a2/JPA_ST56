package vn.iostar.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iostar.entity.User;

@WebFilter(urlPatterns = {"/user/*", "/manager/*", "/admin/*"})
public class AuthenticationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Khởi tạo filter
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		HttpSession session = httpRequest.getSession(false);
		String requestURI = httpRequest.getRequestURI();
		String contextPath = httpRequest.getContextPath();
		
		// Kiểm tra xem user đã đăng nhập chưa
		if (session == null || session.getAttribute("user") == null) {
			httpResponse.sendRedirect(contextPath + "/login");
			return;
		}
		
		User user = (User) session.getAttribute("user");
		int userRole = user.getRoleid();
		
		// Kiểm tra quyền truy cập theo role
		if (requestURI.startsWith(contextPath + "/user/") && userRole != 1) {
			// User role chỉ được vào /user/*
			httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền truy cập trang này");
			return;
		}
		
		if (requestURI.startsWith(contextPath + "/manager/") && userRole != 2) {
			// Manager role chỉ được vào /manager/*
			httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền truy cập trang này");
			return;
		}
		
		if (requestURI.startsWith(contextPath + "/admin/") && userRole != 3) {
			// Admin role chỉ được vào /admin/*
			httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền truy cập trang này");
			return;
		}
		
		// Nếu pass tất cả kiểm tra, cho phép tiếp tục
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// Cleanup filter
	}
}
