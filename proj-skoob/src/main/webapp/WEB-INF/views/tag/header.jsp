<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<header>
	<div class="container row header_main">
		<div class="col">
			<a class="heander_index_title" href="<c:url value="/index"/>" >
				<h1 class="Skoob">Skoob</h1>
			</a>
		</div>
		<div class="col-8 header_form_wrap">
			<form action="<c:url value="/index/search" />" class="header_form_content">
				<input type="text" name="keyword" id="" class="header_input_text" value="${param.keyword}"
					placeholder="請輸入關鍵字">
				<button type="submit" class="header_btn_search">
					<div class="fa fa-search fa-lg"></div>
				</button>
			</form>
		</div>
		<div class="col header_operation_wrap">
			<c:if test="${not empty user}">
                <div class="dropdown">
                    <a class="" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">
                        <div class="header_btn_member">
                            <i class="fa fa-user fa-lg header_btn_cart" aria-hidden="true">${user.account}</i>
                        </div>
                    </a>
                    <ul class="dropdown-menu header_member_dropdown" aria-labelledby="dropdownMenuButton1">
                        <li><a class="dropdown-item" href="<c:url value="/pages/order.controllor"/>">訂單查詢</a></li>
                        <li><a class="dropdown-item" href="<c:url value="/pages/myFav.controller"/>">我的收藏</a></li>
                        <li><a class="dropdown-item" href="#">修改會員資料</a></li>
                        <li><a class="dropdown-item" href="<c:url value="/pages/logout.controller"/>">登出</a></li>
                       
                    </ul>
                </div>
			</c:if>
			<c:if test="${empty user}">
				<button class="btn header_btn_login" onclick="location.href='/proj-skoob/pages/login.controller'">登入/註冊</button>
			</c:if>	
			<div class="header_btn_cart_wrap">
				
				<a href="<c:url value="/pages/cart.controller"/>">
				<i class="fa fa-shopping-cart fa-lg header_btn_cart"
					aria-hidden="true"></i>
				</a>	
				<span class="header_cart_num">
                    <c:out value="${cartDto.productAllNum}" default="0"/> 
                </span>	
					
			</div>
		</div>
	</div>
</header>