<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang>
<head>
<meta charset="UTF-8">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"  crossorigin="anonymous">
<title>Sign up</title>
</head>
<body>
  <section class="edit-info">
    <h2>Đăng ký tài khoản</h2>
    <form:form action="/signup" class="form-edit" style="font-size: 2rem"
    method="post" modelAttribute="account" enctype="multipart/form-data">

        <div class="row">
          <div class="col">
            <label for="fullname">Họ và tên:</label>
            <form:input type="text" cssClass="form-control"
             placeholder="Enter fullname" name="fullname" path="fullname" />
            <form:errors  path="fullname" element="p" cssClass="error-system" />
          </div>
          <div class="col">
            <label for="username">Tài khoản:</label>
            <form:input type="text" cssClass="form-control" id="username"
             placeholder="Enter username" name="username" path="username" />
            <form:errors  path="username" element="p" cssClass="error-system" />
            <c:if test="${errExistAcc != null}">
              <p class="error-system">${errExistAcc}</p>
            </c:if>
          </div>
        </div>

        <div class="row" style="margin-top: 15px;">
          <div class="col">
            <label for="password">Mật khẩu:</label>
            <form:input type="password" cssClass="form-control" id="password"
             placeholder="Enter password" name="password" path="password" />
             <form:errors  path="password" element="p" cssClass="error-system" />
          </div>
            <div class="col">
              <label for="confirm_pw">Nhập lại mật khẩu:</label>
              <input type="password" class="form-control" id="confirm_pw"
               placeholder="Enter password" name="confirm_pw" />
              <c:if test="${errConfirmPw != null}">
                <p class="error-system">${errConfirmPw}</p>
              </c:if>
            </div>
          </div>

          <div class="row"  style="margin-top: 15px;">
            <div class="col">
              <label for="email">Số điện thoại:</label>
              <form:input type="text" cssClass="form-control"
               placeholder="Enter phone" name="phone" path="phone" />
               <form:errors  path="phone" element="p" cssClass="error-system" />
              <c:if test="${errPhone != null}">
                <p class="error-system">${errPhone}</p>
              </c:if>
            </div>
            <div class="col">
              <label for="email">Email:</label>
              <form:input type="email" cssClass="form-control" id="email"
               placeholder="Enter email" name="email" path="email" />
               <form:errors  path="email" element="p" cssClass="error-system" />
            </div>
          </div>

          <div class="row"  style="margin-top: 15px;">
            <div class="col">
              <label for="file">Hình đại diện:</label>
              <input type="file" class="form-control" name="attach" id="file" style="padding: 10px 0px 37px 10px !important;"/>
            </div>
          </div>

          <c:if test="${messageSuccess != null}">
              <p class="success-system">${messageSuccess}</p>
          </c:if>
        <input type="submit" value="Đăng ký" class="btn-default" style="margin-top: 30px;">
    </form:form>
</section>
<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>