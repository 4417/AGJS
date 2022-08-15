<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />

    <title>後台管理 | 客服訊息</title>

    <!-- Custom fonts for this template-->
    <link
      href="vendor/fontawesome-free/css/all.min.css"
      rel="stylesheet"
      type="text/css"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
      rel="stylesheet"
    />

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet" />
    <link
      href="css/back-message.css"
      rel="stylesheet"
      type="text/css"
      media="all"
    />
    <link rel="icon" href="img/logo.ico" type="image/x-icon" />
    <style type="text/css">
	.conbox{
		padding: 200px 100px;
	}
	.text-field{
		width: 400px;
		height: 40px;
		padding: 0px;
		float: left;
    	margin-left: 10px;
    	outline: none;
    	text-indent: 10px;
    	border: none;
	}
	.button{
    	width: 100px;
    	hight: 200px;
    	border-radius: 10px;
    	border-color: #dfb54d98;
    	background-color: #dfb54d98;
    }
    .forminput{
    	display: flex;
    	justify-content: center; 
    	align-items: center; 
    }
	.contactFormSubmitBtn {
    	width: 120px;
    	height: 30px;
    	margin: 0 0 0 5px;
    	text-align:center;
    	border-radius: 10px;
    	border-color: #dfb54d98;
		background-color: #dfb54d98;
	}
    </style>
  </head>

  <body id="page-top">
    <!-- Page Wrapper -->
    <div id="wrapper">
      <!-- Content Wrapper -->
      <div id="content-wrapper" class="d-flex flex-column">
        <!-- Main Content -->
        <div id="content">
          <!-- Begin Page Content -->
          <br />
  		<br>
		<div class="talk_con">
			<div class="conbox">
			<h4 align="center">值班人員驗證</h4>
			<form id="myForm" action="<%=request.getContextPath()%>/admin/chat" method="POST" autocomplete="off">
				<div class="forminput">
				<input id="userName" name="userName" class="text-field" type="password" value="manager" readonly placeholder="請輸入管理員代碼以進入客服系統" /> 
<!-- 				<input type="submit" id="send" class="button" value="送出" onclick="sendName();"></input> -->
				<button type="submit" id="send" class="contactFormSubmitBtn" onclick="sendName();">驗證</button>
				</div>
			</form>
		</div>
	</div>
	<br>

          <!-- /.container-fluid -->
        </div>
        <!-- End of Main Content -->
      </div>
      <!-- End of Content Wrapper -->
    </div>
    <!-- End of Page Wrapper -->

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <!-- <script src="vendor/chart.js/Chart.min.js"></script> -->
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<!--     <script src="js/back-message.js"></script> -->
    <!-- Page level custom scripts -->
    <!-- <script src="js/demo/chart-area-demo.js"></script> -->
    <!-- <script src="js/demo/chart-pie-demo.js"></script> -->
    <!-- util-bar -->
    <script src="js/util-bar.js"></script>
   <script>
	var inputUserName = document.getElementById("userName");
	inputUserName.focus();
	
	function sendName() {
		var userName = inputUserName.value.trim();
		if (userName === "") {
			alert("請驗證管理員身分");
			inputUserName.focus();
			return;
		} else {
			document.getElementById("myForm").submit();
		}
	}
</script>
  </body>
</html>
