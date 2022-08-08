$(document).ready(function () {
  //==============驗證信件AJAX============================
  $(".VERIFY").on("click", () => {
    let mail_reg =
      /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    let email = $.trim($("#exampleInputEmail").val());
    let id = $.trim($("#exampleInputAccount").val());

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
        administratorAccount: id,
        email: email,
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

  $("#btn_submit").on("click", () => {
    let mail_reg =
      /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    let email = $.trim($("#exampleInputEmail").val());
    let id = $.trim($("#exampleInputAccount").val());
    let verify = $.trim($("#exampleInputVerify").val());
    let pwd_reg = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d).{4,25}$/;
    let new_pwd = $.trim($("#exampleInputPwd").val());
    //帳號輸入限制
    if (id === "") {
      alert("請輸入帳號");
      $("#exampleInputAccount").focus();
      return;
    }
    //信箱輸入限制
    if (email === "") {
      alert("請輸入email");
      $("#exampleInputEmail").focus();
      return;
    } else if (email != "" && !email.match(mail_reg)) {
      alert("請以半形輸入，並輸入正確的e-mail。");
      $("#exampleInputEmail").focus();
      return;
    }
    //驗證碼輸入限制
    if (verify === "") {
      alert("請輸入驗證碼");
      $("#exampleInputVerify").focus();
      return;
    }

    //密碼輸入限制
    if (new_pwd === "") {
      alert("請輸入密碼");
      $("#exampleInputPwd").focus();
      return;
    } else if (new_pwd != "" && !new_pwd.match(pwd_reg)) {
      alert("密碼格式需包含英文大小寫、數字各1個以上，長度為4-25碼");
      $("#exampleInputPwd").focus();
      return;
    }

    //===========密碼修改AJAX======================================

    const url = "find_password";

    fetch(url, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json; charset=utf-8",
      },
      body: JSON.stringify({
        administratorAccount: id,
        email: email,
        newAdministratorPassword: new_pwd,
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
          window.location.replace("login.html");
        }
      })
      .catch((error) => {
        console.log(error);
      });
  });

  //========顯示密碼===========================

  $(".custom-control-input").click(function () {
    var check = $("input[class='custom-control-input']:checked").length; //判斷有多少個方框被勾選
    console.log(check);
    if (check == 1) {
      $("#exampleInputPwd").attr("type", "text");
    } else {
      $("#exampleInputPwd").attr("type", "password");
    }
  });
});
