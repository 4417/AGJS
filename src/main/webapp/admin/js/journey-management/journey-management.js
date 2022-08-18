
const date_format = "YYYY-MM-DD";
const url = "jrn/";
const func = {
    "Checktype": "type/", "ItemSearch": "item/", "add": "add/", "Item": "item/",
    "Search": "search/", "Edit": "update/", "DeleteJrn": "delete"
};
const mode = {
    "SohID": "sohid.search", "DateRange": "date.search",
    "TypeName": "typename.item", "KeyWord": "keyword", "journey": "jrn",
    "TypeID": "type", "UpdateDate": "date.update"

};
//存放行程類型
var typeArr = [];
//存放查詢到的行程列表
var jrnArr = [];
//存放查詢到的行程訂單
var jrnItemArr = [];
//
var del_jrn_Id = []

const itemList = $("tbody.jr-select-tbody");
const typeList = $(".jr-type-tbody");
const typeListCount = $(".fb-count");
const typeBlock1 = $("div.type .type-select");
const typeBlock2 = $("div.type2 .type-select");
const editImg = $("ul.picture_list_edit");
const addImg = $("ul.picture_list_add");
const editImgNew = $("ul.picture_list_edit_new");
const jrTypeList = $("select.jr-type");
const jrtypeOption = $(".jr-type")
const optionType = $("option#type_option");
const selectOpt = $('select#selectOpt');

const editFile = $("the_file_eidt");
const addFile = $("the_file_add");

//彈窗欄位
const jrnname = $("input.jr-name");
const type = $("select.tr-type");
const price = $("input.jr-price");
const pricechild = $("input.jr-price-ch");
const limite = $("input.jr-limit");
const info = $("textarea.form-control");
const file = $("#the_file")
const jrnlaunched = $(".jr-launched");
//base64 圖片字串
var img_base64;
//列表id
var list_id;
var list_item_id;


//初始查詢checkbox 行程種類
$.ajax({
    url: url + func.Checktype,
    type: "GET",
    dataType: "json",
    success: function (data) {

        let obj = eval(data);
        console.log("journeyType 取得");
        console.log(obj.length);
        var num = 1;

        //sessionStorage 設定：
        sessionStorage.clear();
        sessionStorage.ssType = JSON.stringify(data);

        typeBlock1.empty();
        typeBlock2.empty();

        $.each(data, function (index, content) {

            let list_html = `<p>
                                <input type="checkbox" class="cboxType" value=${content.typeId}>
                                <label for="cbox${num}">${content.typeName}</label>
                            </p>`;

            typeBlock1.prepend(list_html);
            typeBlock2.prepend(list_html);

            console.log("add" + content.typeName);
            let obj = {};
            obj.typeName = content.typeName;
            obj.typeId = content.typeId;
            typeArr.push(obj);
            // typeArr.push(content.typeId);
            num++;
        });
    }
})

//============================= 日期選擇 區間 ==============================
var date = {};

$(function () {

    $('input[name="datefilter"]').daterangepicker({
        autoUpdateInput: false,
        locale: {
            cancelLabel: 'Clear'
        }
    });

    $('input[name="datefilter"]').on('apply.daterangepicker', function (ev, picker) {
        $(this).val(picker.startDate.format(date_format) + ' - ' + picker.endDate.format(date_format));

        // date.splice(0, date.length);
        date.startDate = picker.startDate.format(date_format);
        date.endDate = picker.endDate.format(date_format);

    });

    $('input[name="datefilter"]').on('cancel.daterangepicker', function (ev, picker) {
        $(this).val('');
    });

});

//========================== click 日期搜尋 行程訂單 =========================
$("button.date_btn").on("click", function () {

    console.log("date search");
    console.log(`${date}`);
    var check = false;

    if (`${date}` === "") {
        alert('請選擇日期')
    } else {
        check = true;
    }
    let jsonData = JSON.stringify(date);
    console.log("提交" + jsonData);

    if (check) {
        $.ajax({
            url: url + func.ItemSearch + mode.DateRange,
            contentType: "application/json; charset=utf-8",
            data: jsonData,
            type: "post",
            dataType: "json",
            success: function (data) {

                updateItemList(data);

            },
            error: function (result) {
                alert("提交失敗！");
                console.log(result);
            }
        })
    }

})

