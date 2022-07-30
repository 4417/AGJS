//----------------變數區----------------
var start_date;
var anm_status;
var type_list;
var the_day = new Date();
var end_set;
var start_set;
var title_set;
var content_set;
var type_set;
var order_set;
var checked_list = new Array();

//--------------------------------------

function convertDate(date) {
  var yyyy = date.getFullYear().toString();
  var mm = (date.getMonth()+1).toString();
  var dd  = date.getDate().toString();

  var mmChars = mm.split('');
  var ddChars = dd.split('');

  return yyyy + '-' + (mmChars[1]?mm:"0"+mmChars[0]) + '-' + (ddChars[1]?dd:"0"+ddChars[0]);
}

//--------------------------------------


$(window).on("load", function () {

  // 載入後顯示所有公告
  $.ajax({
    url: "announcement/all",      // 資料請求的網址
    type: "GET",                     // GET | POST | PUT | DELETE | PATCH
    dataType: "json",                 // 預期會接收到回傳資料的格式： json | xml | html
    success: function (response) {     // request 成功取得回應後執行
      if (response.length != 0) {
        var all_list_header = `
          <!-- 公告清單 -->
          <div class="row" style="margin-top: -20px;" name="list_area">
            <div class="mb-4 filter">
              <div class="card shadow mb-4 text-dark">
                <div class="card-header py-3">
                  <h6 class="m-0 ml-4 font-weight-bold text-br">公告清單</h6>
                </div>
                <table class="anm_list">
                  <tr class="list_header">
                    <th class="checkbox"><input type="checkbox" id="list_all"></th>
                    <th class="anm_type">公告類型</th>
                    <th class="anm_title">公告標題</th>
                    <th class="anm_date">公告日期</th>
                    <th class="anm_status">公告狀態</th>
                    <th class="anm_edit">編輯</th>
                  </tr>
                </table>
              </div>
            </div>
          </div>
        `;

        $(".row_page").after(all_list_header);

        if (response.length != 0) {
          for (var i = 0; i < response.length; i++) {
            var anmTitle = response[i].anmTitle;
            var anmStartDate = new Date(response[i].anmStartDate).toLocaleDateString("zh-TW");
            var anmEndDate = new Date(response[i].anmEndDate).toLocaleDateString("zh-TW");
            if(anmEndDate === "1970/1/1") {
              anmEndDate = "不下架";
            }

            var anmStatus = response[i].anmStatus;
            var anmTypeId = response[i].anmTypeId;
            if(anmTypeId == 1){
              anmTypeId = "住房優惠"
            }
            else if(anmTypeId == 2){
              anmTypeId = "餐飲優惠"
            }
            else if(anmTypeId == 3){
              anmTypeId = "其他"
            }
            
            var all_list = `
              <tr>
                <td class="checkbox"><input type="checkbox" class="anm_check"></td>
                <td class="anm_type">${anmTypeId}</td>
                <td class="anm_title">${anmTitle}</td>
                <td class="anm_date"><span name="anm_startdate">${anmStartDate}</span> ~ <span name="anm_enddate">${anmEndDate}</span></td>
                <td class="anm_status">${anmStatus}</td>
                <td class="anm_edit">
                  <button type="button" class="d-none d-sm-inline-block btn p-0" name="update" data-bs-toggle="modal" data-bs-target="#staticBackdrop">修改</button>
                  / 
                  <button type="button" name="delete_one" class="d-none d-sm-inline-block btn p-0">刪除</button>
                </td>
              </tr>
            `;
            $(".list_header").after(all_list);
          }
        }
      }
    }
  });

  // 搜尋
  $("#keyword").on("keyup", function (e) {
    if (e.which == 13) {
      $("#search").click();
    }
  });

  // 篩選_關鍵字
  $("#search").on("click", function () {
    $.ajax({
      url: "announcement/keyword",      // 資料請求的網址
      type: "POST",                     // GET | POST | PUT | DELETE | PATCH
      data: {
        keyword: $("#keyword").val().trim(),
      },
      dataType: "json",                 // 預期會接收到回傳資料的格式： json | xml | html
      success: function (response) {     // request 成功取得回應後執行
        // 清空舊有的篩選結果
        $("div[name='filter_area'] .card-body").nextAll().remove();
        // 印出回傳結果
        if (response.length != 0) {
          var header_html = `
            <h6 class="mt-2 ml-4 font-weight-bold text-or">篩選結果如下：</h6>
            <table class="result_list">
              <tr>
                <th class="result_type">公告類型</th>
                <th class="result_title">公告標題</th>
                <th class="result_date">公告日期</th>
                <th class="result_status">公告狀態</th>
                <th class="result_edit">編輯</th>
              </tr>
            </table>
          `;

          $("div[name='filter_area'] .card-body").after(header_html);

          for (var i = 0; i < response.length; i++) {
            var anmType;
            var anmTitle = response[i].anmTitle;
            var anmStartDate = response[i].anmStartDate;
            var anmStartDate = new Date(anmStartDate).toLocaleDateString("zh-TW");
            var anmEndDate;
            if(response[i].anmTypeId == 1){
              anmType = "住房優惠"
            }
            else if(response[i].anmTypeId == 2){
              anmType = "餐飲優惠"
            }
            else if(response[i].anmTypeId == 3){
              anmType = "其他"
            }

            if(response[i].anmEndDate === null) {
              anmEndDate = "不下架";
            }
            else{
              anmEndDate = response[i].anmEndDate;
              anmEndDate = new Date(anmEndDate).toLocaleDateString("zh-TW");
            }
            var anmStatus = response[i].anmStatus;
            var list_html = `
            <tr>
              <td class="result_type">${anmType}</td>
                <td class="result_title">${anmTitle}</td>
                <td class="result_date">
                  <span name="result_startdate">${anmStartDate}</span>
                  ~ 
                  <span name="result_enddate">${anmEndDate}</span>
                </td>
                <td class="result_status">${anmStatus}</td>
                <td class="result_edit">
                  <button type="button" class="d-none d-sm-inline-block btn p-0" data-bs-toggle="modal" data-bs-target="#staticBackdrop">修改</button>
                  / 
                  <button type="button" name="delete_one" class="d-none d-sm-inline-block btn p-0">刪除</button>
                </td>
              </tr>
            `;

            $(".result_list tr").last().after(list_html);
          }
        }
        else{
          var header_html = `<h6 class="mt-2 ml-4 font-weight-bold text-or">※※※查無相關公告資訊※※※</h6>`;
          $("div[name='filter_area'] .card-body").after(header_html);
        }

        $("#keyword").val("");
      }
    });
  });

  // 篩選_公告日期
  $("input[name='start_date']").on("click", function () {
    if (this.value == 0) {
      start_date = the_day.toLocaleDateString();
    } else if (this.value == 7) {
      the_day.setDate(the_day.getDate() - 7);
      start_date = the_day.toLocaleDateString();
    } else if (this.value == 30) {
      the_day.setMonth(the_day.getMonth() - 1);
      start_date = the_day.toLocaleDateString();
    } else {
      start_date = $(this).siblings(".cust").val();
    }
  });
  $("input.cust[name='start_date']").on("click", function () {
    $(this).prev().click();
    $(this).on("change", function () {
      start_date = $(this).val();
    });
  });

  // 篩選_公告狀態
  $("input[name='anm_status']").on("click", function () {
    anm_status = new Array();
    $('input:checkbox:checked[name="anm_status"]').each(function (i) {
      anm_status[i] = this.value;
    });
  });

  // 篩選_公告類型
  $("input[name='anm_type']").on("click", function () {
    type_list = new Array();
    $('input:checkbox:checked[name="anm_type"]').each(function (i) {
      type_list[i] = this.value;
    });
  });

  //篩選_送出
  $("#btn_filter").on("click", function () {
    console.log(start_date);
    console.log(anm_status);
    console.log(type_list);
    // $.ajax({
    //     url: "announcement",           // 資料請求的網址
    //     type: "POST",                  // GET | POST | PUT | DELETE | PATCH
    //     data: JSON.stringify({
    //         "startDate": start_date,
    //         "anmStatus": anm_status,
    //         "typeList": type_list
    //     }),                           // 將物件資料(不用雙引號) 傳送到指定的 url
    //     dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
    //     success: function(request){      // request 成功取得回應後執行
    //       console.log(request);
    //     }
    // });
  });

  // 篩選_清空選項
  $("#btn_filter_clear").on("click", function () {
    start_date = $("input[name='start_date']");
    anm_status = $("input[name='anm_status']");
    anm_type = $("input[name='anm_type']");

    start_date.prop("checked", false);
    start_date.val("");
    anm_status.prop("checked", false);
    anm_status.val("");
    anm_type.prop("checked", false);
    anm_type.val("");
  });

  // 清單_全選
  $(document).on("click", "#list_all", function () {
    $(".anm_check").prop("checked", this.checked);
  });

  $(document).on("click", ".anm_check", function () {
    $("#list_all").prop(
      "checked",
      $(".anm_check").length == $(".anm_check:checked").length
    );
  });

  // 有勾選的公告
  $(document).on("click", ".anm_check", function() {
    var list = new Object();
    var delete_anmTitle = $(this).closest("td").siblings(".anm_title").text();
    var delete_list_anmStartDate = new Date($(this).closest("td").siblings(".anm_date").find("span[name='anm_startdate']").text());
    var delete_list_anmEndDate = $(this).closest("td").siblings(".anm_date").find("span[name='anm_enddate']").text();
    if(delete_list_anmEndDate === "不下架") {
      delete_list_anmEndDate = "0";
    }
    else{
      delete_list_anmEndDate = new Date($(this).closest("td").siblings(".anm_date").find("span[name='anm_enddate']").text());
    }

    var delete_list_anmTypeId = $(this).closest("td").siblings(".anm_type").text();
    if(delete_list_anmTypeId === "住房優惠") {
      delete_list_anmTypeId = "1";
    }
    else if(delete_list_anmTypeId === "餐飲優惠") {
      delete_list_anmTypeId = "2";
    }
    else {
      delete_list_anmTypeId = "3";
    }

    list = {
      anmTitle: delete_anmTitle,
      anmStartDate: delete_list_anmStartDate,
      anmEndDate: delete_list_anmEndDate,
      anmTypeId: delete_list_anmTypeId
    };

    if($(this).prop("checked")) {
      checked_list.push(list);
      console.log(checked_list)
    }
    else {
      for (var i = 0; i < checked_list.length; i++) {
        if (JSON.stringify(checked_list[i]) == JSON.stringify(list)) {
          checked_list.splice(i, 1);
        }
      }
    }
  });

  // 刪除公告(多選)
  $("#delete_list").on("click", function () {
    if ($(".anm_check:checked").length != 0) {
      let check = confirm("確定刪除公告？");
      if (check) {
        for (var i = 0; i < checked_list.length; i++) {
          // 找到資料庫內對應的公告ID
          $.ajax({
            url: "announcement/searchAnm",           // 資料請求的網址
            type: "POST",                  // GET | POST | PUT | DELETE | PATCH
            data: JSON.stringify({              // 將物件資料(不用雙引號) 傳送到指定的 url
              "anmTitle": checked_list[i].anmTitle,
              "anmStartDate": checked_list[i].anmStartDate,
              "anmEndDate": checked_list[i].anmEndDate,
              "anmTypeId": checked_list[i].anmTypeId
            }),                           // 將物件資料(不用雙引號) 傳送到指定的 url
            contentType: "application/json; charset=utf-8",
            dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
            success: function(response){      // request 成功取得回應後執行
              for (var j = 0; j < response.length; j++) {
                // 指定的ID刪除
                $.ajax({
                  url: "announcement/delete",           // 資料請求的網址
                  type: "DELETE",                  // GET | POST | PUT | DELETE | PATCH
                  data: JSON.stringify({
                    "anmId": response[j].anmId
                  }),                           // 將物件資料(不用雙引號) 傳送到指定的 url  
                  contentType: "application/json; charset=utf-8",
                  dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
                  success: function(response){      // request 成功取得回應後執行
                  }
                });
              }
            }
          });
        }
        $(".anm_check:checked").closest("tr").remove();
      }
    }
  });

  //刪除公告(單筆)
  $(document).on("click", "button[name='delete_one']", function () {
    let check = confirm("確定刪除公告？");
    if (check) {
      var the_tr = $(this).closest("tr");
      var anmTitle = $(this).closest("td").siblings(".anm_title").text();
      var anmStartDate = new Date($(this).closest("td").siblings(".anm_date").find("span[name='anm_startdate']").text());
      var anmEndDate = $(this).closest("td").siblings(".anm_date").find("span[name='anm_enddate']").text();
      if(anmEndDate === "不下架") {
        anmEndDate = "0";
      }
      else{
        anmEndDate = new Date($(this).closest("td").siblings(".anm_date").find("span[name='anm_enddate']").text());
      }

      var anmTypeId = $(this).closest("td").siblings(".anm_type").text();
      if(anmTypeId === "住房優惠") {
        anmTypeId = "1";
      }
      else if(anmTypeId === "餐飲優惠") {
        anmTypeId = "2";
      }
      else {
        anmTypeId = "3";
      }

      $.ajax({
        url: "announcement/searchAnm",           // 資料請求的網址
        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
        data: JSON.stringify({              // 將物件資料(不用雙引號) 傳送到指定的 url
          "anmTitle": anmTitle,
          "anmStartDate": anmStartDate,
          "anmEndDate": anmEndDate,
          "anmTypeId": anmTypeId
        }),                           // 將物件資料(不用雙引號) 傳送到指定的 url
        contentType: "application/json; charset=utf-8",
        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
        success: function(response){      // request 成功取得回應後執行
          for (var i = 0; i < response.length; i++) {
            $.ajax({
              url: "announcement/delete",           // 資料請求的網址
              type: "DELETE",                  // GET | POST | PUT | DELETE | PATCH
              data: JSON.stringify({
                "anmId": response[i].anmId
              }),                           // 將物件資料(不用雙引號) 傳送到指定的 url  
              contentType: "application/json; charset=utf-8",
              dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
              success: function(response){      // request 成功取得回應後執行
                the_tr.remove();
              }
            });
          }
        }
      });
    }
  });

  // 新增公告
  // 公告標題
  $("#title_set").on("keyup", function(){
    title_set = $("#title_set").val();
  });
  
  // 公告內文
  CKEDITOR.instances.content_editor.on("change", function(){
    content_set = CKEDITOR.instances.content_editor.getData();
  });

  // 公告日期限制
  var today = the_day.toLocaleDateString("en-CA");
  $("#start_set").attr("min", today);

  // 公告日期
  $("#start_set").on("change", function() {
    start_set = $("#start_set").val();
    $("#end_set").attr("min", start_set);
  });

  // 下架日期
  $("#end_set").attr("min", today);
  $("#end_set").on("change", function() {
    end_set = $("#end_set").val();
  });
  $("#noEnd").on("click", function () {
    if ($("#noEnd").prop("checked")) {
      end_set = $("#noEnd").val();
      $("#end_set").attr("disabled", "true");
      $("#end_set").val("");
    }else {
      $("#end_set").removeAttr("disabled");
    }
  });

  // 公告類型
  $("#type_set").on("change", function(){
    type_set = $("#type_set option:selected").val();
  });

  // 公告順序
  $("#order_set").on("change", function(){
    order_set = $("#order_set option:selected").val();
  });

  // 新增公告_點擊新增
  $("#insert").on("click", function() {
    $("#submit").on("click", function () {
      $.ajax({
        url: "announcement/insert",         // 資料請求的網址
        type: "PUT",                        // GET | POST | PUT | DELETE | PATCH
        data: JSON.stringify({              // 將物件資料(不用雙引號) 傳送到指定的 url
          "anmTitle": title_set,
          "anmContent": content_set,
          "anmStartDate": start_set,
          "anmEndDate": end_set,
          "anmTypeId": type_set,
          "anmOrderId": order_set
        }),
        contentType: "application/json; charset=utf-8",
        dataType: "json",                   // 預期會接收到回傳資料的格式： json | xml | html
        success: function (response) {          // request 成功取得回應後執行
          var anmTitle = response.anmTitle;
          var anmStartDate = new Date(response.anmStartDate);
          var anmEndDate = new Date(response.anmEndDate);
          var anmTypeId = (response.anmTypeId).toString();
          var anmOrderId = (response.anmOrderId).toString();
  
          $.ajax({
            url: "announcement/searchAnm",           // 資料請求的網址
            type: "POST",                  // GET | POST | PUT | DELETE | PATCH
            data: JSON.stringify({              // 將物件資料(不用雙引號) 傳送到指定的 url
              "anmTitle": anmTitle,
              "anmStartDate": anmStartDate,
              "anmEndDate": anmEndDate,
              "anmTypeId": anmTypeId,
              "anmOrderId": anmOrderId
            }),                           // 將物件資料(不用雙引號) 傳送到指定的 url
            contentType: "application/json; charset=utf-8",
            dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
            success: function(response){      // request 成功取得回應後執行
              if (response.length != 0) {
                for (var i = 0; i < response.length; i++) {
                  var anmTitle = response[i].anmTitle;
                  var anmStartDate = new Date(response[i].anmStartDate).toLocaleDateString("zh-TW");
                  var anmEndDate = new Date(response[i].anmEndDate).toLocaleDateString("zh-TW");
                  if(anmEndDate === "1970/1/1") {
                    anmEndDate = "不下架";
                  }
  
                  var anmStatus = response[i].anmStatus;
                  var anmTypeId = response[i].anmTypeId;
                  if(anmTypeId == 1){
                    anmTypeId = "住房優惠"
                  }
                  else if(anmTypeId == 2){
                    anmTypeId = "餐飲優惠"
                  }
                  else if(anmTypeId == 3){
                    anmTypeId = "其他"
                  }
                  
                  var html = `
                    <tr>
                      <td class="checkbox"><input type="checkbox" class="anm_check"></td>
                      <td class="anm_type">${anmTypeId}</td>
                      <td class="anm_title">${anmTitle}</td>
                      <td class="anm_date"><span name="anm_startdate">${anmStartDate}</span> ~ <span name="anm_enddate">${anmEndDate}</span></td>
                      <td class="anm_status">${anmStatus}</td>
                      <td class="anm_edit">
                        <button type="button" class="d-none d-sm-inline-block btn p-0" name="update" data-bs-toggle="modal" data-bs-target="#staticBackdrop">修改</button>
                        / 
                        <button type="button" name="delete_one" class="d-none d-sm-inline-block btn p-0">刪除</button>
                      </td>
                    </tr>
                  `;
                  $(".list_header").after(html);
                }
              }
  
              $("#title_set").val("");
              CKEDITOR.instances.content_editor.setData("");
              $("#start_set").val("");
              $("#end_set").val("");
              $("#noEnd").prop("checked", false);
              $("#end_set").removeAttr("disabled");
              $("#type_set").prop("selectedIndex", 0);
              $("#order_set").prop("selectedIndex", 0);
  
            }
          });
        },
      });
    });
  });

  

  // 修改公告
  $(document).on("click", "button[name='update']", function(){

    $("#staticBackdropLabel").text("修改公告");
    // 修改新增按鈕
    $("#submit").text("修改");
    $("#submit").removeAttr("id");
    $("#cancel").siblings("button").attr("id", "update");
    // 如果點取消把submit的id加回按鈕
    $("#cancel").on("click", function(){
      $("#staticBackdropLabel").text("新增公告");
      $("#cancel").siblings("button").text("新增");
      $("#cancel").siblings("button").attr("id", "submit");
    });
    $(".btn-close").on("click", function(){
      $("#staticBackdropLabel").text("新增公告");
      $("#cancel").siblings("button").text("新增");
      $("#cancel").siblings("button").attr("id", "submit");
    });

    var that = $(this);
    var anmTitle = $(this).closest("td").siblings(".anm_title").text();
    var anmStartDate = new Date($(this).closest("td").siblings(".anm_date").find("span[name='anm_startdate']").text());
    var anmEndDate = $(this).closest("td").siblings(".anm_date").find("span[name='anm_enddate']").text();
    if(anmEndDate === "不下架") {
      anmEndDate = "0";
    }
    else{
      anmEndDate = new Date($(this).closest("td").siblings(".anm_date").find("span[name='anm_enddate']").text());
    }

    var anmTypeId = $(this).closest("td").siblings(".anm_type").text();
    if(anmTypeId === "住房優惠") {
      anmTypeId = "1";
    }
    else if(anmTypeId === "餐飲優惠") {
      anmTypeId = "2";
    }
    else {
      anmTypeId = "3";
    }

    $.ajax({
      url: "announcement/searchAnm",           // 資料請求的網址
      type: "POST",                  // GET | POST | PUT | DELETE | PATCH
      data: JSON.stringify({              // 將物件資料(不用雙引號) 傳送到指定的 url
        "anmTitle": anmTitle,
        "anmStartDate": anmStartDate,
        "anmEndDate": anmEndDate,
        "anmTypeId": anmTypeId
      }),                           // 將物件資料(不用雙引號) 傳送到指定的 url
      contentType: "application/json; charset=utf-8",
      dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
      success: function(response){      // request 成功取得回應後執行
        
        // 把原本的資料帶回表格
        for (var i = 0; i < response.length; i++) {
          var anmId = response[i].anmId;
          var anmTitle = response[i].anmTitle;
          var anmContent = response[i].anmContent;
          var anmStartDate = new Date(response[i].anmStartDate).toLocaleDateString("en-CA");
          var anmEndDate = new Date(response[i].anmEndDate).toLocaleDateString("en-CA");
          var anmTypeId = response[i].anmTypeId;
          var anmOrderId = response[i].anmOrderId;
          
          $("#title_set").val(anmTitle)
          CKEDITOR.instances.content_editor.setData(anmContent);
          $("#start_set").attr("value", anmStartDate);

          if(response[i].anmEndDate == null) {
            $("#noEnd").prop("checked", true);
            $("#end_set").attr("disabled", "true");
          }
          else{
            $("#end_set").attr("value", anmEndDate);
          }

          $("#type_set").prop("selectedIndex", anmTypeId);
          $("#order_set").prop("selectedIndex", anmOrderId);

          //取得更新後的欄位值
          title_set = $("#title_set").val();
          content_set = CKEDITOR.instances.content_editor.getData();
          start_set = $("#start_set").val();
          end_set = $("#end_set").val();
          type_set = $("#type_set option:selected").val();
          order_set = $("#order_set option:selected").val();
          var today = the_day.toLocaleDateString("en-CA");
          $("#start_set").attr("min", today);
          $("#end_set").attr("min", start_set);
          $("#noEnd").on("click", function () {
            if ($("#noEnd").prop("checked")) {
              end_set = $("#noEnd").val();
              $("#end_set").attr("disabled", "true");
              $("#end_set").val("");
            }else {
              $("#end_set").removeAttr("disabled");
            }
          });
          if ($("#noEnd").prop("checked")) {
            end_set = $("#noEnd").val();
          }

          $("#update").on("click", function(){
            $.ajax({
              url: "announcement/update",     // 資料請求的網址
              type: "PATCH",                  // GET | POST | PUT | DELETE | PATCH
              data: JSON.stringify({              // 將物件資料(不用雙引號) 傳送到指定的 url
                "anmId": anmId,
                "anmTitle": title_set,
                "anmContent": content_set,
                "anmStartDate": start_set,
                "anmEndDate": end_set,
                "anmTypeId": type_set,
                "anmOrderId": order_set
              }),                          // 將物件資料(不用雙引號) 傳送到指定的 url  
              contentType: "application/json; charset=utf-8",
              dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
              success: function(response){      // request 成功取得回應後執行
                console.log(response)
                var anmTitle = response.anmTitle;
                var anmStartDate = new Date(response.anmStartDate).toLocaleDateString("zh-TW");
                var anmEndDate = new Date(response.anmEndDate).toLocaleDateString("zh-TW");
                if(anmEndDate === "1970/1/1") {
                  anmEndDate = "不下架";
                }

                var anmStatus = response.anmStatus;
                var anmTypeId = response.anmTypeId;
                if(anmTypeId == 1){
                  anmTypeId = "住房優惠"
                }
                else if(anmTypeId == 2){
                  anmTypeId = "餐飲優惠"
                }
                else if(anmTypeId == 3){
                  anmTypeId = "其他"
                }

                $(that).closest("td").siblings(".anm_title").text(anmTitle);
                $(that).closest("td").siblings(".anm_date").find("span[name='anm_startdate']").text(anmStartDate);
                $(that).closest("td").siblings(".anm_date").find("span[name='anm_enddate']").text(anmEndDate);
                $(that).closest("td").siblings(".anm_type").text(anmTypeId);
                $(that).closest("td").siblings(".anm_status").text(anmStatus);
              },
              complete: function(xhr){      // request 完成之後執行(在 success / error 事件之後執行)
                $("#staticBackdropLabel").text("新增公告");
                $("#cancel").siblings("button").text("新增");
                $("#cancel").siblings("button").attr("id", "submit");
                $("#title_set").val("");
                CKEDITOR.instances.content_editor.setData("");
                $("#start_set").val("");
                $("#end_set").val("");
                $("#noEnd").prop("checked", false);
                $("#end_set").removeAttr("disabled");
                $("#type_set").prop("selectedIndex", 0);
                $("#order_set").prop("selectedIndex", 0);
              }
            });
          });
        }


      }
    });

    

  });

});





// // 如果標題是空值
// $("#title_set").on("blur", function () {
//   if ($(title_set).val() == "") {
//     $(this).prev("").text("*請輸入公告標題!");
//   }
// });
// // 如果內文是空值
// $("#content_editor").on("blur", function () {
//   // console.log(this);
//   if (content_set == "") {
//     $(this).prev("").text("*請輸入公告內文!");
//   }
// });