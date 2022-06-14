<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
</head>
<body>
<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="index3.html" class="brand-link">
      <img src="/assets/dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
      <span class="brand-text font-weight-light">AdminLTE 3</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="image">
          <img src="/assets/dist/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
        </div>
        <div class="info">
          <a href="#" class="d-block">Alexander Pierce</a>
        </div>
      </div>

      <!-- SidebarSearch Form -->
      <div class="form-inline">
        <div class="input-group" data-widget="sidebar-search">
          <input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">
          <div class="input-group-append">
            <button class="btn btn-sidebar">
              <i class="fas fa-search fa-fw"></i>
            </button>
          </div>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
          <li class="nav-item menu-open">
            <a href="/admin/index" class="nav-link active">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                Tổng quan
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="/admin/index" class="nav-link active">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Hoạt động</p>
                </a>
              </li>
            </ul>
          </li>
          <!--   
          <li class="nav-item">
            <a href="/admin/notification" class="nav-link">
              <i class="nav-icon fas fa-sms"></i>
              <p>
               	Thông báo
                <span class="right badge badge-danger">Mới</span>
              </p>
            </a>
          </li>
          
          <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-chart-pie"></i>
              <p>
                Biểu đồ
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">       
              <li class="nav-item">
                <a href="/admin/statistics/chartcircle" class="nav-link">
                
                  <i class="far fa-circle nav-icon"></i>
                  <p>Tròn</p>
                </a>
              </li>           
            </ul>
          </li>
          -->
          <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-star"></i>
              <p>
                Nổi bật
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="/admin/outstanding/products" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Sản phẩm</p>
                </a>
              </li>
            </ul>
          </li>
          <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-tshirt"></i>
              <p>
                Quản lí sản phẩm
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="/admin/manage/product/add" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Thêm</p>
                </a>
              </li>
              
               <li class="nav-item"> 
                <!-- <a href="pages/tables/data.html" class="nav-link">  -->
                <a href="/admin/manage/product/views" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Danh sách</p>
                </a>
              </li>
              
                <li class="nav-item">
               <!-- <a href="pages/tables/data.html" class="nav-link">  -->
                <a href="/admin/manage/views/product/deleted" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Sản phẩm đã xóa</p>
                </a>
              </li>
              
               <li class="nav-item">
               <!-- <a href="pages/tables/data.html" class="nav-link">  -->
                <a href="/admin/manage/product/favorite" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Yêu thích</p>
                </a>
              </li>
              
            </ul>
          </li>
          
          <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-table"></i>
              <p>
                Quản lí danh mục
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
        	<ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="/admin/manage/category/add" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Thêm</p>
                </a>
              </li>
              
               <li class="nav-item"> 
                <!-- <a href="pages/tables/data.html" class="nav-link">  -->
                <a href="/admin/manage/category/views" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Danh sách</p>
                </a>
              </li>
              
            </ul>
          </li>
          
           <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-user"></i>
              <p>
               Quản lí người dùng
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
	        <ul class="nav nav-treeview">
	              
	               <li class="nav-item"> 
	                <a href="/admin/manage/user/views" class="nav-link">
	                  <i class="far fa-circle nav-icon"></i>
	                  <p>Danh sách</p>
	                </a>
	              </li>
	              
	              <li class="nav-item"> 
	                <a href="/admin/manage/view/user/disabled" class="nav-link">
	                  <i class="far fa-circle nav-icon"></i>
	                  <p>Ngừng hoạt động</p>
	                </a>
	              </li>
              
            </ul>
          </li>
          
          <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fab fa-cc-paypal"></i>
              <p>
              	Quản lí hóa đơn
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
	        <ul class="nav nav-treeview">
	              <li class="nav-item">
	                <a href="/admin/manage/order?status=waiting" class="nav-link">
	                  <i class="far fa-circle nav-icon"></i>
	                  <p>Chờ xác nhận</p>
	                </a>
	              </li>
	              
	               <li class="nav-item"> 
	                <a href="/admin/manage/order?status=confirmed" class="nav-link">
	                  <i class="far fa-circle nav-icon"></i>
	                  <p>Đã xác nhận</p>
	                </a>
	              </li>
	              
              	   <li class="nav-item"> 
	                <a href="/admin/manage/order?status=removed" class="nav-link">
	                  <i class="far fa-circle nav-icon"></i>
	                  <p>Đã hủy</p>
	                </a>       
	              </li>
	                            
            </ul>
          </li>
          
           <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-users"></i>
              <p>
              	Nhân viên
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
	        <ul class="nav nav-treeview">
  
	               <li class="nav-item"> 
	                <!-- <a href="pages/tables/data.html" class="nav-link">  -->
	                <a href="/admin/employee/views" class="nav-link">
	                  <i class="far fa-circle nav-icon"></i>
	                  <p>Xem thông tin</p>
	                </a>       
	              </li>
	              
            </ul>
          </li>
          
    

        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>
</body>
</html>