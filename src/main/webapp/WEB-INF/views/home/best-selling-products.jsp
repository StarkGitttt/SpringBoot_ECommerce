<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Routine Shop</title>
</head>
<body>
		<c:choose>
			<c:when test="${products.size() <= 0 }">
				<p class="section-p1">Không có sản phẩm nào được hiển thị</p>
			</c:when>
			<c:otherwise>
				<section id="product1" class="section-p1">		
			       <p>Summer Collection New Morden Design</p>
			       <div class="pro-container">
			       <c:forEach var="product" items="${products}">
			       	<div class="pro">
			               <a href="/product/details?id=${product.group.id}"><img src="${product.group.image}" alt=""></a>
			               <div class="des">
			                   <span>${product.group.category.name}</span>
			                   <h5>${product.group.name}</h5>
			                   <div class="star">
			                    <i class="fas fa-star"></i>
			                    <i class="fas fa-star"></i>
			                    <i class="fas fa-star"></i>
			                    <i class="fas fa-star"></i>
			                    <i class="fas fa-star"></i>
			                   </div>
			                   <div class="price">
			                   	<c:choose>
				                    		<c:when test="${product.group.discount <= 0 }">
						                        	<h4 ><fmt:formatNumber value="${product.group.price}" pattern="#,###" />VNĐ</h4>                 		
				                    		</c:when>
				                    		<c:when test="${product.group.discount > 0 }">
							                     <div class="cut"><fmt:formatNumber value="${product.group.price}" pattern="#,###" /></div>
						                         <div class="offer" style="font-size: 17px;">
						                         	<fmt:formatNumber value="${product.group.price * ((100 - product.group.discount) / 100)}" pattern="#,###" /> VNĐ
						                         </div>	                    			
				                    		</c:when>
				               </c:choose>
				               </div>
			               </div>
			               <a href="/cart/add?id=${product.group.id}"><i class="fas fa-shopping-cart cart-default cart-shopping"></i></a>
			               <a href="/product/like?id=${product.group.id}">
			               		<c:set var="isLike" value="false" scope="page"></c:set>
			               		<c:if test="${sessionScope.user != null }">
			               			<c:forEach var="favorite" items="${favorites}">
			               				<c:if test="${favorite.product.id == product.group.id }">
			               						<c:set var="isLike" value="true" scope="page"></c:set>
			               				</c:if>
			               			</c:forEach>
			               		</c:if>
			               		<i class="far fa-heart cart-default cart-favorite ${isLike ? 'isLike' : ''}"></i>
			               </a>
			            </div>
			       </c:forEach>
			       </div>
			       <div class="pagination-container">
			            <ul>
			            	<c:forEach varStatus="i" begin="1" end="${page.totalPages}">
				                <li class="pagination-item ${page.number+1==i.index ? 'active' : '' }">
				               	 	<a href="/product/selling?pageNo=${i.index-1}">${i.index}</a> 
				                </li>            		
			            	</c:forEach>
			            </ul>
			       </div>
			   </section>
			</c:otherwise>
		</c:choose>
</body>
</html>