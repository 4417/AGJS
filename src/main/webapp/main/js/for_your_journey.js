
const jrn_url = "jrn/";
const func = {
    "add": "add/", "Search": "search/", "Edit": "update/", "Delete": "delete"
};
const mode = {
    "EmptyRoomByDate": "empty.daterange", "RoomCard": "roomcard.styleid", "Journey": "jrn"
};
//初始查詢
var reqData = {};
reqData.startDate = sessionStorage.startDateSS;
reqData.endDate = sessionStorage.endDateSS;
var getDateStart = sessionStorage.startDateSS;
var getDateEnd = sessionStorage.endDateSS;
console.log(getDateStart);
console.log(getDateEnd);
const jrnCardBody = $(".jrn_card_body");
//存放行程card陣列
var cardArr = [];
///購物車卡片資料 
var carCardArr = [];
//選擇幾間房
var select_count = 0;
//總共選擇房數
var total_count = 0;
//購物車總價
var car_total_price = 0;
//選擇數量 成人
var cur_adult_num = 0;
//選擇數量 小孩
var cur_kid_num = 0;
//選擇總數
var cur_total_num = 0;
//天數
var day_count = 0;

//============ init ==============
$(function () {

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

    importRoomCart();

});

//============================= 引入已選擇之房型 ============================ 
function importRoomCart() {

    console.log("引入已選擇之房型");
    var ss_soh = sessionStorage.soh;
    var ss_sohJSON = $.parseJSON(ss_soh);
    //引入總金額
    $('#total_price').text(ss_sohJSON.roomPrice);
    car_total_price = ss_sohJSON.roomPrice;

    var ss_orderItem = sessionStorage.order_item;
    console.log(ss_orderItem);
    var orderItemJSON = $.parseJSON(ss_orderItem);
    if (orderItemJSON.length > 0) {

        $("div.rm_seleted").empty();
        for (var i = 0; i < orderItemJSON.length; i++) {
            console.log(i + ":" + orderItemJSON[i].title);
            console.log(i + ":" + orderItemJSON[i].roomStyleId);
            console.log(i + ":" + orderItemJSON[i].totalPrice);
            console.log(i + ":" + orderItemJSON[i].orderRoomQuantity);

            $("div.rm_seleted").append(`<div class="cart_room">
                            <div class="cart_room_name">${orderItemJSON[i].title}</div>
                            <div>數量<span id="roomCount">${orderItemJSON[i].orderRoomQuantity}</span>，<span id="days">${day_count}</span>晚</div>
                            <div class="cart_item_price"><span id="itprice">  ${orderItemJSON[i].totalPrice}</span>元</div>
                        </div>`);
        }
    }
}


//===================================== 初始查詢行程 ====================================
$.ajax({
    url: jrn_url + func.Search + mode.Journey,
    contentType: "application/json; charset=utf-8",
    type: "POST",
    data: JSON.stringify(reqData),
    dataType: "json",
    success: function (data) {

        jrnCardBody.empty();
        emptyCardArr();

        var tr_id = 0;
        console.log(data);
        console.log("初始行程");

        $.each(data, function (index, content) {

            console.log("===" + content);
            let obj = {};
            obj.typeName = content.typeName;
            obj.journeyName = content.journeyName;
            obj.journeyId = content.journeyId;
            obj.journeyInfo = content.journeyInfo;
            obj.journeyPrice = content.journeyPrice;
            obj.journeyPriceChild = content.journeyPriceChild;
            obj.date = getDateStart;
            obj.rest = content.rest;
            obj.journeyPicture = content.journeyPicture;
            cardArr.push(obj);

            let item = {};
            item.status = false;
            carCardArr.push(item);

            let img_64 = content.journeyPicture;

            let card_html = `<div class="room_items" >
                                    <div class="image-box">
                                    <img src="data:image/png;base64,${img_64}" width="400" height="300">
                                    </div>
                        
                                    <div class="about">
                                    <h1 class="room_name" id="room_name${tr_id}">${content.journeyName}</h1>
                                    <p class="room_caption">${content.journeyInfo}</p>
                                    <a href="#" class="know_more">了解更多</a>
                                    </div>
                        
                                    <div class="prices" id="prices">
                                    <div>成人 <span id="adule_price${tr_id}">${content.journeyPrice}</span> 元 / 人</div>
                                    <div>兒童 <span id="kid_price${tr_id}">${content.journeyPriceChild}</span> 元 / 人</div>
                                    </div>
                        
                                </div>

                                <div class="jrntype" style="color:gray">類型: <span>${content.typeName}</span> </div>
                        
                                <div class="count_section">
                                    <div class="rest_room_section">
                                    <div id="rest_room${tr_id}">剩餘數量 <span class="rest_num" id="rest_num${tr_id}">${content.rest}</span> </div>
                                    <div id="no_room${tr_id}" class="hidden_caution" style="color:red">您已經選完最後名額</div>
                                    </div>
                                    <div class="select_block" id="select_block${tr_id}">

                                    <div class="select_btn">成人
                                        <div class="add_btn" id="${tr_id}">+</div>
                                        <div class="adult_count${tr_id}">1</div>
                                        <div class="minus_btn" id="${tr_id}">-</div>
                                    </div>

                                    <div class="select_btn">兒童
                                        <div class="add_btn" id="${tr_id}">+</div>
                                        <div class="kid_count${tr_id}">1</div>
                                        <div class="minus_btn" id="${tr_id}">-</div>
                                    </div>
                                    </div>
                                    <div class="add_into_cart_space"></div>
                                    <button type="button" class="btn" id="${tr_id}" onclick="add_car(this)">加購</button>
                            </div>`;

            tr_id++;
            jrnCardBody.prepend(card_html);
        });

    },
    error: function (result) {
        alert("提交失敗！");
        $('.no_room').css('display', 'block');
        console.log(result);
    }
})

