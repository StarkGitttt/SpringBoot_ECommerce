<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.main {
    display: flex;
    justify-content: center;
  }
  .form {
    width: 360px;
    min-height: 100px;
    padding: 32px 24px;
    text-align: center;
    background: #fff;
    border-radius: 2px;
    margin: 24px;
    align-self: center;
    box-shadow: 0 2px 5px 0 rgba(51, 62, 73, 0.26);
  }
  .form .heading {
    font-size: 2rem;
  }
  .form .desc {
    text-align: center;
    color: #636d77;
    font-size: 1.6rem;
    font-weight: lighter;
    line-height: 2.4rem;
    margin-top: 16px;
    font-weight: 300;
  }
  
  .form-group {
    display: flex;
    margin-bottom: 16px;
    flex-direction: column;
  }
  
  .form-label,
  .form-message {
    text-align: left;
  }
  
  .form-label {
    font-weight: 700;
    padding-bottom: 6px;
    line-height: 1.8rem;
    font-size: 1.2rem;
  }
  
  .form-control {
    height: 40px;
    padding: 8px 12px;
    border: 1px solid #b3b3b3;
    border-radius: 3px;
    outline: none;
    font-size: 1.2rem;
  }
  
  .form-control:hover {
    border-color: #1dbfaf;
  }
  
  .form-group.invalid .form-control {
    transition:0.8s ease;
    border-color: #f33a58;
  }
  
  .form-group.invalid .form-message {
    transition:0.8s ease;
    color: #f33a58;
  }
  
  .form-message {
    font-size: 1.2rem;
    line-height: 1.6rem;
    padding: 4px 0 0;
  }
  
  .form-submit {
    outline: none;
    background-color: #1dbfaf;
    margin-top: 12px;
    padding: 12px 16px;
    font-weight: 600;
    color: #fff;
    border: none;
    width: 100%;
    font-size: 14px;
    border-radius: 8px;
    cursor: pointer;
  }
  
  .form-submit:hover {
    background-color: #1ac7b6;
  }
  
  .spacer {
    margin-top: 36px;
  }
  textarea {
  	outline: none;
  }
</style>
</head>
<body>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Thông tin nhân viên</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="/admin/index">Tổng quan</a></li>
              <li class="breadcrumb-item active">Thông tin</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- Default box -->
      <div class="card card-solid">
        <div class="card-body pb-0">
          <div class="row">
          	<c:forEach var="user" items="${users}">
          	
	            <div class="col-12 col-sm-6 col-md-4 d-flex align-items-stretch flex-column">
	              <div class="card bg-light d-flex flex-fill">
	                <div class="card-header text-muted border-bottom-0">
	                  Quản lí
	                </div>
	                <div class="card-body pt-0">
	                  <div class="row">
	                    <div class="col-7">
	                      <h2 class="lead"><b>${user.fullname }</b></h2>
	                      <p class="text-muted text-sm"><b>About: </b> Web Designer / UX / Backend</p>
	                      <ul class="ml-4 mb-0 fa-ul text-muted">
	                        <li class="small emailTo"><span class="fa-li"><i class="fas fa-envelope"></i></span>${user.email}</li>
	                        <li class="small"><span class="fa-li"><i class="fas fa-lg fa-phone"></i></span>${user.phone}</li>
	                      </ul>
	                    </div>
	                    <div class="col-5 text-center">
	                      <img src="/assets/dist/img/user1-128x128.jpg" alt="user-avatar" class="img-circle img-fluid">
	                    </div>
	                  </div>
	                </div>
	                <div class="card-footer">
	                  <div class="text-right">
	                    <a href="#" class="btn btn-sm bg-teal clickEmailTo" data-toggle="modal" data-target="#myModal">
	                      <i class="fas fa-comments"></i>
	                    </a>
	                    <a href="/admin/view/detail/employee?id=${user.username }" class="btn btn-sm btn-primary">
	                      <i class="fas fa-user"></i> View Profile
	                    </a>
	                  </div>
	                </div>
	              </div>
	            </div>
          		
          	</c:forEach>
           
          </div>
        </div>
        <!-- /.card-body -->
        <div class="card-footer">
          <nav aria-label="Contacts Page Navigation">
            <ul class="pagination justify-content-center m-0">
            	<c:forEach varStatus="i" begin="1" end="${page.totalPages}">
	              <li class="page-item ${page.number+1==i.index ? 'active' : ''}"><a class="page-link" href="/admin/employee/views?pageNo=${i.index-1}">${i.index}</a></li>	
            	</c:forEach>
            </ul>
          </nav>
        </div>
        <!-- /.card-footer -->
      </div>
      <!-- /.card -->
	
    </section>
    <!-- /.content -->
    
     <!-- The Modal -->
  <div class="modal fade" id="myModal">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Gửi mail</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          	<div class="main">

      <form action="/admin/employee/sendmail" method="POST" class="form" id="form-1">

        
        <div class="form-group">
          <label for="emailTo" class="form-label">To</label>
          <input id="emailTo" name="emailTo" type="email" class="form-control" value="">
        </div>
        
          <div class="form-group">
	          <label class="form-label">Nội dung</label>
	          <textarea id="note" cols="" rows="4" placeholder="Type here..."  name="content"></textarea>
	          <span class="form-message success-system">${param.message}</span>
          </div>
          
        
        <button class="form-submit">Gửi</button>

      </form>
      
      </div>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
        </div>
        
      </div>
    </div>
  </div>
    
  </div>
  <!-- /.content-wrapper -->
  <script>
	var parentEmailElement = document.querySelectorAll('.clickEmailTo');
	var to = document.getElementById('emailTo');
	parentEmailElement.forEach( function(element) {
		element.onclick = function() {
				var parentGeneral = element.parentElement.parentElement.parentElement;
				var getEmailTo = parentGeneral.querySelector('.emailTo').innerText;
				to.setAttribute('value', getEmailTo);
			}
		})
  </script>
</body>
</html>