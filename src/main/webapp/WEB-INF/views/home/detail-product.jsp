<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rountine Fashion</title>
</head>
<body>
<form action="/cart/add?id=${product.id}" method="post">
 <section id="prodetails" class="section-p1">
        <div class="single-pro-image">
            <img src="${product.image}" alt="" width="100%" id="MainImg" alt="">

            <div class="small-img-group">
            	<c:forEach var="product" items="${products}">
	                <div class="small-img-col">
	                    <a href="/product/details?id=${product.id }"><img src="${product.image}" width="100%" class="small-img" title="${product.name}"></a>
	                </div>            	
            	</c:forEach>
            </div>
        </div>

        <div class="single-pro-details">
            <h5 style="font-size: 14px"><a href="/index">Home </a><a href="/category?id=${product.category.categoryId }">/ ${product.category.name } </a></h5>
            <h4>${product.name }</h4>
            <h2><fmt:formatNumber value="${product.price * ((100 - product.discount) / 100)}" pattern="#,###"></fmt:formatNumber>VNĐ</h2>
            <select name="" id="">
                <option value="">Select Size</option>
                <c:forEach var="size" items="${sizes }">
	                <option value="">${size.size }</option>
                </c:forEach>
            </select>
	            <input id="qtyProduct" type="number" name="qty" value="1">
	            <button class="btn-normal" type="submit">Add To Cart</button>

            <h4>Product Details</h4>
            <p>
                A product description is the marketing copy that explains what a product is and why it's worth purchasing. 
                The purpose of a product description is to supply customers
                with important information about the features and benefits of the product
                so they're compelled to buy
            </p>
        </div>
    </section>
    </form>
    <script>
			
    </script>
</body>
</html>