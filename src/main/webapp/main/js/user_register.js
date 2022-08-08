$(document).ready(function () {
  //1. 生日下拉式選單
  var i = -1;
  // 新增年份，從1910年開始
  for (i = 1910; i <= new Date().getFullYear(); i++) {
    addOption(birth_year, i, i - 1909);
    /*// 預設選中1988年
	    if (i == 1988) {
	    	birth_year.options[i-1910].selected = true;
	    }*/
  }
  // 新增月份
  for (i = 1; i <= 12; i++) {
    addOption(birth_month, i, i);
  }
  // 新增天份，先預設31天
  for (i = 1; i <= 31; i++) {
    addOption(birth_day, i, i);
  }

  //==============驗證信件AJAX============================
  $(".VERIFY").on("click", () => {
    let user_name = $(".USER_NAME").val().trim();
    let email = $.trim($(".USER_EMAIL").val());
    let mail_reg =
      /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

    //姓名輸入限制
    if (user_name === "") {
      alert("請輸入姓名");
      $(".USER_NAME").focus();
      return;
    }
    //信箱輸入限制
    if (email == "") {
      alert("請輸入email");
      $(".USER_EMAIL").focus();
      return;
    } else if (email != "" && !email.match(mail_reg)) {
      alert("請以半形輸入，並輸入正確的e-mail。");
      $(".USER_EMAIL").focus();
      return;
    }
    const url_2 = "mail_verify";
    console.log("驗證碼");
    // JSON.stringify：物件變 JSON
    fetch(url_2, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        userName: user_name,
        userEmail: email,
      }),
    })
      .then((res) => {
        return res.json();
      })
      .then((res) => {
        alert(res);
      });
    // .catch((error) => {
    //   const msg = body.errMsg ?? "successful";
    //   alert(msg);
    //   console.log(res);
    // });
  });

  //2. 輸入限制條件
  $("#btn_submit").on("click", () => {
    let reg = /^[0-9a-zA-Z]{4,25}$/;
    let pwd_reg = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d).{4,25}$/;
    let idty_reg = /^[A-Z]\d{9}$/;
    let phone_reg = /^09[0-9]{8}$/;
    let mail_reg =
      /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    let user_name = $(".USER_NAME").val().trim();
    let year = parseInt($("#birth_year").val()) + 1909;
    let month = $("#birth_month").val();
    let day = $("#birth_day").val();
    let identitynumber = $.trim($(".USER_IDENTITYNUMBER").val());
    let id = $.trim($(".USER_ACCOUNT").val());
    let email = $.trim($(".USER_EMAIL").val());
    let pwd = $.trim($(".USER_PASSWORD").val());
    let checkpwd = $.trim($(".USER_PASSWORD_CONFIRM").val());
    let phone = $.trim($(".USER_PHONE").val());
    let verify = $.trim($(".VERIFY_CODE").val());
    //姓名輸入限制
    if (user_name === "") {
      alert("請輸入姓名");
      $(".USER_NAME").focus();
      return;
    }
    //生日輸入限制
    if (year == "" || month == "" || day == "") {
      alert("請選擇生日日期");
      $("#birtherr").show();
      document.getElementById("birtherr").focus();
      return;
    } else {
      $("#birtherr").hide();
    }

    //身分證輸入限制
    if (identitynumber === "") {
      alert("請輸入身分證字號");
      $(".USER_IDENTITYNUMBER").focus();
      return;
    } else if (identitynumber != "" && !identitynumber.match(idty_reg)) {
      alert("請輸入正確的身分證字號");
      $(".USER_IDENTITYNUMBER").focus();
      return;
    }

    //帳號輸入限制
    if (id === "") {
      alert("請輸入帳號");
      $(".USER_ACCOUNT").focus();
      return;
    } else if (id != "" && !id.match(reg)) {
      alert("帳號格式需填寫大小寫英文、數字，長度為4-25碼");
      $(".USER_ACCOUNT").focus();
      return;
    }

    //密碼輸入限制
    if (pwd === "") {
      alert("請輸入密碼");
      $(".USER_PASSWORD").focus();
      return;
    } else if (pwd != "" && !pwd.match(pwd_reg)) {
      alert("密碼格式需包含英文大小寫、數字各1個以上，長度為4-25碼");
      $(".USER_PASSWORD").focus();
      return;
    }

    //密碼確認輸入限制
    if (checkpwd == "") {
      alert("請填入『密碼確認』");
      $(".USER_PASSWORD_CONFIRM").focus();
      return;
    } else if (checkpwd != pwd) {
      alert("『密碼確認』不符合上方輸入的密碼");
      $(".USER_PASSWORD_CONFIRM").focus();
      return;
    }

    //手機輸入限制
    if (phone === "") {
      alert("請輸入手機");
      $(".USER_PHONE").focus();
      return;
    } else if (phone != "" && !phone.match(phone_reg)) {
      alert("手機格式為09xxxxxxxx");
      $(".USER_PHONE").focus();
      return;
    }

    //信箱輸入限制
    if (email == "") {
      alert("請輸入email");
      $(".USER_EMAIL").focus();
      return;
    } else if (email != "" && !email.match(mail_reg)) {
      alert("請輸入正確的e-mail");
      $(".USER_EMAIL").focus();
      return;
    }

    //驗證碼輸入限制
    if (verify === "") {
      alert("請輸入驗證碼");
      $(".VERIFY_CODE").focus();
      return;
    }

    //條款同意限制
    var check = $("input[class='privacy_check']:checked").length; //判斷有多少個方框被勾選
    // console.log(check);
    if (check == 0) {
      alert("需勾選同意客戶隱私權政策及客戶服務條款");
      return; //不要提交表單
    }

    //==============會員註冊AJAX============================
    const url = "register";
    //JSON.stringify：物件變 JSON
    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        userAccount: id,
        userPassword: pwd,
        userName: user_name,
        userBirthday: year + "-" + month + "-" + day,
        userEmail: email,
        userPhone: phone,
        userIdentityNumber: identitynumber,
        userRegistrationDate: new Date(),
        emailVerifyStatus: true,
        verifyMsg: verify,
      }),
    })
      .then((res) => {
        return res.json();
      })
      .then((res) => {
        if (res.verifyMsg != null) {
          alert(res.verifyMsg);
        } else if (res.errorMsg != null) {
          alert(res.errorMsg);
        } else {
          alert("註冊成功！");
        }
      });
    // .catch((error) => {
    //   const msg = body.errMsg ?? "successful";
    //   alert(msg);
    //   console.log(res);
    // });
  });

  //3. 顯示密碼

  $(".pwd_read").click(function () {
    var check = $("input[class='pwd_read']:checked").length; //判斷有多少個方框被勾選
    console.log(check);
    if (check == 1) {
      $(".USER_PASSWORD").attr("type", "text");
      $(".USER_PASSWORD_CONFIRM").attr("type", "text");
    } else {
      $(".USER_PASSWORD").attr("type", "password");
      $(".USER_PASSWORD_CONFIRM").attr("type", "password");
    }
  });
});

