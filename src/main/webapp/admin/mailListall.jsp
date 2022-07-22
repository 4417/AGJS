<%@page import="agjs.service.CustomerServiceMailTableService"%>
<%@page import="agjs.bean.CustomerServiceMailVO"%>
<%@page import="agjs.service.CustomerServiceMailService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="agjs.dao.*"%>

<%
    CustomerServiceMailTableService customerServiceMailTableService = new CustomerServiceMailTableService();
    List<CustomerServiceMailVO> list = customerServiceMailTableService.getAll();
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />

    <title>後台管理 | 客服表單</title>
	<link rel="icon" href="img/logo.ico" type="image/x-icon" />
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
    <style>
  table {
	width: 900px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #A14511;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  .listdiv{
  flex-direction: column;
  width: 950px;
  padding: 0 0 0 30px;
  }
  .sub {
    width: 80px;
    border-radius: 10px;
    border-color: #dfb54d98;
    background-color: #dfb54d98;
}
  .check{
  	 text-align: right;
  }
</style>
    <link rel="icon" href="images/logo.ico" type="image/x-icon" />
  </head>

  <body id="page-top">
    <!-- Page Wrapper -->
    <div id="wrapper">
      <!-- Content Wrapper -->
      <div id="content-wrapper" class="d-flex flex-column">
        <!-- Main Content -->
        <div id="content">
    <!-- Begin Page Content -->
 <div class="listdiv">
<table>
	<tr>
		<th>編號</th>
		<th>問題種類</th>
		<th>名稱</th>
		<th>手機</th>
		<th>信箱</th>
		<th>內容</th>
		<th>日期</th>
		<th>待處理</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="customerServiceMailVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${customerServiceMailVO.faqFormId}</td>
			<td>${customerServiceMailVO.faqTypeName}</td>
			<td>${customerServiceMailVO.userName}</td>
			<td>${customerServiceMailVO.userPhone}</td>
			<td>${customerServiceMailVO.userEmail}</td>
			<td>${customerServiceMailVO.contentText}</td> 
			<td>${customerServiceMailVO.createDate}</td>
<%-- 			<fmt:formatDate value="${customerServiceMailVO.createDate}" pattern="yyyy-MM-dd"/> --%>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="empno"  value="${empVO.empno}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
			<td>
			  <FORM METHOD="post" ACTION="CustomerServiceMailTabledo" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="faqFormId"  value="${customerServiceMailVO.faqFormId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
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
    <script src="js/back-message.js"></script>
    <!-- Page level custom scripts -->
    <!-- <script src="js/demo/chart-area-demo.js"></script> -->
    <!-- <script src="js/demo/chart-pie-demo.js"></script> -->
    <!-- util-bar -->
    <script src="js/util-bar.js"></script>
  </body>
</html>