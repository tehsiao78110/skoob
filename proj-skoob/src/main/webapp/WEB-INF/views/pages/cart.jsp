<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>購物車網頁</title>
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
	<!-- header -->
	<!-- 要使用相對路徑 -->
	<jsp:include page="../tag/header.jsp" />

	<!-- 主要內容 -->
	<main class="container">
		<c:if test="${not empty carts}">
			<div class="row">
				<div class="cart_step"> 
					<div class="step step1 on">購物車</div>
					<div class="step step2">資料填寫</div>
				</div>
				<div class="cart_mult_operate">
					<div>
						<button class="cart_btn_mult_Operate" id="btnDeleteAll">整批刪除</button>
					</div>
				</div>
				<div class="cart_area">
					<ul class="row cart_area_header">
						<li class="cart_checkbox_wrap"><input type="checkbox"
							id="checkboxAll" class="form-check-input" /></li>
						<li class="col-6">商品</li>
						<li class="col">單價</li>
						<li class="col">數量</li>
						<li class="col">金額</li>
						<li class="col">操作</li>
					</ul>
					
					<c:forEach var="rowItem" items="${carts}">
						<ul class="row">
							<li class="cart_checkbox_wrap"><input
								value="${rowItem.product.productid}" name="checkbox"
								type="checkbox" class="form-check-input form-isCheck" /></li>
			 
			 
							<li class="col-6">
								<div class="cart_product_wrap">
									<a href="<c:url value="/pages/product.controller?prodid=${rowItem.product.productid}"/>">
										<div
											style="background-image: url(<c:url value="/resources/pic/${rowItem.product.productpic}.jpg"/>);"
											class="cart_product_img">
										</div>
									</a>
									<div class="cart_product_title_wrap">
										<a href="<c:url value="/pages/product.controller?prodid=${rowItem.product.productid}"/>"
										   class="cart_product_title">${ rowItem.product.productname }</a>
									</div>
								</div>
							</li>
							<li class="col">${rowItem.product.price}</li>
							<li class="col cart_li_nmber">
								<button class="cart_btn_add_sub btnNumDec"
									value="${rowItem.product.productid}">-</button> <input
								type="text" class="cart_product_number btnNum"
								onkeyup="this.value=this.value.replace(/\D/g,'')"
								value="${rowItem.number}" />
								<button class="cart_btn_add_sub btnNumAdd"
									value="${rowItem.product.productid}">+</button>
								<button class="cart_btn_inv"
									value="${rowItem.product.inventory}"></button>
							</li>
							<li class="col">${ rowItem.subtotal }</li>
							<li class="col">
								<button class="cart_btn_delete delete"
									value="${rowItem.product.productid}">
									<i class="fa fa-trash-o fa-lg cart_btn_delete"
										aria-hidden="true"> </i>
								</button>
							</li>
						</ul>
						<hr>
					</c:forEach>

					<ul class="row cart_area_checkout">
						<li class="cart_checkbox_wrap">&nbsp;</li>
						<li class="col-6 cart_product_number">總共有 ${cartDto.productNum} 項商品</li>
						<li class="col">&nbsp;</li>
						<li class="col">總金額</li>
						<li class="col cart_total_amount">${cartDto.totalCost}</li>
						<li class="col">
							<a href="<c:url value="/pages/checkout.controller"/>">
								<button class="btn btn-warning" value="下一步" id="checkout">下一步</button>
							</a>
						</li>
					</ul>
				</div>
			</div>
		</c:if>
		<c:if test="${empty carts}">
			<div class="cart_no_product">
				<div class="cart_no_product_content">
					<p>您的購物清單中沒有商品</p>
					<a href="<c:url value="/"/>" class="continue_buy">請繼續選購</a>
				</div>
			</div>
		</c:if>
	</main>

	<!-- footer -->
	<jsp:include page="../tag/footer.jsp" />


</body>

</html>