$(document).ready(function() {
    $('#dataTable_order').DataTable( {
        language: {
           url: "https://cdn.datatables.net/plug-ins/1.11.3/i18n/zh_Hant.json"
        },
        ajax: {
			url: "http://localhost:8081/AGJS/admin/order/search",
			dataSrc: ''
		},
        columns:[
			{"data": "salesOrderHeaderId"},
			{"data": "userId"},
			{"data": "orderStartDate"},
			{"data": "orderEndDate"},
			{"data": "createDate"},
			{"data": "orderChangeDate"},
			{"data": "salesOrderStatusId"},
			{"data": "roomPrice"},
			{"data": "journeyPrice"},
			{"data": "orderRemark"},
		]
    } );
} );


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
//	data: {
//		"salesOrderStatusId":"salesOrderStatusId",
//		"salesOrderStatus":"salesOrderStatus"
//	},
	dataType: "json",
	success: function(data) {

		console.log(data);
		var num = 1;

		//sessionStorage 設定：
		sessionStorage.clear();
		sessionStorage.ssType = JSON.stringify(data);

		typeBlock2.empty();

		$.each(data, function(index, content) {
			let list_html = `<p>
                                    <input type="checkbox" class="cboxType" value=${content.salesOrderStatusId}>
                                    <label for="cbox${num}">${content.salesOrderStatus}</label>
                             </p>`;

			typeBlock2.prepend(list_html);
			typeArr.push(content.salesOrderStatus);
			num++;
		
		});
	}
})

		//初始化訂單列表，直接顯示所有訂單
//$.ajax({
//	// contentType: "application/json; charset=utf-8",
//	url: "http://localhost:8081/AGJS/admin/order/search",
//	type: "POST",
//	dataType: "json",
//	success: function(data) {
//
//		var btn_id = 0;
//		// var arr = [];
//		console.log(data.length);
//
//		if (data.length != 0) {
//
//			//清空表格
//			$(".jr-select-tbody").empty();
//
//			$.each(data, function(index, content) {
//				
//				let list_html = `<tr id="${btn_id}">
//                                        <td>${content.salesOrderHeaderId}</td>
//                                        <td>${content.userId}</td>
//                                        <td>${content.orderStartDate}</td>
//                                        <td>${content.orderEndDate}</td>
//                                        <td>${content.createDate}</td>
//                                        <td>${content.orderChangeDate}</td>
//                                        <td>${content.salesOrderStatusId}</td>
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
//				$(".jr-select-tbody").prepend(list_html);
//				const jsonData = JSON.stringify(content);
//				console.log("json:" + jsonData);
//				sessionStorage.setItem(`${btn_id}`, jsonData);
//				btn_id++;
//
//			});
//		} else {
//
//			alert("沒有資料");
//		}
//	}})

// 日期選擇

