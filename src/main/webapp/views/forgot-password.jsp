<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quên mật khẩu</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
    }
    .forgot-container {
        background-color: white;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
        width: 450px;
    }
    .forgot-header {
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
        font-weight: bold;
    }
    .form-group input {
        width: 100%;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 5px;
        box-sizing: border-box;
    }
    .form-row {
        display: flex;
        gap: 15px;
        margin-bottom: 20px;
    }
    .btn {
        width: 100%;
        padding: 12px;
        background-color: #ffc107;
        color: #212529;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
        margin-bottom: 10px;
    }
    .btn:hover {
        background-color: #e0a800;
    }
    .btn-secondary {
        background-color: #6c757d;
        color: white;
    }
    .btn-secondary:hover {
        background-color: #545b62;
    }
    .alert {
        padding: 10px;
        margin-bottom: 20px;
        border-radius: 5px;
        background-color: #f8d7da;
        color: #721c24;
        border: 1px solid #f5c6cb;
    }
    .info {
        padding: 15px;
        margin-bottom: 20px;
        border-radius: 5px;
        background-color: #d1ecf1;
        color: #0c5460;
        border: 1px solid #bee5eb;
    }
    .links {
        text-align: center;
        margin-top: 20px;
    }
    .links a {
        color: #007bff;
        text-decoration: none;
        margin: 0 10px;
    }
    .links a:hover {
        text-decoration: underline;
    }
</style>
</head>
<body>
    <div class="forgot-container">
        <div class="forgot-header">
            <h2>Quên mật khẩu</h2>
        </div>
        
        <div class="info">
            <strong>Hướng dẫn:</strong> Để đặt lại mật khẩu, vui lòng nhập đúng tên đăng nhập, email và số điện thoại đã đăng ký.
        </div>
        
        <c:if test="${not empty alert}">
            <div class="alert">${alert}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/forgot-password" method="post">
            <div class="form-group">
                <label for="username">Tên đăng nhập:</label>
                <input type="text" id="username" name="username" required 
                       value="${param.username}" placeholder="Nhập tên đăng nhập của bạn...">
            </div>
            
            <div class="form-group">
                <label for="email">Email đã đăng ký:</label>
                <input type="email" id="email" name="email" required 
                       value="${param.email}" placeholder="Nhập email đã đăng ký...">
            </div>
            
            <div class="form-group">
                <label for="phone">Số điện thoại đã đăng ký:</label>
                <input type="tel" id="phone" name="phone" required 
                       value="${param.phone}" placeholder="Nhập số điện thoại đã đăng ký...">
            </div>
            
            <div class="form-row">
                <div class="form-group">
                    <label for="newPassword">Mật khẩu mới:</label>
                    <input type="password" id="newPassword" name="newPassword" required 
                           placeholder="Tối thiểu 6 ký tự...">
                </div>
                <div class="form-group">
                    <label for="confirmPassword">Xác nhận mật khẩu:</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" required 
                           placeholder="Nhập lại mật khẩu mới...">
                </div>
            </div>
            
            <button type="submit" class="btn">Đặt lại mật khẩu</button>
            <a href="${pageContext.request.contextPath}/login" class="btn btn-secondary">Hủy</a>
        </form>
        
        <div class="links">
            <a href="${pageContext.request.contextPath}/login">Quay lại đăng nhập</a>
            <a href="${pageContext.request.contextPath}/register">Đăng ký tài khoản mới</a>
        </div>
    </div>
</body>
</html>
