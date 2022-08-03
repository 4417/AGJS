
const roomCardBody = $(".room_card_body");

var rommRoomIdArr = sessionStorage.emptyRoomStyleId;
console.log(rommRoomIdArr);
//存放房型card陣列
var cardArr = [];
//卡片按鈕ID
var card_id;


//初始查詢房型種類
$.ajax({
    url: url + func.Search + mode.RoomCard,
    contentType: "application/json; charset=utf-8",
    type: "POST",
    data: rommRoomIdArr,
    dataType: "json",
    success: function (data) {

        // let obj = eval(data);
        console.log("初始查詢房型種類");
        emptyCardArr();
        roomCardBody.empty();

        var tr_id = 0;

        $.each(data, function (index, content) {

            console.log(content);
            let obj = {};
            obj.roomStyleId = content.roomStyleId;
            obj.roomName = content.roomName;
            obj.roomQuantity = content.roomQuantity;
            obj.bedType = content.bedType;
            obj.roomType = content.roomType;
            obj.orderRoomPrice = content.orderRoomPrice;
            obj.roomDescription = content.roomDescription;
            obj.roomPhoto = content.roomPhoto;
            cardArr.push(obj);

            let img_64 = content.roomPhoto;

            let card_html = `<div id="${tr_id}">
                                <div class="room_items">
                                <div class="image-box">
                                    <img src="data:image/png;base64,${img_64}" width="400" height="300">
                                </div>
                    
                                <div class="about">
                                    <h1 class="room_name" id="room_name">${content.roomName}(${content.bedType})</h1>
                                    <p class="room_caption">${content.roomDescription}</p>
                                    <a href="#" class="know_more">了解更多</a>
                                </div>
                    
                                <div class="prices" id="prices">${content.orderRoomPrice}<span> 元</span></div>
                                </div>
                    
                                <div class="count_section" id="${tr_id}">
                                    <div class="rest_room_section">
                                        <div id="rest_room">剩餘 <span id="rest_num">${content.roomQuantity}</span> 間</div>
                                        <div id="no_room" class="hidden_caution">您已經選完最後一間</div>
                                    </div>
                                    <!-- 需要增加display:none 當已經選完最後一間時 -->
                                    <div class="add_btn">+</div>
                                    <div class="room_count">1</div>
                                    <div class="minus_btn">-</div>
                                    <!-- <div class="add_into_cart_section"></div> -->
                                    <div class="add_into_cart_space"></div>
                                    <button type="button" class="btn" id="add_into_cart" onclick="add_cart(this) ">選擇</button>
                                </div>
                
                        </div>`;


            tr_id++;
            roomCardBody.prepend(card_html);
        });


        sessionStorage.removeItem("emptyRoomStyleId");
    },
    error: function (result) {
        alert("提交失敗！");
        sessionStorage.removeItem("emptyRoomId");
        $('.no_room').css('display', 'block');
        console.log(result);
    }
})

function add_cart(item) {

    console.log("add_cart");
    console.log(url + func.Search + mode.DatetEmpty);
    card_id = $(item).attr('id');
    console.log(card_id);

    let rest_room_count = 5;
    let total_room = 5;

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
}

//=========================== 清空陣列資料 ===========================
function emptyCardArr() {

    while (cardArr.length) {
        cardArr.pop();
    }
    console.log('清空JrnArr');
}



$("button.task_add").on("click", function () {
})