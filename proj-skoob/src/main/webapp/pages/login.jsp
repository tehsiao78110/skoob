<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登入頁面</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>

<!-- 匯入套件 -->
<link rel="stylesheet" href="<c:url value="/resources/extension/bootstrap/css/bootstrap.min.css"/>">
<script src="<c:url value="/resources/extension/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/extension/font-awesome/css/font-awesome.min.css"/>">

<!-- 匯入自訂的 css -->
<link rel="stylesheet" href="<c:url value="/resources/css/LoginStyle.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/footer.css"/>">
<link rel="stylesheet" href=" <c:url value="/resources/css/header.css" />">
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<jsp:include page="../tag/header.jsp" />

<main class="container">
<form class="container login" action="<c:url value="/pages/login.controller" />" method=post>

	<h3>登入</h3>
	<h3><span class="error">${errors.action}</span></h3>
	

	<div class="txtin">
		<input id="acc" type="text" name="useraccount" value="${param.useraccount}">
		<span id="acc" class="hold" data-placeholder="帳號"></span>				
	</div>	
		<span class="error">${errors.useraccount}</span>
				
	<div class="txtin">
		<input id="pw" type="password" name="password" value="${param.userpassword}">
		<span class="hold" data-placeholder="密碼"></span>	
			
	</div>	
		<i id="eyeopen" class="fa fa-eye fa-lg" style="width:40px; position: relative; left: 430px; top:-43px;display:none;"></i>
		<i id="eyeclose" class="fa fa-eye-slash fa-lg" style="width:40px ;position: relative; left: 430px;top:-43px;display:block;"></i>	
		<span id="pws" class="error">${errors.userpassword}</span>	
		
		
		
		<input type="submit" name="login" value="登入" class="logbtn">
	<div class="txtbottom">
		沒有帳號嗎? <a href="<c:url value="/pages/register.jsp" />">註冊</a>
	</div>	   
	<script type="text/javascript">
	checkacc();
	console.log(document.getElementById('acc').value);
	function checkacc(){
		if(document.getElementById('acc').value!=""){
			$("#acc").addClass("focus");
		}
	}
	
	$("#eyeclose").click(function(){
		$(this).css("display","none");
		$("#eyeopen").css("display","block");
		$("#pw").prop("type", "text");
	})
	$("#eyeopen").click(function(){
		$(this).css("display","none");
		$("#eyeclose").css("display","block");
		$("#pw").prop("type", "password");
	})	
	$(".txtin input").on("focus",function(){
		$(this).addClass("focus");
		
	});
	
	$(".txtin input").on("blur",function(){
		if($(this).val()=="")
			$(this).removeClass("focus");
	});
		
	</script>
</form>
</main>
	<!-- footer -->
	<jsp:include page="../tag/footer.jsp" />

    </body>
</html>