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
                    <th data-order="desc">Ngày đặt <i class="fas fa-sort-alpha-up icon-sort"></i></th>
                    <th data-order="desc">Tổng tiền <i class="fas fa-sort-alpha-up icon-sort"></i></th>
                    <th data-order="desc">Trạng thái <i class="fas fa-sort-alpha-up icon-sort"></i></th>
                    <th data-order="desc">Xem</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td><fmt:formatDate value="${order.ceateDate}" pattern="dd-MM-yyyy"/></td>
                    <td><fmt:formatNumber value="${order.total}" pattern="#,###"></fmt:formatNumber> VNĐ</td>
					<td class="cancel">Đã bị hủy</td>		
                    <td><a href="/order/details?id=${order.id}">Chi tiết</a></td>
                    <td><a href="/order/again?id=${order.id}">Đặt lại</a></td>
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