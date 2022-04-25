<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.service.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<jsp:useBean id="myFavService" class="model.service.MyfavService" />

<html>

<head>
<meta charset="UTF-8">
<title>我的收藏頁面</title>
<!-- CSS link -->

<script src="https://kit.fontawesome.com/9b770e420b.js" crossorigin="anonymous"></script>
<script src="<c:url value="/resources/js/jquery-3.4.1.js" />"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<link rel="stylesheet" href="<c:url value="/resources/extension/bootstrap/css/bootstrap.min.css"/>">
<script src="<c:url value="/resources/extension/bootstrap/js/bootstrap.bundle.min.js"/>"></script>

<!-- 匯入自訂的 css -->
<link rel="stylesheet" href="<c:url value="/resources/css/footer.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/header.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/myFav.css"/>">

</head>

<body>
	<jsp:include page="../tag/header.jsp" />

	<main class="container ">
		<c:if test="${not empty favs}">
			<div class="myFav_area">
	
				<ul class="row myFav_area_header">
					<li class="col-6">收藏商品</li>
					<li class="col">價格</li>
					<li class="col">刪除</li>
					<li class="col">加入購物車</li>
				</ul>
				<c:forEach var="myFavBean" items="${favs}">
					<ul class="row myFav_area_content">
						<input class="proidval" type="number" name="productid"
							value="${myFavBean.product.productid}" style="display: none;">
						<li class="col-6">
							<div class="myFav_product_wrap">
								<img width=120px heith=80px alt=""
									src="<c:url value="/resources/pic/${myFavBean.product.productpic}.jpg" />">
								<div class="myFav_product_title_wrap">${myFavBean.product.productname}</div>
							</div>
						</li>
						<li class="col">${myFavBean.product.price}</li>
						<li class="col"><button type="submit" class="delete fav_btn">
								<i class="far fa-trash-alt fa-lg"></i>
							</button></li>
						<!-- 刪除 -->
	
						<li class="col">
							<button class="addCart fav_btn" name="cartAction" value="insert">
								<i class="fas fa-shopping-cart fa-lg"></i>
							</button>
						</li>
					</ul>
					<hr>
				</c:forEach>
			</div>
		</c:if>
		<c:if test="${empty favs}">
			<div class="myFav_no_product">
				<div class="myFav_no_product_content">
					<p>您沒有收藏任何商品</p>
					<a href="<c:url value="/"/>" class="continue_buy">回首頁</a>
				</div>
			</div>
		</c:if>


	</main>

	<jsp:include page="../tag/footer.jsp" />

	<!-- bootstrap CDN     -->

	<!-- 回頂端JS   好  部會 沒差  那我先關螢幕-->
	<script>
		$(function() {
			$('#BackTop').click(function() {
				$('html,body').animate({
					scrollTop : 0
				}, 333);
			});
			$(window).scroll(function() {
				if ($(this).scrollTop() > 300) {
					$('#BackTop').fadeIn(222);
				} else {
					$('#BackTop').stop().fadeOut(222);
				}
			}).scroll();
		});

		// 刪除收藏
		$(".delete").on("click", function() {
			var dataToServer = {
				productid : $(".proidval").val(),
				favDelete : "delete"
			//屬性:值 
			}

			$.ajax({
				type : "delete",
				dataType : "text",
				data : JSON.stringify(dataToServer),
				url : "/proj-skoob/pages/myFav.controller",
				traditional : true
			}).then(function(e) {
				window.location.reload();
			}); //這邊重製頁面 
		})

		$(".addCart").on("click", function() {
			let dataToServer = {
				productid : $(".proidval").val(),
				cartAction : "insert",
				page : "myFav" //page待命  導致新的一頁 
			}

			console.log(dataToServer);

			$.ajax({
				type : "post",
				dataType : "text",
				data : dataToServer,
				url : "/proj-skoob/pages/myFav.controller/cart",
				traditional : true
			}).done(
				swal({
					icon : 'success',
					title : '已將此商品加入購物車',
					timer : 1500
				}).then( function() {
					window.location.reload();
				})
			)
		})	
		//    滑鼠移到變暗     $(document).ready(function (){  
		//         	$("p").mouseover(function () {
		// 				$("p").css("background-color","coral");
		// 			});
		//         });
	</script>
</body>
</html>