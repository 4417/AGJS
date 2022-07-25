//----------------變數區----------------
var start_date;
var anm_status;
var type_list;
var the_day = new Date();

//--------------------------------------

$(window).on("load", function () {
  // 搜尋
  $("#keyword").on("keyup", function (e) {
    if (e.which == 13) {
      $("#search").click();
    }
  });
  $("#search").on("click", function () {

    $.ajax({
      url: "announcement/keyword",      // 資料請求的網址
      type: "POST",                     // GET | POST | PUT | DELETE | PATCH
      data: {
        keyword: $("#keyword").val().trim(),
      },
      dataType: "json",                 // 預期會接收到回傳資料的格式： json | xml | html
      success: function (request) {     // request 成功取得回應後執行
        // 清空舊有的篩選結果
        $("div[name='filter_area'] .card-body").nextAll().remove();
        // 印出回傳結果
        if (request.length != 0) {
          var header_html = `
            <h6 class="mt-2 ml-4 font-weight-bold text-or">篩選結果如下：</h6>
            <table class="result_list">
              <tr>
                <th class="result_type">公告類型</th>
                <th class="result_title">公告標題</th>
                <th class="result_date">公告日期</th>
                <th class="result_edit">編輯</th>
              </tr>
            </table>
          `;

          $("div[name='filter_area'] .card-body").after(header_html);

          for (var i = 0; i < request.length; i++) {
            var anmType;
            var anmTitle = request[i].anmTitle;
            var anmStartDate = request[i].anmStartDate;
            var anmStartDate = new Date(anmStartDate).toLocaleDateString("zh-TW");
            var anmEndDate;
            if(request[i].anmTypeId == 1){
              anmType = "住房優惠"
            }
            else if(request[i].anmTypeId == 2){
              anmType = "餐飲優惠"
            }
            else if(request[i].anmTypeId == 3){
              anmType = "其他"
            }

            if(request[i].anmEndDate === null) {
              anmEndDate = "不下架";
            }
            else{
              anmEndDate = request[i].anmEndDate;
              anmEndDate = new Date(anmEndDate).toLocaleDateString("zh-TW");
            }
            var list_html = `
            <tr>
              <td class="result_type">${anmType}</td>
                <td class="result_title">${anmTitle}</td>
                <td class="result_date">
                  <span name="result_startdate">${anmStartDate}1</span>
                  ~ 
                  <span name="result_enddate">${anmEndDate}</span>
                </td>
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
      },
      complete: function (xhr) {
        // request 完成之後執行(在 success / error 事件之後執行)
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
  $("#list_all").on("click", function () {
    $(".anm_check").prop("checked", this.checked);
  });

  $(".anm_check").on("click", function () {
    $("#list_all").prop(
      "checked",
      $(".anm_check").length == $(".anm_check:checked").length
    );
  });

  // 清單_刪除
  $("#delete_list").on("click", function () {
    // 如果有勾選公告
    if ($(".anm_check:checked").length != 0) {
      let check = confirm("確定刪除公告？");
      if (check) {
        let delete_list = $(".anm_check:checked").closest("tr");
        delete_list.remove();
      }
    }
  });

  //刪除公告(單筆)
  $("button[name='delete_one']").on("click", function () {
    let check = confirm("確定刪除公告？");
    if (check) {
      $(this).closest("tr").remove();
    }
  });

  // 新增公告

  // 公告日期限制
  var today = the_day.toLocaleDateString("en-CA");
  $("#start_set").attr("min", today);
  $("#start_set").on("change", function () {
    var start_set = $("#start_set").val();
    $("#end_set").attr("min", start_set);
  });

  // 下架日期
  var end_set;
  $("#end_set").attr("min", today);
  $("#end_set").on("change", function () {
    end_set = $("#end_set").val();
    console.log(end_set);
  });
  $("#noEnd").on("click", function () {
    if ($("#noEnd").prop("checked")) {
      end_set = $("#noEnd").val();
      console.log(end_set);
      $("#end_set").attr("disabled", "true");
      $("#end_set").val("");
    }
  });

  // 新增公告_點擊新增
  $("#submit").on("click", function () {
    var html = `
                    <tr>
                        <td class="checkbox"><input type="checkbox" class="anm_check"></td>
                        <td class="anm_type">${type_set}</td>
                        <td class="anm_title">${title_set}</td>
                        <td class="anm_date"><span name="result_startdate">${start_set}</span> ~ <span name="result_enddate">${end_set}</span></td>
                        <td class="anm_edit">
                            <button type="button" class="d-none d-sm-inline-block btn p-0" data-bs-toggle="modal" data-bs-target="#staticBackdrop">修改</button>
                            / 
                            <button type="button" name="delete_one" class="d-none d-sm-inline-block btn p-0">刪除</button>
                            </span>
                        </td>
                    </tr>
                `;

    $(".list_header").after(html);

    $("#title_set").val("");
    CKEDITOR.instances.content_editor.setData("");
    $("#start_set").val("");
    $("#end_set").val("");
    $("#type_set").prop("selectedIndex", 0);
    $("#order_set").prop("selectedIndex", 0);

    $.ajax({
      url: "announcement/insert", // 資料請求的網址
      type: "POST", // GET | POST | PUT | DELETE | PATCH
      data: JSON.stringify({
        setTitle: $("#title_set").val(),
        setContent: CKEDITOR.instances.content_editor.getData(),
        setStartDate: $("#start_set").val(),
        setEndDate: end_set,
        setType: $("#type_set option:selected").text(),
        setOrder: $("#order_set option:selected").val(),
      }), // 將物件資料(不用雙引號) 傳送到指定的 url
      dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
      success: function (data) {
        // request 成功取得回應後執行
        console.log(data);
      },
      complete: function (xhr) {
        // request 完成之後執行(在 success / error 事件之後執行)
        console.log(xhr);
        // 公告加入清單
      },
    });
  });
});

// 如果標題是空值
$("#title_set").on("blur", function () {
  if ($(title_set).val() == "") {
    $(this).prev("").text("*請輸入公告標題!");
  }
});
// 如果內文是空值
$("#content_editor").on("blur", function () {
  // console.log(this);
  if (content_set == "") {
    $(this).prev("").text("*請輸入公告內文!");
  }
});
