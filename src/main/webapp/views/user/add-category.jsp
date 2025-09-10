<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm Category - User</title>
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
        background-color: #28a745;
        color: white;
        padding: 5px 10px;
        border-radius: 15px;
        font-size: 12px;
    }
</style>
</head>
<body>
    <div class="header">
        <div>
            <h2>User - Thêm Category</h2>
            <span class="role-badge">Role: User</span>
        </div>
        <div>
            <span>Chào mừng: ${sessionScope.user.fullname}</span>
            <a href="${pageContext.request.contextPath}/user/home" class="btn btn-primary">Về trang chủ</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn" style="background-color: #dc3545; color: white;">Đăng xuất</a>
        </div>
    </div>
    
    <div class="container">
        <h3>Thêm Category mới</h3>
        
        <c:if test="${not empty message}">
            <div class="alert">${message}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/user/category/add" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="name">Tên Category:</label>
                <input type="text" id="name" name="name" required>
            </div>
            
            <div class="form-group">
                <label for="icon">Icon (tùy chọn):</label>
                <input type="file" id="icon" name="icon" accept="image/*">
            </div>
            
            <div style="text-align: center;">
                <button type="submit" class="btn btn-primary">Thêm Category</button>
                <a href="${pageContext.request.contextPath}/user/category/list" class="btn btn-secondary">Hủy</a>
            </div>
        </form>
    </div>
</body>
</html>
