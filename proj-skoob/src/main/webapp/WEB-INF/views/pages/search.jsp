<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>搜尋結果</title>

<!-- 匯入套件 -->
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script> -->

<!-- 匯入套件 -->
<link rel="stylesheet" href="<c:url value="/resources/extension/bootstrap/css/bootstrap.min.css"/>">
<script src="<c:url value="/resources/extension/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/extension/font-awesome/css/font-awesome.css" />">
<link rel="stylesheet" href="<c:url value="/resources/extension/font-awesome/css/font-awesome.min.css" />">

<!-- 匯入自訂的 css -->
<link rel="stylesheet" href="<c:url value="/resources/css/footer.css"/>">
<link rel="stylesheet" href=" <c:url value="/resources/css/header.css" />">

<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<style type="text/css">
		.toTop-arrow {
            width: 2.5rem;
            height: 2.5rem;
            padding: 0;
            margin: 0;
            border: 0;
            border-radius: 33%;
            opacity: 0.6;
            background: #000;
            cursor: pointer;
            position: fixed;
            right: 1rem;
            bottom: 1rem;
            display: none;
        }

        .toTop-arrow::before,
        .toTop-arrow::after {
            width: 18px;
            height: 5px;
            border-radius: 3px;
            background: #f90;
            position: absolute;
            content: "";
        }

        .toTop-arrow::before {
            transform: rotate(-45deg) translate(0, -50%);
            left: 0.5rem;
        }

        .toTop-arrow::after {
            transform: rotate(45deg) translate(0, -50%);
            right: 0.5rem;
        }

        .toTop-arrow:focus {
            outline: none;
        }        

</style>
</head>
<body>

<jsp:include page="../tag/header.jsp" />

<div class=" container vw-100 justify-content-center" >
    <div class="mx-auto" style="margin: 40px 0;width: 1000px ;">
    
        <h6 class=" col-12 ">搜尋結果</h6>
        <h6 class=" col-12" style="display:inline;">關鍵字:</h6>
        <h6 style="color: #dc3545;display:inline;">${keyword} </h6><br>
        <h6 style="display:inline;">筆數:</h6>
        <h6 style="color: #dc3545;display:inline;">${count}</h6>	
        
        <hr>
        
        <c:forEach var="product" items="${search}">
        
        <div class="row">
        	<div class="col-3 ">
            	<a href="<c:url value="/pages/product.controller?prodid=${product.productid}" />">
            		<img src="<c:url value="/resources/pic/${product.productpic}.jpg" />" style="width: 220px;">
            	</a>
            </div>
   
            <div class="col-6" style="position:relative; top: 20px;">
            	<h6 class="proidval" style="display:none;" name="prodid">${product.productid}</h6>
                <h6>書名: ${product.productname}</h6>
<%--                 <h6>作者: ${product.author}</h6> 先暫時拿掉--%>
                <h6>出版日: <fmt:formatDate type="date" value="${product.shelf}" /></h6>
                <h6>價格: ${product.price}</h6>
                <h6>出版社: ${product.press}</h6>
             </div> 
             <div class="col-3" style="position:relative; top: 100px;">
             <button  class="btn btn-danger addcart" type="submit" name="cartAction" value="insert">加入購物車</button>           
             </div>
             
              
        </div> 
        <hr>
        
        </c:forEach>
       </div>
       <div class="side_fixbtn">
            <button type="button" id="BackTop" class="toTop-arrow"></button>
       </div>
       </div>
       
       	<!-- footer -->
		<jsp:include page="../tag/footer.jsp" />
       
       <script type="text/javascript">
       /*回到最上面*/
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
       
       $(".addcart").on("click", function() {
           swal({
                 icon: 'success',
                 title: '已將此商品加入購物車',
                 timer: 1500
             }).then(function(){
               var dataToServer = {
                   productid: $(".proidval").val(),
                      cartAction: "insert",
                      page:"search",
                   keyword:$("#searcha").val()   
               }
               $.ajax({
                   type: "post",
                   dataType: "text",
                   data: dataToServer,
                   url: "/proj-skoob/member/cart.controller",
                   traditional: true
               })
             })
        })
       </script>
</body>

</html>