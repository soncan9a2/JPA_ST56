<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm Category</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 20px;
        background-color: #f4f4f4;
    }
    .header {
        background-color: #ffc107;
        color: #212529;
        padding: 15px;
        border-radius: 5px;
        margin-bottom: 20px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .container {
        background-color: white;
        padding: 30px;
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
        color: #555;
        font-weight: bold;
    }
    .form-group input[type="text"],
    .form-group input[type="file"] {
        width: 100%;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 5px;
        box-sizing: border-box;
    }
    .btn {
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        text-decoration: none;
        display: inline-block;
        margin: 5px;
    }
    .btn-success {
        background-color: #28a745;
        color: white;
    }
    .btn-secondary {
        background-color: #6c757d;
        color: white;
    }
    .btn:hover {
        opacity: 0.8;
    }
    .form-actions {
        text-align: center;
        margin-top: 30px;
    }
</style>
</head>
<body>
    <div class="header">
        <div>
            <h2>Thêm Category mới</h2>
        </div>
        <div>
            <span>Manager: ${sessionScope.user.fullname}</span>
        </div>
    </div>
    
    <div class="container">
        <form action="${pageContext.request.contextPath}/manager/category/add" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="cateName">Tên Category:</label>
                <input type="text" id="cateName" name="cateName" required 
                       placeholder="Nhập tên category...">
            </div>
            
            <div class="form-group">
                <label for="icons">Icon:</label>
                <input type="file" id="icons" name="icons" accept="image/*">
                <small style="color: #666; font-style: italic;">Chọn file hình ảnh để làm icon cho category</small>
            </div>
            
            <div class="form-actions">
                <button type="submit" class="btn btn-success">Thêm Category</button>
                <a href="${pageContext.request.contextPath}/manager/home" class="btn btn-secondary">Hủy</a>
            </div>
        </form>
    </div>
</body>
</html>