//=========================== 清空陣列資料 ===========================
function emptyCardArr() {

    while (cardArr.length) {
        cardArr.pop();
    }
    console.log('清空JrnArr');
}
//============================ 加入購物車 ===============================
function add_car(item) {

    console.log("加入購物車");
    card_id = $(item).attr('id');
    console.log('card_id=' + card_id);

    //選的數
    cur_adult_num = parseInt($(`.adult_count${card_id}`).text());
    cur_kid_num = parseInt($(`.kid_count${card_id}`).text());
    console.log(cur_adult_num + ":" + cur_kid_num);
    cur_total_num = cur_adult_num + cur_kid_num;

    let price_adult = parseInt(cardArr[card_id].journeyPrice);
    let price_kid = parseInt(cardArr[card_id].journeyPriceChild);
    let cart_item_price = (cur_adult_num * price_adult) + (cur_kid_num * price_kid);
    let check = false;

    // console.log('carStatus=' + carCardArr[card_id].status);
    if (carCardArr[card_id].status) {
        alert('此行程已在購物車');
    } else {
        check = true;
    }

    if (check) {
        $("div.jrn_cart_items").append(
            `<li style="list-style-type: none" class="card_li" id="card_li${card_id}">
                <div class="cart_jrn">
                <div class="cart_jrn_name">${cardArr[card_id].journeyName}</div>
                <div><span>日期:</span> <span>${cardArr[card_id].date}</span></div>
                <div class="select_num"><span>大人:</span><span id="select_count">${cur_adult_num} / ${price_adult}元</span></div>
                <div class="select_num"><span>小孩:</span><span id="select_count">${cur_kid_num} / ${price_kid}元</span></div>
                <div class="cart_caution">將收取取消費用</div>
                <div class="cart_item_price">共${cart_item_price}元</div>
                </div>
                <div class="cart_remove_price">
                <div class="Action" id="${card_id}" onclick="remove_jrncar_cart(this)">x</div>
                </div>
            </li>`
        );

        carCardArr[card_id].status = true;
        carCardArr[card_id].journeyId = cardArr[card_id].journeyId;
        carCardArr[card_id].adult = cur_adult_num;
        carCardArr[card_id].kid = cur_kid_num;
        carCardArr[card_id].totalPrice = cart_item_price;
        carCardArr[card_id].date = cardArr[card_id].date;

        let rest_count = cardArr[card_id].rest - cur_adult_num - cur_kid_num;
        console.log("rest===" + rest_count);

        if (rest_count === 0) {
            console.log("empty");
            /* 顯示最後一間被選完 */
            $(".rest_room_section").find(`#no_room${card_id}`).removeClass("hidden_caution");
            $(".rest_room_section").find(`#rest_room${card_id}`).addClass("hidden_caution");
            $("#select_block${card_id}").addClass("hidden_caution");

            $(`.add_btn#${card_id}`).addClass("hidden_caution");
            $(`.minus_btn#${card_id}`).addClass("hidden_caution");
            $(`.adult_count${card_id}`).addClass("hidden_caution");
            $(`.kid_count${card_id}`).addClass("hidden_caution");
        } else {
            /* 更新房卡 剩餘房數 */
            console.log('rest_count=' + rest_count);
            $(`#rest_num${card_id}`).text(rest_count);
        }

        //更新購物車總額
        car_total_price += carCardArr[card_id].totalPrice;
        console.log('car_total_price=' + car_total_price);
        $('#total_price').text(car_total_price);


    }

}

