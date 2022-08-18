<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<title>線上客服</title>
<style>
  @import url(style/message.css);
</style>
<link href="style/layout.css" rel="stylesheet" type="text/css" media="all">
<link href="style/AGJS.css" rel="stylesheet" type="text/css" media="all">
<link href="style/message.css"rel="stylesheet" type="text/css" media="all">
<link rel="icon" href="image/logo.ico" type="image/x-icon" />
</head>
<body id="top" onload="connect();" onunload="disconnect();">
 <div class="wrapper row0" style="background-color: #f3f1f1;">
    <div id="topbar" class="hoc clear">
      <!-- ################################################################################################ -->
      <div class="fl_right" style="margin-bottom: -20px; margin-top: 5px;">
        <p style="margin: 0 10px; display: inline;"><iconify-icon icon="ci:phone"></iconify-icon> (02)2222-1122</p>
        <p style="margin: 0 10px; display: inline;"><iconify-icon icon="fluent:mail-16-filled"></iconify-icon> info@agjs.com</p>
        <a href="user_account.html" style="margin-left: 10px; margin-right: 5px; color: #D01818; font-weight: bold;"><iconify-icon icon="bxs:user" color: #d01818;"></iconify-icon> 會員中心</a>
      </div>
      <!-- ################################################################################################ -->
    </div>
  </div>
  <div class="wrapper row1">
    <header id="header" class="hoc clear"> 
      <div id="Lheader">
        <div id="LogoImg" style="margin-bottom: 20px;">
          <a href="about.html"><img id="LOGO" src="image/logo_v2.png"style="width: 80%;"></a>
        </div>
        <div id="logo" class="fl_left" style="margin-top: 30px; margin-left: 0;">
          <h1><a href="about.html" style="font-size: 20px;">A GooD Journey SySTem </a></h1>
        </div>
      </div>
      <nav id="mainav" class="fl_right">
        <ul class="clear">
          <li style="margin-right: 5px;">
          <a href="announcement.html" style="padding: 10px 0;">最新消息</a>
          </li>
          <li style="margin-right: 5px;">
            <a href="about.html" style="padding: 10px 0;">關於我們</a>
          </li>
          <li style="margin-right: 5px;">
            <a href="roomlist.html" style="padding: 10px 0;">房型介紹</a>
            <ul>
              <li><a href="roomtype1.html">山景標準房</a></li>
              <li><a href="roomtype2.html">山景雅致房</a></li>
              <li><a href="roomtype3.html">海景標準房</a></li>
              <li><a href="roomtype4.html">海景雅致房</a></li>
            </ul>
          </li>
          <li style="margin-right: 5px;">
            <a href="guide.html" style="padding: 10px 0;">行程介紹</a>
          </li>
          <li style="margin-right: 5px;">
            <a href="restaurant.html" style="padding: 10px 0;">美食饗宴</a>
            <ul>
              <li><a href="./rest_Intro1.html">Java Steak House</a></li>
              <li><a href="./rest_Intro2.html">Momohiya</a></li>
              <li><a href="./rest_Intro3.html">102 BAR</a></li> 
            </ul>
          </li>
          <li style="margin-right: 5px;">
            <a style="padding: 10px 0;">聯絡我們</a>
            <ul>
              <li><a href="mail.html">客服表單</a></li>
              <li><a href="messagename.jsp">線上客服</a></li>
            </ul>
          </li>
          <li class="active" style="margin-right: 5px;">
            <a href="booking_search.html" style="padding: 10px 0;">立即訂房</a>
          </li>
        </ul>
      </nav>
    </header>
  </div>
<!-- ################################################################################################ -->
<!-- content -->
<main>
<br>
<h3 id="statusOutput" class="statusOutput" style="display: none">friend</h3>
<div class="talk_con">
  <div class="containerBox">
    <div class="titleBox">
      <div class="titleSubEn">GET IN TOUCH</div>
      <div class="titleEn">CONTACT US</div>
      <div class="titleSubCn">聯絡我們</div>
      <div class="yiyleCh">
        歡迎您透過本線上客服詢問住宿、訂房相關問題，或與我們分享您的心得與建議。
      </div>
    </div>
  </div>
  <div  id="messagesArea" class="talk_show panel message-area">
  </div>
  <div class="talk_input row">
    <div class="col-9 d-flex justify-content-end">
        <input id="message" class="talk_word" type="text" placeholder="請輸入訊息" onkeydown="if (event.keyCode == 13) sendMessage();" /> 
    </div>
    <div class="col-3 d-flex justify-content-center">

		<input type="button" id="connect" class="button" value="Connect" onclick="connect();" style="display: none"/> 
		<input type="button" id="disconnect" class="button" value="Disconnect" onclick="disconnect();" style="display: none" />
        <button type="submit" value="確認送出" class="talk_sub" id="sendMessage" onclick="sendMessage();">確認送出</button>
    </div>
  </div>
</div>
<br>
<div id="row" class="choose" style="display: block; display: none;"></div>
<br>
</main>
  