//======================== click 訂單編號搜尋 行程訂單 =======================

$("button.soh_id_btn").on("click", function () {

    console.log("soh_id search");

    let obj = {};
    obj.sohId = $('#soh_id').val().trim();
    let jsonData = JSON.stringify(obj);
    console.log("提交" + jsonData);

    $.ajax({
        contentType: "application/json; charset=utf-8",
        url: url + func.ItemSearch + mode.SohID,
        data: jsonData,
        type: "post",
        dataType: "json",
        success: function (data) {

            updateItemList(data);

        },
        error: function (result) {
            alert("提交失敗！");
            console.log(result);
        }
    })

})
//============================= 行程訂單 類型篩選 ============================
$("div.type button.type-select-btn").on("click", function () {

    console.log("行程訂單 類型篩選");
    let check = false;
    //用陣列存放checkbox選擇的資料
    var arr = [];

    //走訪checkbox
    $("div.type input.cboxType:checked").each(function (i, item) {
        arr.push($(item).val());
    });

    if (arr.length != 0) {
        check = true;
    } else {
        alert('請勾選行程類型');
    }

    //把checkbox資料 JourneyTypeId轉成json
    var jsonData = JSON.stringify(arr);
    console.log("提交" + jsonData);

    if (check) {
        $.ajax({
            contentType: "application/json; charset=utf-8",
            url: url + func.ItemSearch + mode.TypeID,
            data: jsonData,
            type: "POST",
            dataType: "json",
            success: function (data) {

                updateItemList(data);
            },
            error: function (result) {
                alert("提交失敗！");
                emptyJrnArr();
                console.log(result);
            }
        })
    }
})

//========================= 更新行程訂單列表 ========================
function updateItemList(data) {

    let tr_id = 0;
    console.log("data=" + data);

    // $(document).ready(function () {
    //     $('#dataTable').DataTable();
    // });

    // $('#dataTable').DataTable({
    //     language: {
    //         url: "https://cdn.datatables.net/plug-ins/1.11.3/i18n/zh_Hant.json"
    //     },

    // 'data': data,
    // 'columns': [
    //     { data: "journeyItemId" },
    //     { data: "sohId" },
    //     { data: "journeyName" },
    //     { data: "adults" },
    //     { data: "children" },
    //     { data: "dateString" },
    //     {
    //         data: null,
    //         render: function (data, type, row) {
    //             return `<button type="button" class="edit-btn" id="${tr_id}"  
    //             onclick = "itemEdit(this) " data - toggle="modal"
    //             data - target="#exampleModalCenter-item-edit" > 修改</button >`;
    //         }
    //     }
    // ],
    // columnDefs: [
    //     {
    //         targets: '_all',
    //         className: 'text-center'
    //     }
    // ]
    // })

    if (data.length != 0) {

        //清空列表
        emptyJrnItemArr();
        //清空表格
        itemList.empty();

        $.each(data, function (index, content) {

            console.log("get select talble");

            let list_html = `<tr id="${tr_id}">
                    <td>${content.journeyItemId}</td>
                    <td>${content.sohId}</td>
                    <td>${content.journeyName}</td>
                    <td>${content.adults}</td>
                    <td>${content.children}</td>
                    <td>${content.dateString}</td>
                    <td><button type="button" class="edit-btn" id="${tr_id}"  
                    onclick="itemEdit(this) " data-toggle="modal"
                    data-target="#exampleModalCenter-item-edit">修改</button></td>
                    </tr>`;

            itemList.prepend(list_html);

            tr_id += 1;

            let obj = {};
            obj.journeyItemId = `${content.journeyItemId}`;
            obj.sohId = `${content.sohId}`;
            obj.journeyName = `${content.journeyName}`;
            obj.adults = `${content.adults}`;
            obj.children = `${content.children}`;
            obj.dateString = `${content.dateString}`;
            console.log(obj);
            jrnItemArr.push(obj);

        });

    } else {

        alert("沒有資料");
    }
}

