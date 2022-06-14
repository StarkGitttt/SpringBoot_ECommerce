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
            <h1>Sản phẩm bán chạy</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="/admin/index">Tổng quan</a></li>
              <li class="breadcrumb-item active">Nổi bật</li>
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
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">Sản phẩm bán chạy</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body">   
                <div class="row mt-4">
      
      				<c:forEach var="product" items="${products}">
	                   <div class="col-md-4" style="margin-top: 10px">
	                    <div class="position-relative">
	                       <a href="/admin/outstanding/detail/product?id=${product.group.id }">
	                       	<img src="${product.group.image }" alt="Photo 3" class="img-fluid">
	                       </a>
	                      <div class="ribbon-wrapper ribbon-lg">
	                        <div class="ribbon bg-danger text-md">
	                          SL Mua:  ${product.quantity }
	                        </div>
	                      </div>
	                    </div>
	                  </div>
					</c:forEach>
                  
                  
                </div>
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