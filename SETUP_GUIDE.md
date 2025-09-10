# Hướng dẫn cài đặt và chạy JPA Category Management System

## Mô tả hệ thống
Hệ thống quản lý Category sử dụng JPA với phân quyền người dùng theo 3 roles:
- **User (role = 1)**: Xem tất cả category
- **Manager (role = 2)**: Quản lý category của chính mình (CRUD)
- **Admin (role = 3)**: Xem tất cả category với quyền cao nhất

## Cài đặt

### 1. Cơ sở dữ liệu
- Tạo database có tên `jpast56` trên SQL Server
- Cập nhật thông tin kết nối trong `persistence.xml` nếu cần

### 2. Thư mục uploads
- Tạo thư mục `C:\uploads\category\` để lưu trữ hình ảnh  
- Hoặc thay đổi đường dẫn trong `Constant.java` (hiện tại: `C:\uploads`)

### 3. Khởi tạo dữ liệu demo
Chạy class `vn.iostar.utils.DatabaseInitializer` để tạo user và category mẫu:

```java
// Chạy main method trong DatabaseInitializer.java
```

## Tài khoản demo
Sau khi chạy DatabaseInitializer, bạn có thể đăng nhập với:

- **Username**: `user`, **Password**: `password` (Role: User)
- **Username**: `manager`, **Password**: `password` (Role: Manager)  
- **Username**: `admin`, **Password**: `password` (Role: Admin)

## Chức năng theo role

### User (role = 1)
- URL: `/user/home`
- Xem tất cả category trong hệ thống
- Không có quyền chỉnh sửa

### Manager (role = 2)
- URL: `/manager/home`
- Xem chỉ các category do chính mình tạo
- Thêm, sửa, xóa category của mình
- URLs:
  - `/manager/category/add` - Thêm category
  - `/manager/category/edit?id=X` - Sửa category
  - `/manager/category/delete?id=X` - Xóa category

### Admin (role = 3)
- URL: `/admin/home`
- Xem tất cả category với thông tin chi tiết
- Dashboard tổng quan hệ thống

## Cấu trúc thư mục

```
src/main/java/vn/iostar/
├── configs/JPAConfig.java
├── controllers/
│   ├── LoginController.java
│   ├── UploadController.java
│   ├── admin/AdminHomeController.java
│   ├── manager/
│   │   ├── ManagerHomeController.java
│   │   └── ManagerCategoryController.java
│   └── user/UserHomeController.java
├── dao/
│   ├── CategoryDao.java
│   ├── UserDao.java
│   └── impl/
├── entity/
│   ├── Category.java
│   └── User.java
├── filters/AuthenticationFilter.java
├── services/
│   ├── CategoryService.java
│   ├── UserService.java
│   └── impl/
└── utils/
    ├── Constant.java
    ├── DatabaseInitializer.java
    └── UploadUtils.java

src/main/webapp/views/
├── login.jsp
├── admin/home.jsp
├── manager/
│   ├── home.jsp
│   ├── category-add.jsp
│   └── category-edit.jsp
└── user/home.jsp
```

## Lưu ý
1. Hệ thống sử dụng Filter để kiểm tra phân quyền
2. Mỗi role chỉ có thể truy cập URL tương ứng
3. Manager chỉ có thể CRUD category của chính mình
4. File upload được lưu trong thư mục ngoài project
5. Session timeout: 30 phút

## Troubleshooting
- Nếu không kết nối được database, kiểm tra `persistence.xml`
- Nếu không hiển thị được hình ảnh, kiểm tra thư mục uploads
- Nếu bị lỗi 403, kiểm tra phân quyền trong AuthenticationFilter