<!-- ################################################################################################ -->
<div class="bgded overlay"> 
<!-- ################################################################################################ -->
<div class="wrapper row4">
  <footer id="footer" class="hoc clear"> 
    <!-- ################################################################################################ -->
    <div class="one_quarter first">
      <h6 class="heading">FOLLOW US</h6>
      <ul class="faico clear">
        <li><a class="faicon-facebook" href="#"><i class="fa fa-facebook"></i></a></li>
        <li><a class="faicon-twitter" href="#"><i class="fa fa-twitter"></i></a></li>
        <li><a class="faicon-dribble" href="#"><i class="fa fa-dribbble"></i></a></li>
        <li><a class="faicon-linkedin" href="#"><i class="fa fa-linkedin"></i></a></li>
      </ul>
      <br>
      <ul class="nospace btmspace-30 linklist contact">
        <li><i class="fa fa-phone"></i> 收到最新消息</li>
        <input class="btmspace-15" type="text" value="" placeholder="Name">
        <button type="submit" value="submit">Submit</button>
      </ul>
    </div>
    <div id="middle" class="one_quarter">
      <img id="f_logo" src="image/logo_v2.png">
      <br>
      <br>
      <ul id="text" class="nospace linklist">
        <li>A GooD Journey SySTem</li>
        <li>連絡電話：(02)2222-1122</li>
        <li>地址：104台北市中山區南京東路三段219號5樓</li>
      </ul>
    </div>
    <div class="one_quarter">
      <h6 class="heading">交通位置</h6>
      <img src="image/map.png">
    </div>
    <!-- ################################################################################################ -->
  </footer>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper row5">
  <div id="copyright" class="hoc clear"> 
    <!-- ################################################################################################ -->
    <p class="fl_left">Copyright &copy; 2022 - All Rights Reserved - <a href="#">A GooD Journey SySTem, Inc.或其附屬公司</a></p>
    <!-- ################################################################################################ -->
  </div>
</div>
<!-- ################################################################################################ -->
</div>
<!-- ################################################################################################ -->
<a id="backtotop" href="#top"><i class="fa fa-chevron-up"></i></a>
<!-- JAVASCRIPTS -->
<script src="vendors/jquery/jquery-3.6.0.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
	var MyPoint = "/FriendWS/${userName}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

	var statusOutput = document.getElementById("statusOutput");
	var messagesArea = document.getElementById("messagesArea");
	var self = '${userName}';
	var webSocket;

	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);

		
		webSocket.onopen = function(event) {
			console.log("Connect Success!");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};

		
		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			if ("open" === jsonObj.type) {
				refreshFriendList(jsonObj);
			} else if ("history" === jsonObj.type) {
				messagesArea.innerHTML = '';
				var ul = document.createElement('ul');
				ul.id = "area";
				messagesArea.appendChild(ul);
				var messages = JSON.parse(jsonObj.message);
				for (var i = 0; i < messages.length; i++) {
					var historyData = JSON.parse(messages[i]);
					var showMsg = historyData.message;
					var li = document.createElement('li');
					historyData.sender === self ? li.className += 'me' : li.className += 'friend';
					li.innerHTML = showMsg;
					ul.appendChild(li);
				}
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("chat" === jsonObj.type) {
				var li = document.createElement('li');
				jsonObj.sender === self ? li.className += 'me' : li.className += 'friend';
				li.innerHTML = jsonObj.message;
				console.log(li);
				document.getElementById("area").appendChild(li);
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("close" === jsonObj.type) {
				refreshFriendList(jsonObj);
			}
			
		};

		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
	}

	
	function sendMessage() {
		var inputMessage = document.getElementById("message");
		var friend = statusOutput.textContent;
		var message = inputMessage.value.trim();
		if (friend === "" ||  true != /manager/i.test(friend)) {
		const Toast = Swal.mixin({
  			toast: true,
  			position: 'center',
  			showConfirmButton: false,
  			timer: 5000,
  			timerProgressBar: true,
  			didOpen: (toast) => {
    		toast.addEventListener('mouseenter', Swal.stopTimer)
    		toast.addEventListener('mouseleave', Swal.resumeTimer)
  			}
		})

		Toast.fire({
  			icon: 'warning',
  			title: '當前非服務時段,請填寫表單或致電'
		})
		
		setTimeout("location.href='mail.html'",6000);
		
		} else if (message === "") {
			alert("請輸入訊息");
			inputMessage.focus();
		} else {
			var jsonObj = {
				"type" : "chat",
				"sender" : self,
				"receiver" : "manager",
				"message" : message
			};
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}
	
	
	function refreshFriendList(jsonObj) {
		var friends = jsonObj.users;
		var row = document.getElementById("row");
		row.innerHTML = '';
		for (var i = 0; i < friends.length; i++) {
			if (friends[i] === self) { continue; }
			row.innerHTML +='<div id=' + i + ' class="column" name="friendName" value=' + friends[i] + ' ><h2>' + friends[i] + '</h2></div>';
		}
		addListener();
	}
	
	
	function addListener() {
		var container = document.getElementById("row");
		container.addEventListener("click", function(e) {
			var friend = e.srcElement.textContent;
			updateFriendName(friend);
			var jsonObj = {
					"type" : "history",
					"sender" : self,
					"receiver" : friend,
					"message" : ""
				};
			webSocket.send(JSON.stringify(jsonObj));
		});
	}
	

	setTimeout(function(e){
		document.getElementById("row").click();
	},1000);

	
	function disconnect() {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}

	
	function updateFriendName(name) {
		statusOutput.innerHTML = name;
	}
</script>
<script src="https://code.iconify.design/iconify-icon/1.0.0-beta.3/iconify-icon.min.js"></script>
<!-- <script src="js/message.js"></script> -->
<!-- <script src="layout/scripts/jquery.min.js"></script>
<script src="layout/scripts/jquery.backtotop.js"></script>
<script src="layout/scripts/jquery.mobilemenu.js"></script> -->
</body>
</html>