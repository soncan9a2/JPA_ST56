<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Home</title>
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
    .btn-danger {
        background-color: #dc3545;
        color: white;
    }
    .btn-secondary {
        background-color: #6c757d;
        color: white;
    }
    .role-badge {
        background-color: #dc3545;
        color: white;
        padding: 5px 10px;
        border-radius: 15px;
        font-size: 12px;
    }
    .stats {
        display: flex;
        justify-content: space-around;
        margin-bottom: 20px;
    }
    .stat-card {
        background-color: #f8f9fa;
        padding: 20px;
        border-radius: 5px;
        text-align: center;
        min-width: 150px;
    }
    .stat-number {
        font-size: 24px;
        font-weight: bold;
        color: #dc3545;
    }
</style>
</head>
<body>
    <div class="header">
        <div>
            <h2>Admin Dashboard</h2>
            <span class="role-badge">Role: Admin</span>
        </div>
        <div>
            <span>Chào mừng: ${sessionScope.user.fullname}</span>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">Đăng xuất</a>
        </div>
    </div>
    
    <div class="container">
        <h3>Tổng quan hệ thống</h3>
        
        <div class="stats">
            <div class="stat-card">
                <div class="stat-number">${categories.size()}</div>
                <div>Tổng số Category</div>
            </div>
            <!-- Có thể thêm các thống kê khác -->
        </div>
        
        <h3>Danh sách tất cả Category</h3>
        <p><i>Với quyền Admin, bạn có thể xem tất cả category trong hệ thống.</i></p>
        
        <table class="category-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên Category</th>
                    <th>Icon</th>
                    <th>Người tạo</th>
                    <th>Role người tạo</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="category" items="${categories}">
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
                        <td>
                            <c:if test="${not empty category.user}">
                                <c:choose>
                                    <c:when test="${category.user.roleid == 1}">
                                        <span style="color: #28a745;">User</span>
                                    </c:when>
                                    <c:when test="${category.user.roleid == 2}">
                                        <span style="color: #ffc107;">Manager</span>
                                    </c:when>
                                    <c:when test="${category.user.roleid == 3}">
                                        <span style="color: #dc3545;">Admin</span>
                                    </c:when>
                                </c:choose>
                            </c:if>
                            <c:if test="${empty category.user}">
                                Không xác định
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty categories}">
                    <tr>
                        <td colspan="5" style="text-align: center; color: #666;">
                            Chưa có category nào trong hệ thống
                        </td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</body>
</html>
