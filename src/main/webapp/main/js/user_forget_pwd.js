$(document).ready(function () {
  $(".btn_submit").on("click", () => {
    let pwd_reg = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d).{4,25}$/;
    let pwd = $.trim($(".USER_EMAIL").val());
    //信箱輸入限制
    if (pwd == "") {
      alert("請輸入密碼");
      $(".USER_EMAIL").focus();
      return;
    } else if (pwd != "" && !pwd.match(pwd_reg)) {
      alert("密碼格式需包含英文大小寫、數字各1個以上，長度為4-25碼");
      $(".USER_EMAIL").focus();
      return;
    }
  });
});
