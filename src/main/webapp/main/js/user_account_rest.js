//看20~77、487~490行
$(document).ready(function() {
	//===========datatable_AJAX：自動抓會員的所有訂單資料(巢狀AJAX)===============
	const url_3 = "order/search/byUser";
	fetch(url_3, {
		method: "POST",
		headers: {
			"Content-Type": "application/json; charset=utf-8",
		},
		body: JSON.stringify({}),
	})
		.then((response) => {
			return response.json();
		})
		.then((response) => {
			console.log(response[0].salesOrderHeaderId);
			$("#order_table").DataTable({
				language: {
					url: "https://cdn.datatables.net/plug-ins/1.11.3/i18n/zh_Hant.json",
				},
				data: response,

				columns: [
					{
						data: null,
						render: function(data, type, full, meta) {
							return meta.row + 1;
						},
					},
					{ data: "salesOrderHeaderId" },
					{ data: "restDate" },
					{ data: "restNum" },
					{
						data: null,
						render: function(data, type, row) {
							return data.roomPrice + data.journeyPrice;
						},
					},
					{
						data: null,
						render: function(data, type, row) {
							let id = data.salesOrderHeaderId;
							return (
								'<button type="button" class="btn btn-primary btn-checkItem" data-toggle="modal" data-target="#' +
								id +
								'" data-id="' +
								id +
								'">查看詳細</button>'
							);
						},
					},
				],
				columnDefs: [
					{
						targets: "_all",
						className: "text-center",
					},
				]
					.catch((error) => {
						console.log(error);
					})