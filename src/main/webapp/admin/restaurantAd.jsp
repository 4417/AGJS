<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="agjs.bean.restaurant.*"%>


<%
RestaurantADVO restaurantADVO = (RestaurantADVO) request.getAttribute("restaurantADVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>餐廳優惠</title>
<link rel="icon" href="img/logo.ico" type="image/x-icon" />
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet" />
<link href="css/ResuaurantBack.css" rel="stylesheet" />
<link href="css/sb-admin-2.min.css" rel="stylesheet" />
</head>

<body id="page-top">
	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">
			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="index.html">
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="fas fa-laugh-wink"></i>
				</div>
				<div class="sidebar-brand-text mx-3">後台管理</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0" />

			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="index.html"> <i class="fas fa-fw fa-tachometer-alt"></i> <span>公告管理</span></a>
			</li>

			<!-- Divider -->
			<hr class="sidebar-divider my-0" />

			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="index.html"> <i class="fas fa-fw fa-tachometer-alt"></i> <span>訂單修改管理</span></a>
			</li>

			<!-- Divider -->
			<hr class="sidebar-divider my-0" />

			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="roomManagement.html"> <i
					class="fas fa-fw fa-tachometer-alt"></i> <span>房間管理</span></a></li>

			<!-- Divider -->
			<hr class="sidebar-divider my-0" />
			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="index.html"> <i class="fas fa-fw fa-tachometer-alt"></i> <span>行程管理</span></a>
			</li>
			<!-- Divider -->
			<hr class="sidebar-divider my-0" />
			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="report.html"> <i class="fas fa-fw fa-tachometer-alt"></i>
					<span>財務報表</span></a></li>

			<!-- Divider -->
			<hr class="sidebar-divider my-0" />
			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="restaurantBackStage.html"> <i
					class="fas fa-fw fa-tachometer-alt"></i> <span>餐廳管理</span></a></li>

			<!-- Divider -->
			<hr class="sidebar-divider my-0" />
			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="index.html"> <i class="fas fa-fw fa-tachometer-alt"></i> <span>客服</span></a>
			</li>
			<!-- Divider -->
			<hr class="sidebar-divider" />
			<!-- Heading -->
		</ul>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>


					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">

						<div class="topbar-divider d-none d-sm-block"></div>

						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small">管理員</span> <img
								class="img-profile rounded-circle" src="img/undraw_profile.svg" />
						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="dropdown-item" href="#"> <i
									class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Profile
								</a> <a class="dropdown-item" href="#"> <i
									class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
									Settings
								</a> <a class="dropdown-item" href="#"> <i
									class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
									Activity Log
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									Logout
								</a>
							</div></li>
					</ul>
				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4"></div>

					<!-- 餐廳優惠介面 -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h4 class="font-weight-bold text-primary">餐廳優惠介面</h4>
							
							<!-- 彈窗 -->
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/admin/restaurantAd.jsp"
								name="form1">
									<div class="modal-dialog modal-lg" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalLabel">更新優惠資訊</h5>
											</div>
											<div class="modal-body">
												<div class="container-fluid ">
													<div class="row card-header ">
														<div class="col-md-12">
															<div class="mb-3">
																<input type="text" class="form-control" name="adName"
																	id="exampleFormControlInput1" placeholder="優惠名稱"
																	value="<%=(restaurantADVO == null) ? "優惠名稱" : restaurantADVO.getAdName()%>">
															</div>
															<div class="mb-3">
																<textarea class="form-control" name="adIntro"
																	id="exampleFormControlTextarea1" rows="3"
																	placeholder="優惠介紹"
																	value="<%=(restaurantADVO == null) ? "優惠介紹" : restaurantADVO.getAdIntro()%>"></textarea>
															</div>

														</div>
														<!-- <div class="col-md-4 ml-auto">.col-md-4 .ml-auto</div> -->
													</div>
													<div class="row" style="width: 2000px">
														<div class="col-6 d-flex align-items-center"
															style="padding: 10px">
															<span style="color: blacks;">餐廳&nbsp&nbsp&nbsp&nbsp</span>
															<div class="form-check form-check-inline">
																<input class="form-check-input" type="checkbox"
																	id="inlineCheckbox1" value="1"> <label
																	class="form-check-label" for="inlineCheckbox1">Java
																	Steak House</label>
															</div>
															<div class="form-check form-check-inline">
																<input class="form-check-input" type="checkbox"
																	id="inlineCheckbox1" value="2"> <label
																	class="form-check-label" for="inlineCheckbox1">Monohiya</label>
															</div>
															<div class="form-check form-check-inline">
																<input class="form-check-input" type="checkbox"
																	id="inlineCheckbox1" value="3"> <label
																	class="form-check-label" for="inlineCheckbox1">102
																	BAR</label>
															</div>
														</div>
													</div>

													<div class="row card">
														<div class="card-header">到期時間</div>
														<div class="col-md-12 card-body ">
															<div class="form-check form-check-inline">
																<input class="form-check-input" type="text"
																	name="adTime" id="inlineCheckbox1"
																	value="<%=(restaurantADVO == null) ? "優惠到期時間" : restaurantADVO.getAdTime()%>">
															</div>
														</div>
													</div>
													<div class="row ">
														<div class="col-sm-6 d-flex align-items-center"
															style="padding: 10px">
															<span>優惠照片 &nbsp</span>
															<div class="col-sm-6 custom-file">
																<input type="file" class="room-file-input" id="AdFile"
																	name="AdFile" multiple> <label
																	class="room-file-label" for="roomFile"></label>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													><a href="restaurantBackStage.html">關閉</a></button>
												<input type="hidden" name="action" value="insert"> <input
													type="submit" value="新增" class="btn btn-primary">
											</div>
										</div>
									</div>
							</FORM>
						</div>

						<!-- 頁底 copyright -->
						<div class="card-body" id="roomList">
							<footer class="sticky-footer bg-white">
								<div class="container my-auto">
									<div class="copyright text-center my-auto">
										<span class="copyright"> &copy; 2022, A GooD Journey
											SySTem, Inc.或其附屬公司</span>
									</div>
								</div>
							</footer>
						</div>
						<!-- End of Content Wrapper -->
					</div>
					<!-- End of Page Wrapper -->

					<!-- 至頂Button-->
					<a class="scroll-to-top rounded" href="#page-top"> <i
						class="fas fa-angle-up"></i>
					</a>

					<!-- 登出按鈕-->
					<div class="modal fade" id="logoutModal" tabindex="-1"
						role="dialog" aria-labelledby="exampleModalLabel"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Ready to
										Leave?</h5>
									<button class="close" type="button" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
								</div>
								<div class="modal-body">Select "Logout" below if you are
									ready to end your current session.</div>
								<div class="modal-footer">
									<button class="btn btn-secondary" type="button"
										data-dismiss="modal">Cancel</button>
									<a class="btn btn-primary" href="login.html">Logout</a>
								</div>
							</div>
						</div>
					</div>

					<!-- Bootstrap core JavaScript-->
					<script src="vendor/jquery/jquery.min.js"></script>
					<!-- 彈窗 -->
					<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

					<!-- Core plugin JavaScript-->
					<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

					<!-- Custom scripts for all pages-->
					<script src="js/sb-admin-2.min.js"></script>

					<!-- Page level plugins -->
					<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

					<!-- Page level custom scripts -->
					<script src="js/demo/datatables-demo.js"></script>
</body>


</html>