


/* +- room number*/
$(document).on("click", ".add_btn", function(e){
  
  let num = parseInt($(this).next().text());
  num ++;
  $(this).next().text(num);
});

$(document).on("click", ".minus_btn", function(e){
  
  let num = parseInt($(this).prev().text());
  if(num > 1){
    num --;
    $(this).prev().text(num);
  }
});

/* 按下 選擇 加入購物車清單 add into cart*/
$(document).on("click", "button.btn", function (e) {
  let room_number = parseInt($(this).siblings(".room_count").text());
  let room_price = $(this).parent().siblings(".room_items").find(".prices");
  let room_name_text = $(this).parent().siblings().find("h1.room_name");
  for (var i = 0; i < room_number; i++) {
    $("div.cart_items").append(
      `<div class="cart_room">
            <div class="cart_room_name">${room_name_text.text()}</div>
            <div><span>2</span> 位賓客，<span>2</span>晚</div>
            <div class="cart_caution">將收取取消費用</div>
          </div>
          <div class="cart_remove_price">
            <div class="Action" class="cart_item_remove">x</div>
            <div class="cart_item_price"><span>${parseInt(
              room_price.text()
            )}</span> 元</div>
          </div>`
    );
  }
});
/* 購物車內的移除 */
// $(document).on("click", ".cart_item_remove", function (e) {
//   let r = confirm("確認移除?");
//     if(r){
//       $(this).closest()
//     }
// });

/* 按下 加購行程 */
$("button#add_journey").on("click", function(e){
  e.stopPropagation();
  alert("跳轉加購");
  location.href = "./for_your_journey.html";
});