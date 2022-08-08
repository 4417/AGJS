
const date_format = "YYYY-MM-DD";
const url = "roomstatus/";
const func = {
    "add": "add/", "Search": "search/", "Edit": "update/", "Delete": "delete"
};
const mode = {
    "EmptyRoomByDate": "empty.daterange", "RoomCard": "roomcard.styleid"
};
var date = {};
//========================= 按鈕 查詢空房 ==========================
$('#btn_search_date').on("click", function () {

    let check = false;
    console.log("查詢空房");

    // let start = new Date(2022,8,25);
    // let end = new Date(2022,8,30);
    // let difference = Math.abs(end - end);
    // dateCount = difference / (1000 * 3600 * 24)
    // console.log(dateCount);

    if ($("input#datepicker").val() == "") {
        $("#datepicker_caution").css("display", "block").css("color", "red");
    }
    else {
        let datesplite = datepicker.getValue().split(' - ');
        console.log(datesplite[0]);
        console.log(datesplite[1]);
        date.startDate = datesplite[0];
        date.endDate = datesplite[1];

        sessionStorage.clear();
        sessionStorage.removeItem("startDateSS");
        sessionStorage.removeItem("endDateSS");
        sessionStorage.setItem('startDateSS', datesplite[0]);
        sessionStorage.setItem('endDateSS', datesplite[1]);

        check = true;
        let jsonData = JSON.stringify(date);
        console.log("提交" + jsonData);

        if (check) {

            $.ajax({
                url: url + func.Search + mode.EmptyRoomByDate,
                contentType: "application/json; charset=utf-8",
                data: jsonData,
                type: "post",
                dataType: "json",
                success: function (data) {

                    console.log("sus");
                    console.log(data);
                    let idlist = data.emptyRoomStyleId;
                    console.log(idlist);
                    $.each(idlist, function (index, content) {
                        console.log(content);
                    })

                    //sessionStorage 設定：
                    sessionStorage.emptyRoomStyleId = JSON.stringify(idlist);
                    var rommRoomIdArr = sessionStorage.emptyRoomStyleId;
                    console.log("sess==" + rommRoomIdArr);
                    console.log("jso==" + JSON.stringify(rommRoomIdArr));
                    /* 跳頁 + 傳送資料 */
                    location.href = "./start_your_booking.html";

                },
                error: function (result) {
                    alert("查無空房！");
                    console.log(result);
                }
            })
        }
    }

})


//============================ 取日期值 ==============================
// $(function () {

//     $('input[name="daterange"]').daterangepicker({
//         autoUpdateInput: false,
//         locale: {
//             cancelLabel: 'Clear'
//         }
//     });

//     $('input[name="daterange"]').on('apply.daterangepicker', function (ev, picker) {
//         $(this).val(picker.startDate.format(date_format) + ' - ' + picker.endDate.format(date_format));

//         // date.splice(0, date.length);
//         date.startDate = picker.startDate.format(date_format);
//         date.endDate = picker.endDate.format(date_format);
//         console.log(date);

//     });

//     $('input[name="daterange"]').on('cancel.daterangepicker', function (ev, picker) {
//         $(this).val('');
//     });

// });
