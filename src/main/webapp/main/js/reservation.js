const odprocess_url = "orderprocess/";
const ecpay_url = "ecpayprocess/";
const func = {
    "Check": "check/", "Pay": "pay/", "Pay2": "dopay/"
};
const mode = {
    "User": "user", "Pay": "pay"
};

var getDateStart = sessionStorage.startDateSS;
var getDateEnd = sessionStorage.endDateSS;
//購物車總價
var car_total_price = 0;


//訂單主檔
var ss_soh = sessionStorage.soh;
var sohJSON;
//訂單明細
var ss_orderItem = sessionStorage.order_item;
var orderItemJSON;
//行程訂單
var ss_jrnItem = sessionStorage.jrn_item;
var jrnItemJSON;
console.log(ss_jrnItem);
//天數幾晚
var day_count = 0;
var birth_year = $('select#birth_year');
var birth_month = $('select#birth_month');
var birth_day = $('select#birth_day');
var userBirthday;

var orderSubmitdVo = {};
$("#loader").fadeOut(1000);

//============ init ==============
$(function () {

    $('#loader').show();
    console.log('Init');

    //日期顯示
    let start = getDateStart.split('-');
    s_year = start[0];
    s_month = start[1];
    s_day = start[2];
    let end = getDateEnd.split('-');
    e_year = end[0];
    e_month = end[1];
    e_day = end[2];
    var day_list = ['週日', '週一', '週二', '週三', '週四', '週五', '週六'];

    start = new Date(s_year, (parseInt(s_month) - 1), s_day);
    end = new Date(e_year, (parseInt(e_month) - 1), e_day);

    let startDay = day_list[start.getDay()];
    let endDay = day_list[end.getDay()];
    let difference = Math.abs(end - start);
    day_count = difference / (1000 * 3600 * 24)
    console.log('住天數' + day_count);

    let date_detail = `${s_year}年${s_month}月${s_day}日 (${startDay}) - 
    ${e_year}年${e_month}月${e_day}日 (${endDay})`;
    $('span.date_detail').text(date_detail);
    $('span.date_count').text(`${day_count} 晚`);

    //訂單主檔
    sohJSON = $.parseJSON(ss_soh);
    //訂單明細
    orderItemJSON = $.parseJSON(ss_orderItem);
    //行程訂單
    jrnItemJSON = $.parseJSON(ss_jrnItem);

    importCart();
    forminit();
    $('#loader').hide();

});

function forminit() {

    console.log('forminit');


    //1. 生日下拉式選單
    var i = -1;
    // 新增年份，從1910年開始
    for (i = 1910; i <= new Date().getFullYear(); i++) {
        addOption(birth_year, i, i - 1909);
    }
    // 新增月份
    for (i = 1; i <= 12; i++) {
        addOption(birth_month, i, i);
    }
    // 新增天份，先預設31天
    for (i = 1; i <= 31; i++) {
        addOption(birth_day, i, i);
    }

}

