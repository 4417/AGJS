/* 要從後端傳入的剩餘房間數 */
/* 要寫成單一房型卡片的區域變數 */
var rest_room_count = 5;



/* +- room number*/
$(document).on("click", ".add_btn", function(e){
  
  let num = parseInt($(this).next().text());
  if(num < rest_room_count){
    num ++;
    $(this).next().text(num);
  };
});

$(document).on("click", ".minus_btn", function(e){
  
  let num = parseInt($(this).prev().text());
  if(num > 1){
    num --;
    $(this).prev().text(num);
  } 
});

var total_room = 0;
var total_price = 0;

/* 按下 選擇 加入購物車清單 add into cart*/
$(document).on("click", "button.btn", function (e) {
  let room_number = parseInt($(this).siblings(".room_count").text());
  let room_price = $(this).parent().siblings(".room_items").find(".prices");
  let room_name_text = $(this).parent().siblings().find("h1.room_name");

  if(rest_room_count >> 0){

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
    $(".num_of_people_detail").text(total_room +" 個房間");
    
    total_price = total_price + (parseInt(room_price.text()) * room_number);
    $("#room_total_price").text("總計 " + total_price + " 元");
  
    /* +- room count 回歸為1*/
    $(this).siblings(".room_count").text("1");
    /* 更新剩餘房間數 */
    rest_room_count = rest_room_count - room_number;
    $(this).siblings(".rest_room_section").find("span#rest_num").text(rest_room_count);
  
    /* 顯示最後一間被選完 */
    if(rest_room_count == 0){
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
    if(r){
        $(this).closest("li").animate({
          "opacity": 0
      }, "slow", function () {
          $(this).remove();
      });
    }
     /* 修改 summary */
    total_room = total_room - 1;
    $(".num_of_people_detail").text(total_room +" 個房間");

    let room_price = $(this).next().text();
    console.log(room_price);
    total_price = total_price - parseInt(room_price);
    $("#room_total_price").text("總計 " + total_price + " 元");
});


/* 按下 加購行程 */
$("button#add_journey").on("click", function(e){
  e.stopPropagation();
  
  /* 如果沒有選房間 */

  /* 取得並儲存單一房間 */
  var room_final_list = new Array();
  var counter = 0;
  
  var room_list = $('div.cart_items').find('li');
  for (var i = 0; i < room_list.length; i ++){
    counter ++;
    room_list.room_name = $(".cart_room_name").text();
    room_list.room_price = $(".cart_item_price").text().split(" 元");
    room_final_list.push(room_list);
  };
  
  // console.log(room_list);
  console.log(room_final_list);
  // location.href = "./for_your_journey.html";
});