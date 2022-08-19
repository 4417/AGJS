// var hdpkr = new HotelDatepicker(document.getElementById('datepicker'));
// var datepicker = new HotelDatepicker(hdpkr);

var hdpkr = document.getElementById('datepicker');
var datepicker = new HotelDatepicker(hdpkr, {
    format: 'YYYY-MM-DD',
    startDate: new Date()
});

/* 傳送資料到下頁面 */

/* 跳頁 + 傳送資料 */
// $("a#btn_search_date").on("click", function(){
    
    
//     if($("input#datepicker").val() == ""){
//         $("#datepicker_caution").css("display", "block").css("color", "red");
//     }
//     else{
//         location.href = "./start_your_booking.html";
//     }
// });
