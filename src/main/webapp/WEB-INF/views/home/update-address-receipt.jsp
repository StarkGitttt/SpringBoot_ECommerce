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
            <h2>Thông tin nhận hàng</h2>
            <form:form action="/order/update/address/receipt?id=${param.id}" class="form-edit" style="font-size: 2rem"
            method="post" modelAttribute="placeReceipt">
            
                <div class="row">
                
                  <div class="col">
                    <label for="username">Họ và tên:</label>
                    <form:input type="text" cssClass="form-control" 
                     placeholder="Enter fullname" name="fullname" path="fullname" />
                    <form:errors  path="fullname" element="p" cssClass="error-system" />
                  </div>
                  
                  <div class="col">
                    <label for="fullname">Số điện thoại:</label>
                    <form:input type="text" cssClass="form-control"
                     placeholder="Enter phone" name="phone" path="phone" />
                    <form:errors  path="phone" element="p" cssClass="error-system" />
                  </div>
                </div>
                
                <div class="row" style="margin-top: 15px;">
                
                    <div class="col">
                      <label for="email">Email xác nhận đơn hàng:</label>
                      <form:input type="email" cssClass="form-control" id="email"
                       placeholder="Enter email" name="email" path="email" />
                       <form:errors  path="email" element="p" cssClass="error-system" />
                    </div>
                    
                    <div class="col">
                      <label for="email">Địa chỉ nhận hàng:</label>
                      <form:input type="text" cssClass="form-control"
                       placeholder="Enter address" name="address" path="address" />
                       <form:errors  path="address" element="p" cssClass="error-system" />
                    </div>
                    
               </div>
                  
                   <div class="row" style="margin-top: 15px;">
	                      <div class="col">
	                      <label for="email">Ghi chú:</label>
				          <textarea name="note" rows="4" placeholder="Type here..."  class="form-control" ></textarea>
	                    </div>
                  </div>
	                    <c:if test="${messageSuccess != null}">
		                      <p class="success-system">${messageSuccess}</p>
		                 </c:if>
                  

                <input type="submit" value="Xác nhận" class="btn-default" style="margin-top: 30px;">
            </form:form>
        </section>
        
	    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
	    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>