//============================= 引入訂單清單 ============================ 
function importCart() {

    console.log("引入清單");
    $(".jrn_card_body").empty();
    $(".room_card").empty();

    //引入總金額
    car_total_price = sessionStorage.tp;
    $('#total_price').text('總計 ' + car_total_price + '元');
    sessionStorage.removeItem("tp");

    //引入房間訂單
    var ss_rmdata = sessionStorage.rmdata;
    var rmDataJSON = $.parseJSON(ss_rmdata);

    if (orderItemJSON.length > 0) {

        $("div.rm_seleted").empty();
        $("div.room_card").empty();
        for (var i = 0; i < orderItemJSON.length; i++) {
            console.log(i + '==================');
            console.log(i + ":" + orderItemJSON[i].title);
            console.log(i + ":" + orderItemJSON[i].roomStyleId);
            console.log(i + ":" + orderItemJSON[i].totalPrice);
            console.log(i + ":" + orderItemJSON[i].orderRoomQuantity);
            console.log(i + ":" + rmDataJSON[i].roomPhoto);
            console.log(i + ":" + rmDataJSON[i].roomDescription);

            $("div.rm_seleted").append(`<div class="cart_roomi">
                                            <div class="cart_room_name">${orderItemJSON[i].title})</div>
                                            <div>數量 ${orderItemJSON[i].orderRoomQuantity}<span id="roomCount"></span>，<span id="days"></span> ${day_count} 晚</div>
                                            <div class="cart_item_price"><span id="itprice">${orderItemJSON[i].totalPrice}</span>元</div>
                                        </div>`);

            $("div.room_card").append(`<div class="items">
                                            <div class="image-box">
                                            <img src="data:image/png;base64,${rmDataJSON[i].roomPhoto}" width="400" height="300">
                                            </div>
                                
                                            <div class="about">
                                            <h1 class="room_name">${orderItemJSON[i].title}</h1>
                                            <p class="room_caption">${rmDataJSON[i].roomDescription}</p>
                                            <a href="#" class="know_more">了解更多</a>
                                            </div>
                                
                                            <div class="prices">
                                            <span>${orderItemJSON[i].orderRoomPrice} 元 / ${orderItemJSON[i].orderRoomQuantity}間</span>
                                            </div>
                                        </div>`);
        }
    }
    //引入形成訂單
    var ss_jrnData = sessionStorage.jrn_data;
    console.log(ss_jrnData);
    var jrnDataJSON = $.parseJSON(ss_jrnData);

    if (jrnItemJSON.length > 0) {

        $("div.jrn_cart_items").empty();
        $("div.jrn_card_body").empty();
        for (var i = 0; i < jrnItemJSON.length; i++) {
            console.log(i + '=====================');
            console.log(i + ":" + jrnItemJSON[i].journeyId);
            console.log(i + ":" + jrnItemJSON[i].adults);
            console.log(i + ":" + jrnItemJSON[i].children);
            console.log(i + ":" + jrnItemJSON[i].totalPrice);
            console.log(i + ":" + jrnItemJSON[i].journeyDate);

            console.log(i + ":" + jrnDataJSON[i].journeyName);
            console.log(i + ":" + jrnDataJSON[i].journeyInfo);
            console.log(i + ":" + jrnDataJSON[i].adultPrice);
            console.log(i + ":" + jrnDataJSON[i].kidPrice);
            console.log(i + ":" + jrnDataJSON[i].info);
            console.log(i + ":" + jrnDataJSON[i].pic);

            $("div.rm_seleted").append(`<li style="list-style-type: none" class="card_li" id="card_li">
                                            <div class="cart_jrn">
                                            <div class="cart_jrn_name">${jrnDataJSON[i].journeyName}</div>
                                            <div><span>日期:</span> <span>${jrnItemJSON[i].journeyDate}</span></div>
                                            <div class="select_num"><span>大人:</span><span id="select_count">${jrnItemJSON[i].adults}</span><span> 小孩:</span><span
                                                id="select_count">${jrnItemJSON[i].children}</span></div>
                                            <div class="cart_item_price">${jrnItemJSON[i].totalPrice}元</div>
                                            </div>
                                        </li>`);

            $("div.jrn_card_body").append(`<div class="items">
                                                <div class="image-box">
                                                <img src="data:image/png;base64,${jrnDataJSON[i].pic}" width="400" height="300">
                                                </div>
                                    
                                                <div class="about">
                                                <h1 class="room_name">${jrnDataJSON[i].journeyName}</h1>
                                                <p class="room_caption">${jrnDataJSON[i].info}</p>
                                                <a href="#" class="know_more">了解更多</a>
                                                </div>
                                    
                                                <div class="prices" id="prices">
                                                <div>成人 <span>${jrnDataJSON[i].adultPrice}元  ${jrnItemJSON[i].adults}人</span> </div>
                                                <div>兒童 <span>${jrnDataJSON[i].kidPrice}元  ${jrnItemJSON[i].children}人</span> </div>
                                                </div>
                                            </div>`);
        }
    }
}

//======   將彈窗Form 資料轉換為serializeObject物件，轉json再送出到後端。 ======

jQuery.fn.serializeObject = function () {
    var formData = {};
    var formArray = this.serializeArray();

    for (var i = 0, n = formArray.length; i < n; ++i)
        formData[formArray[i].name] = (formArray[i].value).trim();

    return formData;
};

function pay_check(item) {

    console.log('pay_check');

    var formData = {};
    formData = $('form#login').serializeObject();
    console.log('JSON: ' + JSON.stringify(formData));
}

function verify() {
    let phone_reg = /^09[0-9]{8}$/;
    let mail_reg =
        /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    let user_name = $(".USER_NAME").val().trim();
    let idty_reg = /^[A-Z]\d{9}$/;
    let year = parseInt($("#birth_year").val()) + 1909;
    let month = $("#birth_month").val();
    let day = $("#birth_day").val();
    let identitynumber = $.trim($(".USER_IDENTITYNUMBER").val());
    let email = $.trim($(".USER_EMAIL").val());
    let phone = $.trim($(".USER_PHONE").val());

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
        userBirthday = `${year}-${month}-${day}`;
        console.log(userBirthday);
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
        alert("請輸入正確的e-mail");
        $(".USER_EMAIL").focus();
        return;
    }
    return true;
}

