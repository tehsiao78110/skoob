<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

 <!DOCTYPE html>
<html lang="en">

<head>
    <title>skoob網路書城</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <!-- 匯入套件 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="<c:url value="/resources/js/jquery-3.4.1.js"/>"></script>
    
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/extension/bootstrap/css/bootstrap.min.css"/>">
    			
    <link rel="stylesheet" href="<c:url value="/resources/extension/font-awesome/css/font-awesome.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/extension/font-awesome/css/font-awesome.min.css" />">
    
    <link rel="stylesheet" href="<c:url value="/resources/extension/bootstrap/css/bootstrap.min.css"/>">
    <script src="<c:url value="/resources/extension/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
	
	<!-- 匯入自訂的 css -->
	<link rel="stylesheet" href="<c:url value="/resources/css/footer.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/header.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/product.css" />">
    
</head>

<body id="gotop">
	<jsp:include page="../tag/header.jsp" />
    

    <div class="container" style="margin-top:30px">
        <div class="row">
            <div class="col-sm-4">
                <div>

                  	<div class="slideshow-container">

						<div class="mySlides ifade">
							<div style="background-image: url(<c:url value="/resources/pic/${pro.productpic}.jpg"/>);"
								 class="product_img"></div>
						</div>
						
						<div class="mySlides ifade">
							<div style="background-image: url(<c:url value="/resources/pic/${pro.productpic}-1.jpg"/>);"
								 class="product_img"></div>
						</div>
						
						<div class="mySlides ifade">
							<div style="background-image: url(<c:url value="/resources/pic/${pro.productpic}-2.jpg"/>);"
								 class="product_img"></div>
						</div>
						
						<div class="prevb"><a class="prev" onclick="plusSlides(-1)">&#10094;</a></div>
						<div class="nextb"><a class="next" onclick="plusSlides(1)">&#10095;</a></div>
					
					</div>
					<br>

					<div style="text-align:center">
					  <span class="dot" onclick="currentSlide(1)"></span> 
					  <span class="dot" onclick="currentSlide(2)"></span> 
					  <span class="dot" onclick="currentSlide(3)"></span> 
					</div>
                </div>
                
                <br>
                <br>
                <br>
                <br>
                <br>
                <hr class="d-sm-none">
            </div>
            <div class="col-sm-8">
            	
            	<!--  
                <div>被討厭的勇氣 二部曲完結篇：人生幸福的行動指南</div>-->
                <div style="font-size: 23px;">${pro.productname}</div>
                <hr>
                <!--
                <p>作者 : 岸見一郎 、 古賀史健</p>-->
                定價:<span style="color: crimson;font-size: 25px;">${pro.price}</span><span>元</span>
                <hr>
                <p>作者 : ${aut.authorname}</p>
                <p>出版社 : ${pro.press}</p>
                <p>出版日 : <fmt:formatDate type="date" value="${pro.shelf}" /></p>
                <hr>
                <!--定價:<span style="color: crimson;font-size: 25px;">224</span><span>元</span>-->
                
                
                <!--${like}
                get: <%=request.getAttribute("like") %>-->
                <!--  <button class="btn btn-outline-primary">立即結帳</button>-->
                <!--<form action="<c:url value="/member/cart.controller"/>" method="post">
				    <input id="proidval" type="number" name="productid" value="${pro.productid}" style="display:none;" >
				    <a href="<c:url value="/member/Fav.controller" />" style="text-decoration:none;" name="prodaction" value="Select"><span style="font-size: 30px;" class="fav <%=request.getAttribute("like") %>">&hearts;</span> 收藏</a>
	                <button id="addcart" class="btn btn-danger" type="submit" name="cartAction" value="insert">加入購物車</button>
				</form>-->
				<!--<input id="proidval" type="number" name="productid" value="${pro.productid}" style="display:none;" >
			    <!--  <button type="submit" name="cartAction" value="insert">提交資料</button>-->
                <input id="proidval" type="number" name="prodid" value="${pro.productid}" style="display:none;" >
<%--                 <a href="<c:url value="/member/Fav.controller?prodid=${pro.productid}" />" style="text-decoration:none;" name="prodaction" value="Select"> --%>
<%--                 	<span style="font-size: 30px;" class="fav <%=request.getAttribute("like") %>">&hearts;</span> 收藏 --%>
<!--                 </a> -->
                <c:if test="${empty like}">
					<button class="btn btn-outline-primary fav "> &hearts; 收藏 </button>&nbsp;&nbsp;&nbsp;
				</c:if>
				<c:if test="${not empty like}">
