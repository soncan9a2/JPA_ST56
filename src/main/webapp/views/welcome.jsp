<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
        background-color: #f8f9fa;
    }
    .container {
        max-width: 800px;
        margin: 0 auto;
        background-color: white;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    .header {
        text-align: center;
        margin-bottom: 30px;
    }
    .user-info {
        background-color: #e9ecef;
        padding: 15px;
        border-radius: 5px;
        margin-bottom: 30px;
    }
    .menu {
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
        justify-content: center;
    }
    .menu-item {
        background-color: #007bff;
        color: white;
        padding: 20px 30px;
        text-decoration: none;
        border-radius: 10px;
        text-align: center;
        min-width: 200px;
        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        transition: background-color 0.3s;
    }
    .menu-item:hover {
        background-color: #0056b3;
        color: white;
    }
    .logout-btn {
        background-color: #dc3545;
        color: white;
        padding: 10px 20px;
        text-decoration: none;
        border-radius: 5px;
        float: right;
    }
    .logout-btn:hover {
        background-color: #c82333;
        color: white;
    }
</style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Chào mừng đến với hệ thống quản lý Category</h1>
            <a href="${pageContext.request.contextPath}/logout" class="logout-btn">Đăng xuất</a>
            <div style="clear: both;"></div>
        </div>
        
        <div class="user-info">
            <h3>Thông tin người dùng:</h3>
            <p><strong>Tên đăng nhập:</strong> ${sessionScope.account.username}</p>
            <p><strong>Họ tên:</strong> ${sessionScope.account.fullname}</p>
            <p><strong>Email:</strong> ${sessionScope.account.email}</p>
            <p><strong>Điện thoại:</strong> ${sessionScope.account.phone}</p>
        </div>
        
        <div class="menu">
            <c:choose>
                <c:when test="${sessionScope.account.roleid == 3}">
                    <!-- Admin -->
                    <a href="${pageContext.request.contextPath}/admin/category/list" class="menu-item">
                        <h3>Quản lý Category Admin</h3>
                        <p>Thêm, sửa, xóa các danh mục Admin</p>
                    </a>
                    <a href="${pageContext.request.contextPath}/admin/category/findall" class="menu-item" style="background-color: #dc3545;">
                        <h3>Xem tất cả Category Admin</h3>
                        <p>Xem toàn bộ category của Admin</p>
                    </a>
                </c:when>
                <c:when test="${sessionScope.account.roleid == 2}">
                    <!-- Manager -->
                    <a href="${pageContext.request.contextPath}/manager/category/list" class="menu-item" style="background-color: #ffc107; color: #212529;">
                        <h3>Quản lý Category Manager</h3>
                        <p>Thêm, sửa, xóa các danh mục Manager</p>
                    </a>
                    <a href="${pageContext.request.contextPath}/manager/category/findall" class="menu-item" style="background-color: #fd7e14;">
                        <h3>Xem tất cả Category Manager</h3>
                        <p>Xem toàn bộ category của Manager</p>
                    </a>
                </c:when>
                <c:when test="${sessionScope.account.roleid == 1}">
                    <!-- User -->
                    <a href="${pageContext.request.contextPath}/user/category/list" class="menu-item" style="background-color: #28a745;">
                        <h3>Quản lý Category User</h3>
                        <p>Thêm, sửa, xóa các danh mục User</p>
                    </a>
                    <a href="${pageContext.request.contextPath}/user/category/findall" class="menu-item" style="background-color: #20c997;">
                        <h3>Xem tất cả Category User</h3>
                        <p>Xem toàn bộ category của User</p>
                    </a>
                </c:when>
            </c:choose>
        </div>
    </div>
</body>
</html>
