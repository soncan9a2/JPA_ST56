<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách Category</title>
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

.management-section {
	background: white;
	border-radius: 5px;
	padding: 20px;
	box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.search-area {
	display: flex;
	gap: 10px;
	margin-bottom: 20px;
	align-items: center;
}

.search-input {
	padding: 8px 12px;
	border: 1px solid #ddd;
	border-radius: 4px;
	flex-grow: 1;
	max-width: 300px;
}

.btn {
	padding: 8px 16px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	text-decoration: none;
	font-size: 14px;
	display: inline-block;
}

.btn-search {
	background-color: #6c757d;
	color: white;
}

.btn-add {
	background-color: #17a2b8;
	color: white;
	margin-left: auto;
}

.btn:hover {
	opacity: 0.9;
}

.table-section {
	background: #d1ecf1;
	padding: 15px;
	border-radius: 5px;
	margin-bottom: 20px;
}

.table-title {
	margin: 0;
	color: #0c5460;
	font-size: 16px;
	font-weight: normal;
}

.data-table {
	width: 100%;
	border-collapse: collapse;
	background: white;
	border-radius: 5px;
	overflow: hidden;
	box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.data-table th {
	background-color: #f8f9fa;
	padding: 12px;
	text-align: left;
	border-bottom: 1px solid #dee2e6;
	font-weight: bold;
	color: #495057;
}

.data-table td {
	padding: 12px;
	border-bottom: 1px solid #dee2e6;
}

.data-table tr:hover {
	background-color: #f8f9fa;
}

.icon-img {
	width: 40px;
	height: 40px;
	object-fit: cover;
	border-radius: 4px;
}

.action-links a {
	color: #007bff;
	text-decoration: none;
	margin-right: 10px;
}

.action-links a:hover {
	text-decoration: underline;
}

.action-links .delete-link {
	color: #dc3545;
}

.no-data {
	text-align: center;
	padding: 40px;
	color: #6c757d;
}
</style>
</head>
<body>
	<div class="header">
		<span class="icon">📁</span>
		<h2>Giao diện</h2>
	</div>

	<div class="management-section">
		<h3>Quản lý danh mục</h3>
		
		<div class="search-area">
			<input type="text" class="search-input" placeholder="Tìm kiếm...">
			<button class="btn btn-search">Search</button>
			<a href="${pageContext.request.contextPath}/admin/category/add" class="btn btn-add">Thêm danh mục</a>
		</div>
		
		<div class="table-section">
			<h4 class="table-title">Danh sách danh mục</h4>
		</div>
		
		<table class="data-table">
			<thead>
				<tr>
					<th>ID</th>
					<th>Tên danh mục</th>
					<th>Icon</th>
					<th>Hành động</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listcate}" var="cate">
					<tr>
						<td>${cate.cateId}</td>
						<td>${cate.cateName}</td>
						<td>
							<c:if test="${cate.icons != null && !empty cate.icons}">
								<img src="${pageContext.request.contextPath}/uploads/category/${cate.icons}" 
									 alt="${cate.cateName}" class="icon-img">
							</c:if>
							<c:if test="${cate.icons == null || empty cate.icons}">
								<img src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iNDAiIGhlaWdodD0iNDAiIHZpZXdCb3g9IjAgMCA0MCA0MCIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPHJlY3Qgd2lkdGg9IjQwIiBoZWlnaHQ9IjQwIiBmaWxsPSIjZjVmNWY1Ii8+CjxwYXRoIGQ9Ik0yMCAyMEM4Ljk1NDMgMjAgMjAgOC45NTQzIDIwIDIwUzguOTU0MyAyMCAyMCAyMFoiIGZpbGw9IiNkZGQiLz4KPC9zdmc+" class="icon-img" alt="No icon">
							</c:if>
						</td>
						<td class="action-links">
							<a href="${pageContext.request.contextPath}/admin/category/edit?id=${cate.cateId}">Cập nhật</a>
							<span>|</span>
							<a href="${pageContext.request.contextPath}/admin/category/delete?id=${cate.cateId}" 
							   class="delete-link"
							   onclick="return confirm('Bạn có chắc chắn muốn xóa?')">Xóa</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<c:if test="${empty listcate}">
			<div class="no-data">
				<p>Không có dữ liệu category nào.</p>
			</div>
		</c:if>
	</div>
</body>
</html>