<!-- 					<button class="btn btn-outline-primary "> <span style="font-size: 30px;" class="fav like">&hearts;</span> 收藏</button> -->
					<button class="btn btn-outline-primary fav like"> &hearts; 收藏</button>&nbsp;&nbsp;&nbsp;
				</c:if>
                <button id="addcart" class="btn btn-danger" type="submit" name="cartAction" value="insert">加入購物車</button>
                <br>
                <text style="font-size:12px">庫存量 : ${pro.inventory}</text>
                <br>
                
            </div>
            <div class="col-md-12">
                <div>
                    <div class="title_pcoll arup ">
                        <h5 class="title_style1b">內容簡介</h5>
                        <hr>
                    </div>
                    <div class="content_pcoll">
                        <div class="contentcolumn_pcoll less">
                            <div class="pdintro_txt1field panelCon">
                                <span>
                                	${pro.introduction}
                                </span>
                            </div>
                            <!--pdintro_txt1field end-->
                            <div class="mod_picview1"></div>
                        </div>
                        <!--contentcolumn_pcoll end-->
                        <div class="btncolumn_pcoll">
                            <div class="btnbox_pcoll"><a class="btnmore_pcoll more">看更多</a></div>
                        </div>
                        <!--btncolumn_pcoll end-->
                    </div>

                </div>
            </div>
            <div class="col-sm-12">
                <div class="title_pcoll arup">
                    <h5 class="title_style1b">作者</h5>
                    <hr>
                </div>
                <div class="content_pcoll" style>
                    <div class="contentcolumn_pcoll less">
                        <div class="authorintrofield panelCon">
                            <span>
                            ${aut.authorintro}                            
                            </span>
                        </div>                        
                    </div>                    
                </div>
            </div>
            <div class="col-sm-12">                
               	<div class="title_pcoll arup">
               		<h5 class="title_style1b">退換貨須知</h5>
	               	<hr>	
               	</div>
                  	<div class="content_pcoll" style>
		                <div class="contentcolumn_pcoll less">
		                    <div class="backnotefield">
		                        <ul>
		                            <li>
		                                依據「消費者保護法」第19條及行政院消費者保護處公告之「通訊交易解除權合理例外情事適用準則」，以下商品購買後，除商品本身有瑕疵外，將不提供7天的猶豫期：
		                                <ol>
		                                    <li>易於腐敗、保存期限較短或解約時即將逾期。（如：生鮮食品）</li>
		                                    <li>依消費者要求所為之客製化給付。（客製化商品）</li>
		                                    <li>報紙、期刊或雜誌。（含MOOK、外文雜誌）</li>
		                                    <li>經消費者拆封之影音商品或電腦軟體。</li>
		                                    <li>非以有形媒介提供之數位內容或一經提供即為完成之線上服務，經消費者事先同意始提供。（如：電子書、電子雜誌、下載版軟體、虛擬商品…等）</li>
		                                    <li>已拆封之個人衛生用品。（如：內衣褲、刮鬍刀、除毛刀…等）</li>
		                                </ol>
		                            </li>
		                            <li>若非上列種類商品，均享有到貨7天的猶豫期（含例假日）。</li>
		                            <li>	辦理退換貨時，商品（組合商品恕無法接受單獨退貨）必須是您收到商品時的原始狀態（包含商品本體、配件、贈品、保證書、所有附隨資料文件及原廠內外包裝…等），請勿直接使用原廠包裝寄送，或於原廠包裝上黏貼紙張或書寫文字。</li>
		                            <li>退回商品若無法回復原狀，將請您負擔回復原狀所需費用，嚴重時將影響您的退貨權益。</li>
		                        </ul>
		                    </div>
		                </div>
		            </div>
            </div>
        </div>
    </div>
    <div class="mod_t_fixbtn basicFixStyle">
        <div class="side_fixbtn">
            <button type="button" id="BackTop" class="toTop-arrow"></button>
        </div>
        	<jsp:include page="../tag/footer.jsp" />
            
        <!-- </div> -->
    </div>
</body>

<script>
    $(".title_pcoll").on("click", function (event) {
        $(this).siblings().toggleClass("more")
    })
    $(function () {
        $('.fav').click(function () {
            let btn = $(this);
            if($(this).hasClass("like")){
                
                var dataToServer = {
		        	productid: $("#proidval").val()
		        }
		        $.ajax({
			        type: "post",
				    dataType: "text",
				    data: dataToServer,
				    url: "/proj-skoob/pages/myFav.controller",
				    traditional: true
		        }).done(function (e) {
		        	alert("已取消收藏")
		        	btn.toggleClass("like")
		        }).fail(function(){
					swal({
						  icon: 'error',
						  title: '請先登入帳戶',			  
						  timer: 1500
						})
				});
            }else{
                
                var dataToServer = {
		        	productid: $("#proidval").val()
		        }
		        $.ajax({
			        type: "post",
				    dataType: "text",
				    data: dataToServer,
				    url: "/proj-skoob/pages/myFav.controller",
				    traditional: true
		        }).done(function (e) {
		        	alert("已加入收藏")
		        	btn.toggleClass("like")
		        }).fail(function(){
					swal({
						  icon: 'error',
						  title: '請先登入帳戶',			  
						  timer: 1500
						})
				});
            }

        })
    });
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
    /*圖片區*/
	var slideIndex = 1;
	showSlides(slideIndex);
	
	// Next/previous controls
	function plusSlides(n) {
	  showSlides(slideIndex += n);
	}
	
	// Thumbnail image controls
	function currentSlide(n) {
	  showSlides(slideIndex = n);
	}
	
	function showSlides(n) {
	  var i;
	  var slides = document.getElementsByClassName("mySlides");
	  var dots = document.getElementsByClassName("dot");
	  if (n > slides.length) {slideIndex = 1}
	  if (n < 1) {slideIndex = slides.length}
	  for (i = 0; i < slides.length; i++) {
	      slides[i].style.display = "none";
	  }
	  for (i = 0; i < dots.length; i++) {
	      dots[i].className = dots[i].className.replace(" active", "");
	  }
	  slides[slideIndex-1].style.display = "block";
	  dots[slideIndex-1].className += " active";
	}
	
	//0905
	$("#addcart").on("click", function() {	
		//console.log("OK!!!");
		var dataToServer = {
	        productid: $("#proidval").val(),
	       	cartAction: "insert",
	       	page:"product"
	    }

		$.ajax({
		    type: "post",
			dataType: "text",
			data: dataToServer,
			url: "/proj-skoob/pages/cart.controller",
			traditional: true
	    }).done(function(){	
			swal({
			  icon: 'success',
			  title: '已將此商品加入購物車',			  
			  timer: 1500
			})
			setTimeout(() => {
				window.location.reload();
			}, 1500);

		}).fail(function(){
			swal({
			  icon: 'error',
			  title: '請先登入帳戶',			  
			  timer: 1500
			})
		})		
    })

	
</script>


</html>