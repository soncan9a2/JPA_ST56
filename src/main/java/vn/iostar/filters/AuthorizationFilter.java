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

@WebFilter(urlPatterns = {"/admin/*", "/manager/*", "/user/*"})
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        HttpSession session = httpRequest.getSession(false);
        
        if (session == null || session.getAttribute("user") == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }
        
        User user = (User) session.getAttribute("user");
        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        String path = requestURI.substring(contextPath.length());
        
        boolean isAuthorized = false;
        
        if (path.startsWith("/admin/")) {
            isAuthorized = (user.getRoleid() == 3);
        } else if (path.startsWith("/manager/")) {
            isAuthorized = (user.getRoleid() == 2);
        } else if (path.startsWith("/user/")) {
            isAuthorized = (user.getRoleid() == 1);
        }
        
        if (isAuthorized) {
            chain.doFilter(request, response);
        } else {
            redirectToHomePage(user, httpRequest, httpResponse);
        }
    }
    
    private void redirectToHomePage(User user, HttpServletRequest request, HttpServletResponse response) 
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

    @Override
    public void destroy() {
        // Cleanup code if needed
    }
}
