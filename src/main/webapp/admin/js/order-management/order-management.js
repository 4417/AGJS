


//網站分享
	//https://stackoverflow.com/questions/71965631/i-cant-load-data-to-datatable-with-ajax
	//https://datatables.net/manual/ajax
	//https://ithelp.ithome.com.tw/articles/10272813
	//https://ithelp.ithome.com.tw/articles/10230169
	
//initialize my dataTables


$(document).ready(function() {
	

	
});


$.ajax({
	url: "http://localhost:8081/AGJS/admin/order/search",
	type: "POST",
	dataType: "json",
	success: function(data) {

		$('#dataTable_order').DataTable({
			language: {
				url: "https://cdn.datatables.net/plug-ins/1.11.3/i18n/zh_Hant.json"
			},
			lengthMenu: [10, 15, 30],
			autoWidth: false,
			data: data,
			buttons: ['copy', 'excel', 'pdf'],
			columns: [
				{ data: "salesOrderHeaderId"},
				{ data: "userId"},
				{ data: "orderStartDate" },
				{ data: "orderEndDate" },
				{ data: "createDate" },
				{ data: "orderChangeDate" },
				{ data: "salesOrderStatusId" },
//				{ data: "roomPrice" },
//				{ data: "journeyPrice" },
				{ data: "orderRemark", orderable: false},
				{
					data: null, title: "修改",
					render: function(data, type, row, meta) {
						return '<button type="button" class="edit-btn" id=btn_' + meta.row + ' onclick="edit(this) " data-toggle="modal"data-target="#exampleModalCenter"><i class="fa-solid fa-pen-to-square"></i></button> '
					},
					orderable: false
				}
			],
				rowId:{function(a){
					return 'id_' + a.uid;
				}},
				columnDefs: [
					{
						targets: '_all',//全部攔
						className: 'text-center'
					}
				],
			//change the formate
//			"dom": `<'row'<'col-sm-12 col-md-6'l><'col-sm-12 col-md-5'f>>
//            		<'row'<'col-sm-12'tr>>
//            		<'row'<'col-sm-12 col-md-7'p>>`
			});
		},
		error: function(data) {
			alert(data.responseText);
		}
});

var order_table = $('#dataTable_order');


// 點選編輯表格內的訂單
    
    $('#dataTable_order').on('click', '.edit-btn', function(e){

    var id = $(this).attr("id").match(/\d+/)[0];
  	var data = $('#dataTable_order').DataTable().row( id ).data();
  	edit(data);
	
});

//"編輯"函式
function edit(data) {

	let orgStrDate = data.orderStartDate;
	let orgEndDate = data.orderEndDate;

	$('input.date-original').val(orgStrDate + ' - ' + orgEndDate);
	
//	$('.datefilter').val('');
}




//	let journeyType = jr_temp.journeyType;
//	let journeyPrice = jr_temp.journeyPrice;
//	let journeyPriceChild = jr_temp.journeyPriceChild;
//	let applyLimit = jr_temp.applyLimit;
//	let launched = jr_temp.launched;
//	console.log(journeyName);
//	$(".jr-name").val(journeyName);
//	$(".jr-price").val(journeyPrice);
//	$(".jr-price-ch").val(journeyPriceChild);
//	$(".jr-limit").val(applyLimit);


//checkbox filter


// //設定：
// sessionStorage.setItem("mycolor", "456");
// sessionStorage.mycolor = '456';
// //獲取：
// var $color = sessionStorage.getItem("mycolor");
// var $color = sessionStorage.mycolor
// var $color = sessionStorage.key(0);//獲取第一個鍵，按角標獲取
// var $color = sessionStorage.key("");//獲取最後一個鍵
// var $length = sessionStorage.length;//獲取資料的長度
// //刪除
// sessionStorage.removeItem("mycolor");
// //清空
// sessionStorage.clear();//將所有儲存的資料刪除

// //儲存

// sessionStorage.setItem('arr', JSON.stringify(ary))
// sessionStorage.setItem('json', JSON.stringify(json))

// //取值

// var ary = sessionStorage.getItem('arr')
// var json = sessionStorage.getItem('json')
// var array = JSON.parse('ary')
// var item = JSON.parse('json')




const typeBlock1 = $("div.type .type-select"); //訂單日期與訂單編號搜尋欄位
const typeBlock2 = $("div.type-select"); //訂單狀態搜尋欄位



