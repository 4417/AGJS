/* user_register js *//
$(document).ready(function () {
  //1. 生日下拉式選單
  var i = -1;
  // 新增年份，從1910年開始
  for (i = 1910; i <= new Date().getFullYear(); i++) {
    addOption(birth_year, i, i - 1909);
    //    // 預設選中1988年
    //	    if (i == 1988) {
    //	    	birth_year.options[i-1910].selected = true;
    //	    }
  }
  // 新增月份
  for (i = 1; i <= 12; i++) {
    addOption(birth_month, i, i);
  }
  // 新增天份，先預設31天
  for (i = 1; i <= 31; i++) {
    addOption(birth_day, i, i);
  }

  //2. 輸入限制條件
  $("#btn_submit").on("click", () => {
    let reg = /^[0-9a-zA-Z]{4,25}$/;
    let idty_reg = /^[A-Z]\d{9}$/;
    let phone_reg = /^09[0-9]{8}$/;
    let mail_reg =
      /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    let user_name = $(".USER_NAME").val().trim();
    let year = $("#birth_year").val();
    let month = $("#birth_month").val();
    let day = $("#birth_day").val();
    let identitynumber = $.trim($(".USER_IDENTITYNUMBER").val());
    let id = $.trim($(".USER_ACCOUNT").val());
    let email = $.trim($(".USER_EMAIL").val());
    let pwd = $.trim($(".USER_PASSWORD").val());
    let checkpwd = $.trim($(".USER_PASSWORD_CONFIRM").val());
    let phone = $.trim($(".USER_PHONE").val());
    let vertify = $.trim($(".VERTIFY_CODE").val());
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
      alert("請以半形輸入，並輸入正確的e-mail。");
      $(".USER_EMAIL").focus();
      return;
    }

    //驗證碼輸入限制(待連信箱API)
    if (vertify === "") {
      alert("請輸入驗證碼");
      $(".VERTIFY_CODE").focus();
      return;
    }

    //條款同意限制
    var check = $("input[class='privacy_check']:checked").length; //判斷有多少個方框被勾選
    // console.log(check);
    if (check == 0) {
      alert("需勾選同意客戶隱私權政策及客戶服務條款");
      return; //不要提交表單
    }
  });

  //3. 顯示密碼

  $(".pwd_read").click(function () {
    var check = $("input[class='pwd_read']:checked").length; //判斷有多少個方框被勾選
    console.log(check);
    if (check == 1) {
      $(".USER_PASSWORD").attr("type", "text");
    } else {
      $(".USER_PASSWORD").attr("type", "password");
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





/* 要從後端傳入的剩餘房間數 */
/* 要寫成單一房型卡片的區域變數 */
var rest_room_count = 5;



/* +- room number*/
$(document).on("click", ".add_btn", function () {

  let num = parseInt($(this).next().text());
  if (num < rest_room_count) {
    num++;
    $(this).next().text(num);
  };
});

$(document).on("click", ".minus_btn", function () {

  let num = parseInt($(this).prev().text());
  if (num > 1) {
    num--;
    $(this).prev().text(num);
  }
});

var total_room = 0;
var total_price = 0;

/* 按下 選擇 加入購物車清單 add into cart*/
$("button.task_add").on("click", function () {
})

$("button.btn").on("click", function () {
  console.log("=====");
  console.log($(item).attr('id'));


  let room_number = parseInt($(this).siblings(".room_count").text());
  let room_price = $(this).parent().siblings(".room_items").find(".prices");
  let room_name_text = $(this).parent().siblings().find("h1.room_name");

  if (rest_room_count >> 0) {

    for (var i = 0; i < room_number; i++) {
      $("div.cart_items").append(
        `<li style="list-style-type: none;">
          <div class="cart_room">
              <div class="cart_room_name">${room_name_text.text()}</div>
              <div><span>2</span> 位賓客，<span>2</span>晚</div>
              <div class="cart_caution">將收取取消費用</div>
            </div>
            <div class="cart_remove_price">
              <div class="Action" id="cart_item_remove">x</div>
              <div class="cart_item_price">${parseInt(
          room_price.text()
        )} 元</div>
              </div>
              </li>`
      );
    }
    /* 修改 summary */
    total_room = total_room + room_number;
    $(".num_of_people_detail").text(total_room + " 個房間");

    total_price = total_price + (parseInt(room_price.text()) * room_number);
    $("#room_total_price").text("總計 " + total_price + " 元");

    /* +- room count 回歸為1*/
    $(this).siblings(".room_count").text("1");
    /* 更新剩餘房間數 */
    rest_room_count = rest_room_count - room_number;
    $(this).siblings(".rest_room_section").find("span#rest_num").text(rest_room_count);

    /* 顯示最後一間被選完 */
    if (rest_room_count == 0) {
      $(this).siblings(".rest_room_section").find("#no_room").removeClass("hidden_caution");
      $(this).siblings(".rest_room_section").find("#rest_room").addClass("hidden_caution");
      $(this).siblings(".add_btn").addClass("hidden_caution");
      $(this).siblings(".room_count").addClass("hidden_caution");
      $(this).siblings(".minus_btn").addClass("hidden_caution");
      $(this).addClass("hidden_caution");

    };

  };

});

/* 購物車內的移除 */
$("div.cart_items").on("click", "#cart_item_remove", function (e) {
  e.stopPropagation();

  let r = confirm("確認移除此房間?");
  if (r) {
    $(this).closest("li").animate({
      "opacity": 0
    }, "slow", function () {
      $(this).remove();
    });
  }
  /* 修改 summary */
  total_room = total_room - 1;
  $(".num_of_people_detail").text(total_room + " 個房間");

  let room_price = $(this).next().text();
  console.log(room_price);
  total_price = total_price - parseInt(room_price);
  $("#room_total_price").text("總計 " + total_price + " 元");
});


/* 按下 加購行程 */
$("button#add_journey").on("click", function (e) {
  e.stopPropagation();

  /* 如果沒有選房間 */

  /* 取得並儲存單一房間 */
  var room_final_list = new Array();
  var counter = 0;

  var room_list = $('div.cart_items').find('li');
  for (var i = 0; i < room_list.length; i++) {
    counter++;
    room_list.room_name = $(".cart_room_name").text();
    room_list.room_price = $(".cart_item_price").text().split(" 元");
    //    room_final_list.push(room_list);
  };

  console.log(room_list);
  //  console.log(room_final_list);
  // location.href = "./for_your_journey.html";
});


