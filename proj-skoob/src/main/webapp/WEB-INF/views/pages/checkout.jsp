<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>結帳頁面</title>
    
    <!-- 匯入套件 -->
    <link rel="stylesheet" href="<c:url value="/resources/extension/font-awesome/css/font-awesome.min.css" />">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script> 

    <link rel="stylesheet" href="<c:url value="/resources/extension/bootstrap/css/bootstrap.min.css"/>">
    <script src="<c:url value="/resources/extension/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
	
	<!-- 匯入自訂的 css -->
    <link rel="stylesheet" href="<c:url value="/resources/css/footer.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/header.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/checkout.css" />">
    
</head>

<body>
    <div>
        <jsp:include page="../tag/header.jsp" />
        <div class="cart_step"> 
            <div class="step step1 ">購物車</div>
            <div class="step step2 on">資料填寫</div>
        </div>    
    <form class="purchase" onsubmit="return submit_sure()" method="post" action="<c:url value="/pages/order.controllor"/>">
        <strong class="top">選擇配送方式</strong>
        <fieldset class="side border border-primary">
            <legend></legend><br>
            <input name="delivery" id="seven" type="radio" value="7-11取貨"><label for="seven">7-11取貨</label><br><br>
            <input name="delivery" id="home" type="radio" value="宅配到府"><label for="home">宅配到府</label><br><br>
        </fieldset><br>
        <strong class="top">選擇付款方式</strong>
        <fieldset class="side border border-primary">
            <legend></legend><br>
            <input name="payment" id="credit" type="radio" value="信用卡線上刷卡"><label for="credit">信用卡線上刷卡</label><br><br>
            <input name="payment" id="icash" type="radio" value="icash付款"><label for="icash">icash付款</label><br><br>
        </fieldset><br>
        <strong class="top">選擇優惠方式</strong>
        <fieldset class="side border border-primary">
            <legend></legend><br>
            <input name="discount" id="ecoupon" type="radio" value="ecoupon"><label for="ecoupon">E-Coupon 電子折價券 / 單品折價券</label><br><br>
        </fieldset><br>
        <strong class="top">訂購人資訊</strong>
        <fieldset class="side border border-primary">
            <legend></legend><br>
            <label for="orderuser">姓名:</label>
            <input id="orderuser" name="name" type="text" placeholder="例:王曉明" required><br><br>
            <label for="cellphone">手機:</label>
            <input id="cellphone" name="cellphone" type="text" placeholder="例:0912-345678" required><br><br>
            <label for="phone">電話:</label>
            <input id="phone" name="phone" type="text"><br><br>
            <label for="mail">Email:</label>
            <input id="mail" name="mail" type="email"><br><br>
        </fieldset><br>
        <strong class="top">收件人資訊</strong>
        <fieldset class="side border border-primary">
            <legend></legend><br>
            <input name="thesame" id="same" type="checkbox"><label>同訂購人資訊</label><br><br>
            <label for="recipient">收件人:</label>
            <input name="name" id="recipient" type="text" placeholder="例:王曉明"><br><br>
            <label for="mobilephone">行動電話:</label>
            <input name="mobilephone" id="mobilephone" type="text"><br><br>
        </fieldset><br>
        <strong class="top">發票資訊</strong>
        <fieldset class="side border border-primary">
            <legend></legend><br>
            <input name="bill" id="donate" type="radio" value="donate"><label for="credit">捐贈發票</label><br><br>
            <input name="bill" id="twopart" type="radio" value="twopart"><label for="twopart">二聯電子發票</label><br><br>
            <input name="bill" id="threepart" type="radio" value="threepart"><label for="threepart">三聯電子發票</label><br><br>
        </fieldset><br>
        <button style="background-color:rgb(230, 133, 226);color: white;" id="check" type="submit" value="checkout" name="orderaction">確認訂購</button>
    </form>
    <br><br>
    <div class="footer">
        <jsp:include page="../tag/footer.jsp" />
    </div>
</div>
<div class="side_fixbtn">
    <button type="button" id="BackTop" class="toTop-arrow"></button>
 </div>
 <script>
     $(function () {
	    $('#BackTop').click(function () {
		    $('html,body').animate({ scrollTop: 0 }, 333);
	    });
	    $(window).scroll(function () {
		    if ($(this).scrollTop() > 300) {
			    $('#BackTop').fadeIn(222);
		    } else {
			    $('#BackTop').stop().fadeOut(222);
		    }
	    }).scroll();
    });
    function submit_sure(){
        let flag =confirm("確定訂購?");
        if(flag==true){
            alert("訂購完成");
            return true;
        }else{
            return false;
        }
    }
 </script>
 
</body>

</html>