<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Danh mục</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="/admin/index">Tổng quan</a></li>
              <li class="breadcrumb-item active">Danh mục</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row justify-content-center">
          <!-- left column -->
          <div class="col-6">
            <!-- general form elements -->
            <div class="card card-info">
              <div class="card-header">
                <h3 class="card-title">Danh mục</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form:form action="/admin/manage/category/add" method="post" modelAttribute="categoryDTO" >
                <div class="card-body">
                 <div class="row">            
	                  <div class="form-group col-12">
	                    <label for="exampleInputEmail1">Mã danh mục</label>
	                    <form:input type="text" cssClass="form-control" path="id" />
	                    <form:errors path="id" element="p" cssClass="error-system"></form:errors>
	                  </div> 
                 </div>
                 
                  <div class="row">            
	                  <div class="form-group col-12">
	                     <label for="exampleInputEmail1">Tên danh mục</label>
                   		 <form:input type="text" cssClass="form-control"  path="name" />
                   		 <form:errors path="name" element="p" cssClass="error-system"></form:errors>
	                   		 <c:if test="${existCategory != null }">
		                    	<p class="error-system">${existCategory } </p>
		                    </c:if>
	                  </div>      
                 </div>
                    <div class="row">            
	                  <div class="form-group col-12">
	                      <c:if test="${successAdd != null }">
		                    	<p class="success-system">${successAdd } </p>
		                    </c:if>
	                  </div>      
                 </div>
                 
                  
		            
                </div>
                <!-- /.card-body -->
                <div class="card-footer">
                  <button type="submit" class="btn btn-info">Thêm</button>
                </div>
              </form:form>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</body>
</html>