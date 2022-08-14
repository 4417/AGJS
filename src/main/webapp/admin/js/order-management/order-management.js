
const url = "http://localhost:8081/AGJS/admin/order/";
const func = {
		"Checktype": "type/", "ItemSearch": "item/", "create": "create/",
		"Search": "search/", "Update": "update/"
};
const mode = {
		"Journey": "journeyItem", "DateRange": "date",
		"StatusName": "status", "Room": "roomItem", "order": "odr"
};


var order_table = $('#dataTable_order');


/* 手風琴(下拉式)資訊欄: 顯示訂單明細與行程明細 */
function format(d) {
	// `d` is the original data object for the row
	return (
		`<dl class="row">
     		<dt class="col-sm-3">房間總額</dt>
     		<dd class="col-sm-9">${d.orderRoomPrice}</dd>
     	 	<dt class="col-sm-3">行程總額</dt>
  			<dd class="col-sm-9">${d.journeyItemPrice ? d.journeyItemPrice: 0}</dd>
     	 	<dt class="col-sm-3">訂單備註</dt>
  			<dd class="col-sm-9" word-wrap:break-word>${d.orderRemark　? d.orderRemark : ''}</dd>
	  		<dt class="col-sm-9">訂房明細</dt>
	  		<dd class="col-sm-9" id="room${d.salesOrderHeaderId}"></dd>
	  		<dt class="col-sm-9">行程明細</dt>
	  		<dd class="col-sm-9" id="journey${d.salesOrderHeaderId}"></dd>
  		</dl>
 
  		
  		`
	);
}

//  		<dd class="col-sm-9">
//	  		<dt class="col-sm-9">行程明細</dt>
//	  		<table id="journeyTable">
//					<tr>
//						<th scope="col"></th>
//						<th scope="col">行程名稱</th>
//						<th scope="col">行程日期</th>
//						<th scope="col">成人數量</th>
//						<th scope="col">小孩數量</th>
//					</tr>
//				<tbody id="journey${d.salesOrderHeaderId}>
//				</tbody>
//					<tr>
//						<td></td>
//						<td></td>
//						<td></td>
//						<td></td>
//						<td></td>
//					</tr>
//				</table>
//				</dd>



//網站分享
//https://stackoverflow.com/questions/71965631/i-cant-load-data-to-datatable-with-ajax
//https://datatables.net/manual/ajax
//https://ithelp.ithome.com.tw/articles/10272813
//https://ithelp.ithome.com.tw/articles/10230169

//initialize my dataTables

$(document).ready(function() {
	
	var orderTable = $('#dataTable_order').DataTable({
	
		language: {
			url: "https://cdn.datatables.net/plug-ins/1.11.3/i18n/zh_Hant.json"
		},
		ajax: {
			"url": url + func.Search + mode.order,
			"type": "POST",
			"dataSrc": "",
		},
		lengthMenu: [10, 15, 30],
		autoWidth: false,
		//			buttons: ['copy', 'excel', 'pdf'],
		columns: [
		 	{
			 	className: 'dt-control',
			 	data: null,
			 	render: function(data, type, row, meta) {
			 		return '<i class="fa-solid fa-circle-info"></i>'
			 	},
			 	orderable: false
			},
 			{ data: "salesOrderHeaderId" },
 			{ data: "userName" },
 			{ data: "orderStartDate" },
 			{ data: "orderEndDate" },
 			{ data: "createDate" },
 			{ data: "orderChangeDate" },
 			{ data: "salesOrderStatus" },
			//				{ data: "roomPrice" },
			//				{ data: "journeyPrice" },
			//				{ data: "orderRemark", orderable: false},
			{
 				data: null, title: "修改",
 				render: function(data, type, row, meta) {
 					return '<button type="button" class="edit-btn" id=btn_' + meta.row + ' onclick="edit(this) " data-toggle="modal"data-target="#exampleModalCenter"><i class="fa-solid fa-pen-to-square"></i></button> '
 				},
				orderable: false
			}
		],
		rowId: {
			function(a) {
				return 'id_' + a.uid;
			}
		},
		columnDefs: [
			{
				targets: '_all',//全部攔
				className: 'text-center'
			}
		],
		 select: {
        selector:'td:not(:first-child)',
        style:    'os'
    }
		//change the format
		//			"dom": `<'row'<'col-sm-12 col-md-6'l><'col-sm-12 col-md-5'f>>
		//            		<'row'<'col-sm-12'tr>>
		//            		<'row'<'col-sm-12 col-md-7'p>>`
	});



	// 表格第一欄位的 i 圖示開關
	$('#dataTable_order tbody').on('click', 'td.dt-control', function() {
		var tr = $(this).closest('tr');
		var row = orderTable.row(tr);
		var data = row.data();
		if (row.child.isShown()) {
			// This row is already open - close it
			row.child.hide();
			tr.removeClass('shown');
		} else {
			// Open this row
			row.child(format(row.data())).show();
			tr.addClass('shown');
			getRoomDetails(data);
			getJourneyDetails(data);
		}
	});

	function getJourneyDetails(data) {
		var j_url = url + func.Search + mode.Journey;
		fetch(j_url, {
			method: "POST",
			headers: {'Content-Type': 'application/json; charset=utf-8'},
			body: JSON.stringify({salesOrderHeaderId: data.salesOrderHeaderId})
        })
		.then((res) => {
			return res.json();
		})
		.then((res) => {
			//針對每個回傳物件跑迴圈
			var index = 1;
			$.each(res, function(index, item) {
				let list_html = "";
				list_html += `<li>${res[index].journeyName　? res[index].journeyName : ''} ，成人共 ${res[index].adults　? res[index].adults : 0} 位  兒童共 ${res[index].children　? res[index].children : 0} 位 ，行程日期: ${res[index].journeyDate　? res[index].journeyDate : ''}</li>`;
				$(`#journey${data.salesOrderHeaderId}`).append(list_html);
				index ++;
				});
				})
		.catch((error) => { console.log(error); })
	};

	
	function getRoomDetails(data) {
		var r_url = url + func.Search + mode.Room;
		fetch(r_url, {
			method: "POST",
			headers: {'Content-Type': 'application/json; charset=utf-8'},
			body: JSON.stringify({salesOrderHeaderId: data.salesOrderHeaderId})
        })
		.then((res) => {
			return res.json();
		})
		.then((res) => {
			console.log(res);
			//針對每個回傳物件跑迴圈
			$.each(res, function(index, item) {
				let list_html = "";
				list_html += `<li>${res[index].roomName} 共 ${res[index].orderRoomQuantity　? res[index].orderRoomQuantity : 0} 間， 訂房價格為 ${res[index].orderRoomPrice　? res[index].orderRoomPrice : 0} 元</li>`;
				$(`#room${data.salesOrderHeaderId}`).append(list_html);
				});
				})
		.catch((error) => { console.log(error); })
	};
	

});




