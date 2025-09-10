<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tất cả Category User</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 20px;
        background-color: #f4f4f4;
    }
    .header {
        background-color: #007bff;
        color: white;
        padding: 15px;
        border-radius: 5px;
        margin-bottom: 20px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .container {
        background-color: white;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    .category-table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }
    .category-table th,
    .category-table td {
        padding: 12px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }
    .category-table th {
        background-color: #f8f9fa;
        font-weight: bold;
    }
    .category-table tr:hover {
        background-color: #f5f5f5;
    }
    .btn {
        padding: 8px 16px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        text-decoration: none;
        display: inline-block;
        margin: 2px;
    }
    .btn-primary {
        background-color: #007bff;
        color: white;
    }
    .btn-danger {
        background-color: #dc3545;
        color: white;
    }
    .role-badge {
        background-color: #28a745;
        color: white;
        padding: 5px 10px;
        border-radius: 15px;
        font-size: 12px;
    }
    .actions {
        margin-bottom: 20px;
    }
</style>
</head>
<body>
    <div class="header">
        <div>
            <h2>User - Tất cả Category User</h2>
            <span class="role-badge">Role: User</span>
        </div>
        <div>
            <span>Chào mừng: ${sessionScope.user.fullname}</span>
            <a href="${pageContext.request.contextPath}/user/home" class="btn btn-primary">Về trang chủ</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">Đăng xuất</a>
        </div>
    </div>
    
    <div class="container">
        <h3>Tất cả Category do các User tạo</h3>
        <p><i>Danh sách tất cả category được tạo bởi các user (role=1) trong hệ thống.</i></p>
        
        <div class="actions">
            <a href="${pageContext.request.contextPath}/user/category/list" class="btn btn-primary">Quản lý Category của tôi</a>
        </div>
        
        <table class="category-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên Category</th>
                    <th>Icon</th>
                    <th>Người tạo</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="category" items="${allCategories}">
                    <tr>
                        <td>${category.cateId}</td>
                        <td>${category.cateName}</td>
                        <td>
                            <c:if test="${not empty category.icons}">
                                <img src="${pageContext.request.contextPath}/uploads/category/${category.icons}" 
                                     alt="Icon" style="width: 50px; height: 50px; object-fit: cover;">
                            </c:if>
                            <c:if test="${empty category.icons}">
                                Không có icon
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${not empty category.user}">
                                ${category.user.fullname} (${category.user.username})
                            </c:if>
                            <c:if test="${empty category.user}">
                                Không xác định
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty allCategories}">
                    <tr>
                        <td colspan="4" style="text-align: center; color: #666;">
                            Chưa có category nào được tạo bởi User
                        </td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</body>
</html>
