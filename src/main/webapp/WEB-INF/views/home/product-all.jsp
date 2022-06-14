<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang>
<head>
<meta charset="UTF-8">
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"  crossorigin="anonymous">
     <!-- Slick css -->
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<title>Routine Shop</title>
</head>
<body>
 
	   <section id="product1" class="section-p1">
		<c:forEach var="products" items="${mapProduct }">
			<div class="mr-top">
	       		<h3 style="text-align: left;">${products.key.name}</h3>
	            <div class="${products.value.size() < 4 ? 'unslider' : 'slider' }">
		            	<c:forEach var="product" items="${products.value}">
			                <div class="pro">
			                <a href="/product/details?id=${product.id}"><img src="${product.image}"  alt=""></a>
			                <div class="des">
			                    <span>${product.category.name}</span>
			                    <h5>${product.name}</h5>
			                    <div class="star">
			                        <i class="fas fa-star"></i>
			                        <i class="fas fa-star"></i>
			                        <i class="fas fa-star"></i>
			                        <i class="fas fa-star"></i>
			                        <i class="fas fa-star"></i>
			                    </div>
			                    <h4><fmt:formatNumber value="${product.price}" pattern="#,###" />VNĐ</h4>
			                </div>
			                <a href="/cart/add?id=${product.id}"><i class="fas fa-shopping-cart cart-default cart-shopping"></i></a>
			                <a href="/product/like?id=${product.id}?page=viewproductsall">
			                	<c:set var="isLike" value="false" scope="page"></c:set>
			               		<c:if test="${sessionScope.user != null }">
			               			<c:forEach var="favorite" items="${favorites}">
			               				<c:if test="${product.id == favorite.product.id}">
			               						<c:set var="isLike" value="true" scope="page"></c:set>
			               				</c:if>
			               			</c:forEach>
			               		</c:if>
			                	<i class="far fa-heart cart-default cart-favorite ${isLike ? 'isLike' : ''}"></i>
			                </a>
			                </div>
					</c:forEach>
	            </div>
		</div>
		</c:forEach>
   </section>
   
		
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script
      type="text/javascript"
      src="https://code.jquery.com/jquery-1.11.0.min.js"
    ></script>
    <script
      type="text/javascript"
      src="https://code.jquery.com/jquery-migrate-1.2.1.min.js"
    ></script>
    <script
      type="text/javascript"
      src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"
    ></script>
    <script>
        $(document).ready(function(){

            $('.slider').slick({
                slidesToShow: 4,
                slidesToScroll: 2,
                infinite: true,
                autoplay: false,
                arrows: false,
                autoplaySpeed: 3000,
            });

        });
    </script>
</body>
</html>