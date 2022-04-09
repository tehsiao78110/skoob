<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員資料</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
<link rel="stylesheet" href="../extension/font-awesome/css/font-awesome.css">
<link rel="stylesheet" href="../extension/font-awesome/css/font-awesome.min.css">

<link rel="stylesheet" href="../CSS/MemberInfoStyle.css">
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/tw-city-selector@2.1.1/dist/tw-city-selector.min.js"></script>

</head>
<body>

	<script type="text/javascript">
		new TwCitySelector();
	</script>

<header>
        <div class="container row header_main">
            <div class="col">
                <h1>Skoob</h1>
            </div>
            <div class="col-8 header_form_wrap">
                <form action="" class="header_form_content">
                    <input type="text" name="" id="" class="header_input_text" placeholder="請輸入關鍵字">
                    <button type="submit" class="header_btn_search">
                        <div class="fa fa-search fa-lg"></div>
                    </button>
                </form>
            </div>
            <div class="col header_operation_wrap">
                <button class="btn header_btn_login">登入/註冊</button>
                <i class="fa fa-shopping-cart fa-lg header_btn_cart" aria-hidden="true"></i>
            </div>
        </div>
    </header>
	
	

	<div class="row  " style="height: 1000px;">
	<form class="Info" action="<c:url value="/member/memberedit.controller" />" method=post>
	<h3>會員資料</h3>
	<h3><span class="error">${errors.action}</span></h3>	
	
	       <table>
            <tr>
                <td style="width: 180px;">帳號 :</td>
                <td style="width: 200px;">${user.account}</td>
            </tr>
        </table>
        <hr>
        <table>
            <tr>
                <td style="width: 180px;">姓名 :</td>
                <td style="width: 200px;">${user.name}</td>
            </tr>
        </table>
        <hr>
        <table>
            <tr>
	            <td rowspan="2" style="width: 180px;">地址 :</td>             
	            <td style="width: 600px;">   
	             <div role="tw-city-selector" data-county-value="${user.county}"data-district-value="${user.district}" >
				                
	             </div>
	            </td>
            </tr>
            <tr>
	           <td>
	            <input size="60" type="text" name="address" value="${user.address}">
               </td>
            </tr>	           
            <tr>           
                <td><span class="error">${errors.address}</span></td>
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
                <td style="width: 180px;">生日 :</td>
                <td style="width: 200px;">${birth}</td>
            </tr>
        </table>
        <hr>
        <table>
            <tr>
                <td style="width: 180px;">市話 :</td>
                <td style="width: 200px;"><input type="text" name="tel" value="${user.tel}"></td>
                <td><span class="error">${errors.tel}</span></td>
            </tr>
        </table>
        <hr>
        <table>
            <tr>
                <td style="width: 180px;">手機 :</td>
                <td style="width: 200px;"><input type="text" name="phone" value="${user.phone}"></td>
                <td><span class="error">${errors.phone}</span></td>
            </tr>
        </table>
       
		
	<div class="assy position-absolute start-50 translate-middle" >	
	<input class="Check position-absolute top-0 start-0 translate-middle"  type="submit" name="cancel" value="取消">
	<input  class="Check position-absolute top-0 start-100 translate-middle"  type="submit" name="save" value="儲存">
	</div>
	

</form>



</div>


<footer class="">
        <div class="container row footer_main">
            <section class="col footer_item">
                <h4>關於我們</h4>
                <ul>
                    <li>
                        <a href="">skoob書城</a>
                    </li><br>
                    <li>
                        <a href="">skoob網路書城</a>
                    </li><br>
                    <li>
                        <a href="">skoob書城全台門市</a>
                    </li><br>
                    <li>
                        <a href="">出版情報</a>
                    </li><br>
                    <li>
                        <a href="">工作機會</a>
                    </li><br>
                </ul>
            </section>
            <section class="col footer_item">
                <h4>會員服務</h4>
                <ul>
                    <li>
                        <a href="">我的帳號</a>
                    </li><br>
                    <li>
                        <a href="">訂單查詢</a>
                    </li><br>
                    <li>
                        <a href="">訂閱電子報</a>
                    </li><br>
                    <li>
                        <a href="">會員服務條款</a>
                    </li><br>
                    <li>
                        <a href="">隱私權政策</a>
                    </li><br>
                    <li>
                        <a href="">網路交易安全</a>
                    </li><br>
                </ul>
            </section>
            <section class="col footer_item">
                <h4>客服中心</h4>
                <ul>
                    <li>
                        <a href="">常見問題</a>
                    </li><br>
                    <li>
                        <a href="">購物說明</a>
                    </li><br>
                    <li>
                        <a href="">付款方式</a>
                    </li><br>
                    <li>
                        <a href="">寄送方式</a>
                    </li><br>
                    <li>
                        <a href="">售後服務</a>
                    </li><br>
                    <li>
                        <a href="">聯絡我們</a>
                    </li><br>
                </ul>
            </section>
            <section class="col footer_item">
                <h4>合作提案</h4>
                <ul>
                    <li>
                        <a href="">異業合作</a>
                    </li><br>
                    <li>
                        <a href="">我要成為供應商</a>
                    </li><br>
                    <li>
                        <a href="">廣告合作</a>
                    </li><br>
                    <li>
                        <a href="">大量採購</a>
                    </li><br>
                    <li>
                        <a href="">福利平台</a>
                    </li><br>
                    <li>
                        <a href="">加盟</a>
                    </li><br>
                </ul>
            </section>
            <section class="col footer_item">
                <h4>其他服務</h4>
                <ul>
                    <li>
                        <a href="">加入好友</a>
                    </li><br>
                    <li>
                        <a href="">加入粉絲團</a>
                    </li><br>
                    <li>
                        <a href="">追蹤我們</a>
                    </li><br>
                </ul>
            </section>
            <section class="col footer_item">
                <h4>廠商專區</h4>
                <ul>
                    <li>
                        <a href="">供應商後台</a>
                    </li><br>
                    <li>
                        <a href="">B2B供應鏈平台</a>
                    </li><br>
                </ul>
            </section>
        </div>
    </footer>
</body>
</html>