$(function() {

	$('input[name="datefilter"]').daterangepicker({
		autoUpdateInput: false,
		locale: {
			cancelLabel: 'Clear'
		}
	});

	$('input[name="datefilter"]').on('apply.daterangepicker', function(ev, picker) {
		$(this).val(picker.startDate.format('MM/DD/YYYY') + ' - ' + picker.endDate.format('MM/DD/YYYY'));
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

	$.ajax({
		// contentType: "application/json; charset=utf-8",
		url: "http://localhost:8081/AGJS/admin/order/search",
		type: "POST",
		dataType: "json",
		success: function(data) {

			var btn_id = 0;
			

			if (data.length != 0) {

				//清空表格
				$(".jr-select-tbody").empty();

				$.each(data, function(index, content) {
			
					let statusName = typeArr[(content.salesOrderStatusId -1)];
					
					list_count += 1;
					let list_html = `<tr id="${btn_id}">
                                        <td>${content.salesOrderHeaderId}</td>
                                        <td>${content.userId}</td>
                                        <td>${content.orderStartDate}</td>
                                        <td>${content.orderEndDate}</td>
                                        <td>${content.createDate}</td>
                                        <td>${content.orderChangeDate}</td>
                                        <td>${statusName}</td>
                                        <td>${content.roomPrice}</td>
                                        <td>${content.journeyPrice}</td>
                                        <td>${content.orderRemark}</td>
                                        <td><button type="button" class="edit-btn" id="${btn_id}"  
                                                onclick="edit(this) " data-toggle="modal"
                                                data-target="#exampleModalCenter">編輯</button></td>
                                    </tr>`;




					$(".jr-select-tbody").prepend(list_html);
					const jsonData = JSON.stringify(content);
					sessionStorage.setItem(`${btn_id}`, jsonData);
					
					orderJson.data = jsonData;
					
					btn_id++;

				});
			} else {

				alert("沒有資料");
			}

			$(".fb-count").text(list_count);
		},
		error: function(result) {
			alert("提交失敗！");
			$(".fb-count").text('0');
			console.log(result);
		}
	})

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




//點擊 "編輯"
function edit(item) {

	console.log("id=" + $(item).attr('id'));
	let temp_id = $(item).attr('id');
	var jr_temp = JSON.parse(sessionStorage.getItem(temp_id));
	let journeyName = jr_temp.journeyName;
	let journeyType = jr_temp.journeyType;
	let journeyPrice = jr_temp.journeyPrice;
	let journeyPriceChild = jr_temp.journeyPriceChild;
	let applyLimit = jr_temp.applyLimit;
	let launched = jr_temp.launched;
	console.log(journeyName);
	$(".jr-name").val(journeyName);
	$(".jr-price").val(journeyPrice);
	$(".jr-price-ch").val(journeyPriceChild);
	$(".jr-limit").val(applyLimit);

	$.each(typeArr, function(index, value) {

		let html_list;
		if (journeyType === `${value}`) {

			html_list = `<option selected>${value}</option>`;
		} else {
			html_list = `<option >${value}</option>`;
		}
		console.log(`${index}: ${value}`);
		$(".jr-type").prepend(html_list);
	});

	if (launched === true) {
		$(".jr-launched").prepend(`<option selected>上架</option>`);
		$(".jr-launched").prepend(`<option >下架</option>`);
	} else {
		$(".jr-launched").prepend(`<option selected>下架</option>`);
		$(".jr-launched").prepend(`<option >上架</option>`);
	}

}


$('#exampleModalCenter').on('show.bs.modal', function(e) {

	console.log(e);
	// do something...
})

//  編輯彈窗
$("button.add-btn").on("click", function() {

	console.log("修改");

	var jrType = sessionStorage.getItem("ssType");
	console.log(jrType.journeyTypeId);

	// $.each(jrType, function (index, content) {

	//     console.log(content.journeyTypeId + "," + content.journeyTypeName);

	// });

	// let list_html = `<option>1</option>`;

	// $(".form-control").prepend(list_html);

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
	$('form.update-jr').on('submit', function(e) {
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



//===========Sophie
// updateList
function updateItemList(data) {

	let tr_id = 0;
	console.log("更新訂單列表");
	console.log(data.length);

	if (data.length != 0) {

		//清空表格
		itemList.empty();

		$.each(data, function(index, content) {

			console.log("get select talble");


			let list_html = `<tr id="${tr_id}">
                                 
                                        <td>${content.salesOrderHeaderId}</td>
                                        <td>${content.userId}</td>
                                        <td>${content.orderStartDate}</td>
                                        <td>${content.orderEndDate}</td>
                                        <td>${content.createDate}</td>
                                        <td>${content.orderChangeDate}</td>
                                        <td>${content.salesOrderStatusId}</td>
                                        <td>${content.roomPrice}</td>
                                        <td>${content.journeyPrice}</td>
                                        <td>${content.orderRemark}</td>
                                        <td><button type="button" class="edit-btn" id="${btn_id}"  
                                                onclick="edit(this) " data-toggle="modal"
                                                data-target="#exampleModalCenter">編輯</button></td>
                                    </tr>`;

			itemList.prepend(list_html);

			tr_id += 1;

		});

	} else {

		alert("沒有資料");
	}
}
