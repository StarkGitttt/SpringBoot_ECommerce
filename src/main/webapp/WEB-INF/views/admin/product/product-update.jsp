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
            <h1>Cập nhập sản phẩm</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="/admin/index">Home</a></li>
              <li class="breadcrumb-item active">Cập nhập sản phẩm</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <!-- left column -->
          <div class="col-12">
            <!-- general form elements -->
            <div class="card card-info">
              <div class="card-header">
                <h3 class="card-title">Cập nhập sản phẩm</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form:form action="/admin/manage/product/update?id=${product.id}" method="post" modelAttribute="product" 
              enctype="multipart/form-data">
                <div class="card-body">
                 <div class="row">            
	                  <div class="form-group col-6">
	                    <label for="exampleInputEmail1">Tên sản phẩm</label>
	                    <form:input type="text" cssClass="form-control" path="name"  />             
	                  </div>
	                  
	                  <div class="form-group col-6">
	                    <label for="exampleInputEmail1">Giá</label>
	                    <form:input type="number" cssClass="form-control "  path="price" />
	                  </div>        
                 </div>
                 
                  <div class="row">            
	                  <div class="form-group col-6">
	                     <label for="exampleInputEmail1">Số lượng</label>
                   		 <form:input type="number" cssClass="form-control"  path="quantity"  />
                   		
	                  </div>
	                  
	                  <div class="form-group col-6">
	                     <label for="exampleInputEmail1">Giảm giá (nếu có)</label>
                    	<form:input type="number" cssClass="form-control "  path="discount" />
	                  </div>            
                 </div>
                 
                  <div class="row">            
	                  <div class="form-group col-12">
	                      <label for="exampleInputEmail1">Link hình</label>
                    	  <form:input type="text" cssClass="form-control " path="image"/>
	                  </div>                 
                 </div>


                  <div class="form-group">
                    <label for="exampleInputFile">File input</label>
                    <div class="input-group">
                        <input type="file"  id="exampleInputFile" name="attach" 
                        style="border: 1px solid gray; padding: 10px 5px; width:100%">
                    </div>
                  </div>
                  
                  	<c:if test="${saveSuccess != null }">
						<div class="form-group">
		                   		<p class="success-system">${saveSuccess}</p>					
						</div>
		            </c:if>
                  
                </div>
				
                <div class="card-footer">
                  <button type="submit" class="btn btn-info">Cập nhập</button>
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