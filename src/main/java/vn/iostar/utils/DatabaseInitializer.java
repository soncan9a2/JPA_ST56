package vn.iostar.utils;

import vn.iostar.entity.User;
import vn.iostar.entity.Category;
import vn.iostar.services.UserService;
import vn.iostar.services.CategoryService;
import vn.iostar.services.impl.UserServiceImpl;
import vn.iostar.services.impl.CategoryServiceImpl;

/**
 * Class để khởi tạo dữ liệu demo cho hệ thống
 * Chạy class này một lần để tạo user và category mẫu
 */
public class DatabaseInitializer {
    
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        CategoryService categoryService = new CategoryServiceImpl();
        
        try {
            // Tạo user demo
            System.out.println("Tạo user demo...");
            
            // User role = 1
            User user1 = new User("user", "password", "Nguyễn Văn User", "user@example.com", "0123456789", 1);
            userService.insert(user1);
            System.out.println("Đã tạo user: " + user1.getUsername());
            
            // Manager role = 2
            User user2 = new User("manager", "password", "Trần Thị Manager", "manager@example.com", "0987654321", 2);
            userService.insert(user2);
            System.out.println("Đã tạo manager: " + user2.getUsername());
            
            // Admin role = 3
            User user3 = new User("admin", "password", "Lê Văn Admin", "admin@example.com", "0555666777", 3);
            userService.insert(user3);
            System.out.println("Đã tạo admin: " + user3.getUsername());
            
            // Tạo một vài category demo cho manager
            System.out.println("\nTạo category demo...");
            
            Category cat1 = new Category("Electronics", "electronics.png");
            cat1.setUser(user2); // Manager tạo
            categoryService.insert(cat1);
            System.out.println("Đã tạo category: " + cat1.getCateName());
            
            Category cat2 = new Category("Books", "books.png");
            cat2.setUser(user2); // Manager tạo
            categoryService.insert(cat2);
            System.out.println("Đã tạo category: " + cat2.getCateName());
            
            Category cat3 = new Category("Clothing", "clothing.png");
            cat3.setUser(user3); // Admin tạo
            categoryService.insert(cat3);
            System.out.println("Đã tạo category: " + cat3.getCateName());
            
            System.out.println("\n=== KHỞI TẠO DỮ LIỆU THÀNH CÔNG! ===");
            System.out.println("Tài khoản demo đã được tạo:");
            System.out.println("- Username: user, Password: password (Role: User)");
            System.out.println("- Username: manager, Password: password (Role: Manager)");
            System.out.println("- Username: admin, Password: password (Role: Admin)");
            
        } catch (Exception e) {
            System.err.println("Lỗi khi khởi tạo dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