function submit(item) {

    console.log('立即前往付款');
    $('#loader').show();
    let verifyCheck = verify();
    let check = false;
    console.log(verifyCheck);

    $("input.privacy_check:checked").each(function (i, item) {
        if ($(item).val() === '1') {
            console.log('yes');
            check = true;
        } else {
            console.log('no');
        }
    });

    if (verifyCheck) {
        if (check) {
            $('#loader').show();
            fetchMemberCheck();
        } else {
            alert('請閱讀客戶隱私權政策及客戶服務條款及住宿服務條款，並勾選同意');
        }
    }
}
//=============================== 檢查會員資料 ============================
function fetchMemberCheck() {

    var formData = $('form#login').serializeObject();
    formData.userBirthday = userBirthday;
    formData.userName = formData.userName.replace(/\s/g, "");
    console.log('JSONSTR: ' + JSON.stringify(formData));
    delete (formData.birth_year);
    delete (formData.birth_month);
    delete (formData.birth_day);
    console.log(formData);
    orderSubmitdVo.user = formData;
    console.log(orderSubmitdVo);
    console.log(JSON.stringify(orderSubmitdVo));

    //訂單主檔
    // orderSubmitdVo["'soh'"] = ss_soh;
    var nowdate = new Date();
    sohJSON.createDate = `${nowdate.getFullYear()}-${nowdate.getMonth() + 1}-${nowdate.getDate()}`;
    let jrntotal = parseInt(sessionStorage.jrn_tp);
    console.log('jrntotal==' + jrntotal);
    sohJSON.journeyPrice = jrntotal;
    orderSubmitdVo.soh = sohJSON;

    //訂單明細
    for (let i = 0; i < orderItemJSON.length; i++) {
        console.log(orderItemJSON[i]);
        delete (orderItemJSON[i].title);
        console.log(JSON.stringify(orderItemJSON[i]));
    }
    console.log('orderItemJSON=' + JSON.stringify(orderItemJSON));
    // orderSubmitdVo["'soiList'"] = JSON.stringify(orderItemJSON);
    orderSubmitdVo.soiList = orderItemJSON;

    //行程訂單
    console.log('ss_jrnItem=' + ss_jrnItem);
    orderSubmitdVo.jiList = jrnItemJSON;

    //描述    
    orderSubmitdVo.tradeDesc = sessionStorage.tradeDesc;

    let jsonData = JSON.stringify(orderSubmitdVo);
    console.log(jsonData);
    let msg = '';

    $.ajax({
        url: odprocess_url + func.Check + mode.User,
        contentType: "application/json; charset=utf-8",
        type: "POST",
        data: jsonData,
        dataType: "json",
        beforeSend: function () {
            $('#loader').show();
            console.log('beforeSend');
        },
        success: function (data) {

            $('#loader').hide();
            console.log(data);
            console.log("data=" + JSON.stringify(data));
            let jsondata = $.parseJSON(JSON.stringify(data));
            console.log(jsondata.msg);
            console.log(jsondata.isMember);

            if (jsondata.msg == null) {
                msg = '下單失敗，請聯絡客服';
            } else {
                msg = jsondata.msg;
                fetchECPay(data)
            }
            alert(msg);
            sessionStorage.clear();
            sessionStorage.removeItem("newsoh");
            sessionStorage.setItem('newsoh', JSON.stringify(data));

        },
        error: function (result) {
            $('#loader').hide();
            alert("提交失敗！");
            console.log(result);
        }
    })

}
//=============================== 提交綠界 ============================

function fetchECPay(data) {

    console.log("pay");
    console.log(JSON.stringify(data));
    fetch(ecpay_url + func.Pay2, {

        headers: {
            'content-type': 'application/json'
        },
        method: "post",
        body: JSON.stringify(data)
        // body: data

    }).then(response => response.text())
        .then(text => {
            console.log("feedback");
            $("#xxx").prepend(text);
            return;
        })

    // $.ajax({
    //     url: odprocess_url + func.ECPay + mode.Pay,
    //     contentType: "application/json; charset=utf-8",
    //     type: "POST",
    //     data: JSON.stringify(data),
    //     dataType: "text/html; charset=UTF-8",
    //     success: function (data) {
    //         console.log("=========" + data);
    //         $("#xxx").prepend(data);
    //     },
    //     error: function (result) {
    //         alert("提交失敗！");
    //         console.log(result);
    //     }
    // })

}

function cancel() {
    console.log("cancel");
    $('#loader').show();
}


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
    // var option = document.createElement("option");
    // option.text = text;
    // option.value = value;
    // selectbox.options.add(option);
    selectbox.append($("<option></option>")
        .attr("value", value)
        .text(text));
}