//==========點修改日期時也關掉第一個彈窗===============================
$(document).on("click", "#dateUpdatedButton", () => {
  //   console.log("aaa");

  $("#close").trigger("click");
});

//===========訂單修改日期的月曆========================================
var nowDate = new Date();
var today = new Date(
  nowDate.getFullYear(),
  nowDate.getMonth(),
  nowDate.getDate(),
  0,
  0,
  0,
  0
);
$(function () {
  $('input[name="daterange"]').daterangepicker(
    {
      opens: "left",
      dateFormat: "YYYY-MM-DD",
      //從今天算起再加一天

      minDate: today,
      //三個月
    },
    function (start, end, label) {
      console.log(
        "A new date selection was made: " +
          start.format("YYYY-MM-DD") +
          " to " +
          end.format("YYYY-MM-DD")
      );
    }
  );
});

//===========訂單取消的警告，sweetalert2========================================

$(document).on("click", ".order_cancel", () => {
  Swal.fire({
    title: "確定取消此筆訂單？",
    text: "取消後若仍要入住，將需重新下訂",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#d33",
    cancelButtonColor: "grey",
    confirmButtonText: "確認",
    cancelButtonText: "取消",
  }).then((result) => {
    if (result.isConfirmed) {
      Swal.fire("已取消！", "已幫您取消此筆訂單", "success");
    }
  });
});

$(document).ready(function () {
  //===========datatable========================================
  $("#order_table").DataTable();

  //===========會員資訊管理========================================

  //送出
  $(".btn_submit").on("click", (e) => {
    e.preventDefault();
    let email = $.trim($("#email-name").val());
    let phone = $.trim($("#phone").val());
    let mail_reg =
      /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    let phone_reg = /^09[0-9]{8}$/;

    //信箱更新限制
    if (email == "") {
      alert("請輸入email");
      $("#email-name").focus();
      return;
    } else if (email != "" && !email.match(mail_reg)) {
      alert("請以半形輸入，並輸入正確的e-mail。");
      $("#email-name").focus();
      return;
    } else {
      $("#email-name").attr("value", email);
    }

    //信箱更新限制
    if (phone === "") {
      alert("請輸入手機");
      $("#phone").focus();
      return;
    } else if (phone != "" && !phone.match(phone_reg)) {
      alert("手機格式為09xxxxxxxx");
      $("#phone").focus();
      return;
    } else {
      $("#phone").attr("value", phone);
    }
  });
  //===========密碼資訊管理========================================

  //============顯示密碼====================================

  $(".pwd_read").click(function () {
    var check = $("input[class='pwd_read']:checked").length; //判斷有多少個方框被勾選
    console.log(check);
    if (check == 1) {
      $("input[class='password-txt']").attr("type", "text");
    } else {
      $("input[class='password-txt']").attr("type", "password");
    }
  });
  //送出
  $(".btn_submit_2").on("click", (e) => {
    e.preventDefault();
    var check_val = true; //預設都有填

    //增加密碼的長度判斷
    let old_pwd = $.trim($("#old-password").val());
    let new_pwd = $.trim($("#newpassword").val());
    let check_new_pwd = $.trim($("#checkpassword").val());
    let reg = /^[0-9a-zA-Z]{4,25}$/;
    if (old_pwd === "") {
      check_val = false;
      $("#old_password").focus();
      alert("請填入舊密碼");
      return;
    }

    if (new_pwd != "") {
      console.log("新密碼：" + $("#newpassword").val());
    } else {
      check_val = false;
      $("#newpassword").focus();
      alert("請填入新密碼");
      return;
    }

    if (check_new_pwd === "") {
      check_val = false;
      $("#checkpassword").focus();
      alert("請填入密碼確認");
      return;
    }

    // --- 確認都有填值 ---
    if (check_val == true) {
      var double_check = true; //可送出表單
      //新密碼更新限制
      if (new_pwd != "" && !new_pwd.match(reg)) {
        double_check = false;
        alert("密碼格式僅能填寫大小寫英文與數字，長度為4-25碼");
        $("#new_password").focus();
        return;
      }

      if (new_pwd != check_new_pwd) {
        double_check = false;
        $("#check_password").focus();
        alert("新密碼與確認密碼不符");
        return;
      }

      if (double_check == true) {
        return true;
      }
    } else {
      return false;
    }
  });
});

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
