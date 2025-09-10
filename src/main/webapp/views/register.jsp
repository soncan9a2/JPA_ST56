<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký tài khoản</title>
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
    .register-container {
        background-color: white;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
        width: 500px;
    }
    .register-header {
        text-align: center;
        margin-bottom: 30px;
        color: #333;
    }
    .form-row {
        display: flex;
        gap: 15px;
        margin-bottom: 20px;
    }
    .form-group {
        flex: 1;
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
    .required {
        color: red;
    }
    .btn {
        width: 100%;
        padding: 12px;
        background-color: #28a745;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
        margin-bottom: 10px;
    }
    .btn:hover {
        background-color: #218838;
    }
    .btn-secondary {
        background-color: #6c757d;
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
    .text-center {
        text-align: center;
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
    <div class="register-container">
        <div class="register-header">
            <h2>Đăng ký tài khoản</h2>
        </div>
        
        <c:if test="${not empty alert}">
            <div class="alert">${alert}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/register" method="post">
            <div class="form-group">
                <label for="username">Tên đăng nhập <span class="required">*</span></label>
                <input type="text" id="username" name="username" required 
                       value="${param.username}" placeholder="Nhập tên đăng nhập...">
            </div>
            
            <div class="form-row">
                <div class="form-group">
                    <label for="password">Mật khẩu <span class="required">*</span></label>
                    <input type="password" id="password" name="password" required 
                           placeholder="Tối thiểu 6 ký tự...">
                </div>
                <div class="form-group">
                    <label for="confirmPassword">Xác nhận mật khẩu <span class="required">*</span></label>
                    <input type="password" id="confirmPassword" name="confirmPassword" required 
                           placeholder="Nhập lại mật khẩu...">
                </div>
            </div>
            
            <div class="form-group">
                <label for="fullname">Họ và tên <span class="required">*</span></label>
                <input type="text" id="fullname" name="fullname" required 
                       value="${param.fullname}" placeholder="Nhập họ và tên...">
            </div>
            
            <div class="form-group">
                <label for="email">Email <span class="required">*</span></label>
                <input type="email" id="email" name="email" required 
                       value="${param.email}" placeholder="Nhập địa chỉ email...">
            </div>
            
            <div class="form-group">
                <label for="phone">Số điện thoại</label>
                <input type="tel" id="phone" name="phone" 
                       value="${param.phone}" placeholder="Nhập số điện thoại...">
            </div>
            
            <button type="submit" class="btn">Đăng ký</button>
            <a href="${pageContext.request.contextPath}/login" class="btn btn-secondary">Hủy</a>
        </form>
        
        <div class="links">
            <a href="${pageContext.request.contextPath}/login">Đã có tài khoản? Đăng nhập</a>
            <a href="${pageContext.request.contextPath}/forgot-password">Quên mật khẩu?</a>
        </div>
    </div>
</body>
</html>
