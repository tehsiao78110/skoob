<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單詳細內容</title>
<!-- 匯入套件 -->
<link rel="stylesheet" href="<c:url value="/resources/extension/bootstrap/css/bootstrap.min.css"/>">
<script src="<c:url value="/resources/extension/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/extension/font-awesome/css/font-awesome.css"/>">
<link rel="stylesheet" href=" <c:url value="/resources/extension/font-awesome/css/font-awesome.min.css"/>">
<script src="<c:url value="/resources/extension/jQuery/jquery-3.4.1.js"/>"></script>

<!-- 匯入自訂的 css -->
<link rel="stylesheet" href="<c:url value="/resources/css/order.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/footer.css"/>">
<link rel="stylesheet" href=" <c:url value="/resources/css/header.css" />">

<!-- 匯入自訂的 js -->
<script src="<c:url value="/resources/js/cart.js"/>"></script>
</head>
<body>
	<!-- header -->
	<jsp:include page="../tag/header.jsp" />
	
	<main class="container order_main">
		<div class="row">
			<div class="order_product_content">
				<ul class="row big_title_wrap">
					<p class="big_title">訂單內容</p>
				</ul>

				<ul class="row product_content_header">

					<li class="col-6">商品</li>
					<li class="col">售價</li>
					<li class="col">數量</li>
					<li class="col">小計</li>

				</ul>
				
				<c:set value="0" var="productNum" />
				<c:forEach var="orderitem" items="${orderitem}">
					<ul class="row">
						<li class="col-6">
							<div class="productList_img_area">
								<a href="<c:url value="/pages/product.controller?prodid=${orderitem.product.productid}"/>">
									<div style="background-image: url(<c:url value="/resources/pic/${orderitem.product.productpic}.jpg"/>);"
										 class="productList_img"></div>
								</a>
								<div class="productList_title_wrap">
									<a href="<c:url value="/pages/product.controller?prodid=${orderitem.product.productid}"/>" 
									   class="productList_title">${ orderitem.product.productname }</a>
								</div>
								
							</div>
						</li>
						<li class="col">${ orderitem.product.price }</li>
						<li class="col">${ orderitem.num }</li>
						<li class="col">${ orderitem.price }</li>
					</ul>
					<hr>
					<c:set value="${productNum + 1}" var="productNum" />
				</c:forEach>

				<ul class="row product_content_checkout">
					<li class="col-6 checkout_product_number">總共有 ${productNum} 項商品</li>
					<li class="col">&nbsp;</li>
					<li class="col">總金額</li>
					<li class="col checkout_total_amount">${order.totalprice}</li>
				</ul>

			</div>
		</div>

		<div class="order_payment_content">
			<div class="big_title_wrap">
				<p class="big_title">付款資訊</p>
			</div>
			<table class="table payment_content_table">
				<tbody>
					<tr>
						<th>訂單編號</th>
						<td>${order.orderid}</td>
					</tr>
					<tr>
						<th>下訂時間</th>
						<td>
							<fmt:formatDate type="both" value="${order.ordertime}" />
						</td>
						
					</tr>
					<tr>
						<th>訂購人</th>
						<td>${order.member.name}</td>
					</tr>
					<tr>
						<th>收件人</th>
						<td>${order.name}</td>
					</tr>
					<tr>
						<th>收件人連絡電話</th>
						<td>${order.phone}</td>
					</tr>
					<tr>
						<th>配送方式</th>
						<td>${order.delivery}</td>
					</tr>
					<tr>
						<th>付款方式</th>
						<td>${order.payment}</td>
					</tr>
					<tr>
						<th>發票形式</th>
						<td>${order.invoicetype}</td>
					</tr>
					<tr>
						<th>訂單狀態</th>
						<td>
							<c:choose>
								<c:when test="${order.state==0}">新訂單</c:when>
								<c:when test="${order.state==2}">已完成</c:when>
								<c:when test="${order.state==3}">已取消</c:when>
								<c:otherwise>未知的狀態</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</tbody>
			</table>
		</div>

	</main>
	
	<!-- footer -->
	<jsp:include page="../tag/footer.jsp" />
	
</body>
</html>