//======   將彈窗Form 資料轉換為serializeObject物件，轉json再送出到後端。 ======

jQuery.fn.serializeObject = function () {
    var formData = {};
    var formArray = this.serializeArray();

    for (var i = 0, n = formArray.length; i < n; ++i)
        formData[formArray[i].name] = (formArray[i].value).trim();

    return formData;
};

//=================== 行程訂單 "編輯" =====================
function itemEdit(item) {

    console.log("行程訂單 編輯");
    //取得按鈕id 對應 tr-id
    // list_item_id = $(item).attr('id');
    list_item_id = $(item).attr('id');

    $(".jrl_item_id").val(jrnItemArr[list_item_id].journeyItemId);
    $(".jrl_id").val(jrnItemArr[list_item_id].sohId);
    $(".jrl_name").val(jrnItemArr[list_item_id].journeyName);
    $(".jrl-adults").val(jrnItemArr[list_item_id].adults);
    $(".jrl-child").val(jrnItemArr[list_item_id].children);
    $(".jrl-date").val(jrnItemArr[list_item_id].dateString);

}


//========================== 提交 行程訂單"編輯" ==========================
function jrlistUpdate(item) {

    console.log("提交行程單修改");
    var formData = {};
    formData = $('form.jr-list-update').serializeObject();
    formData.journeyItemId = jrnItemArr[list_item_id].journeyItemId;

    console.log('JSON: ' + JSON.stringify(formData));

    $.ajax({
        contentType: "application/json; charset=utf-8",
        url: url + func.Item + mode.UpdateDate,
        data: JSON.stringify(formData),
        type: "POST",
        dataType: "json",
        success: function (data) {

            console.log("修改資料送出");

            alert("修改成功");
        },
        error: function (result) {
            alert("提交失敗！");
            console.log(result);
        }
    })
}


//======================= 修改 訂單日期 單日 =======================
$(function () {

    $('input[name="updateDate"]').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
        format: date_format,
        minYear: 2022,
        maxYear: parseInt(moment().format(date_format), 10),
        locale: {
            format: date_format
        }
    }, function (start, end, label) {
    });

    $('input[name="updateDate"]').on('apply.daterangepicker', function (ev, picker) {
        // $(this).val(picker.startDate.format(date_format) + ' - ' + picker.endDate.format(date_format));
    });

    $('input[name="updateDate"]').on('cancel.daterangepicker', function (ev, picker) {
        $(this).val('');
    });

});



//========================== 行程 類型篩選=============================
$("div.type2 button.type-select-btn-2").on("click", function () {

    console.log("行程 類型篩選");
    let check = false;
    //用陣列存放checkbox選擇的資料
    var arr = [];

    //走訪checkbox
    $("div.type2 input.cboxType:checked").each(function (i, item) {
        arr.push($(item).val());
    });

    if (arr.length != 0) {
        check = true;
    } else {
        alert('請勾選行程類型');
    }

    //把checkbox資料 JourneyTypeId轉成json
    var jsonData = JSON.stringify(arr);
    console.log("提交" + jsonData);

    if (check) {
        $.ajax({
            contentType: "application/json; charset=utf-8",
            url: url + func.Search + mode.TypeID,
            data: jsonData,
            type: "POST",
            dataType: "json",
            success: function (data) {

                updateTypeList(data);
            },
            error: function (result) {
                alert("提交失敗！");
                console.log(result);
            }
        })
    }
})

//=========================== 清空陣列資料 ===========================
function emptyJrnArr() {

    while (jrnArr.length) {
        jrnArr.pop();
    }
    console.log('清空JrnArr');
}


