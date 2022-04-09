<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>註冊頁面</title>
<!-- 匯入套件 -->
<link rel="stylesheet" href="<c:url value="/resources/extension/bootstrap/css/bootstrap.min.css"/>">
<script src="<c:url value="/resources/extension/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/extension/font-awesome/css/font-awesome.css" />">
<link rel="stylesheet" href="<c:url value="/resources/extension/font-awesome/css/font-awesome.min.css" />">

<!-- 匯入自訂的 css -->
<link rel="stylesheet" href="<c:url value="/resources/css/RegisterStyle.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/header.css" />" />
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
<body>
	<!-- 要使用相對路徑 -->
	<jsp:include page="/tag/header.jsp" />

<main class="container">
	<form  class="container Register" action="/proj-skoob/pages/register.controller" method=post>
	<h3>註冊</h3>
	
		<table>		
            <tr>
                <td style="width: 180px;">帳號 :</td>
                <td style="width: 300px;"><input size="30" type="text" name="account" value="${param.account}"></td>           
            
        		<td><span class="error">${errors.account}</span></td>
       		</tr>
        </table>
        <hr>
        
        <table>		
            <tr>
                <td style="width: 180px;">密碼 :</td>
                <td style="width: 300px;"><input id="pw" size="30" type="password" name="password" value="${param.password}">           
            
	            <i id="eyeopen" class="fa fa-eye fa-lg" style="width:40px; position: relative; left: 0px; top:0px;display:none;"></i>
				<i id="eyeclose" class="fa fa-eye-slash fa-lg" style="width:40px ;position: relative; left: 0px;top:0px;display:inline;"></i>
				</td>
            
        		<td><span class="error">${errors.password}</span></td>
       		</tr>
        </table>
        <hr>
        
        <table>		
            <tr>
                <td style="width: 180px;">確認密碼 :</td>
                <td style="width: 300px;"><input id="pw2" size="30" type="password" name="password2" value="${param.password2}">
                
                <i id="eyeopen2" class="fa fa-eye fa-lg" style="width:40px; position: relative; left: 0px; top:0px;display:none;"></i>
				<i id="eyeclose2" class="fa fa-eye-slash fa-lg" style="width:40px ;position: relative; left: 0px;top:0px;display:inline;"></i>          
           	    </td> 
           	    
        		<td><span class="error">${errors.password2}</span></td>
       		</tr>
        </table>
        <hr>
        
        <table>		
            <tr>
                <td style="width: 180px;">姓名 :</td>
                <td style="width: 300px;"><input size="30" type="text" name="name" value="${param.name}"></td>           
            
        		<td><span class="error">${errors.name}</span></td>
       		</tr>
        </table>
        <hr>
        
        <table>		
            <tr>
                <td style="width: 180px;">電子信箱 :</td>
                <td style="width: 300px;"><input size="30" type="text" name="email" value="${param.email}"></td>           
            
        		<td><span class="error">${errors.email}</span></td>
       		</tr>
        </table>
        <hr>
        
        <table>		
            <tr>
                <td style="width: 180px;">生日 :</td>
                <td style="width: 300px;"><input size="25" type="date" name="birth" value="${birth}"></td>           
            
        		<td><span class="error">${errors.birth}</span></td>
       		</tr>
        </table>
        <hr>
        <div class=" position-absolute start-50  top-100 translate-middle" style="width: 200px; top:90%;">
        <input  class="Registerbtn position-absolute start-50 translate-middle"  type="submit" name="register" value="註冊">
        </div>	
	</form>
	<script type="text/javascript">
	$("#eyeclose").click(function(){
		$(this).css("display","none");
		$("#eyeopen").css("display","inline");
		$("#pw").prop("type", "text");
	})
	$("#eyeopen").click(function(){
		$(this).css("display","none");
		$("#eyeclose").css("display","inline");
		$("#pw").prop("type", "password");
	})	
	$("#eyeclose2").click(function(){
		$(this).css("display","none");
		$("#eyeopen2").css("display","inline");
		$("#pw2").prop("type", "text");
	})
	$("#eyeopen2").click(function(){
		$(this).css("display","none");
		$("#eyeclose2").css("display","inline");
		$("#pw2").prop("type", "password");
	})	
	
	</script>
</main>
<jsp:include page="../tag/footer.jsp" />

</body>
</html>