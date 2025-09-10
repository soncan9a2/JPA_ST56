package vn.iostar.controllers.manager;

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

@WebServlet(urlPatterns = { "/manager/category/edit" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
                 maxFileSize = 1024 * 1024 * 10,      
                 maxRequestSize = 1024 * 1024 * 50) 
public class ManagerCategoryEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null || user.getRoleid() != 2) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        
        int cateid = Integer.parseInt(req.getParameter("id"));
        Category category = cateService.findById(cateid);
        
        // Kiểm tra quyền sở hữu
        if (category == null || category.getUser() == null || category.getUser().getUserId() != user.getUserId()) {
            resp.sendRedirect(req.getContextPath() + "/manager/category/list");
            return;
        }
        
        req.setAttribute("category", category);
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/manager/edit-category.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null || user.getRoleid() != 2) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        try {
            String catename = req.getParameter("name");
            int cateid = Integer.parseInt(req.getParameter("id"));
            String fname = "";

            Category category = cateService.findById(cateid);
            
            // Kiểm tra quyền sở hữu
            if (category == null || category.getUser() == null || category.getUser().getUserId() != user.getUserId()) {
                resp.sendRedirect(req.getContextPath() + "/manager/category/list");
                return;
            }
            
            String fileold = category.getIcons();

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

            category.setCateName(catename);
            category.setUser(user);

            if (!fname.isEmpty()) {
                category.setIcons(fname);
            } else {
                category.setIcons(fileold);
            }

            cateService.update(category);

            resp.sendRedirect(req.getContextPath() + "/manager/category/list");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "Lỗi khi upload file: " + e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/manager/edit-category.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
