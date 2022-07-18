var hdpkr = new HotelDatepicker(document.getElementById('datepicker'));
// var datepicker = new HotelDatepicker(hdpkr);

console.log(hdpkr.getValue());

/* 傳送資料到下頁面 */

/* 跳頁 + 傳送資料 */
$("a#btn_search_date").on("click", function(e){
    // alert($("input#datepicker").val());
    alert(hdpkr.getValue());
    location.href = "./start_your_booking.html";
});
