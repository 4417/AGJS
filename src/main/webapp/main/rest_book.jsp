<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="agjs.bean.restaurant.*"%>


<%
RestaurantBookVO restaurantBookVO = (RestaurantBookVO) request.getAttribute("restaurantBookVO");
%>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>| 美食饗宴 | 訂位 |</title>
<link rel="stylesheet" href="style/AGJS_2.css">
<!-- <link rel="stylesheet" href="style/AGJS.css"> -->
<link rel="icon" href="image/logo.ico" type="image/x-icon" />
</head>

<body>

	<main>
		<div class="pic_book">
			<div class="picture">
				<img src="image/hotelRSRT-1.png" alt="">
			</div>
			<div class="picture">
				<img src="image/hotelRSRT-2.png" alt="">
			</div>
			<div class="picture">
				<img src="image/hotelRSRT-3.png" alt="">
			</div>
		</div>
		<div class="reservation">
			<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
			<div>
				<p>*為必填</p>
			</div>

			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/main/rest_book" name="form1">
				<div class="elem-group">
					<label for="restaurant-selection">選擇餐廳*</label> <input type="text"
						id="restaurant" placeholder="Java Steak House" required
						class="restaurant" name="REST_ID">
				</div>
				<div class="elem-group">
					<label for="name">姓名*</label> <input type="text" id="name"
						name="USER_NAME" name="visitor_name" placeholder="王小明"
						pattern=[\u4e00-\u9fa5]{2,4} required class="name">
				</div>
				<div class="elem-group">
					<label for="phone">手機號碼*</label> <input type="tel" id="phone"
						name="REST_TEL" placeholder="0912-345-678"
						pattern=(\d{4})-?\s?(\d{3})-?\s?(\d{3}) required class="phone">
				</div>
				<hr>
				<div class="elem-group inlined">
					<label for="people">人數*</label> <input type="number" id="people"
						name="REST_NUM" placeholder="1" min="1" required class="people">
				</div>
				<div class="elem-group inlined">
					<label for="reservation-date">訂位日期*</label> <input type="date"
						id="f_date1" name="REST_DATE" required class="date">
				</div>

				<hr>
				<div class="elem-group">
					<label for="message">特殊備註</label>
					<textarea id="message" name="visitor_message" name="REST_NOTE"
						placeholder="Tell us anything else that might be important."
						class="note"></textarea>
				</div>
				<div class="modal-footer">
					<input type="hidden" name="action" value="insert"> <input
						type="submit" value="確認訂位" class="btn btn-primary">
				</div>
			</Form>
		</div>
	</main>
	<!--///////////////////////////////////////////////////////////////////////////////////////////////////////////-->
	<script src="vendors/jquery/jquery-3.6.0.min.js"></script>
	<script src="js/booking.js"></script>
	<script
		src="https://code.iconify.design/iconify-icon/1.0.0-beta.3/iconify-icon.min.js"></script>
	<script src="js/header_footer.js"></script>
</body>




</html>