function emptyJrnItemArr() {

    while (jrnItemArr.length) {
        jrnItemArr.pop();
    }
    console.log('jrnItemArr');
}

function emptyJrnDelArr() {

    while (del_jrn_Id.length) {
        del_jrn_Id.pop();
    }
    console.log('emptyJrnDelArr');
}

//=============================== 更新搜尋的 行程列表 ===============================
function updateTypeList(data) {

    console.log('更新搜尋的 行程列表');
    console.log("DATA==" + data);
    console.log("JSON==" + JSON.stringify(data));
    emptyJrnArr();
    let btn_id = 0;

    if (data.length != 0) {
        //清空表格
        typeList.empty();

        $.each(data, function (index, content) {

            let list_html = `<tr id="${btn_id}">
                                <td><input type="checkbox" class="jrtype_box" value="${btn_id}"></td>
                                <td>${btn_id}</td>
                                <td>${content.journeyName}</td>
                                <td>${content.journeyTypeName}</td>
                                <td>${content.journeyPrice}</td>
                                <td>${content.journeyPriceChild}</td>
                                <td>${content.applyLimit}</td>
                                <td>${content.launched}</td>
                                <td><button type="button" class="edit-btn" id="${btn_id}"  
                                onclick="jrEdit(this) " data-toggle="modal"
                                data-target="#exampleModalCenter-jr-edit">編輯</button></td>
                                </tr>`;

            typeList.prepend(list_html);

            let obj = {};
            obj.journeyId = `${content.journeyId}`;
            obj.journeyName = `${content.journeyName}`;
            obj.journeyTypeName = `${content.journeyTypeName}`;
            obj.journeyPrice = `${content.journeyPrice}`;
            obj.journeyPriceChild = `${content.journeyPriceChild}`;
            obj.applyLimit = `${content.applyLimit}`;
            obj.info = `${content.info}`;
            obj.launched = `${content.launched}`;
            obj.journeyPicture = `${content.journeyPicture}`;

            console.log(obj);
            jrnArr.push(obj);
            btn_id++;

        });
    } else {

        alert("沒有資料");
    }
    $.each(jrnArr, function (index, content) {
        console.log("arr====");
        console.log(content.journeyName);
    })


    typeListCount.text(btn_id);
}

//=============================== click搜尋  用行程名稱 搜尋行程 ================================= 

$("button.task_add").on("click", function () {

    console.log("task_add search");

    let obj = {};
    obj.searchKeyword = ($('.txt_box').val()).trim();
    let jsonData = JSON.stringify(obj);

    console.log("提交" + jsonData);

    $.ajax({
        url: url + func.Search + mode.KeyWord,
        contentType: "application/json; charset=utf-8",
        data: jsonData,
        type: "post",
        dataType: "json",
        success: function (data) {

            updateTypeList(data);

        },
        error: function (result) {
            alert("提交失敗！");
            console.log(result);
        }
    })
})

function emptyInputFile() {

    jrnname.val("");
    file.val("");
    price.val("");
    pricechild.val("");
    limite.val("");
    info.val("");
    optionType.remove();
    selectOpt.empty();

    console.log('清空行程彈窗欄位');
}


