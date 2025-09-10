<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tất cả Category trong hệ thống</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
    }
    .container {
        max-width: 1400px;
        margin: 0 auto;
    }
    .header {
        text-align: center;
        margin-bottom: 30px;
    }
    .back-btn {
        background-color: #6c757d;
        color: white;
        padding: 10px 20px;
        text-decoration: none;
        border-radius: 5px;
        margin-bottom: 20px;
        display: inline-block;
    }
    .stats {
        background-color: #f8f9fa;
        padding: 15px;
        border-radius: 5px;
        margin-bottom: 20px;
        text-align: center;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }
    th, td {
        border: 1px solid #ddd;
        padding: 12px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
        text-align: center;
    }
    td {
        text-align: center;
    }
    .user-info {
        font-size: 12px;
        color: #666;
    }
    .role-badge {
        padding: 3px 8px;
        border-radius: 15px;
        font-size: 11px;
        font-weight: bold;
    }
    .role-user { background-color: #28a745; color: white; }
    .role-manager { background-color: #ffc107; color: #212529; }
    .role-admin { background-color: #dc3545; color: white; }
    .category-image {
        width: 80px;
        height: 60px;
        object-fit: cover;
        border-radius: 5px;
    }
</style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Tất cả Category trong hệ thống</h1>
            <a href="${pageContext.request.contextPath}/welcome" class="back-btn">← Về trang chủ</a>
            <a href="${pageContext.request.contextPath}/admin/category/list" class="back-btn" style="background-color: #007bff;">Quản lý Category của tôi</a>
        </div>
        
        <div class="stats">
            <h3>Thống kê: Có <strong>${allCategories.size()}</strong> category trong hệ thống</h3>
        </div>
        
        <table>
            <thead>
                <tr>
                    <th>STT</th>
                    <th>ID</th>
                    <th>Ảnh đại diện</th>
                    <th>Tên Category</th>
                    <th>Người tạo</th>
                    <th>Role</th>
                    <th>Ngày tạo</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${allCategories}" var="cate" varStatus="STT">
                    <tr>
                        <td>${STT.index+1}</td>
                        <td>${cate.cateId}</td>
                        <td>
                            <c:if test="${cate.icons != null && cate.icons != ''}">
                                <c:url value="/image?fname=${cate.icons}" var="imgUrl"></c:url>
                                <img src="${imgUrl}" alt="${cate.cateName}" class="category-image" />
                            </c:if>
                            <c:if test="${cate.icons == null || cate.icons == ''}">
                                <span>Không có ảnh</span>
                            </c:if>
                        </td>
                        <td><strong>${cate.cateName}</strong></td>
                        <td>
                            <c:if test="${cate.user != null}">
                                <div>${cate.user.fullname}</div>
                                <div class="user-info">(${cate.user.username})</div>
                                <div class="user-info">${cate.user.email}</div>
                            </c:if>
                            <c:if test="${cate.user == null}">
                                <span style="color: #999;">Không xác định</span>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${cate.user != null}">
                                <c:choose>
                                    <c:when test="${cate.user.roleid == 1}">
                                        <span class="role-badge role-user">User</span>
                                    </c:when>
                                    <c:when test="${cate.user.roleid == 2}">
                                        <span class="role-badge role-manager">Manager</span>
                                    </c:when>
                                    <c:when test="${cate.user.roleid == 3}">
                                        <span class="role-badge role-admin">Admin</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="role-badge" style="background-color: #6c757d; color: white;">Unknown</span>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                            <c:if test="${cate.user == null}">
                                <span style="color: #999;">-</span>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${cate.createdDate != null}">
                                ${cate.createdDate}
                            </c:if>
                            <c:if test="${cate.createdDate == null}">
                                <span style="color: #999;">Không có</span>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <c:if test="${empty allCategories}">
            <div style="text-align: center; margin-top: 50px;">
                <h3>Chưa có category nào trong hệ thống</h3>
                <p style="color: #666;">Hãy tạo category đầu tiên!</p>
            </div>
        </c:if>
    </div>
</body>
</html>
