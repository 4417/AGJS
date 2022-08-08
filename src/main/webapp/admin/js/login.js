$(document).ready(function () {
  $("#btn-login").on("click", () => {
    let account = $("#exampleInputEmail").val().trim();
    let pwd = $("#exampleInputPassword").val().trim();
    //帳號輸入限制
    if (account === "") {
      alert("請輸入帳號");
      $("#exampleInputEmail").focus();
      return;
    } else {
      //   alert(account);
    }

    //密碼輸入限制
    if (pwd === "") {
      alert("請輸入密碼");
      $("#exampleInputPassword").focus();
      return;
    } else {
      //   alert(pwd);
    }

    //==============會員登入AJAX============================
    const url = "login";
    //JSON.stringify：物件變 JSON
    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        administratorAccount: account,
        administratorPassword: pwd,
      }),
    })
      .then((res) => {
        return res.json();
      })
      .then((res) => {
        const msg = res.errorMsg ?? "登入成功！";

        if (msg === "登入成功！") {
          alert(res.administratorAccount + "," + msg);
          window.location.replace("index.html");
        } else {
          alert(msg);
        }
      })
      .catch((error) => {
        alert(error);
      });
  });

  //顯示密碼

  $("#customCheck").click(function () {
    var check = $("input[type='checkbox']:checked").length; //判斷有多少個方框被勾選
    // console.log(check);
    if (check == 1) {
      $("#exampleInputPassword").attr("type", "text");
    } else {
      $("#exampleInputPassword").attr("type", "password");
    }
  });
});