//=====================編輯行程 彈窗======================
function jrEdit(item) {

    console.log("jredit");
    list_id = $(item).attr('id');
    console.log("id=" + list_id);

    emptyInputFile();

    editImgNew.empty();
    editImg.empty();
    img_base64 = null;
    console.log("jrnarr==" + jrnArr[list_id]);
    jrnname.val(jrnArr[list_id].journeyName);
    price.val(jrnArr[list_id].journeyPrice);
    pricechild.val(jrnArr[list_id].journeyPriceChild);
    limite.val(jrnArr[list_id].applyLimit);
    info.val(jrnArr[list_id].info);

    if (jrnArr[list_id].launched === "true") {
        jrnlaunched.append(`<option selected>上架</option>`);
        jrnlaunched.append(`<option >下架</option>`);
    } else {
        jrnlaunched.append(`<option selected>下架</option>`);
        jrnlaunched.append(`<option >上架</option>`);
    }

    //引入行程類型
    $.each(typeArr, function (key, value) {

        if (jrnArr[list_id].journeyTypeName === `${value.typeName}`) {
            html_list = `<option id="type_option" selected>${value.typeName}</option>`;
        } else {
            html_list = `<option id="type_option">${value.typeName}</option>`;
        }
        jrTypeList.append(html_list);
    });

    //引入圖片
    // var that = this; // 將 this 用 that 來替代，底下程式會用到
    // let reader = new FileReader(); // 用來讀取檔案
    let img_64 = jrnArr[list_id].journeyPicture;
    console.log("get");
    let img64_html = `<img class="preview" src="data:image/png;base64,${img_64}" width="200" height="150">`;
    editImg.append(img64_html);


}


//======================= 編輯行程 上傳圖片==========================

$('#the_file_eidt').on("change", function (e) {

    var that = this; // 將 this 用 that 來替代，底下程式會用到
    let reader = new FileReader(); // 用來讀取檔案

    reader.readAsDataURL(this.files[0]); // 讀取檔案

    reader.addEventListener("load", function (e) {

        // 加進節點
        editImgNew.append(`
                <li class="type_img">
                  <img class="preview" src="` + reader.result + `" width="250" height="150">
                  <div class="progressbar"><span class="progress" style="width: 0%;"></span></div>
                </li>
              `);

        var result = reader.result;
        var arr = result.split(',');
        img_base64 = arr[1];
    })
})


//===================== 提交 編輯行程 =======================
function jrEditAdd(item) {

    console.log("jrEditAdd");
    var formData = $('form.jr-TypeEdit').serializeObject();

    formData.journeyPicture = img_base64;
    console.log("img_base64==" + img_base64);
    //加入journeyId
    formData.journeyId = jrnArr[list_id].journeyId;

    console.log('JSON: ' + JSON.stringify(formData));

    var check = false;
    var name = $("input.jr-name");
    var price = $("input.jr-price");
    var pricechild = $("input.jr-price-ch");
    var limite = $("input.jr-limit");

    //=====================空白驗證======================
    if (formData.journeyName === "") {
        alert("行程名稱不能空白");
        name.focus();
    } else if (formData.journeyPrice === "") {
        alert("行程價格不能空白");
        price.focus();
    } else if (formData.journeyPriceChild === "") {
        alert("行程價格(兒童)不能空白");
        pricechild.focus();
    } else if (formData.applyLimit === "") {
        alert("報名人數上限不能空白");
        limite.focus();
    } else {
        check = true;
    }

    //=====================ajax編輯行程======================
    if (check) {
        $.ajax({

            url: url + func.Edit + mode.journey,
            data: JSON.stringify(formData),
            contentType: "application/json; charset=utf-8",
            type: "POST",
            dataType: "json",
            success: function (data) {

                console.log("Edit");

                alert("修改成功");
                $("div#exampleModalCenter-add").modal('hide');
            },
            error: function (result) {
                alert("提交失敗！");
                console.log(result);
            }
        })
    }
}

$('#exampleModalCenter').on('show.bs.modal', function (e) {

    console.log(e);
    // do something...
})

//=====================  新增行程 彈窗=========================
function jrAddInit(item) {

    console.log("jrAddInit");
    emptyInputFile()
    addImg.empty();
    img_base64 = null;

    jrtypeOption.html("");

    let html_list;
    //引入行程類型
    $.each(typeArr, function (key, value) {

        console.log(value.typeName);
        html_list = `<option id="type_option">${value.typeName}</option>`;
        jrTypeList.prepend(html_list);

    });
    jrTypeList.prepend(html_list);

}

var img_base64;

