$(document).ready(function () {
  $("#btn_submit").on("click", () => {
    let mail_reg =
      /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    let email = $.trim($("#exampleInputEmail").val());
    //信箱輸入限制
    if (email == "") {
      alert("請輸入email");
      $("#exampleInputEmail").focus();
      return;
    } else if (email != "" && !email.match(mail_reg)) {
      alert("請以半形輸入，並輸入正確的e-mail。");
      $("##exampleInputEmail").focus();
      return;
    }
  });
});
