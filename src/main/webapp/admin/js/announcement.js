//----------------變數區----------------
var start_date = null;
var anm_status = null;
var type_list = null;
var the_day = new Date();
var end_set;
var start_set;
var title_set;
var content_set;
var type_set;
var order_set;
var checked_list = new Array();

//--------------------------------------

$(window).on("load", function () {

  // 載入後顯示所有公告
  $.ajax({
    url: "announcement/all",      // 資料請求的網址
    type: "GET",                     // GET | POST | PUT | DELETE | PATCH
    dataType: "json",                 // 預期會接收到回傳資料的格式： json | xml | html
    success: function (data) {     // request 成功取得回應後執行

      $('#anm_list').DataTable({
        "data": data,
        "columns": [ //列的標題一般是從DOM中讀取（你還可以使用這個屬性為表格創建列標題)
          { data: null,
            className: "checkbox",
            render: function (data, type, row) {
              return '<input type="checkbox" class="anm_check" value="1">'
            } 
          },
          { data: "anmTypeId", 
            className: "anm_type",
            render: function (data, type, row) {
              if(data == 1){
                var anmTypeId = "住房優惠"
              }
              else if(data == 2){
                var anmTypeId = "餐飲優惠"
              }
              else if(data == 3){
                var anmTypeId = "其他"
              }
              return anmTypeId
            }
          },
          { data: "anmTitle", className: "anm_title"},
          { data: null, className: "anm_date",
            render: function (data, type, row) {
              var anmStartDate = data.anmStartDate;
              var anmEndDate = data.anmEndDate;
              if (anmEndDate == null){
                anmEndDate = "不下架";
              }
              return '<span name="anm_startdate" class="pr-1 pl-3">' + anmStartDate + '</span>~' +
              '<span name="anm_enddate" class="pl-1 pr-3">' + anmEndDate + '</span>'
            }
            },
          { data: "anmStatus", className: "anm_status",},
          { data: null, className: "anm_edit",
            render: function (data, type, row) {
              return '<button type="button" name="update" class="d-none d-sm-inline-block btn p-0 px-1" data-bs-toggle="modal" data-bs-target="#staticBackdrop">修改</button>/' +
              '<button type="button" name="delete_one" class="d-none d-sm-inline-block btn p-0 px-1">刪除</button>'
            } 
          },
        ],
        "destroy": true,
        "searching": false,
        "ordering": false,
        "autoWidth": false,
        "language": {
          "lengthMenu": "顯示 _MENU_ 項結果",
          "info": "顯示第 _START_ 至 _END_ 項結果，共 _TOTAL_ 項",
          "infoFiltered": "(從 _MAX_ 項結果中過濾)",
          "search": "搜尋:",
          "paginate": {
              "first": "第一頁",
              "previous": "上一頁",
              "next": "下一頁",
              "last": "最後一頁"
          }
        },
      });


    //   if (response.length != 0) {
    //     for (var i = 0; i < response.length; i++) {
    //       var anmTitle = response[i].anmTitle;
    //       var anmStartDate = new Date(response[i].anmStartDate).toLocaleDateString("zh-TW");
    //       var anmEndDate = new Date(response[i].anmEndDate).toLocaleDateString("zh-TW");
    //       if(anmEndDate === "1970/1/1") {
    //         anmEndDate = "不下架";
    //       }

    //       var anmStatus = response[i].anmStatus;
    //       var anmTypeId = response[i].anmTypeId;
    //       if(anmTypeId == 1){
    //         anmTypeId = "住房優惠"
    //       }
    //       else if(anmTypeId == 2){
    //         anmTypeId = "餐飲優惠"
    //       }
    //       else if(anmTypeId == 3){
    //         anmTypeId = "其他"
    //       }
          
    //       var all_list = `
    //         <tr>
    //           <td class="checkbox"><input type="checkbox" class="anm_check" value="1"></td>
    //           <td class="anm_type">${anmTypeId}</td>
    //           <td class="anm_title">${anmTitle}</td>
    //           <td class="anm_date"><span name="anm_startdate">${anmStartDate}</span> ~ <span name="anm_enddate">${anmEndDate}</span></td>
    //           <td class="anm_status">${anmStatus}</td>
    //           <td class="anm_edit">
    //             <button type="button" class="d-none d-sm-inline-block btn p-0" name="update" data-bs-toggle="modal" data-bs-target="#staticBackdrop">修改</button>
    //             / 
    //             <button type="button" name="delete_one" class="d-none d-sm-inline-block btn p-0">刪除</button>
    //           </td>
    //         </tr>
    //       `;
    //       $("tbody").prepend(all_list);
    //     }
    //   }
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
    if($("#keyword").val().trim() != ""){
      $.ajax({
        url: "announcement/keyword",      // 資料請求的網址
        type: "POST",                     // GET | POST | PUT | DELETE | PATCH
        data: JSON.stringify({
          keyword: $("#keyword").val().trim(),
        }),
        contentType: "application/json; charset=utf-8",
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
                    <button type="button" name="update" class="d-none d-sm-inline-block btn p-0" data-bs-toggle="modal" data-bs-target="#staticBackdrop">修改</button>
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
        }
      });
    }
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
      start_date = new Date($(this).val()).toLocaleDateString();
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
    $.ajax({
        url: "announcement/filter",    // 資料請求的網址
        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
        data: JSON.stringify({
            "anmStartDate": start_date,
            "anmStatus": anm_status,
            "anmTypeId": type_list

        }),                           // 將物件資料(不用雙引號) 傳送到指定的 url
        contentType: "application/json; charset=utf-8",
        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
        success: function(response){      // request 成功取得回應後執行

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
                    <button type="button" name="update" class="d-none d-sm-inline-block btn p-0" data-bs-toggle="modal" data-bs-target="#staticBackdrop">修改</button>
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

        }
    });
  });

  // 篩選_清空選項
  $("#btn_filter_clear").on("click", function () {
    $("input[name='start_date']").prop("checked", false);
    start_date = null;
    $("input[name='anm_status']").prop("checked", false);
    anm_status = null;
    $("input[name='anm_type']").prop("checked", false);
    type_list = null;

    $("div[name='filter_area'] .card-body").nextAll().remove();
  });

  // 篩選_刪除
  $(document).on("click", ".result_list button[name='delete_one']", function () {
    let check = confirm("確定刪除公告？");
    if (check) {
      var the_tr = $(this).closest("tr");
      var anmTitle = $(this).closest("td").siblings(".result_title").text();
      var anmTypeId = $(this).closest("td").siblings(".result_type").text();
      var anmStartDate = $(this).closest("td").siblings(".result_date").find("span[name='result_startdate']").text();
      var anmEndDate = $(this).closest("td").siblings(".result_date").find("span[name='result_enddate']").text();
      if(anmEndDate === "不下架") {
        anmEndDate = "1970/1/1";
      }

      // if(anmTypeId === "住房優惠") {
      //   anmTypeId = "1";
      // }
      // else if(anmTypeId === "餐飲優惠") {
      //   anmTypeId = "2";
      // }
      // else {
      //   anmTypeId = "3";
      // }

      $.ajax({
        url: "announcement/searchAnm",           // 資料請求的網址
        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
        data: JSON.stringify({              // 將物件資料(不用雙引號) 傳送到指定的 url
          "anmTitle": anmTitle,
          "anmStartDate": anmStartDate,
          "anmEndDate": anmEndDate,
          "anmType": anmTypeId
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
              },
              complete: function(){
                the_tr.remove();
              }
            });
          }
        }
      });
    }
  });

  // 篩選_修改
  $(document).on("click", ".result_list button[name='update']", function(){
    $("#staticBackdropLabel").text("修改公告");
    $(".modal-body").find("span").removeClass("warning");
    $(".modal-body").find("span.warn").text("");
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
    var anmTitle = $(this).closest("td").siblings(".result_title").text();
    var anmStartDate = $(this).closest("td").siblings(".result_date").find("span[name='result_startdate']").text();
    var anmEndDate = $(this).closest("td").siblings(".result_date").find("span[name='result_enddate']").text();
    var anmTypeId = $(this).closest("td").siblings(".result_type").text();
    if(anmEndDate === "不下架") {
      anmEndDate = "1970/1/1";
    }

    // if(anmTypeId === "住房優惠") {
    //   anmTypeId = "1";
    // }
    // else if(anmTypeId === "餐飲優惠") {
    //   anmTypeId = "2";
    // }
    // else {
    //   anmTypeId = "3";
    // }

    $.ajax({
      url: "announcement/searchAnm",           // 資料請求的網址
      type: "POST",                  // GET | POST | PUT | DELETE | PATCH
      data: JSON.stringify({              // 將物件資料(不用雙引號) 傳送到指定的 url
        "anmTitle": anmTitle,
        "anmStartDate": anmStartDate,
        "anmEndDate": anmEndDate,
        "anmType": anmTypeId
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
          start_set = new Date($("#start_set").val()).toLocaleDateString("zh-TW");
          end_set = new Date($("#end_set").val()).toLocaleDateString("zh-TW");
          type_set = $("#type_set option:selected").text();
          order_set = $("#order_set option:selected").val();
          var today = the_day.toLocaleDateString("en-CA");
          $("#start_set").attr("min", today);
          $("#end_set").attr("min", new Date($("#start_set").val()).toLocaleDateString("en-CA"));
          $("#noEnd").on("click", function () {
            if ($("#noEnd").prop("checked")) {
              end_set = new Date("1970/1/1").toLocaleDateString("zh-TW");
              $("#end_set").attr("disabled", "true");
              $("#end_set").val("");
            }else {
              $("#end_set").removeAttr("disabled");
            }
          });
          if ($("#noEnd").prop("checked")) {
            end_set = "1970/1/1";
          }
        }
        
        //修改_送出
        $("#update").on("click", function(){
          if(!$("span.warn").hasClass("warning")) {
            $.ajax({
              url: "announcement/update",     // 資料請求的網址
              type: "PATCH",                  // GET | POST | PUT | DELETE | PATCH
              data: JSON.stringify({              // 將物件資料(不用雙引號) 傳送到指定的 url
                "anmId": anmId,
                "anmTitle": title_set,
                "anmContent": content_set,
                "anmStartDate": start_set,
                "anmEndDate": end_set,
                "anmType": type_set,
                "anmOrderId": order_set
              }),                          // 將物件資料(不用雙引號) 傳送到指定的 url  
              contentType: "application/json; charset=utf-8",
              dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
              success: function(response){      // request 成功取得回應後執行
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
      
                $(that).closest("td").siblings(".result_title").text(anmTitle);
                $(that).closest("td").siblings(".result_date").find("span[name='result_startdate']").text(anmStartDate);
                $(that).closest("td").siblings(".result_date").find("span[name='result_enddate']").text(anmEndDate);
                $(that).closest("td").siblings(".result_type").text(anmTypeId);
                $(that).closest("td").siblings(".result_status").text(anmStatus);
              },
              complete: function(){      // request 完成之後執行(在 success / error 事件之後執行)
                $("#cancel").click();
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
                $("#title_set").siblings("span").text("請輸入公告標題");
                $("#title_set").siblings("span").addClass("warning");;
                $(".anm_content_set").siblings("span").text("請輸入公告內文");
                $(".anm_content_set").siblings("span").addClass("warning");
                $(".start_set_warn").text("請選擇公告日期");
                $(".start_set_warn").addClass("warning");
                $(".end_set_warn").text("請選擇公告下架日期");
                $(".end_set_warn").addClass("warning");
                $(".type_set_warn").text("請選擇公告類型");
                $(".type_set_warn").addClass("warning");
                $(".order_set_warn").text("請選擇公告順序");
                $(".order_set_warn").addClass("warning");
              }
            });
          }
          else{
            alert("請填寫公告相關資訊");
          }
        });
      }
    });
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
    var delete_list_anmTypeId = $(this).closest("td").siblings(".anm_type").text();
    var delete_list_anmStartDate = $(this).closest("td").siblings(".anm_date").find("span[name='anm_startdate']").text();
    var delete_list_anmEndDate = $(this).closest("td").siblings(".anm_date").find("span[name='anm_enddate']").text();
    if(delete_list_anmEndDate === "不下架") {
      delete_list_anmEndDate = "1970/1/1";
    }

    // if(delete_list_anmTypeId === "住房優惠") {
    //   delete_list_anmTypeId = "1";
    // }
    // else if(delete_list_anmTypeId === "餐飲優惠") {
    //   delete_list_anmTypeId = "2";
    // }
    // else {
    //   delete_list_anmTypeId = "3";
    // }

    list = {
      anmTitle: delete_anmTitle,
      anmStartDate: delete_list_anmStartDate,
      anmEndDate: delete_list_anmEndDate,
      anmTypeId: delete_list_anmTypeId
    };

    if($(this).prop("checked")) {
      checked_list.push(list);
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
  $("#delete_list",).on("click", function () {
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
              "anmType": checked_list[i].anmTypeId
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
                  },
                  complete: function(){
                    the_tr.remove();
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
  $(document).on("click", "#anm_list button[name='delete_one']", function () {
    let check = confirm("確定刪除公告？");
    if (check) {
      var the_tr = $(this).closest("tr");
      var anmTitle = $(this).closest("td").siblings(".anm_title").text();
      var anmTypeId = $(this).closest("td").siblings(".anm_type").text();
      var anmStartDate = $(this).closest("td").siblings(".anm_date").find("span[name='anm_startdate']").text();
      var anmEndDate = $(this).closest("td").siblings(".anm_date").find("span[name='anm_enddate']").text();
      if(anmEndDate === "不下架") {
        anmEndDate = "1970/1/1";
      }

      // if(anmTypeId === "住房優惠") {
      //   anmTypeId = "1";
      // }
      // else if(anmTypeId === "餐飲優惠") {
      //   anmTypeId = "2";
      // }
      // else {
      //   anmTypeId = "3";
      // }

      $.ajax({
        url: "announcement/searchAnm",           // 資料請求的網址
        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
        data: JSON.stringify({              // 將物件資料(不用雙引號) 傳送到指定的 url
          "anmTitle": anmTitle,
          "anmStartDate": anmStartDate,
          "anmEndDate": anmEndDate,
          "anmType": anmTypeId
        }),                           // 將物件資料(不用雙引號) 傳送到指定的 url
        contentType: "application/json; charset=utf-8",
        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
        success: function(response){      // request 成功取得回應後執行
          for (var i = 0; i < response.length; i++) {
            $.ajax({
              url: "announcement/delete",           // 資料請求的網址
              type: "DELETE",                   // GET | POST | PUT | DELETE | PATCH
              data: JSON.stringify({
                "anmId": response[i].anmId
              }),                               // 將物件資料(不用雙引號) 傳送到指定的 url  
              contentType: "application/json; charset=utf-8",
              dataType: "json",                 // 預期會接收到回傳資料的格式： json | xml | html
              success: function(response){      // request 成功取得回應後執行
              },
              complete: function(){
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
    if($("#title_set").val().trim() == ""){
      $(this).siblings("span").text("請輸入公告標題");
      $(this).siblings("span").addClass("warning");
    }
    else{
      $(this).siblings("span").text("");
      $(this).siblings("span").removeClass("warning");
    }
  });
  
  // 公告內文
  CKEDITOR.instances.content_editor.on("change", function(){
    content_set = CKEDITOR.instances.content_editor.getData();
    if( CKEDITOR.instances.content_editor.getData() == ""){
      $(".anm_content_set").siblings("span").text("請輸入公告內文");
      $(".anm_content_set").siblings("span").addClass("warning");
    }
    else{
      $(".anm_content_set").siblings("span").text("");
      $(".anm_content_set").siblings("span").removeClass("warning");
    }
  });

  // 公告日期限制
  var today = the_day.toLocaleDateString("en-CA");
  $("#start_set").attr("min", today);

  // 公告日期
  $("#start_set").on("change", function() {
    start_set = new Date($("#start_set").val()).toLocaleDateString("zh-TW");
    $("#end_set").attr("min", new Date($("#start_set").val()).toLocaleDateString("en-CA"));
    if(start_set == ""){
      $(".start_set_warn").text("請選擇公告日期");
      $(".start_set_warn").addClass("warning");
    }
    else if(start_set == "Invalid Date"){
      $(".start_set_warn").text("請選擇公告日期");
      $(".start_set_warn").removeAttr("style");
    }
    else{
      $(".start_set_warn").text("");
      $(".start_set_warn").removeClass("warning");
    }
  });

  // 下架日期
  $("#end_set").attr("min", today);
  $("#end_set").on("change", function() {
    end_set = new Date($("#end_set").val()).toLocaleDateString("zh-TW");
    if(end_set == ""){
      $(".end_set_warn").text("請選擇公告下架日期");
      $(".end_set_warn").addClass("warning");
      $(".end_set_warn").removeAttr("style");
    }
    else if(end_set == "Invalid Date"){
      $(".end_set_warn").text("請選擇公告下架日期");
      $(".end_set_warn").removeAttr("style");
    }
    else if(end_set == start_set){
      $(".end_set_warn").text("公告日期不可等於下架日期");
      $(".end_set_warn").css("right", "115px");
    }
    else{
      $(".end_set_warn").text("");
      $(".end_set_warn").removeClass("warning");
      $(".end_set_warn").removeAttr("style");
    }
  });
  $("#noEnd").on("click", function () {
    if ($("#noEnd").prop("checked")) {
      end_set = new Date("1970/1/1").toLocaleDateString("zh-TW");
      $("#end_set").attr("disabled", "true");
      $("#end_set").val("");
      $(".end_set_warn").text("");
      $(".end_set_warn").removeClass("warning");
    }else {
      $("#end_set").removeAttr("disabled");
      $(".end_set_warn").text("請選擇公告下架日期");
      $(".end_set_warn").addClass("warning");
    }
  });

  // 公告類型
  $("#type_set").on("change", function(){
    type_set = $("#type_set option:selected").text();
    if(type_set == ""){
      $(".type_set_warn").text("請選擇公告類型");
      $(".type_set_warn").addClass("warning");
    }
    else{
      $(".type_set_warn").text("");
      $(".type_set_warn").removeClass("warning");
    }
    // if(type_set == "1") {
    //   type_set = 1;
    // }
    // else if(type_set == "2") {
    //   type_set = 2;
    // }
    // else{
    //   type_set = 3;
    // }
  });

  // 公告順序
  $("#order_set").on("change", function(){
    order_set = $("#order_set option:selected").val();
    if(order_set == ""){
      $(".order_set_warn").text("請選擇公告順序");
      $(".order_set_warn").addClass("warning");
    }
    else{
      $(".order_set_warn").text("");
      $(".order_set_warn").removeClass("warning");
    }

    if(order_set == "1") {
      order_set = 1;
    }
    else if(order_set == "2") {
      order_set = 2;
    }
    else{
      order_set = 3;
    }
  });

  // 按取消
  $("#cancel").on("click", function(){
    $("#title_set").val("");
    CKEDITOR.instances.content_editor.setData("");
    $("#start_set").val("");
    $("#end_set").val("");
    $("#noEnd").prop("checked", false);
    $("#end_set").removeAttr("disabled");
    $("#type_set").prop("selectedIndex", 0);
    $("#order_set").prop("selectedIndex", 0);
    $("#title_set").siblings("span").text("請輸入公告標題");
    $("#title_set").siblings("span").addClass("warning");;
    $(".anm_content_set").siblings("span").text("請輸入公告內文");
    $(".anm_content_set").siblings("span").addClass("warning");
    $(".start_set_warn").text("請選擇公告日期");
    $(".start_set_warn").addClass("warning");
    $(".end_set_warn").text("請選擇公告下架日期");
    $(".end_set_warn").addClass("warning");
    $(".type_set_warn").text("請選擇公告類型");
    $(".type_set_warn").addClass("warning");
    $(".order_set_warn").text("請選擇公告順序");
    $(".order_set_warn").addClass("warning");
  });
  // 按X
  $(".btn-close").on("click", function(){
    $("#title_set").val("");
    CKEDITOR.instances.content_editor.setData("");
    $("#start_set").val("");
    $("#end_set").val("");
    $("#noEnd").prop("checked", false);
    $("#end_set").removeAttr("disabled");
    $("#type_set").prop("selectedIndex", 0);
    $("#order_set").prop("selectedIndex", 0);
    $("#title_set").siblings("span").text("請輸入公告標題");
    $("#title_set").siblings("span").addClass("warning");;
    $(".anm_content_set").siblings("span").text("請輸入公告內文");
    $(".anm_content_set").siblings("span").addClass("warning");
    $(".start_set_warn").text("請選擇公告日期");
    $(".start_set_warn").addClass("warning");
    $(".end_set_warn").text("請選擇公告下架日期");
    $(".end_set_warn").addClass("warning");
    $(".type_set_warn").text("請選擇公告類型");
    $(".type_set_warn").addClass("warning");
    $(".order_set_warn").text("請選擇公告順序");
    $(".order_set_warn").addClass("warning");
  });

  // 新增公告
  $("#insert").on("click", function () {
    $("#submit").on("click", function () {
      if(!$("span.warn").hasClass("warning")) {
        $.ajax({
          url: "announcement/insert",         // 資料請求的網址
          type: "PUT",                        // GET | POST | PUT | DELETE | PATCH
          data: JSON.stringify({              // 將物件資料(不用雙引號) 傳送到指定的 url
            "anmTitle": title_set,
            "anmContent": content_set,
            "anmStartDate": start_set,
            "anmEndDate": end_set,
            "anmType": type_set,
            "anmOrderId": order_set
          }),
          contentType: "application/json; charset=utf-8",
          dataType: "json",                   // 預期會接收到回傳資料的格式： json | xml | html
          success: function (response) {          // request 成功取得回應後執行
            var anmTitle = response.anmTitle;
            var anmTypeId = response.anmType;
            var anmStatus = response.anmStatus;
            var anmStartDate = new Date(response.anmStartDate).toLocaleDateString("zh-TW");
            var anmEndDate = new Date(response.anmEndDate).toLocaleDateString("zh-TW");
            if(anmEndDate === "1970/1/1") {
              anmEndDate = "不下架";
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
            $("tbody").prepend(html);
            $("#title_set").val("");
            CKEDITOR.instances.content_editor.setData("");
            $("#start_set").val("");
            $("#end_set").val("");
            $("#noEnd").prop("checked", false);
            $("#end_set").removeAttr("disabled");
            $("#type_set").prop("selectedIndex", 0);
            $("#order_set").prop("selectedIndex", 0);
          },
          complete: function(){
            $("#cancel").click();
            $("#title_set").siblings("span").text("請輸入公告標題");
            $("#title_set").siblings("span").addClass("warning");;
            $(".anm_content_set").siblings("span").text("請輸入公告內文");
            $(".anm_content_set").siblings("span").addClass("warning");
            $(".start_set_warn").text("請選擇公告日期");
            $(".start_set_warn").addClass("warning");
            $(".end_set_warn").text("請選擇公告下架日期");
            $(".end_set_warn").addClass("warning");
            $(".type_set_warn").text("請選擇公告類型");
            $(".type_set_warn").addClass("warning");
            $(".order_set_warn").text("請選擇公告順序");
            $(".order_set_warn").addClass("warning");
          }
        });
      }
      else{
        alert("請填寫公告相關資訊");
      }
    });
  });
  // $("#submit").on("click", function () {
  //   if(!$("span.warn").hasClass("warning")) {
  //     $.ajax({
  //       url: "announcement/insert",         // 資料請求的網址
  //       type: "PUT",                        // GET | POST | PUT | DELETE | PATCH
  //       data: JSON.stringify({              // 將物件資料(不用雙引號) 傳送到指定的 url
  //         "anmTitle": title_set,
  //         "anmContent": content_set,
  //         "anmStartDate": start_set,
  //         "anmEndDate": end_set,
  //         "anmTypeId": type_set,
  //         "anmOrderId": order_set
  //       }),
  //       contentType: "application/json; charset=utf-8",
  //       dataType: "json",                   // 預期會接收到回傳資料的格式： json | xml | html
  //       success: function (response) {          // request 成功取得回應後執行
  //         var anmTitle = response.anmTitle;
  //         var anmStartDate = new Date(response.anmStartDate).toLocaleDateString("zh-TW");
  //         var anmEndDate = new Date(response.anmEndDate).toLocaleDateString("zh-TW");
  //         var anmTypeId = (response.anmTypeId).toString();
  //         var anmStatus = (response.anmStatus).toString();
          
  //         if(anmEndDate === "1970/1/1") {
  //           anmEndDate = "不下架";
  //         }

  //         if(anmTypeId == 1){
  //           anmTypeId = "住房優惠"
  //         }
  //          else if(anmTypeId == 2){
  //           anmTypeId = "餐飲優惠"
  //         }
  //         else if(anmTypeId == 3){
  //           anmTypeId = "其他"
  //         }

  //         var html = `
  //         <tr>
  //           <td class="checkbox"><input type="checkbox" class="anm_check"></td>
  //           <td class="anm_type">${anmTypeId}</td>
  //           <td class="anm_title">${anmTitle}</td>
  //           <td class="anm_date"><span name="anm_startdate">${anmStartDate}</span> ~ <span name="anm_enddate">${anmEndDate}</span></td>
  //           <td class="anm_status">${anmStatus}</td>
  //           <td class="anm_edit">
  //             <button type="button" class="d-none d-sm-inline-block btn p-0" name="update" data-bs-toggle="modal" data-bs-target="#staticBackdrop">修改</button>
  //             / 
  //             <button type="button" name="delete_one" class="d-none d-sm-inline-block btn p-0">刪除</button>
  //           </td>
  //         </tr>
  //         `;
  //         $("tbody").prepend(html);
  //         $("#title_set").val("");
  //         CKEDITOR.instances.content_editor.setData("");
  //         $("#start_set").val("");
  //         $("#end_set").val("");
  //         $("#noEnd").prop("checked", false);
  //         $("#end_set").removeAttr("disabled");
  //         $("#type_set").prop("selectedIndex", 0);
  //         $("#order_set").prop("selectedIndex", 0);
  //       },
  //       complete: function(){
  //         $("#cancel").click();
  //         $("#title_set").siblings("span").text("請輸入公告標題");
  //         $("#title_set").siblings("span").addClass("warning");;
  //         $(".anm_content_set").siblings("span").text("請輸入公告內文");
  //         $(".anm_content_set").siblings("span").addClass("warning");
  //         $(".start_set_warn").text("請選擇公告日期");
  //         $(".start_set_warn").addClass("warning");
  //         $(".end_set_warn").text("請選擇公告下架日期");
  //         $(".end_set_warn").addClass("warning");
  //         $(".type_set_warn").text("請選擇公告類型");
  //         $(".type_set_warn").addClass("warning");
  //         $(".order_set_warn").text("請選擇公告順序");
  //         $(".order_set_warn").addClass("warning");
  //       }
  //     });
  //   }
  //   else{
  //     alert("請填寫公告相關資訊");
  //   }
  // });

  // 修改公告
  $(document).on("click", "#anm_list button[name='update']", function(){
    $("#staticBackdropLabel").text("修改公告");
    $(".modal-body").find("span").removeClass("warning");
    $(".modal-body").find("span.warn").text("");
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
    var anmStartDate = $(this).closest("td").siblings(".anm_date").find("span[name='anm_startdate']").text();
    var anmEndDate = $(this).closest("td").siblings(".anm_date").find("span[name='anm_enddate']").text();
    var anmTypeId = $(this).closest("td").siblings(".anm_type").text();
    if(anmEndDate === "不下架") {
      anmEndDate = "1970/1/1";
    }

    $.ajax({
      url: "announcement/searchAnm",           // 資料請求的網址
      type: "POST",                  // GET | POST | PUT | DELETE | PATCH
      data: JSON.stringify({              // 將物件資料(不用雙引號) 傳送到指定的 url
        "anmTitle": anmTitle,
        "anmStartDate": anmStartDate,
        "anmEndDate": anmEndDate,
        "anmType": anmTypeId
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
          start_set = new Date($("#start_set").val()).toLocaleDateString("zh-TW");
          end_set = new Date($("#end_set").val()).toLocaleDateString("zh-TW");
          type_set = $("#type_set option:selected").text();
          order_set = $("#order_set option:selected").val();
          var today = the_day.toLocaleDateString("en-CA");
          $("#start_set").attr("min", today);
          $("#end_set").attr("min", new Date($("#start_set").val()).toLocaleDateString("en-CA"));
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
            end_set = "1970/1/1";
          }
        }
        
        //修改_送出
        $("#update").on("click", function(){
          if(!$("span.warn").hasClass("warning")) {
            $.ajax({
              url: "announcement/update",     // 資料請求的網址
              type: "PATCH",                  // GET | POST | PUT | DELETE | PATCH
              data: JSON.stringify({              // 將物件資料(不用雙引號) 傳送到指定的 url
                "anmId": anmId,
                "anmTitle": title_set,
                "anmContent": content_set,
                "anmStartDate": start_set,
                "anmEndDate": end_set,
                "anmType": type_set,
                "anmOrderId": order_set
              }),                          // 將物件資料(不用雙引號) 傳送到指定的 url  
              contentType: "application/json; charset=utf-8",
              dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
              success: function(response){      // request 成功取得回應後執行
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
              complete: function(){      // request 完成之後執行(在 success / error 事件之後執行)
                $("#cancel").click();
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
                $("#title_set").siblings("span").text("請輸入公告標題");
                $("#title_set").siblings("span").addClass("warning");;
                $(".anm_content_set").siblings("span").text("請輸入公告內文");
                $(".anm_content_set").siblings("span").addClass("warning");
                $(".start_set_warn").text("請選擇公告日期");
                $(".start_set_warn").addClass("warning");
                $(".end_set_warn").text("請選擇公告下架日期");
                $(".end_set_warn").addClass("warning");
                $(".type_set_warn").text("請選擇公告類型");
                $(".type_set_warn").addClass("warning");
                $(".order_set_warn").text("請選擇公告順序");
                $(".order_set_warn").addClass("warning");
              }
            });
          }
          else{
            alert("請填寫公告相關資訊");
          }
        });
      }
    });
  });
});