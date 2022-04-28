<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.google.protobuf.Type"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!doctype html>
<html lang="en">
<head>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- 匯入套件 -->
<link rel="stylesheet" href="<c:url value="/resources/extension/font-awesome/css/font-awesome.css"/>">
<link rel="stylesheet" href=" <c:url value="/resources/extension/font-awesome/css/font-awesome.min.css"/>">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11" ></script>

<link rel="stylesheet" href="<c:url value="/resources/extension/bootstrap/css/bootstrap.min.css"/>">

<!-- 匯入自訂的 css -->
<link rel="stylesheet" href="<c:url value="/resources/css/footer.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/header.css" />">

<link rel="stylesheet" href="<c:url value="/resources/extension/bootstrap/css/bootstrap.min.css"/>">
<script src="<c:url value="/resources/extension/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
<%-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="<c:url value="/resources/extension/bootstrap/js/bootstrap.bundle.min.js"/>"></script> --%>
<link rel="stylesheet" href="<c:url value="/resources/css/order_list.css" />">

<!-- 匯入自訂的 js -->
<script src="<c:url value="/resources/js/order.js"/>"></script>

<title>訂單紀錄</title>

</head>
<body>

	<jsp:include page="../tag/header.jsp" />
    <br>

	<main class="container order_main">
	<nav>
		<div>
			<p>訂單紀錄</p>
		</div>
		<div class="detail">
			<div>訂單編號</div>
			<div>下單時間</div>
			<div>總價</div>
			<div>訂單狀態</div>
			<div>可執行操作</div>
		</div>
		<br>

			<c:forEach var="rowItem" items="${lists}">
				<div class="item2">
					<a href="<c:url value="/pages/order/${rowItem.orderid}"/>" class="order_orderid">
						<div class="item1">${rowItem.orderid}</div>
					</a>
					<div class="item1">
						<fmt:formatDate type="both" value="${rowItem.ordertime}" />
					</div>
					<div class="item1">${rowItem.totalprice}</div>
					<div class="item1">
						<c:choose>
							<c:when test="${rowItem.state==0}">新訂單</c:when>
							<c:when test="${rowItem.state==2}">已完成</c:when>
							<c:when test="${rowItem.state==3}">已取消</c:when>
							<c:otherwise>未知的狀態</c:otherwise>
						</c:choose>
					</div>
					<div class="item1">
						<c:if test="${rowItem.state==0}">
							<div>
								<button type="button" class="btn btn-danger cancelbtn" name="cancel"
										value="${rowItem.orderid}">取消訂單</button>
															
								<button type="button" class="btn btn-success done" name="done"
										value="${rowItem.orderid}">完成訂單</button>
							</div>
						</c:if>
					</div>

				</div>
				<hr>
				<!-- Modal -->
				<div class="modal fade" id="${rowItem.orderid}" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								
								<h4 class="modal-title">商品列表</h4>
							</div>
							<c:set var="orderitems" value="${rowItem.orderitems}" />
							<c:forEach var="orderitem" items="${orderitems}">
								<div class="modal-body">
									<p>${orderitem.product.productname}<br>數量:${orderitem.num}件
									</p>

								</div>
							</c:forEach>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">確認</button>
							</div>
						</div>
					</div>
				</div>
				<!-- Modal content-->
				<br>

			</c:forEach>
	</nav>
	<br>
	<div class="page">
		<button>上一頁</button>
		<button>1</button>

		<button>下一頁</button>
	</div>
	</main>
	<jsp:include page="../tag/footer.jsp" />
</body>
</html>