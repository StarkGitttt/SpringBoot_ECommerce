<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang>
<head>
<meta charset="UTF-8">
<title>Routine Shop</title>
</head>
<body>
	<header class="header-default">

    <a href="/index" class="logo"> <i class="fas fa-shopping-cart"></i> shop </a>

    <nav class="navbar-default">
        <ul>
            <li><a href="/index">Trang chủ</a></li>
            <li><a href="/product/all">Danh mục</a>
                <c:if test="${sessionScope.categories != null}">
                    <ul>
                        <c:forEach var="category" items="${sessionScope.categories}">
                            <li><a href="/category?id=${category.categoryId}&pageNo=0">${category.name}</a></li>
                        </c:forEach>
                    </ul>
                </c:if>
            </li>
            <li><a href="/product/selling">Xu hướng</a></li>
            <li><a href="/product/sales">Giảm giá</a></li>
            <c:if test="${sessionScope.user != null}">
	             <li><a href="/favorite">Yêu thích</a></li>        
	             
	                <c:if test="${sessionScope.user.admin==true}">
	             		<li><a href="/admin/index">Quản lý</a></li>           	
	                </c:if>
	                
	        		<c:if test="${sessionScope.user.admin==false}">
	                    <li><a href="#">Đơn hàng</a>
	                    	<ul>
		                            <li><a href="/order/user">Đăng đặt</a></li>
		                            <li><a href="/order/cancel">Đã hủy</a></li>
		                            <li><a href="/order/history">Lịch sử đặt hàng</a></li>
		                    </ul>
	                    </li>           	
	                </c:if>
	                
		                 <li>
		                    <a href="#">Chào ${sessionScope.user.fullname}!</a>
		                    <ul>
		                        <li><a href="/update/password">Đổi mật khẩu</a></li>
		                        <li><a href="/update/info">Chỉnh sửa</a></li>
		                        <li><a href="/shutdown">Đăng xuất</a></li>
		                    </ul>
		                 </li>
            </c:if>
        </ul>
    </nav>

    <div class="icons">
        <div id="menu-btn" class="fas fa-bars"></div>
        <div id="search-btn" class="fas fa-search"></div>
        <a href="/cart" class="fas fa-shopping-cart"></a>
        <c:if test="${sessionScope.user == null }">
	        <a href="/login" class="fas fa-user-alt" title="Đăng nhập"></a>        
        </c:if>
    </div>
    <form action="/product/search" class="search-form" method="post">
        <input type="search" name="keyword" placeholder="search here..." id="search-box">
        <button type="submit" style="background: none;"><label for="search-box" class="fas fa-search"></label></button>
    </form>

</header>
	<!-- home section starts  -->

<section class="home" id="home">

    <div class="swiper home-slider">

        <div class="swiper-wrapper">

            <div class="swiper-slide slide" style="background:url(/assets/images/banner1.jpg) no-repeat">
                <div class="content">
                    <span>upto 50% off</span>
                    <h3>women's Fashion</h3>
                    <a href="#" class="btn-default">Chi tiết</a>
                </div>
            </div>

            <div class="swiper-slide slide" style="background:url(/assets/images/banner2.jpg) no-repeat">
                <div class="content">
                    <span>upto 50% off</span>
                    <h3>men's Fashion</h3>
                    <a href="#" class="btn-default">Chi tiết</a>
                </div>
            </div>

            <div class="swiper-slide slide" style="background:url(/assets/images/banner3.jpg) no-repeat">
                <div class="content">
                    <span>upto 50% off</span>
                    <h3>kid's Fashion</h3>
                    <a href="#" class="btn-default">Chi tiết</a>
                </div>
            </div>

        </div>

        <div class="swiper-button-next"></div>
        <div class="swiper-button-prev"></div>

    </div>

</section>

<!-- home section ends -->
</body>
</html>