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
  
  .choose{
    width: 900px;
    border: none;
    border-radius: 5px;
    margin: 0px auto 0px;
    padding: 15px 10px 10px 10px;
    background-color: #e0c47f98;
  }
  .column{
    display : inline-block;
    text-align:center;
    width: 270px;
    height: 40px;
    border: #dfb54d98;
    background-color: #fff;
    border-radius: 5px;
    margin: auto 10px 10px;
    }
    ul{
  list-style: none;
  margin: 0;
  padding: 0;
}

ul li{
/*   display:inline-block; */
  clear: both;
  padding: 20px;
  border-radius: 30px;
  margin-bottom: 2px;
  font-family: Helvetica, Arial, sans-serif;
}

.friend{
	margin: 10px;
    background: #0181cc;
    border-radius: 10px;
    color: #fff;
    padding: 5px 10px;
    max-width: 200px;
    white-space: pre-wrap;
    text-align: left;
    list-style:none;
}

.me{
   	margin: 10px;
    text-align: right;
    list-style:none;
    }
 
</style>
<link href="style/layout.css" rel="stylesheet" type="text/css" media="all">
<link href="style/AGJS.css" rel="stylesheet" type="text/css" media="all">
<link href="style/message.css"rel="stylesheet" type="text/css" media="all">
<link rel="icon" href="image/logo.ico" type="image/x-icon" />
</head>
<body id="top" onload="connect();" onunload="disconnect();">
<div class="wrapper row1">
  <header id="header" class="hoc clear"> 
    <!-- ################################################################################################ -->
    <div id="LogoImg">
          <img id="LOGO" src="image/logo_v2.png" alt="">
    </div>
    <div id="logo" class="fl_left">
      <h1><a href="index.html">A GooD Journey SySTem </a></h1>
    </div>
    <nav id="mainav" class="fl_right">
      <ul class="clear">
        <li class="active"><a href="index.html">最新消息</a></li>

        <li><a class="drop" href="#">關於我們</a>
          <ul>
            <li><a href="pages/gallery.html">Gallery</a></li>
            <li><a href="pages/full-width.html">Full Width</a></li>
            <li><a href="pages/sidebar-left.html">Sidebar Left</a></li>
            <li><a href="pages/sidebar-right.html">Sidebar Right</a></li>
            <li><a href="pages/basic-grid.html">Basic Grid</a></li>
          </ul>
        </li>

        <li><a class="drop" href="#">房型介紹</a>
          <ul>
            <li><a href="#">山景標準房</a></li>
            <li><a href="#">山景雅致房</a></li>
            <li><a href="#">海景標準房</a></li>
            <li><a href="#">海景雅致房</a></li>
          </ul>
        </li>

        <li><a href="#">行程介紹</a></li>
        <li><a href="#">美食饗宴</a>
          <ul>
            <li><a href="#">Java Steak House</a></li>
            <li><a href="#">Momohiya</a></li>
            <li><a href="#">102 BAR</a></li> 
          </ul>
        </li>
        <li><a href="">聯絡我們</a></li>
        <!-- <li><a href="">立即訂位</a></li> -->
      </ul>

    </nav>
    <!-- ################################################################################################ -->
  </header>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
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
    <!-- =========================== 對話訊息 ================================== -->
    <!-- <div class="atalk" style="text-align: left;">
      <span id="asay">今晚, 我想來點</span>
    </div>
    <div class="btalk">
      <span id="bsay">JAVA!!</span>
    </div> -->
  </div>
  <div class="talk_input row">
    <!-- <p src="">會員登入</p> -->
    <div class="col-9 d-flex justify-content-end">
      <!-- ======================================= 測試用 =========================================== -->
<!--         <select class="whotalk" id="who"> -->
<!--           <option value="0">接收方</option> -->
<!--           <option value="1">傳送方</option> -->
<!--         </select> -->
        <!-- ===================================== 測試結束 =================================================== -->
<!--         <a href="https://www.google.com/" style="color:black">會員/登入</a> -->
<!--         <textarea -->
<!--           class="talk_word" -->
<!--           id="talkwords" -->
<!--           placeholder="請輸入訊息文字" -->
<!--           style="overflow-y: hidden;" -->
<!--         ></textarea> -->
        <input id="message" class="talk_word" type="text" placeholder="請輸入訊息" onkeydown="if (event.keyCode == 13) sendMessage();" /> 
    </div>
    <div class="col-3 d-flex justify-content-center">

		<input type="button" id="connect" class="button" value="Connect" onclick="connect();" style="display: none"/> 
		<input type="button" id="disconnect" class="button" value="Disconnect" onclick="disconnect();" style="display: none" />
        <input type="submit" value="確認送出" class="talk_sub" id="sendMessage" onclick="sendMessage();"/>
<!--         <button class="talk_sub" id="sendMessage" type="submit" value="Send" onclick="sendMessage();"> -->
<!--           確認送出 -->
<!--         </button> -->
    </div>
  </div>
</div>
<br>
<!-- <div id="row" class="choose" style="display: block;"> -->
<div id="row" class="choose" style="display: block; display: none;">
<!--             這裡是當前使用者選擇窗 -->
          </div>
<br>

  
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- Footer Background Image Wrapper -->
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
          <li>A GooD Journey SySTem</a></li>
          <li>連絡電話:(02)2222-1122</a></li>
          <li>地址:台北市中山區XX南路XX號</a></li>
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
<!-- End Footer Background Image Wrapper -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
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
				// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
				var messages = JSON.parse(jsonObj.message);
				for (var i = 0; i < messages.length; i++) {
					var historyData = JSON.parse(messages[i]);
					var showMsg = historyData.message;
					var li = document.createElement('li');
					// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
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
		console.log(friend);
		if (message === "") {
			alert("請輸入訊息");
			inputMessage.focus();
		} else if (friend === "") {
			alert("當前非服務時段,請改用表單或致電");
		} else {
			var jsonObj = {
				"type" : "chat",
				"sender" : self,
				"receiver" : "manager",
// 				"receiver" : friend,
				"message" : message
			};
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}
	
	// 有好友上線或離線就更新列表
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
	// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
// 	function addListener() {
// 		var container = document.getElementById("row");
// 		container.addEventListener("click", function(e) {
// 			var friend = e.srcElement.textContent;
// 			updateFriendName(friend);
// 			var jsonObj = {
// 					"type" : "history",
// 					"sender" : self,
// 					"receiver" : friend,
// 					"message" : ""
// 				};
// 			webSocket.send(JSON.stringify(jsonObj));
// 		});
// 	}
	//=====================================
function addListener() {
		var container = document.getElementById("row");
// 		container.click();
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
},2000);


	//=====================================
	
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
<!-- <script defer="defer"> -->
<!-- // alert("頁面載入完我才執行的") -->
<!-- </script> -->
<!-- <script src="js/message.js"></script> -->
<!-- <script src="layout/scripts/jquery.min.js"></script>
<script src="layout/scripts/jquery.backtotop.js"></script>
<script src="layout/scripts/jquery.mobilemenu.js"></script> -->
</body>
</html>