<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Routine Fashion</title>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.message != null }">
			<h3 class="section-p1" style="font-size: 19px; color: #52c41a;">${sessionScope.message}</h3>
		</c:when>
		<c:when test="${empty sessionScope.cartDtos}">
			<h3 class="section-p1" style="font-size: 19px">Giỏ hàng trống</h3>
		</c:when>
		<c:otherwise>
			<form action="/cart/save" method="post">
			<section id="cart" class="section-p1">
		        <table id="myTable">
		            <thead id="heading">
		                <tr>
		                    <th>Image</th>
		                    <th data-order="desc">Product <i class="fas fa-sort-alpha-up icon-sort"></i></th>
		                    <th data-order="desc">Price <i class="fas fa-sort-alpha-up icon-sort"></i></th>
		                    <th data-order="desc">Quantity <i class="fas fa-sort-alpha-up icon-sort"></i></th>
		                    <th data-order="desc">Subtotal <i class="fas fa-sort-alpha-up icon-sort"></i></th>
		                    <th>Remove</th>
		                </tr>
		            </thead>
		            <tbody>
		            <c:forEach var="cartDto" items="${sessionScope.cartDtos}">
		                <tr>
		                    <td><img src="${cartDto.value.product.image }" alt=""></td>
		                    <td>${cartDto.value.product.name }</td>
		                    <td><fmt:formatNumber value="${cartDto.value.product.price}" pattern="#,###"></fmt:formatNumber> VNĐ</td>
		                    <td><input type="number" value="${cartDto.value.quantity}" name="qty" min="1" max="${cartDto.value.product.quantity}"></td>
		                    <td><fmt:formatNumber value="${cartDto.value.product.price}" pattern="#,###"></fmt:formatNumber> VNĐ</td>
		                    <td><a href="/cart/remove?id=${cartDto.value.product.id}"><i class="far fa-times-circle"></i></a></td>
		                </tr>            	
		            </c:forEach>
		            </tbody>
		        </table>
		    </section>
		    
		    <section id="cart-add" class="section-p1">
		        <div id="coupon">
		            <h3 style="font-size: 16px;">Coupon</h3>
		            <div>
		                <input type="text" name="address" id="" placeholder="CODE19902022">
		                <button class="btn-normal">Xác nhận</button>
		            </div>
		        </div>
		        <div id="subtotal">
		            <h3>Cart Totals</h3>
		            <table>
		                <tr>
		                    <td>Free Shipping</td>
		                    <td>Free</td>
		                </tr>
		                <tr>
		                    <td><strong>Total</strong></td>
		                    <td class="total"><strong></strong></td>
		                </tr>
		            </table>
		            <button class="btn-normal" type="submit" >Đặt hàng</button>
		        </div>
		    </section>
		    </form>
		</c:otherwise>
	</c:choose>
    
      <script src="/assets/js/tablesorter.js"></script>
     <!-- Initializing TableSorter -->
     <script>
        $(document).ready( function() {
            $('#myTable').tablesorter();
        })
    </script>
</body>
</html>