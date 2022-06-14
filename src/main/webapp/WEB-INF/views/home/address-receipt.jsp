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
                    <th data-order="desc">Họ và tên <i class="fas fa-sort-alpha-up icon-sort"></i></th>
                    <th data-order="desc">Địa chỉ <i class="fas fa-sort-alpha-up icon-sort"></i></th>
                    <th data-order="desc">Email <i class="fas fa-sort-alpha-up icon-sort"></i></th>
                    <th data-order="desc">Số điện thoại <i class="fas fa-sort-alpha-up icon-sort"></i></th>
                    <c:if test="${ placeReceipt.order.status == false}">
                    	<th></th>
                    </c:if>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${placeReceipt.fullname}</td>
                    <td>${placeReceipt.address}</td>
                    <td>${placeReceipt.email}</td>
                    <td>${placeReceipt.phone}</td>
                    <c:if test="${ placeReceipt.order.status == false}">
                    	<td><a href="/order/update/address/receipt?id=${ placeReceipt.order.id }">Chỉnh sửa</a></td>
                    </c:if>
                </tr>            	
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