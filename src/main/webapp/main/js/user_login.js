$(document).ready(function () {
  $(".btn_submit").on("click", () => {
    let account = $(".login_account").val().trim();
    let pwd = $(".login_pwd").val().trim();
    //========帳號輸入限制===========================
    // if (account === "") {
    //   alert("請輸入帳號");
    //   $(".login_account").focus();
    //   return;
    // } else {
    //   //   alert(account);
    // }

    //=========密碼輸入限制============================
    // if (pwd === "") {
    //   alert("請輸入密碼");
    //   $(".login_pwd").focus();
    //   return;
    // } else {
    //   //   alert(pwd);
    // }
    //==============會員登入AJAX============================
    const url = "login";
    //JSON.stringify：物件變 JSON
    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        userAccount: account,
        userPassword: pwd,
      }),
    })
      .then((res) => {
        return res.json();
      })
      .then((res) => {
        const msg = res.errorMsg ?? "登入成功！";

        if (msg === "登入成功！") {
          alert(res.userName + "," + msg);
          window.location.replace("user_account.html");
        } else {
          alert(msg);
        }
      });
    // .catch((error) => {
    //   const msg = body.errMsg ?? "successful";
    //   alert(msg);
    //   console.log(res);
    // });
  });

  //==============顯示密碼============================

  $("#remember").click(function () {
    var check = $("input[type='checkbox']:checked").length; //判斷有多少個方框被勾選
    // console.log(check);
    if (check == 1) {
      $(".login_pwd").attr("type", "text");
    } else {
      $(".login_pwd").attr("type", "password");
    }
  });
});
