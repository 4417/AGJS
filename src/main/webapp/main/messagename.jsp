<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-tw">
<head>
<title>線上客服-歡迎</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="style/layout.css" rel="stylesheet" type="text/css" media="all">
<link href="style/AGJS.css" rel="stylesheet" type="text/css" media="all">
<link href="style/message.css" rel="stylesheet" type="text/css" media="all">
<link rel="icon" href="image/logo.ico" type="image/x-icon" />
</head>
<body id="top">

<!-- content -->
<main>
	<br>
	<div class="talk_con">
		<div class="conbox">
			<h1 align="center">歡迎使用線上客服系統,請問該如何稱呼您?</h1>
			<form id="myForm" action="<%=request.getContextPath()%>/main/chat" method="POST" autocomplete="off">
				<div class="forminput">
				<input id="userName" name="userName" class="text-field" type="text" placeholder="請輸入您的稱呼,客服人員即將為您服務" /> 
				<button type="submit" id="send" class="contactFormSubmitBtn" value="送出" onclick="sendName();">送出</button>
				</div>
			</form>
		</div>
	</div>
	<br>
</main>

	<a id="backtotop" href="#top"><i class="fa fa-chevron-up"></i></a>
	<!-- JAVASCRIPTS -->
	<script src="vendors/jquery/jquery-3.6.0.min.js"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script src="js/messagename.js"></script>
	<script src="js/header_footer.js"></script>
	<script src="https://code.iconify.design/iconify-icon/1.0.0-beta.3/iconify-icon.min.js"></script>
<!-- 	<script src="layout/scripts/jquery.min.js"></script> -->
<!-- 	<script src="layout/scripts/jquery.backtotop.js"></script> -->
<!-- 	<script src="layout/scripts/jquery.mobilemenu.js"></script> -->
</body>
</html>