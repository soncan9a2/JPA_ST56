# JPA Category Management System
**Sinh viên:** Huỳnh Thanh Nhân  
**MSSV:** 23110280
Ứng dụng web Java sử dụng JPA để quản lý Category với phân quyền người dùng.

## Tính năng

- **Đăng nhập theo role**: User (1), Manager (2), Admin (3)
- **Phân quyền URL**: Mỗi role chỉ truy cập được URL của mình
- **Quản lý Category**: CRUD category theo ownership
- **FindAll Category**: Xem tất cả category theo từng role
- **Upload file**: Hỗ trợ upload icon cho category

## Quy tắc phân quyền

- **User**: Xem tất cả category, chỉ CRUD category của mình, findAll category User
- **Manager**: Chỉ xem và CRUD category của mình, findAll category Manager
- **Admin**: Xem tất cả category, chỉ CRUD category của mình, findAll category Admin

## Cài đặt

1. Cấu hình database trong `persistence.xml`
2. Deploy war file lên Tomcat
3. Truy cập `/init-data` để tạo tài khoản demo
4. Đăng nhập tại `/login`

## Tài khoản demo

- `user/password` → User role
- `manager/password` → Manager role
- `admin/password` → Admin role

## Cấu trúc

```
├── Entity: User (1) ↔ (Many) Category
├── DAO: Data Access Layer
├── Service: Business Logic Layer
├── Controller: Role-based controllers
├── Filter: Authorization filter
└── View: JSP pages
```