$(document).ready(function () {
  //==============驗證信件AJAX============================
  $(".VERIFY").on("click", () => {
    let user_name = $(".user_name").val().trim();
    let mail_reg =
      /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    let email = $.trim($(".user_email").val());

    //姓名輸入限制
    if (user_name === "") {
      alert("請輸入姓名");
      $(".user_name").focus();
      return;
    }
    //信箱輸入限制
    if (email == "") {
      alert("請輸入email");
      $(".user_email").focus();
      return;
    } else if (email != "" && !email.match(mail_reg)) {
      alert("請以半形輸入，並輸入正確的e-mail。");
      $(".user_email").focus();
      return;
    }
    const url_2 = "mail_verify_pwd";
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
      })
      .catch((error) => {
        alert(error);
      });
  });

  $(".btn_submit").on("click", () => {
    let user_name = $(".user_name").val().trim();
    let mail_reg =
      /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    let email = $.trim($(".user_email").val());
    let verify = $.trim($(".verify_code").val());
    let pwd_reg = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d).{4,25}$/;
    let new_pwd = $.trim($(".new_pwd").val());
    let checkpwd = $.trim($(".new_pwd_confirm").val());

    //姓名輸入限制
    if (user_name === "") {
      alert("請輸入姓名");
      $(".user_name").focus();
      return;
    }
    //信箱輸入限制
    if (email == "") {
      alert("請輸入email");
      $(".user_email").focus();
      return;
    } else if (email != "" && !email.match(mail_reg)) {
      alert("請以半形輸入，並輸入正確的e-mail。");
      $(".user_email").focus();
      return;
    }
    //驗證碼輸入限制
    if (verify === "") {
      alert("請輸入驗證碼");
      $(".verify_code").focus();
      return;
    }

    //密碼輸入限制
    if (new_pwd === "") {
      alert("請輸入密碼");
      $(".new_pwd").focus();
      return;
    } else if (new_pwd != "" && !new_pwd.match(pwd_reg)) {
      alert("密碼格式需包含英文大小寫、數字各1個以上，長度為4-25碼");
      $(".new_pwd").focus();
      return;
    }

    //密碼確認輸入限制
    if (checkpwd == "") {
      alert("請填入『密碼確認』");
      $(".new_pwd_confirm").focus();
      return;
    } else if (checkpwd != new_pwd) {
      alert("『密碼確認』不符合上方輸入的密碼");
      $(".new_pwd_confirm").focus();
      return;
    }

    //===========密碼修改AJAX======================================

    const url = "user/find_password";

    fetch(url, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json; charset=utf-8",
      },
      body: JSON.stringify({
        userName: user_name,
        userEmail: email,
        newUserPassword: new_pwd,
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
          alert("設定成功！");
        }
      })
      .catch((error) => {
        console.log(error);
      });
  });

  //========顯示密碼===========================

  $(".pwd_read").click(function () {
    var check = $("input[class='pwd_read']:checked").length; //判斷有多少個方框被勾選
    console.log(check);
    if (check == 1) {
      $(".new_pwd").attr("type", "text");
      $(".new_pwd_confirm").attr("type", "text");
    } else {
      $(".new_pwd").attr("type", "password");
      $(".new_pwd_confirm").attr("type", "password");
    }
  });
});

// --- 判斷密碼強度 ---
function checkPwStrong(new_pwd) {
  var strong = "";

  if (new_pwd.length >= 4 && new_pwd.length <= 6) {
    strong = 1;
  } else if (new_pwd.length >= 7 && new_pwd.length <= 14) {
    strong = 2;
  } else if (new_pwd.length >= 15) {
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