// 設定每個月份的天數
function setDays(year, month, day) {
  var monthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
  var yea = year.options[year.selectedIndex].text;
  var mon = month.options[month.selectedIndex].text;
  var num = monthDays[mon - 1];
  if (mon == 2 && isLeapYear(yea)) {
    num++;
  }
  for (var j = day.options.length - 1; j >= num; j--) {
    day.remove(j);
  }
  for (var i = day.options.length; i <= num; i++) {
    addOption(birth_day, i, i);
  }
}

// 判斷是否閏年
function isLeapYear(year) {
  return year % 4 == 0 || (year % 100 == 0 && year % 400 == 0);
}

// 向select尾部新增option
function addOption(selectbox, text, value) {
  var option = document.createElement("option");
  option.text = text;
  option.value = value;
  selectbox.options.add(option);
}

// --- 判斷密碼強度 ---
function checkPwStrong(pwd) {
  var strong = "";

  if (pwd.length >= 4 && pwd.length <= 6) {
    strong = 1;
  } else if (pwd.length >= 7 && pwd.length <= 14) {
    strong = 2;
  } else if (pwd.length >= 15) {
    strong = 3;
  }

  switch (strong) {
    case 1:
      $("#pwderr").html("密碼強度：弱");
      break;
    case 2:
      $("#pwderr").html("密碼強度：中");
      break;
    case 3:
      $("#pwderr").html("密碼強度：強");
      break;
  }
}
