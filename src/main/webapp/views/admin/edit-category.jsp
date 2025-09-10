<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sửa Category - Admin</title>
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
        max-width: 600px;
        margin: 0 auto;
    }
    .form-group {
        margin-bottom: 20px;
    }
    .form-group label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
        color: #333;
    }
    .form-group input,
    .form-group textarea {
        width: 100%;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 5px;
        box-sizing: border-box;
    }
    .btn {
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        text-decoration: none;
        display: inline-block;
        margin: 5px;
    }
    .btn-primary {
        background-color: #007bff;
        color: white;
    }
    .btn-secondary {
        background-color: #6c757d;
        color: white;
    }
    .alert {
        padding: 10px;
        margin-bottom: 20px;
        border-radius: 5px;
        background-color: #f8d7da;
        color: #721c24;
        border: 1px solid #f5c6cb;
    }
    .role-badge {
        background-color: #dc3545;
        color: white;
        padding: 5px 10px;
        border-radius: 15px;
        font-size: 12px;
    }
    .current-icon {
        max-width: 100px;
        max-height: 100px;
        margin-bottom: 10px;
    }
</style>
</head>
<body>
    <div class="header">
        <div>
            <h2>Admin - Sửa Category</h2>
            <span class="role-badge">Role: Admin</span>
        </div>
        <div>
            <span>Chào mừng: ${sessionScope.user.fullname}</span>
            <a href="${pageContext.request.contextPath}/admin/home" class="btn btn-primary">Về trang chủ</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn" style="background-color: #dc3545; color: white;">Đăng xuất</a>
        </div>
    </div>
    
    <div class="container">
        <h3>Sửa Category</h3>
        
        <c:if test="${not empty message}">
            <div class="alert">${message}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/admin/category/edit" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${category.cateId}">
            
            <div class="form-group">
                <label for="name">Tên Category:</label>
                <input type="text" id="name" name="name" value="${category.cateName}" required>
            </div>
            
            <div class="form-group">
                <label>Icon hiện tại:</label>
                <c:if test="${not empty category.icons}">
                    <div>
                        <img src="${pageContext.request.contextPath}/uploads/category/${category.icons}" 
                             alt="Current Icon" class="current-icon">
                        <p>File hiện tại: ${category.icons}</p>
                    </div>
                </c:if>
                <c:if test="${empty category.icons}">
                    <p>Chưa có icon</p>
                </c:if>
            </div>
            
            <div class="form-group">
                <label for="icon">Thay đổi Icon (để trống nếu không muốn thay đổi):</label>
                <input type="file" id="icon" name="icon" accept="image/*">
            </div>
            
            <div style="text-align: center;">
                <button type="submit" class="btn btn-primary">Cập nhật Category</button>
                <a href="${pageContext.request.contextPath}/admin/category/list" class="btn btn-secondary">Hủy</a>
            </div>
        </form>
    </div>
</body>
</html>