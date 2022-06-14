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
                    <th data-order="desc">Mã hóa đơn <i class="fas fa-sort-alpha-up icon-sort"></i></th>
                    <th data-order="desc">Ngày đặt <i class="fas fa-sort-alpha-up icon-sort"></i></th>
                    <th data-order="desc">Tổng tiền <i class="fas fa-sort-alpha-up icon-sort"></i></th>
                    <th data-order="desc">Trạng thái <i class="fas fa-sort-alpha-up icon-sort"></i></th>
                    <th data-order="desc">Thông tin nhận hàng <i class="fas fa-sort-alpha-up icon-sort"></i></th>
                    <th data-order="desc">Xem</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.id}</td>
                    <td><fmt:formatDate value="${order.ceateDate}" pattern="dd-MM-yyyy"/></td>
                    <td><fmt:formatNumber value="${order.total}" pattern="#,###"></fmt:formatNumber> VNĐ</td>
                    	<c:choose>
                    		<c:when test="${order.status == false}">
                    			<td class="waiting">Chờ xác nhận</td>
                    		</c:when>
                    		<c:when test="${order.status == true}">
                    			<td class="confirmed">Đã xác nhận</td>
                    		</c:when>
                    	</c:choose>
                    <td><a href="/order/address/receipt?id=${order.id}">Chi tiết</a></td>
                    <td><a href="/order/details?id=${order.id}">Chi tiết</a></td>
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