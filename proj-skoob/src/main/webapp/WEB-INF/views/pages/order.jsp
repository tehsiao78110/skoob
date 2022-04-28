<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet" href="<c:url value="/resources/css/cart.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/footer.css"/>">
<link rel="stylesheet" href=" <c:url value="/resources/css/header.css" />">
<!-- 匯入自訂的 js -->
<script src="<c:url value="/resources/js/cart.js"/>"></script>
</head>
<body>
	<main class="container">
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
				<ul class="row">

					<li class="col-6">
						<div class="productList_img_area">
							<a href="">
								<div style="background-image: url(./img/商品圖片1.jpg);"
									class="productList_img"></div>
							</a>
							<div class="productList_title_wrap">
								<a href="" class="productList_title">火來了，快跑</a>
							</div>
						</div>
					</li>
					<li class="col">100</li>
					<li class="col">1</li>
					<li class="col">200</li>
				</ul>
				<hr>

				<ul class="row product_content_checkout">
					<li class="col-6 checkout_product_number">總共有 3 項商品</li>
					<li class="col">&nbsp;</li>
					<li class="col">總金額</li>
					<li class="col checkout_total_amount">$200</li>
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
						<td>202204270001</td>
					</tr>
					<tr>
						<th>下訂時間</th>
						<td>2022-10-10 XXX XXX</td>
					</tr>
					<tr>
						<th>訂購人</th>
						<td>Mark</td>
					</tr>
					<tr>
						<th>收件人</th>
						<td>Mark</td>
					</tr>
					<tr>
						<th>收件人連絡電話</th>
						<td>0939833522</td>
					</tr>
					<tr>
						<th>配送方式</th>
						<td>7-11取貨</td>
					</tr>
					<tr>
						<th>付款方式</th>
						<td>信用卡線上刷卡</td>
					</tr>
					<tr>
						<th>發票形式</th>
						<td>二聯電子發票</td>
					</tr>
					<tr>
						<th>訂單狀態</th>
						<td>已完成</td>
					</tr>
				</tbody>
			</table>
		</div>

	</main>
</body>
</html>