<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sửa Category</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 20px;
	background-color: #f8f9fa;
}

.header {
	background-color: #007bff;
	color: white;
	padding: 15px 20px;
	margin: -20px -20px 20px -20px;
	display: flex;
	align-items: center;
}

.header h2 {
	margin: 0;
	font-size: 18px;
}

.header .icon {
	margin-right: 10px;
	font-size: 18px;
}

.form-container {
	background: white;
	border-radius: 5px;
	padding: 30px;
	box-shadow: 0 2px 4px rgba(0,0,0,0.1);
	max-width: 600px;
	margin: 0 auto;
}

.form-title {
	margin-bottom: 30px;
	color: #333;
	border-bottom: 2px solid #007bff;
	padding-bottom: 10px;
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

.form-group input[type="text"],
.form-group input[type="file"] {
	width: 100%;
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 4px;
	box-sizing: border-box;
	font-size: 14px;
}

.form-group input[type="text"]:focus {
	border-color: #007bff;
	outline: none;
	box-shadow: 0 0 5px rgba(0,123,255,0.3);
}

.form-group small {
	color: #6c757d;
	font-size: 12px;
	margin-top: 5px;
	display: block;
}

.current-icon {
	margin-top: 15px;
	padding: 15px;
	background-color: #f8f9fa;
	border-radius: 4px;
	border: 1px solid #dee2e6;
}

.current-icon img {
	width: 100px;
	height: 100px;
	object-fit: cover;
	border-radius: 4px;
	border: 1px solid #ddd;
}

.form-actions {
	text-align: center;
	margin-top: 30px;
	padding-top: 20px;
	border-top: 1px solid #eee;
}

.btn {
	padding: 10px 20px;
	margin: 0 10px;
	border: none;
	border-radius: 4px;
	text-decoration: none;
	font-size: 14px;
	cursor: pointer;
	display: inline-block;
}

.btn-primary {
	background-color: #007bff;
	color: white;
}

.btn-primary:hover {
	background-color: #0056b3;
}

.btn-secondary {
	background-color: #6c757d;
	color: white;
}

.btn-secondary:hover {
	background-color: #545b62;
}

.breadcrumb {
	margin-bottom: 20px;
	color: #6c757d;
}

.breadcrumb a {
	color: #007bff;
	text-decoration: none;
}

.breadcrumb a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<div class="header">
		<span class="icon">📁</span>
		<h2>Giao diện</h2>
	</div>

	<div class="breadcrumb">
		<a href="${pageContext.request.contextPath}/admin/categories">Quản lý danh mục</a> &gt; Sửa danh mục
	</div>

	<div class="form-container">
		<h3 class="form-title">Sửa Category</h3>
		
		<form action="${pageContext.request.contextPath}/admin/category/update" method="post" enctype="multipart/form-data">
			<input type="hidden" name="cateid" value="${cate.cateId}">
			
			<div class="form-group">
				<label for="catename">Tên Category:</label>
				<input type="text" id="catename" name="catename" value="${cate.cateName}" required>
			</div>
			
			<div class="form-group">
				<label for="icons">Icon:</label>
				<input type="file" id="icons" name="icons" accept="image/*">
				<small>Chọn file mới để thay đổi icon (để trống nếu muốn giữ nguyên)</small>
				
				<c:if test="${cate.icons != null && !empty cate.icons}">
					<div class="current-icon">
						<strong>Icon hiện tại:</strong><br><br>
						<img src="${pageContext.request.contextPath}/uploads/category/${cate.icons}" 
							 alt="${cate.cateName}">
					</div>
				</c:if>
			</div>
			
			<div class="form-actions">
				<button type="submit" class="btn btn-primary">Cập nhật Category</button>
				<a href="${pageContext.request.contextPath}/admin/categories" class="btn btn-secondary">Hủy</a>
			</div>
		</form>
	</div>
</body>
</html>