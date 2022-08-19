var inputUserName = document.getElementById("userName");
	inputUserName.focus();
	
	function sendName() {
		var userName = inputUserName.value.trim();
		if (userName === "") {
			alert("請輸入您的稱呼");
			return;
		} else {
			document.getElementById("myForm").submit();
		}
	}