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
	<section id="cart" class="section-p1">
        <table id="myTable">
            <thead id="heading">
                <tr>
                    <th data-order="desc">Tên sản phẩm <i class="fas fa-sort-alpha-up icon-sort"></i></th>
                    <th data-order="desc">Giá sản phẩm <i class="fas fa-sort-alpha-up icon-sort"></i></th>
                    <th data-order="desc">Số lượng <i class="fas fa-sort-alpha-up icon-sort"></i></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orderDetails}">
                <tr>
                    <td>${order.product.name}</td>
                    <td><fmt:formatNumber value="${order.price}" pattern="#,###"></fmt:formatNumber> VNĐ</td>
                    <td>${order.quantity}</td>
                </tr>            	
            </c:forEach>
            </tbody>
        </table>
    </section>

      <script src="/assets/js/tablesorter.js"></script>
     <!-- Initializing TableSorter -->
     <script>
        $(document).ready( function() {
            $('#myTable').tablesorter();
        })
    </script>
</body>
</html>