// 點選編輯表格內的訂單

$('#dataTable_order').on('click', '.edit-btn', function(e) {

	var id = $(this).attr("id").match(/\d+/)[0];
	var data = $('#dataTable_order').DataTable().row(id).data();

	edit(data);

});

//"編輯"函式帶入訂單入住日期至彈窗
function edit(data) {

	let orgStrDate = data.orderStartDate;
	let orgEndDate = data.orderEndDate;

	$('input.date-original').val(orgStrDate + ' - ' + orgEndDate);

	console.log("order data = ");
	console.log(data);

	//彈窗 確認修改並傳送電子郵件，待完成
	$('form.update-oder').on('submit', function(e) {
		e.preventDefault();
		var odrId = data.salesOrderHeaderId;
		formData = $(this).serializeObject();

		let range = formData.datefilter.trim().split(' ');

		formData["salesOrderHeaderId"] = odrId;
		formData["startDate"] = range[0];
		formData["endDate"] = range[2];
		//驗證

		//按下送出編輯
		$.ajax({
			url: url + func.Update + mode.order,
			type: "PATCH",
			dataType: "json",
			data: JSON.stringify({
				"salesOrderHeaderId": formData.salesOrderHeaderId,
				"startDate": formData.startDate,
				"endDate": formData.endDate,
				"status": formData.status
			}),
			contentType: "application/json; charset=utf-8",
			success: function(response) {
				console.log("傳送成功!");
				console.log(response);
				$("div#exampleModalCenter-add").modal('hide');
			},
			//			error: function(result) {
			//				console.log("傳送失敗!");
			//				console.log(result);
			//			},
			complete: function() {
				formData = null;
			}
		});
	});

	//	$('.datefilter').val('');
}


// 彈窗表格 serializeObject轉換為物件，轉成json再送出到後端。
$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name] != undefined) {
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
	//	url: "http://localhost:8081/AGJS/admin/order/status",
	url: url + func.Search + mode.StatusName,
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
								<input id="cbox${num}" type="checkbox" class="cboxType" name="status" value=${content.salesOrderStatus}
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
	function(settings, searchData, index, rowData, counter) {
		var positions = $('input:checkbox[name="status"]:checked').map(function() {
			return this.value;
		}).get();

		for (let i = 0; i < positions.length; i++) {
			const pos = positions[i];
			if (searchData[7].indexOf(pos) === -1) {
				return false;
			}
		}

		return true;
	},
	function(settings, data, dataIndex) {
		//搜尋邏輯 7/2(min) - 7/4(mix)有住房的訂單，未完成
		let range = $('input[name="datesearch"]').val().trim().split(' ');

		var min = moment(range[0], 'YYYY-MM-DD', true).isValid() ?
			moment(range[0], 'YYYY-MM-DD', true).unix() :
			null;
		var max = moment(range[2], 'YYYY-MM-DD', true).isValid() ?
			moment(range[2], 'YYYY-MM-DD', true).unix() :
			null;

		var strDate = new Date(data[2], { format: 'YYYY-MM-DD' });
		var endDate = new Date(data[3], { format: 'YYYY-MM-DD' });

		if (
			(min === null && max === null) ||
			//            ( min === null && strDate <= max ) ||
			//            ( min <= endDate   && max === null ) ||
			(min <= endDate && strDate <= max)
		) {
			return true;
		}
		return false;
	});


//入住日期區間搜尋，待完成
$('input[name="datesearch"]').on('change', function() {
	console.log("改到了");
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

		$('input[name="datesearch"]').on('change', function() {
			$('#dataTable_order').DataTable().draw();
		});

	});

	$('input[name="datesearch"]').on('cancel.daterangepicker', function(ev, picker) {
		$(this).val('');
	});




	//修改日期欄位的datefilter
	$('input[name="datefilter"]').daterangepicker({
		autoUpdateInput: false, //(true/false) Indicates whether the date range picker should automatically update the value of the <input> element it's attached to at initialization and when the selected dates change.
		locale: {
			cancelLabel: 'Clear',
			format: 'YYYY-MM-DD',
			//			minDate: today
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


$('#exampleModalCenter').on('show.bs.modal', function(e) {

	console.log(e);
	// do something...
})






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

