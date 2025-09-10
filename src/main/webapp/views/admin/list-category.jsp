<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý Category - Admin</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 20px;
        background-color: #f4f4f4;
    }
    .header {
        background-color: #dc3545;
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
    .btn-warning {
        background-color: #ffc107;
        color: #212529;
    }
    .btn-danger {
        background-color: #dc3545;
        color: white;
    }
    .btn-success {
        background-color: #28a745;
        color: white;
    }
    .role-badge {
        background-color: #dc3545;
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
            <h2>Admin - Quản lý Category</h2>
            <span class="role-badge">Role: Admin</span>
        </div>
        <div>
            <span>Chào mừng: ${sessionScope.user.fullname}</span>
            <a href="${pageContext.request.contextPath}/admin/home" class="btn btn-primary">Về trang chủ</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">Đăng xuất</a>
        </div>
    </div>
    
    <div class="container">
        <h3>Quản lý Category của bạn</h3>
        <p><i>Bạn chỉ có thể xem và chỉnh sửa các category do chính bạn tạo ra.</i></p>
        
        <div class="actions">
            <a href="${pageContext.request.contextPath}/admin/category/add" class="btn btn-success">Thêm Category mới</a>
            <a href="${pageContext.request.contextPath}/admin/category/findall" class="btn" style="background-color: #17a2b8; color: white;">Tất cả Category Admin</a>
        </div>
        
        <table class="category-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên Category</th>
                    <th>Icon</th>
                    <th>Thao tác</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="category" items="${cateList}">
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
                            <a href="${pageContext.request.contextPath}/admin/category/edit?id=${category.cateId}" 
                               class="btn btn-warning">Sửa</a>
                            <a href="${pageContext.request.contextPath}/admin/category/delete?id=${category.cateId}" 
                               class="btn btn-danger"
                               onclick="return confirm('Bạn có chắc chắn muốn xóa category này?')">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty cateList}">
                    <tr>
                        <td colspan="4" style="text-align: center; color: #666;">
                            Bạn chưa tạo category nào. <a href="${pageContext.request.contextPath}/admin/category/add">Tạo category đầu tiên</a>
                        </td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</body>
</html>