<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<title>Fashion Shop</title>
</head>
<body>
        <section class="edit-info">
            <h2>Chỉnh sửa thông tin cá nhân</h2>
            <form:form action="/update/info" class="form-edit" style="font-size: 2rem"
            method="post" modelAttribute="account">
                <div class="row">
                  <div class="col">
                    <label for="username">Tài khoản:</label>
                    <form:input type="text" cssClass="form-control" id="username"
                     placeholder="Enter username" name="username" path="username" />
                    <form:errors  path="username" element="p" cssClass="error-system" />
                  </div>
                  <div class="col">
                    <label for="fullname">Họ và tên:</label>
                    <form:input type="text" cssClass="form-control"
                     placeholder="Enter fullname" name="fullname" path="fullname" />
                    <form:errors  path="fullname" element="p" cssClass="error-system" />
                  </div>
                </div>
                <div class="row" style="margin-top: 15px;">
                    <div class="col">
                      <label for="email">Email:</label>
                      <form:input type="email" cssClass="form-control" id="email"
                       placeholder="Enter email" name="email" path="email" />
                       <form:errors  path="email" element="p" cssClass="error-system" />
                    </div>
                    <div class="col">
                      <label for="email">Số điện thoại:</label>
                      <form:input type="text" cssClass="form-control"
                       placeholder="Enter phone" name="phone" path="phone" />
                       <form:errors  path="phone" element="p" cssClass="error-system" />
                    </div>
                  </div>
                  <c:if test="${messageErr != null}">
                      <p class="error-system">${messageErr}</p>
                  </c:if>
                  <c:if test="${messageSuccess != null}">
                      <p class="success-system">${messageSuccess}</p>
                  </c:if>
                <input type="submit" value="Cập nhập" class="btn-default" style="margin-top: 30px;">
            </form:form>
        </section>
        
	    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
	    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>