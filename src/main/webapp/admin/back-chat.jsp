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
      .talk_show.ul{
  list-style: none;
  margin: 0;
  padding: 0;
}

.talk_show.ul .talk_show.li{
/*   display:inline-block; */
  clear: both;
  padding: 20px;
  border-radius: 30px;
  margin-bottom: 2px;
  font-family: Helvetica, Arial, sans-serif;
}

.friend{
	margin: 10px 0 0 -35px  ;
    background: #0181cc;
    border-radius: 10px;
    color: #fff;
    padding: 5px;
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
  </head>

  <body id="page-top" onunload="disconnect();">
    <!-- Page Wrapper -->
    <div id="wrapper">
      <!-- Content Wrapper -->
      <div id="content-wrapper" class="d-flex flex-column">
        <!-- Main Content -->
        <div id="content">
          <!-- Begin Page Content -->
          <br />
          <div class="seetable" style="text-align: right; padding: 0 90px 0 0;">
          	<div>請值班管理員確認回覆信箱後於此消除已處理<a href="mailListall.jsp">查看</a></div>
          </div>
          <h3 id="statusOutput" class="statusOutput" style="display: none"></h3>
          <div class="talk_con">
            <div class="containerBox">
              <div class="titleBox">
                <div class="titleSubEn">GET IN TOUCH</div>
                <div class="titleEn">CONTACT US</div>
                <div class="titleSubCn">線上客服</div>
                <div class="yiyleCh">
                  熱誠服務的精神與敬業的態度,決定服務品質
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
<!--                 <select class="whotalk" id="who"> -->
<!--                   <option value="0">接收方</option> -->
<!--                   <option value="1">傳送方</option> -->
<!--                 </select> -->
                <!-- ===================================== 測試結束 =================================================== -->
                <!-- <a href="https://www.google.com/" style="color: black"
                  >會員/登入</a
                > -->
<!--                 <textarea -->
<!--                   class="talk_word" -->
<!--                   id="talkwords" -->
<!--                   placeholder="請輸入訊息文字" -->
<!--                   style="overflow-y: hidden" -->
<!--                 ></textarea> -->
			<input id="message" class="talk_word" type="text" placeholder="請輸入訊息" onkeydown="if (event.keyCode == 13) sendMessage();" /> 
              </div>
              <div class="col-3 d-flex justify-content-center">
              <input type="button" id="connect" class="button" value="Connect" onclick="connect();" style="display: none"/> 
			  <input type="button" id="disconnect" class="button" value="Disconnect" onclick="disconnect();" style="display: none" />	
              <input type="submit" value="確認送出" class="talk_sub" id="sendMessage" onclick="sendMessage();"/>
                <!-- <input type="button" value="確認送出" class="talk_sub" id="talksub" /> -->
<!--                 <button class="talk_sub" id="talksub">確認送出</button> -->
<!--               <button class="talk_sub" id="sendMessage" type="submit" value="Send" onclick="sendMessage();"> -->
<!--           確認送出 -->
<!--         </button> -->
              </div>
            </div>
          </div>
          <br />
          <br />
          <div id="row" class="choose" style="display: block">
<!--             <div id="i" class="column" name="friendName" value=""> -->
<!--               <h2>123</h2> -->
<!--             </div> -->
<!--             <div id="i" class="column" name="friendName" value=""> -->
<!--               <h2>456</h2> -->
<!--             </div> -->
<!--             <div id="i" class="column" name="friendName" value=""> -->
<!--               <h2>789</h2> -->
<!--             </div> -->
          </div>
          <!-- /.container-fluid -->
        </div>
        <!-- End of Main Content -->
      </div>
      <!-- End of Content Wrapper -->
    </div>
    <!-- End of Page Wrapper -->

    <!-- Bootstrap core JavaScript-->
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

	connect();
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

		if (message === "") {
			alert("請輸入訊息");
			inputMessage.focus();
		} else if (friend === "") {
			alert("請選擇服務對象");
		} else {
			var jsonObj = {
				"type" : "chat",
				"sender" : self,
				"receiver" : friend,
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
  </body>
</html>
