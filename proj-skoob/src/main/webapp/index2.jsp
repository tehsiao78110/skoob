<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>

<h3>Welcome ${user.account}</h3>


<h3><a href="<c:url value="/member/register.jsp" />">註冊測試頁面</a></h3>
<h3><a href="<c:url value="/member/login.jsp" />">登入測試頁面</a></h3>
<h3><a href="<c:url value="/product/product.controller?prodid=2" />">產品測試頁面</a></h3>
<h3><a href="<c:url value="/search/search.jsp" />">產品搜尋頁面</a></h3>
<h3><a href="<c:url value="/member/checkout.jsp" />">下訂頁面</a></h3>
<h3><a href="<c:url value="/member/frontpage.jsp" />">首頁頁面</a></h3>
<h3><a href="<c:url value="/TransactionRecord/TransactionRecord.jsp" />">首頁頁面</a></h3>
</body>
</html>