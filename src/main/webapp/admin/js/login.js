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
