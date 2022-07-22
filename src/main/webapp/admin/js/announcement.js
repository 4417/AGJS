// 搜尋
$("#keyword").on("keyup", function(e){
    if(e.which == 13){
        $("#search").click();
    }
});
$("#search").on("click", function(){
    var keyword = $("#keyword").val().trim();
});

// 篩選_公告日期
$("input[name='start_date']").on("click", function() {
    let the_date = new Date();
    if(this.value == 0) {
        var start_date = the_date.toLocaleDateString();
        console.log(start_date);
    }
    if(this.value == 7) {
        the_date.setDate(the_date.getDate()-7);
        var start_date = the_date.toLocaleDateString();
        console.log(start_date);
    }
    else if(this.value == 30) {
        the_date.setMonth(the_date.getMonth()-1);
        var start_date = the_date.toLocaleDateString();
        console.log(start_date);
    }
    else{
        var start_date = $(this).siblings(".cust").val();
        console.log(start_date);
    }
});
$("input.cust[name='start_date']").on("click", function(){
    $(this).prev().click();
    $(this).on("change", function(){
        var start_date = $(this).val();
        console.log(start_date);
    });
});


// 篩選_下架日期
$("input[name='end_date']").on("click", function() {
    let the_date = new Date();
    if(this.value == 1) {
        var end_date = the_date.toLocaleDateString();
        console.log(end_date);
    }
    else if(this.value == 7) {
        the_date.setDate(the_date.getDate()-7);
        var end_date = the_date.toLocaleDateString();
        console.log(end_date);
    }
    else if(this.value == 30) {
        the_date.setMonth(the_date.getMonth()-1);
        var end_date = the_date.toLocaleDateString();
        console.log(end_date);
    }
    else if(this.value == 0) {
        var end_date = "";
        console.log(end_date);
    }
    else{
        var end_date = $(this).siblings(".cust").val();
        console.log(end_date);
    }
});
$("input.cust[name='end_date']").on("click", function(){
    $(this).prev().click();
    $(this).on("change", function(){
        var end_date = $(this).val();
        console.log(end_date);
    });
});

// 篩選_公告類型
$("input[name='anm_type']").on("click", function(){
    var type_list = new Array();
    $('input:checkbox:checked[name="anm_type"]').each(function(i) {
        type_list[i] = this.value; 
    });
    console.log(type_list);
});

// 篩選_清空選項
$("#btn_filter_clear").on("click", function(){
    var start_date = $("input[name='start_date']");
    var end_date = $("input[name='end_date']");
    var anm_type = $("input[name='anm_type']");

    start_date.prop('checked',false);
    start_date.val("");
    end_date.prop('checked',false);
    end_date.val("");
    anm_type.prop('checked',false);
    anm_type.val("");

    console.log(start_date.val());
    console.log(end_date.val());
    console.log(anm_type.val());
});


// 清單_全選
$("#list_all").on("click", function(){
    $(".anm_check").prop("checked", this.checked);
});

$(".anm_check").on("click", function(){
    $("#list_all").prop("checked", $(".anm_check").length == $(".anm_check:checked").length);
});

// 清單_刪除
$("#delete_list").on("click", function() {
    // 如果有勾選公告
    if($(".anm_check:checked").length != 0) {
        let check = confirm("確定刪除公告？");
        if (check) {
            let delete_list = $(".anm_check:checked").closest("div.anm");
            delete_list.remove();
        }
    }
});

//刪除公告(單筆)
$("#anm_list").on("click", "button[name='delete_one']", function() {
    let check = confirm("確定刪除公告？");
    if (check) {
        let delete_one = $(this).closest("div.anm");
        delete_one.remove();
    }
});

// 新增公告
$("#submit").on("click", function(){
    // 標題
    var title_set = $("#title_set").val();

    // 內文(取得編輯器的內容(html))
    var content_set = CKEDITOR.instances.content_editor.getData();
    console.log(content_set);

    // 公告日期
    let the_date = new Date();
    var start_set = $("#start_set").val();

    // 下架日期
    var end_set = $("#end_set").val();

    // 公告類型
    if($("#order_set").change()){
        var type_set = $("#type_set option:selected").text();
    };
    
    // 公告排序
    if($("#order_set").change()){
        var order_set = $("#order_set option:selected").val();
    }
    
    // 公告加入清單
    var html = `
    <div class="anm ml-3 py-3">
        <input type="checkbox" class="anm_check">
        <p class="m-0 anm_type">${type_set}</p>
        <p class="m-0 anm_title">${title_set}</p>
        <p class="m-0 anm_date">${start_set} ~ ${end_set}</p>
        <div class="m-0 anm_edit">
            <button type="button" class="d-none d-sm-inline-block btn p-0" data-bs-toggle="modal" data-bs-target="#staticBackdrop">修改</button>
            / 
            <button type="button" name="delete_one" class="d-none d-sm-inline-block btn p-0">刪除</button>
        </div>
    </div>
    `;

    $("#anm_list").prepend(html);

    $("#title_set").val("");
    CKEDITOR.instances.content_editor.setData("");
    $("#start_set").val("");
    $("#end_set").val("");
     $('#type_set').prop('selectedIndex', 0);
    $('#order_set').prop('selectedIndex', 0);


});

// 如果標題是空值
$("#title_set").on("blur", function(){
    if($(title_set).val() == ""){
        $(this).prev("").text("*請輸入公告標題!");
    }
});
// 如果內文是空值
$("#content_editor").on("blur", function(){
    // console.log(this);
    if(content_set == ""){
        $(this).prev("").text("*請輸入公告內文!");
    }
});


// 如果公告日期是空值
// 如果公告類型是空值>value = ""
// 如果公告順序是空值>value = ""