//初始查詢checkbox 訂單狀態種類
var typeArr = [];
//抓取資料庫內的訂單狀態 並渲染至篩選列表中           
$.ajax({
	url: "http://localhost:8081/AGJS/admin/order/status",
	type: "GET",
	dataType: "json",
	success: function(data) {
		var num = 1;

		//sessionStorage 設定：
		sessionStorage.clear();
		sessionStorage.ssType = JSON.stringify(data);

		typeBlock2.empty();

		$.each(data, function(index, content) {
			let list_html = `<p>
								<input id="cbox${num}" type="checkbox" class="cboxType" name="status" value=${content.salesOrderStatusId}
								onchange="$('#dataTable_order').DataTable().draw()">
                                <label for="cbox${num}">  ${content.salesOrderStatus}</label>      
                             </p>`;

			typeBlock2.prepend(list_html);
			typeArr.push(content.salesOrderStatus);
			num++;

		});
	}
})

//篩選功能



$.fn.dataTable.ext.search.push(
    function( settings, searchData, index, rowData, counter ) {
      var positions = $('input:checkbox[name="status"]:checked').map(function() {
        return this.value;
      }).get();
   
      if (positions.length === 0) {
        return true;
      }
      
      if (positions.indexOf(searchData[6]) !== -1) {
        return true;
      }
      
      return false;
//    },
//    function( settings, data, dataIndex ) {
//        var min = minDate.val();
//        var max = maxDate.val();
//       
//        var date = new Date( data[4] );
// 
//        if (
//            ( min === null && max === null ) ||
//            ( min === null && date <= max ) ||
//            ( min <= date   && max === null ) ||
//            ( min <= date   && date <= max )
//        ) {
//            return true;
//        }
//        return false;
    });	


//入住日期區間搜尋，待完成
$('input[name="datesearch"]').on('change', function () {
	console.log($('.datesearch').val());
    $('#dataTable_order').DataTable().draw();
});



// 日期選擇器
$(function() {
	
	
	
	//搜尋日期欄位
	$('input[name="datesearch"]').daterangepicker({
		autoUpdateInput: false, //(true/false) Indicates whether the date range picker should automatically update the value of the <input> element it's attached to at initialization and when the selected dates change.
		locale: {
			cancelLabel: 'Clear',
			format: 'YYYY-MM-DD'
		}
	});
	
	$('input[name="datesearch"]').on('apply.daterangepicker', function(ev, picker) {
		$(this).val(picker.startDate.format('YYYY-MM-DD') + ' - ' + picker.endDate.format('YYYY-MM-DD'));
	});

	$('input[name="datesearch"]').on('cancel.daterangepicker', function(ev, picker) {
		$(this).val('');
	});
	
	var today = new Date().toString().split('T')[0];
	
	
	//修改日期欄位的datefilter
	$('input[name="datefilter"]').daterangepicker({
		autoUpdateInput: false, //(true/false) Indicates whether the date range picker should automatically update the value of the <input> element it's attached to at initialization and when the selected dates change.
		locale: {
			cancelLabel: 'Clear',
			format: 'YYYY-MM-DD',
			minDate: today
		}
	});

	$('input[name="datefilter"]').on('apply.daterangepicker', function(ev, picker) {
		$(this).val(picker.startDate.format('YYYY-MM-DD') + ' - ' + picker.endDate.format('YYYY-MM-DD'));
	});

	$('input[name="datefilter"]').on('cancel.daterangepicker', function(ev, picker) {
		$(this).val('');
	});

});


//text 欄位新增代辦事項

$("input.task_name").on("keyup", function(e) {
	if (e.which == 13) {//按下enter
		$("button.tack_add").click();
	}

});

$("button.task_add").on("click", function() {

	let task_text = ($("input.task_name").val()).trim();//濾掉空格
	console.log(task_text);

})