//====================== 移除購物車卡片 ========================
function remove_jrncar_cart(item) {

    console.log("移除購物車卡片");
    card_id = $(item).attr('id');
    console.log('card_id' + card_id);

    $(`.jrn_cart_items>li#card_li${card_id}`).remove();
    // $(`.cart_items>li#card_li0`).remove();

    carCardArr[card_id].status = false;

    //恢復房卡 剩餘房數
    $(`#rest_num${card_id}`).text(cardArr[card_id].rest);

    //更新購物車總額
    car_total_price -= carCardArr[card_id].totalPrice;
    console.log('car_total_price=' + car_total_price);
    $('#total_price').text(car_total_price);

    $(".rest_room_section").find(`#no_room${card_id}`).addClass("hidden_caution");
    $(".rest_room_section").find(`#rest_room${card_id}`).removeClass("hidden_caution");
    $("#select_block${card_id}").removeClass("hidden_caution");
    $(`.add_btn#${card_id}`).removeClass("hidden_caution");
    $(`.minus_btn#${card_id}`).removeClass("hidden_caution");
    $(`.adult_count${card_id}`).removeClass("hidden_caution");
    $(`.kid_count${card_id}`).removeClass("hidden_caution");

}


function emptyChange() {
    console.log("empty");
    /* 顯示最後一間被選完 */
    $(".rest_room_section").find(`#no_room0`).removeClass("hidden_caution");
    $(".rest_room_section").find(`#rest_room0`).addClass("hidden_caution");
    $("#select_block0").addClass("hidden_caution");

    $(`.add_btn#0`).addClass("hidden_caution");
    $(`.minus_btn#0`).addClass("hidden_caution");
    $(`.adult_count0`).addClass("hidden_caution");
    $(`.kid_count0`).addClass("hidden_caution");
}

/* +- room number*/
$(document).on("click", ".add_btn", function () {

    console.log('adddd');
    let num = parseInt($(this).next().text());
    card_id = $(this).attr('id');
    console.log("card_id=" + card_id);

    cur_adult_num = parseInt($(`.adult_count${card_id}`).text());
    cur_kid_num = parseInt($(`.kid_count${card_id}`).text());
    console.log(cur_adult_num + ":" + cur_kid_num);
    cur_total_num = cur_adult_num + cur_kid_num;

    let quantity = cardArr[card_id].rest;
    // let quantity = 5;
    console.log(quantity);
    console.log($(this).next().text());

    if (cur_total_num < quantity) {
        num++;
        $(this).next().text(num);
    }
});

$(document).on("click", ".minus_btn", function () {

    let num = parseInt($(this).prev().text());
    if (num > 0) {
        num--;
        $(this).prev().text(num);
    }
})

function order() {

    console.log("確認訂單");
    var jItemList = [];
    var jrndata = [];
    let jrntotal = 0;
    for (var i = 0; i < carCardArr.length; i++) {
        if (carCardArr[i].status) {

            let item = {}
            item.journeyId = carCardArr[i].journeyId;
            item.adults = carCardArr[i].adult;
            item.children = carCardArr[i].kid;
            item.totalPrice = carCardArr[i].totalPrice;
            item.journeyDate = carCardArr[i].date;
            jrntotal += carCardArr[i].totalPrice;
            jItemList.push(item);

            let data = {}
            data.journeyName = cardArr[i].journeyName;
            data.journeyInfo = cardArr[i].journeyInfo;
            data.adultPrice = cardArr[i].journeyPrice;
            data.kidPrice = cardArr[i].journeyPriceChild;
            data.pic = cardArr[i].journeyPicture;
            data.info = cardArr[i].journeyInfo;
            jrndata.push(data);
        }
    }
    //行程總額
    console.log('jrntotal' + jrntotal);
    sessionStorage.removeItem("jrn_tp");
    sessionStorage.setItem('jrn_tp', jrntotal);

    //行程訂單資料
    console.log(jItemList);
    console.log(JSON.stringify(jItemList));
    sessionStorage.removeItem("jrn_item");
    sessionStorage.setItem('jrn_item', JSON.stringify(jItemList));

    //形成資訊
    console.log(jrndata);
    console.log(JSON.stringify(jrndata));
    sessionStorage.removeItem("jrn_data");
    sessionStorage.setItem('jrn_data', JSON.stringify(jrndata));

    sessionStorage.setItem('tp', car_total_price);

    location.href = "./reservation_details_.html";
}
