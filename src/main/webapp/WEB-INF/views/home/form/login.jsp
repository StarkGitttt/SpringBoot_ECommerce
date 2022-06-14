<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.7.2/css/all.min.css" integrity="sha512-3M00D/rn8n+2ZVXBO9Hib0GKNpkm8MSUU/e2VNthDyBYxKWG+BftNYYcuEjXlyrSO637tidzMBXfE7sQm0INUg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="/assets/css/form.css">
<title>Login</title>
</head>
<body style="height: 100vh">
<div class="main">
        <form:form action="/login" method="post" class="form" id="form-1" modelAttribute="account">
            <h3 class="heading">ĐĂNG NHẬP</h3>
            <div class="spacer"></div>
        
            <div class="form-group">
              <label for="username" class="form-label">Tài khoản</label>
              <form:input id="username" name="username" path="username"
               type="text" placeholder="VD: email@domain.com" class="form-control email-signUp" />     
               <form:errors  path="username" element="label" cssClass="form-label error-system"/>        
                <c:if test="${notExistAccount != null}">
                  <label class="form-label form-errror" style="margin-top: 10px">${notExistAccount}</label>
                </c:if>
                <c:if test="${disabledAccount != null}">
                  <label class="form-label form-errror" style="margin-top: 10px">${disabledAccount}</label>
                </c:if>
            </div>
        
            <div class="form-group">
              <label for="password" class="form-label">Mật khẩu</label>
              <input id="password" name="password"
               type="password" placeholder="Nhập mật khẩu" class="form-control password-signUp">
               <form:errors  path="password" element="label" cssClass="form-label error-system"/>
                <c:if test="${param.message != null}">
	                <label class="form-label form-errror" style="margin-top: 10px;">${param.message}</label>
                </c:if>
            </div>
         	<!--  
            <div class="form-group">
                <a class="login-google" href="http://accounts.google.com/o/oauth2/auth?scope=email
                &redirect_uri=http://localhost:8080/login
                &response_type=code
                &client_id=936408264015-t8h56ddde9r8dhkcqiasmk1c36ula55j.apps.googleusercontent.com
                &approval_prompt=force"><i class="fab fa-google"></i> Đăng nhập với Google</a>
            </div>
           -->
            <div class="form-group" style="display: flex; flex-direction: row;justify-content: space-around;">
              <label for="password" class="form-label">
                  <a href="/signup" style="text-decoration: none">Tạo tài khoản mới?</a>
              </label>
              <label for="password" class="form-label">
                  <a href="/forgot/password" style="text-decoration: none">Quên mật khẩu?</a>
              </label>
            </div>
            <button class="form-submit">Đăng nhập</button>
          </form:form>
    </div>
</body>
</html>