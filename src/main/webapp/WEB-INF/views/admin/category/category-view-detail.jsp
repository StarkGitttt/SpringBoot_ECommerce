<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
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
            <h1>Chi tiết danh mục</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="/admin/index">Tổng quan</a></li>
              <li class="breadcrumb-item active">Chi tiết</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-12">
            <div class="card">
              <div class="card-header">
                <h3 class="card-title">Danh sách sản phẩm liên quan danh mục</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body" id="table-product">
                <table id="example1" class="table table-bordered table-striped">
                  <thead>
                  <tr>
                    <th>Mã sản phẩm</th>
                  	<th>HÌnh ảnh</th>
                    <th>Tên sản phẩm</th>
                    <th>Giá</th>
                    <th>Số lượng</th>
                    <th>Ngày thêm</th>              		
                  </tr>
                  </thead>
                  <tbody>
                  		<c:forEach var="product"  items="${products }">
                  		<tr>
                  			<td>${ product.id }</td>
	                		<td><img src="/assets/images/products/f1.jpg" alt=""></td>
                  			<td>${ product.name }</td>
                  			<td><fmt:formatNumber value=" ${ product.price }" pattern="#,###" /> VNĐ</td>
                  			<td>${ product.quantity }</td>
                  			<td><fmt:formatDate value="${ product.createDate }" pattern="dd-MM-yyyy" /> </td>
                  		</tr>
                  		</c:forEach>
                  </tbody>

                </table>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
          </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->
      </div>
      <!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
</body>
</html>