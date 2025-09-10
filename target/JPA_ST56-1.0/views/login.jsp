<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    .login-container {
        background-color: white;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
        width: 400px;
    }
    .login-header {
        text-align: center;
        margin-bottom: 30px;
        color: #333;
    }
    .form-group {
        margin-bottom: 20px;
    }
    .form-group label {
        display: block;
        margin-bottom: 5px;
        color: #555;
    }
    .form-group input {
        width: 100%;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 5px;
        box-sizing: border-box;
    }
    .btn {
        width: 100%;
        padding: 12px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
    }
    .btn:hover {
        background-color: #0056b3;
    }
    .alert {
        padding: 10px;
        margin-bottom: 20px;
        border-radius: 5px;
        background-color: #f8d7da;
        color: #721c24;
        border: 1px solid #f5c6cb;
    }
</style>
</head>
<body>
    <div class="login-container">
        <div class="login-header">
            <h2>Đăng nhập hệ thống</h2>
        </div>
        
        <c:if test="${not empty alert}">
            <div class="alert">${alert}</div>
        </c:if>
        
        <c:if test="${not empty success}">
            <div style="padding: 10px; margin-bottom: 20px; border-radius: 5px; background-color: #d4edda; color: #155724; border: 1px solid #c3e6cb;">
                ${success}
            </div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label for="username">Tên đăng nhập:</label>
                <input type="text" id="username" name="username" required>
            </div>
            
            <div class="form-group">
                <label for="password">Mật khẩu:</label>
                <input type="password" id="password" name="password" required>
            </div>
            
            <button type="submit" class="btn">Đăng nhập</button>
        </form>
        
        <div style="margin-top: 20px; text-align: center;">
            <p><a href="${pageContext.request.contextPath}/register" style="color: #007bff; text-decoration: none;">Đăng ký tài khoản mới</a></p>
            <p><a href="${pageContext.request.contextPath}/forgot-password" style="color: #007bff; text-decoration: none;">Quên mật khẩu?</a></p>
            <hr>
            <p style="color: #666; font-size: 14px;">Tài khoản demo:</p>
            <p style="color: #666; font-size: 12px;">user/password (Role: User)<br>
               manager/password (Role: Manager)<br>
               admin/password (Role: Admin)</p>
        </div>
    </div>
</body>
</html>
