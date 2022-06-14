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
            <h2>Lấy lại mật khẩu</h2>
            <form:form action="/forgot/password/getcode" class="form-edit" style="font-size: 2rem" method="post" 
             modelAttribute="accountDTO">
            
                <div class="row">
                  <div class="col">
                    <label for="username">Tên tài khoản: </label>
                    <form:input type="text" cssClass="form-control" id="username" name="username" path="username" />
                   	<form:errors  path="username" element="p" cssClass="error-system" />
                   	<c:if test="${notExistAccount != null}">
		              <p class="error-system">${notExistAccount}</p>
		          </c:if>
                  </div>
                </div>
   
                <div class="row" style="margin-top: 15px;">
                   <div class="col">
	                    <label for="code">Mã xác thực</label>
	                    <input type="number" class="form-control" id="code" name="code" />         
	                    <form:errors  path="code" element="p" cssClass="error-system" /> 
                  </div>
                 </div>
                  <c:if test="${message != null}">
		              <p class="success-system">${message}</p>
		          </c:if>
		           <c:if test="${notMatchCode != null}">
		              <p class="error-system">${notMatchCode}</p>
		          </c:if>
             	 <button type="submit" class="btn-default" style="margin-top: 30px;">Nhận mã</button>
             	 <button type="submit"  formaction="/fotgot/password/verificode"
             	 	class="btn-default" style="margin-top: 30px;" ${isGetCode ? '' : 'hidden' }>Lấy lại mật khẩu
             	 </button>
             	 
            </form:form>
        </section>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
	    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>