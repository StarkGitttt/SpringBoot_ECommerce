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
            <form action="/forgot/password/getpass" class="form-edit" style="font-size: 2rem" method="post"  >
            
                <div class="row">
                  <div class="col">
                    <label for="username">Tên tài khoản: </label>
                    <input type="text" class="form-control" id="username" name="username" value="${accountDTO.username }" disabled/>
                  </div>
                </div>
   
                <div class="row" style="margin-top: 15px;">
                   <div class="col">
	                    <label for="code">Mật khẩu mới</label>
	                    <input type="password" class="form-control" id="code" name="password" />         
                  </div>
                  
                 </div>
                 
                  <div class="row" style="margin-top: 15px;">
                  <div class="col">
	                    <label for="code">Nhập lại mật khẩu</label>
	                    <input type="password" class="form-control" id="code" name="confirmPassword" />         
				           <c:if test="${messageErr != null}">
				              <p class="error-system">${messageErr}</p>
				          </c:if>
                  </div>
                 </div>
                                 
                   <c:if test="${messageSuccess != null}">
		              <p class="success-system">${messageSuccess}</p>
		          </c:if>
		          
             	 <button type="submit" class="btn-default" style="margin-top: 30px;">Cập nhập mật khẩu</button>
             	 
            </form>
        </section>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
	    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>