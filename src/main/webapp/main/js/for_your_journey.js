console.log('jrn');

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
    dateCount = difference / (1000 * 3600 * 24)
    console.log(dateCount);

    let date_detail = `${s_year}年${s_month}月${s_day}日 (${startDay}) - 
    ${e_year}年${e_month}月${e_day}日 (${endDay})`;
    $('span.date_detail').text(date_detail);
    $('span.date_count').text(`${dateCount} 晚`);


    var ssoi = sessionStorage.order_item;
    console.log(ssoi);

    var order_item = $.parseJSON(ssoi);
    $.each(order_item, function (i, item) {

        console.log(item['roomStyleId']);
        console.log(item['orderRoomQuantity']);
    });



});

function initJourneyBody() {
    console.log("初始查詢行程");
    jrnCardBody.empty();

}

function importCar() {

    emptyCardArr();
    $("div.cart_items").append(
        `<li style="list-style-type: none" class="card_li" id="card_li${card_id}">
                <div class="cart_room">
                    <div class="cart_room_name">${cardArr[card_id].roomName}(${cardArr[card_id].roomType})</div>
                    <div><span>2</span> 位賓客，<span>${cardArr[card_id].roomName}</span>晚</div>
                    <div class="select_num"><span>數量:</span><span id="select_count">${select_room_count}</span></div>
                    <div class="cart_caution">將收取取消費用</div>
                </div>
                    <div class="cart_remove_price">
                        <div class="Action" id="${card_id}" onclick=remove_car_cart(this) >x</div>
                    </div>
                    <div class="cart_item_price">${cart_item_price}元</div>
                </div>
          </li>`
    );
}

function add_car(item) {

    console.log("add_cart");
    card_id = $(item).attr('id');
    console.log('card_id=' + card_id);

    //選的數
    cur_adult_num = parseInt($(`.adult_count${card_id}`).text());
    cur_kid_num = parseInt($(`.kid_count${card_id}`).text());
    console.log(cur_adult_num + ":" + cur_kid_num);
    cur_total_num = cur_adult_num + cur_kid_num;

    // let price = parseInt(cardArr[card_id].orderRoomPrice);
    let price_adult = 200;
    let price_kid = 100;
    let cart_item_price = (cur_adult_num * price_adult) + (cur_kid_num * price_kid);
    let check = true;

    // console.log('carStatus=' + carCardArr[card_id].status);
    if (carCardArr[card_id].status) {
        alert('此房型已在購物車');
    } else {
        check = true;
    }

    if (check) {
        $("div.cart_items").append(
            `<li style="list-style-type: none" class="card_li" id="card_li${card_id}">
            <div class="cart_room">
            <div class="cart_room_name">${cardArr[card_id].roomName}(${cardArr[card_id].roomType})</div>
            <div><span>2</span> 位賓客，<span>${cardArr[card_id].roomName}</span>晚</div>
            <div class="select_num"><span>數量:</span><span id="select_count">${select_room_count}</span></div>
            <div class="cart_caution">將收取取消費用</div>
            </div>
            <div class="cart_remove_price">
            <div class="Action" id="${card_id}" onclick=remove_car_cart(this) >x</div>
            </div>
            <div class="cart_item_price">${cart_item_price}元</div>
            </li>`
        );

        // carCardArr[card_id].status = true;
        // carCardArr[card_id].roomStyleId = cardArr[card_id].roomStyleId;
        // carCardArr[card_id].people = '2';
        // carCardArr[card_id].roomCount = select_room_count;
        // carCardArr[card_id].price = cart_item_price;
        // carCardArr.push(item);

        //總量
        // let jrn_total = parseInt(cardArr[card_id].roomQuantity);
        let jrn_total = 5;
        console.log('type_room_total=' + jrn_total);
        let rest_count = jrn_total - cur_total_num;
        console.log("type_room_total - select_room_count ===" + rest_count);

        if (rest_count === 0) {
            console.log("empty");
            /* 顯示最後一間被選完 */
            $(".rest_room_section").find(`#no_room${card_id}`).removeClass("hidden_caution");
            $(".rest_room_section").find(`#rest_room${card_id}`).addClass("hidden_caution");
            $(`.add_btn#${card_id}`).addClass("hidden_caution");
            $(`.room_count${card_id}`).addClass("hidden_caution");
            $(`.minus_btn#${card_id}`).addClass("hidden_caution");
        } else {
            /* 更新房卡 剩餘房數 */
            console.log('rest_count=' + rest_count);
            $(`#rest_num${card_id}`).text(rest_count);
        }
    }
}

/* +- room number*/
$(document).on("click", ".add_btn", function () {

    console.log('adddd');
    let num = parseInt($(this).next().text());
    // card_id = $(this).attr('id');
    card_id = 0;

    cur_adult_num = parseInt($(`.adult_count${card_id}`).text());
    cur_kid_num = parseInt($(`.kid_count${card_id}`).text());
    console.log(cur_adult_num + ":" + cur_kid_num);
    cur_total_num = cur_adult_num + cur_kid_num;

    // let quantity = cardArr[card_id].roomQuantity;
    let quantity = 5;
    console.log(quantity);
    console.log($(this).next().text());

    if (cur_total_num < quantity) {
        num++;
        $(this).next().text(num);
    }
});

$(document).on("click", ".minus_btn", function () {

    let num = parseInt($(this).prev().text());
    if (num > 1) {
        num--;
        $(this).prev().text(num);
    }
})