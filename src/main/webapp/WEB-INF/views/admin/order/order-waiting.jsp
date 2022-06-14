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
            <h1>Danh sách hóa đơn</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="/admin/index">Tổng quan</a></li>
              <li class="breadcrumb-item active">Hóa đơn</li>
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
                <h3 class="card-title">Hóa đơn đang chờ</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body"  id="order">
                <table id="example1" class="table table-bordered table-striped">
                  <thead>
                  <tr>
                   	<th >Mã hóa đơn </th>
                    <th >Ngày đặt </th>
                    <th >Tổng tiền </th>
                    <th >Trạng thái </th>
                    <th >Địa chỉ giao hàng </th>
                    <th >Xem</th>
                    <th></th>
                    <th></th>
                  </tr>
                  </thead>
                  <tbody>
                  		<c:forEach var="order"  items="${orders}">
                  		<tr>
                  		   	<td>${order.id}</td>
		                    <td><fmt:formatDate value="${order.ceateDate}" pattern="dd-MM-yyyy"/></td>
		                    <td><fmt:formatNumber value="${order.total}" pattern="#,###"></fmt:formatNumber> VNĐ</td>
							<td class="waiting">Chờ xác nhận</td>
		                    <td><a href="/admin/manage/view/address/receipt?id=${order.id}">Chi tiết</a></td>
		                    <td><a href="/admin/manage/view/details/order?status=waiting&id=${order.id}">Chi tiết</a></td>
							<td><a href="/admin/manage/order/approval?id=${order.id}">Duyệt đơn</a></td>
							<td><a href="/admin/manage/order/delete?id=${order.id}">Hủy đơn</a></td>
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