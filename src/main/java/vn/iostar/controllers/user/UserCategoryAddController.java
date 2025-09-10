package vn.iostar.controllers.user;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.iostar.entity.Category;
import vn.iostar.entity.User;
import vn.iostar.services.CategoryService;
import vn.iostar.services.impl.CategoryServiceImpl;
import vn.iostar.utils.Constant;

@WebServlet(urlPatterns = { "/user/category/add" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
                 maxFileSize = 1024 * 1024 * 10,      
                 maxRequestSize = 1024 * 1024 * 50)   
public class UserCategoryAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null || user.getRoleid() != 1) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/add-category.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null || user.getRoleid() != 1) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        try {
            String catename = req.getParameter("name");
            String fname = "";

            Part filePart = req.getPart("icon");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                fname = System.currentTimeMillis() + "_" + fileName;

                Path uploadDir = Paths.get(Constant.DIR + "\\category\\");
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                Path filePath = uploadDir.resolve(fname);
                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
                }
            }

            Category category = new Category();
            category.setCateName(catename);
            category.setIcons(fname);
            category.setUser(user);

            cateService.insert(category);

            resp.sendRedirect(req.getContextPath() + "/user/category/list");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "Lỗi khi upload file: " + e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/add-category.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
