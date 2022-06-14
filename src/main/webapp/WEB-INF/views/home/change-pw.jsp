<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<title>Fashion Shop</title>
</head>
<body>
        <section class="edit-info">
            <h2>Đổi mật khẩu</h2>
            <form:form action="/update/password" class="form-edit" style="font-size: 2rem"
            method="post" modelAttribute="accountDTO">
            
                <div class="row">
                  <div class="col">
                    <label for="old">Mật khẩu cũ:</label>
                    <form:input type="password" cssClass="form-control" id="old" name="oldPassword" path="oldPassword" />
                    <form:errors  path="oldPassword" element="p" cssClass="error-system" />
                  </div>
                </div>
                
                 <div class="row" style="margin-top: 15px;">
                    <div class="col">
                      <label for="new">Mật khẩu mới:</label>
                      <form:input type="password" class="form-control" id="new" name="newPassword" path="newPassword"/>
                       <form:errors  path="newPassword" element="p" cssClass="error-system" />
                    </div>
                 </div>
                 
                 
                <div class="row" style="margin-top: 15px;">
                    <div class="col">
                      <label for="confirm">Xác nhận mật khẩu:</label>
                      <input type="password" class="form-control" id="confirm" name="confirmPassword"  />
                       <form:errors  path="confirmPassword" element="p" cssClass="error-system" />
                       <c:if test="${messageErr != null}">
                      		<p class="error-system">${messageErr}</p>
                  	   </c:if>
                    </div>
                 </div>
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