//=======================新增行程 上傳圖片==========================
$("#the_file_add").on("change", function (e) {


    var that = this; // 將 this 用 that 來替代，底下程式會用到
    let reader = new FileReader(); // 用來讀取檔案

    reader.readAsDataURL(this.files[0]); // 讀取檔案

    reader.addEventListener("load", function (e) {
        console.log("load 事件");

        console.log(reader.result);

        // 加進節點
        addImg.append(`
                <li class="type_img">
                  <img class="preview" src="` + reader.result + `" width="200" height="150">
                  <div class="progressbar"><span class="progress" style="width: 0%;"></span></div>
                </li>
              `);
        console.log("ddddd");

        // 這裡實際將檔案傳送出去：ajax 技術
        // let journeyPicture = new FormData();
        // journeyPicture.append('the_file', that.files[0]);
        // console.log(journeyPicture);
        // const base64Str = btoa(event.target.result);

        var result = reader.result;
        var arr = result.split(',');
        img_base64 = arr[1];
        console.log("img_base64==" + JSON.stringify({ file: img_base64 }));

    })

})


//=====================提交 新增行程======================

function jrAdd(item) {

    console.log("jrAdd");
    var formData = $('form.jr-add').serializeObject();

    formData.journeyPicture = img_base64;

    console.log("formData ==" + formData);
    console.log('JSON: ' + JSON.stringify(formData));

    var check = false;
    var name = $("input.jr-name");
    var price = $("input.jr-price");
    var pricechild = $("input.jr-price-ch");
    var limite = $("input.jr-limit");

    //===================空白驗證===================
    if (formData.journeyName === "") {
        alert("行程名稱不能空白");
        name.focus();
        // name.css("background-color", "#FFFFCC");
        // name.css("border", " 1px solid red");
    } else if (formData.journeyPrice === "") {
        alert("行程價格不能空白");
        price.focus();
    } else if (formData.journeyPriceChild === "") {
        alert("行程價格(兒童)不能空白");
        pricechild.focus();
    } else if (formData.applyLimit === "") {
        alert("報名人數上限不能空白");
        limite.focus();
    } else {
        check = true;
    }

    //=================提交新增行程==================
    if (check) {
        $.ajax({

            url: url + func.add,
            data: JSON.stringify(formData),
            contentType: "application/json; charset=utf-8",
            type: "POST",
            dataType: "json",
            success: function (data) {

                console.log("add");

                alert("修改成功");
                $("div#exampleModalCenter-add").modal('hide');
            },
            error: function (result) {
                alert("提交失敗！");
                console.log(result);
            }
        })
    }
}

//============================ 刪除勾選行程 =============================
$("button.delete-btn").on("click", function () {

    console.log("刪除勾選行程");

    emptyJrnDelArr();
    let check = false;
    //用陣列存放checkbox選擇的資料
    let arr = [];
    // $(`.jr-type-tbody>tr#111`).remove();

    //走訪checkbox
    $("tbody.jr-type-tbody input.jrtype_box:checked").each(function (i, item) {
        let indexx = $(item).val();
        del_jrn_Id.push(indexx);
        arr.push(jrnArr[indexx].journeyId);
    });

    $("input.jrtype_box:checked").each(function (i, item) {
        console.log($(item).val());
    });

    if (arr.length != 0) {
        check = true;
    } else {
        alert('請勾選欲刪除行程');
    }

    //把checkbox資料 JourneyTypeId轉成json
    var jsonData = JSON.stringify(arr);
    console.log("提交" + jsonData);

    if (check) {
        $.ajax({
            contentType: "application/json; charset=utf-8",
            url: url + func.DeleteJrn,
            data: jsonData,
            type: "POST",
            dataType: "json",
            success: function (data) {

                alert('刪除成功');

                $.each(del_jrn_Id, function (key, value) {

                    console.log(value);
                    // $(".jr-type-tbody>tr#111").remove();
                    $(`.jr-type-tbody>tr#${value}`).remove();
                    console.log('del=' + `.jr-type-tbody>tr#${value}`);

                });
            },
            error: function (result) {
                alert("提交失敗！");
                console.log(result);
            }
        })
    }
})


