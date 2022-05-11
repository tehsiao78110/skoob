<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員資料</title>

<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script> -->
<!-- 匯入套件 -->
<link rel="stylesheet"
	href="<c:url value="/resources/extension/bootstrap/css/bootstrap.min.css"/>">
<script
	src="<c:url value="/resources/extension/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
<link rel="stylesheet"
	href="<c:url value="/resources/extension/font-awesome/css/font-awesome.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/extension/font-awesome/css/font-awesome.min.css" />">

<!-- 匯入自訂的 css -->
<link rel="stylesheet" href="<c:url value="/resources/css/footer.css"/>">
<link rel="stylesheet"
	href=" <c:url value="/resources/css/header.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/edit.css"/>">

<script
	src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/tw-city-selector@2.1.1/dist/tw-city-selector.min.js"></script> -->
<script type="text/javascript"
	src="<c:url value="/resources/js/tw-city-selector.js"/>"></script>

<input id="PageContext" type="hidden" value="${pageContext.request.contextPath}" />
</head>
<body>

	<jsp:include page="../tag/header.jsp" />

	<main class="container edit_main">
		<form method="post" class="Info" action="<c:url value="/pages/user/edit"/>">
			<h3>修改會員資料</h3>
			<h3>
				<span class="error">${errors.action}</span>
			</h3>

			<table>
				<tr>
					<td style="width: 180px;">帳號 :</td>
					<td style="width: 200px;">${user.account}</td>
				</tr>
			</table>
			<hr>

			<table>
				<tr>
					<td style="width: 180px;">電子信箱 :</td>
					<td style="width: 200px;">${user.email}</td>
				</tr>
			</table>
			<hr>

			<table>
				<tr>
					<td style="width: 180px;">市話 :</td>
					<td style="width: 200px;"><input type="text" name="tel"
						value="${user.tel}"></td>
					<td><span class="error">${errors.tel}</span></td>
				</tr>
			</table>
			<hr>

			<table>
				<tr>
					<td rowspan="2" style="width: 180px;">地址 :</td>
					<td style="width: 600px;">
						<div role="tw-city-selector" data-county-value="${user.county}"
							data-district-value="${user.district}"></div>
					</td>
				</tr>
				<tr>
					<td><input size="60" type="text" name="address"
							   value="${user.address}"></td>
				</tr>
				<tr>
					<td><span class="error">${errors.address}</span></td>
				</tr>
			</table>
			<hr>

			<table>
				<tr>
					<td style="width: 180px;">生日 :</td>
					<td style="width: 200px;">
						<input size="25" type="date" name="birth" 
							   value="<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${user.birth}" />">
					</td>
				</tr>
			</table>

			<div class="edit_h5_wrap">
				<h5>填寫下面個人資料，下次購物時，訂單便可自動填入</h5>
			</div>

			<table>
				<tr>
					<td style="width: 180px;">姓名 :</td>
					<td style="width: 200px;"><input type="text" name="name"
						value="${user.name}"></td>
				</tr>
			</table>
			<hr>

			<table>
				<tr>
					<td style="width: 180px;">手機 :</td>
					<td style="width: 200px;">
						<input type="text" name="phone" value="${user.phone}">
					</td>
					<td><span class="error">${errors.phone}</span></td>
				</tr>
			</table>
			<hr>

			<div class="edit_botton_wrap">
				<button type="submit" class="edit_botton" name="save">儲存</button>
			</div>


		</form>
	</main>
	<jsp:include page="../tag/footer.jsp" />

	<script type="text/javascript">
		new TwCitySelector();
		let context = $("#PageContext").val();		
		function ajaxPut(dataToServer) {
			$.ajax({
				type : "post",
				dataType : "json",
				contentType: "application/json",
				data : JSON.stringify(dataToServer),
				url : "/proj-skoob/pages/user/edit",
				traditional : true,
			}).done(function(e) {
				/* window.location.reload(); */
				console.log("context = " + e);
				window.location.href = context + "/pages/user/edit";
			}).fail(function(e) {
				console.log("e = " + e);
			});
		}

		$("#save").click(function() {
			let tel = $('#tel').val();
			let county = $('.county option:selected').val();
			let district = $('.district option:selected').val();
			let address = $('#address').val();
			let birth = $('#birth').val();
			let name = $('#name').val();
			let phone = $('#phone').val();

			console.log("tel = " + tel);
			console.log("address = " + address);
			console.log("birth = " + birth);
			console.log("name = " + name);
			console.log("phone = " + phone);
			console.log("county = " + county);
			console.log("district = " + district);

			let dataToServer = {
				tel : tel,
				county : county,
				district : district,
				address : address,
				birth : birth,
				name : name,
				phone : phone,
			};

			ajaxPut(dataToServer);
		})
	</script>
</body>
</html>