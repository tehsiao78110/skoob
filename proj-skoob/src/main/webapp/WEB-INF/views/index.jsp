<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>skoob書城首頁</title>
    
<!--     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script> -->
    
    <link rel="stylesheet" href="<c:url value="/resources/extension/font-awesome/css/font-awesome.min.css" />">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script> 
    
	<link rel="stylesheet" href="<c:url value="/resources/extension/bootstrap/css/bootstrap.min.css"/>">
    <script src="<c:url value="/resources/extension/bootstrap/js/bootstrap.bundle.min.js"/>"></script> 
	
	<link rel="stylesheet" href="<c:url value="/resources/css/footer.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/header.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/frontpage.css"/>">
    
</head>

<body>
<jsp:include page="tag/header.jsp" />
    <br>
    <main class="container">
        <h2>新書推薦</h2>
        <div class="container">
                <ul class="row index_list_ul">
                    <li class="dot col">
                        <div>
                            <a href="<c:url value="/pages/product.controller?prodid=1" />">
                            	<img alt="偷書賊" title="偷書賊" src="<c:url value="/resources/pic/1.jpg" />" width="192px" height="192px" style="display: block; margin: auto;">
                            </a>
                        </div>
                        <div>
                            <h3><a href="<c:url value="/pages/product.controller?prodid=1" />" >鐳女孩</a></h3>
                        </div>
                        <div>
                            <span>
                                <b>75 </b>折
                            </span>
                            <span>
                                特價<b> 500 </b>元
                            </span>
                        </div>
                    </li>
                    <li class="dot col">
                        <div>
                            <a href="<c:url value="/pages/product.controller?prodid=9" />">
                            	<img alt="這世界很煩，但你要很可愛。" title="這世界很煩，但你要很可愛。" src="<c:url value="/resources/pic/9.jpg" />" width="192px" height="192px" style="display: block; margin: auto;">
                            </a>
                        </div>
                        <div>
                            <h3><a href="<c:url value="/pages/product.controller?prodid=9" />">這世界很煩，但你要很可愛。</a></h3>
                        </div>
                        <div>
                            
                            <span>
                                特價<b> 300 </b>元
                            </span>
                        </div>
                    </li>
                    <li class="dot col">
                        <div>
                            <a href="<c:url value="/pages/product.controller?prodid=7" />">
                            	<img alt="如果不行，就逃跑吧!" title="如果不行，就逃跑吧!" src="<c:url value="/resources/pic/7.jpg" />" width="192px" height="192px" style="display: block; margin: auto;">
                            </a>
                        </div>
                        <div>
                            <h3><a href="<c:url value="/pages/product.controller?prodid=7" />">如果不行，就逃跑吧!</a></h3>
                        </div>
                        <div>
                            <span>
                                <b>75 </b>折
                            </span>
                            <span>
                                特價<b> 500 </b>元
                            </span>
                        </div>
                    </li>
                    <li class="dot col">
                        <div>
                            <a href="<c:url value="/pages/product.controller?prodid=8" />">
                            	<img alt="智慧之書" title="智慧之書" src="<c:url value="/resources/pic/8.jpg" />" width="192px" height="192px" style="display: block; margin: auto;">
                            </a>
                        </div>
                        <div>
                            <h3><a href="<c:url value="/pages/product.controller?prodid=8" />">智慧之書</a></h3>
                        </div>
                        <div>
                            <span>
                                <b>75 </b>折
                            </span>
                            <span>
                                特價<b> 340 </b>元
                            </span>
                        </div>
                    </li>
                    <li class="dot col">
                        <div>
                            <a href="<c:url value="/pages/product.controller?prodid=21" />">
                            	<img alt="赤燒書" title="赤燒書" src="<c:url value="/resources/pic/21.jpg" />" width="192px" height="192px" style="display: block; margin: auto;">
                            </a>
                        </div>
                        <div>
                            <h3><a href="<c:url value="/pages/product.controller?prodid=21" />">島嶼浮世繪</a></h3>
                        </div>
                        <div>
                           
                            <span>
                                特價<b> 379 </b>元
                            </span>
                        </div>
                    </li>
                </ul>
        </div><br>
        <h2>熱銷書籍</h2>
        <div class="container">
            <ul class="row index_list_ul">
                <li class="dot col">
                    <div>
                        <a href="<c:url value="/pages/product.controller?prodid=25" />">
                        	<img alt="測試測試" title="測試測試" src="<c:url value="/resources/pic/25.jpg" />" width="192px" height="192px" style="display: block; margin: auto;">
                        </a>
                    </div>
                    <div>
                        <h3><a href="<c:url value="/pages/product.controller?prodid=25" />" >從上海到香港，最後的金融大帝
                        </a></h3>
                    </div>
                    <div>
                       
                        <span>
                            特價<b> 100 </b>元
                        </span>
                    </div>
                </li>
                <li class="dot col">
                    <div>
                        <a href="<c:url value="/pages/product.controller?prodid=9" />">
                        	<img alt="這世界很煩，但你要很可愛。" title="這世界很煩，但你要很可愛。" src="<c:url value="/resources/pic/9.jpg" />" width="192px" height="192px" style="display: block; margin: auto;">
                        </a>
                    </div>
                    <div>
                        <h3><a href="<c:url value="/pages/product.controller?prodid=9" />">這世界很煩，但你要很可愛。</a></h3>
                    </div>
                    <div>
                        <span>
                            定價<b> 300 </b>元
                        </span>
                    </div>
                </li>
                <li class="dot col">
                    <div>
                        <a href="<c:url value="/pages/product.controller?prodid=4" />">
                        	<img alt="如果不行，就逃跑吧!" title="如果不行，就逃跑吧!" src="<c:url value="/resources/pic/4.jpg" />" width="192px" height="192px" style="display: block; margin: auto;">
                        </a>
                    </div>
                    <div>
                        <h3><a href="<c:url value="/pages/product.controller?prodid=4" />">航海王98集</a></h3>
                    </div>
                    <div>
                        
                        <span>
                            特價<b> 95 </b>元
                        </span>
                    </div>
                </li>
                <li class="dot col">
                    <div>
                        <a href="<c:url value="/pages/product.controller?prodid=10" />">
                        	<img alt="如果不行，就逃跑吧!" title="如果不行，就逃跑吧!" src="<c:url value="/resources/pic/10.jpg" />" width="192px" height="192px" style="display: block; margin: auto;">
                        </a>
                    </div>
                    <div>
                        <h3><a href="<c:url value="/pages/product.controller?prodid=10" />">急診醫生</a></h3>
                    </div>
                    <div>
                        <span>
                            <b>75 </b>折
                        </span>
                        <span>
                            特價<b> 450 </b>元
                        </span>
                    </div>
                </li>
                <li class="dot col">
                    <div>
                        <a href="<c:url value="/pages/product.controller?prodid=11" />">
                        	<img alt="如果不行，就逃跑吧!" title="如果不行，就逃跑吧!" src="<c:url value="/resources/pic/11.jpg" />" width="192px" height="192px" style="display: block; margin: auto;">
                        </a>
                    </div>
                    <div>
                        <h3><a href="<c:url value="/pages/product.controller?prodid=11" />">宮前町九十番地</a></h3>
                    </div>
                    <div>
                        
                        <span>
                            特價<b> 111 </b>元
                        </span>
                    </div>
                </li>
            </ul>
    </div><br>
        <h2>暢銷排行</h2>
        <div class="container">
            <ul class="row index_list_ul">
                <li class="dot col">
                    <div>
                        <a href="<c:url value="/pages/product.controller?prodid=13" />">
                        	<img alt="測試測試" title="測試測試" src="<c:url value="/resources/pic/13.jpg" />" width="192px" height="192px" style="display: block; margin: auto;">
                        </a>
                    </div>
                    <div>
                        <h3><a href="<c:url value="/pages/product.controller?prodid=13" />" >沙丘（1）【電影書衣珍藏版】</a></h3>
                    </div>
                    <div>
                        
                        <span>
                            特價<b> 800 </b>元
                        </span>
                    </div>
                </li>
                <li class="dot col">
                    <div>
                        <a href="<c:url value="/pages/product.controller?prodid=14" />">
                        	<img alt="這世界很煩，但你要很可愛。" title="這世界很煩，但你要很可愛。" src="<c:url value="/resources/pic/14.jpg" />" width="192px" height="192px" style="display: block; margin: auto;">
                        </a>
                    </div>
                    <div>
                        <h3><a href="<c:url value="/pages/product.controller?prodid=14" />">接體員大師兄火葬場來報到：《火來了，快跑》</a></h3>
                    </div>
                    <div>
                        <span>
                            <b>75 </b>折
                        </span>
                        <span>
                            特價<b> 728 </b>元
                        </span>
                    </div>
                </li>
                <li class="dot col">
                    <div>
                        <a href="<c:url value="/pages/product.controller?prodid=15" />">
                        	<img alt="如果不行，就逃跑吧!" title="如果不行，就逃跑吧!" src="<c:url value="/resources/pic/15.jpg" />" width="192px" height="192px" style="display: block; margin: auto;">
                        </a>
                    </div>
                    <div>
                        <h3><a href="<c:url value="/pages/product.controller?prodid=15" />">費德勒：王者之路（隨書附贈「費德勒生涯大事紀」精美書衣海報）</a></h3>
                    </div>
                    <div>
                        
                        <span>
                            特價<b> 580 </b>元
                        </span>
                    </div>
                </li>
                <li class="dot col">
                    <div>
                        <a href="<c:url value="/pages/product.controller?prodid=17" />">
                        	<img alt="如果不行，就逃跑吧!" title="如果不行，就逃跑吧!" src="<c:url value="/resources/pic/17.jpg" />" width="192px" height="192px" style="display: block; margin: auto;">
                        </a>
                    </div>
                    <div>
                        <h3><a href="<c:url value="/pages/product.controller?prodid=17" />">米澤穗信與古籍研究社【《冰菓》系列公式紀念MOOK．收錄全新短篇小說】</a></h3>
                    </div>
                    <div>
                        <span>
                            <b>75 </b>折
                        </span>
                        <span>
                            特價<b> 299 </b>元
                        </span>
                    </div>
                </li>
                <li class="dot col">
                    <div>
                        <a href="<c:url value="/pages/product.controller?prodid=19" />">
                        	<img alt="如果不行，就逃跑吧!" title="如果不行，就逃跑吧!" src="<c:url value="/resources/pic/19.jpg" />" width="192px" height="192px" style="display: block; margin: auto;">
                        </a>
                    </div>
                    <div>
                        <h3><a href="<c:url value="/pages/product.controller?prodid=19" />">咖啡帝國：勞動、剝削與資本主義，一部全球貿易下的咖啡上癮史</a></h3>
                    </div>
                    <div>
                        <span>
                            <b>75 </b>折
                        </span>
                        <span>
                            特價<b> 500 </b>元
                        </span>
                    </div>
                </li>
            </ul>
    </div><br>
        
    </main>

	<jsp:include page="tag/footer.jsp" />

    
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
 </script>
</body>

</html>