//按下 篩選按鈕
$("div.type button.type-select-btn").on("click", function() {

	console.log("div.type button.type-select-btn");

	//用set存放checkbox選到的value <= journeytypeID
	// var dataSet = new Set();

	//用陣列存放checkbox選擇的資料
	var arr = [];;

	//走訪checkbox
	$("div.type2 input.cboxType:checked").each(function(i, item) {

		// dataSet.add($(item).val());

		//用物件型態紀錄資料放進array
		let obj = {};
		obj.salesOrderStatusId = $(item).val();
		arr.push(obj);
	});

	//把checkbox資料 salesOrderStatusId轉成json
	const jsonData = JSON.stringify(arr);
	//console.log("提交salesOrderStatusId" + jsonData);

	// dataSet.forEach((item) => console.log('item', item));
	//將SET轉成JSON
	// const jsonData = JSON.stringify(Array.from(dataSet));
	// console.log(jsonData);

	// $.post("http://localhost:8081/AGJS4/JourneyController/*", function (jsonData) {
	//     console.log("post");
	// }, "json");


	var list_count = 0;

	//	$.ajax({
	//		// contentType: "application/json; charset=utf-8",
	//		url: "http://localhost:8081/AGJS/admin/order/search",
	//		type: "POST",
	//		dataType: "json",
	//		success: function(data) {
	//
	//			var btn_id = 0;
	//			
	//
	//			if (data.length != 0) {
	//
	//				//清空表格
	//				$(".jr-select-tbody").empty();
	//
	//				$.each(data, function(index, content) {
	//			
	//					let statusName = typeArr[(content.salesOrderStatusId -1)];
	//					
	//					list_count += 1;
	//					let list_html = `<tr id="${btn_id}">
	//                                        <td>${content.salesOrderHeaderId}</td>
	//                                        <td>${content.userId}</td>
	//                                        <td>${content.orderStartDate}</td>
	//                                        <td>${content.orderEndDate}</td>
	//                                        <td>${content.createDate}</td>
	//                                        <td>${content.orderChangeDate}</td>
	//                                        <td>${statusName}</td>
	//                                        <td>${content.roomPrice}</td>
	//                                        <td>${content.journeyPrice}</td>
	//                                        <td>${content.orderRemark}</td>
	//                                        <td><button type="button" class="edit-btn" id="${btn_id}"  
	//                                                onclick="edit(this) " data-toggle="modal"
	//                                                data-target="#exampleModalCenter">編輯</button></td>
	//                                    </tr>`;
	//
	//
	//
	//
	//					$(".jr-select-tbody").prepend(list_html);
	//					const jsonData = JSON.stringify(content);
	//					sessionStorage.setItem(`${btn_id}`, jsonData);
	//					
	//					
	//					
	//					btn_id++;
	//
	//				});
	//			} else {
	//
	//				alert("沒有資料");
	//			}
	//
	//			$(".fb-count").text(list_count);
	//		},
	//		error: function(result) {
	//			alert("提交失敗！");
	//			$(".fb-count").text('0');
	//			console.log(result);
	//		}
	//	})

	// fetch("http://localhost:8081/AGJS4/JourneyController/search", {

	//     method: 'POST',
	//     headers: {
	//         'Content-Type': 'application/json'
	//     },
	//     body: JSON.stringify({


	//     })
	// })

	// var _grid = document.getElementById("ctl00_Content1_GvMerge");

	// for (i = 1; i <= document.getElementById("HRecordCount").value; i++) {
	// }


	// if (document.getElementById('cboxType').checked) {
	//     $("#txtAge").show();
	// }

	// for (var i = 0; i < obj.length; i++) {
	//     console.log("journeyType: " + data.content.journeyType + " journeyTypeId: " + data.content.journeyTypeId);

	// }
	// let task_text = ($("input.task_name").val()).trim();//濾掉空格

})







$('#exampleModalCenter').on('show.bs.modal', function(e) {

	console.log(e);
	// do something...
})



// 彈窗表格 serializeObject轉換為物件，轉乘json再送出到後端。
$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};

$(function() {
	$('form.update-oder').on('submit', function(e) {
		e.preventDefault();

		var formData = $(this).serializeObject();
		console.log("formData ==" + formData);

		//走訪checkbox
		$("input.select_box:checked").each(function(i, item) {

			// dataSet.add($(item).val());

			//用物件型態紀錄資料放進array
			let obj = {};
			obj.journeyId = $(item).val();
			arr.push(obj);
		});
	});
});


$('#chk>input').click(function() {
	if ($(this).prop('checked')) {
		$('#chk>input:checkbox').prop('checked', false);
		$(this).prop('checked', true);
	}
});


$("button.btn btn-primary").on("click", function() {

	console.log("提交");

})

function typeMapping(id) {

	return 0;
}

