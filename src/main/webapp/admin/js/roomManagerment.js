// const BASE_URL = 'http://localhost:8081/AGJS';

const API_URL = {
  style: 'roomStyle',
  update: 'roomStyle/update',
  management: 'roomManagement',
  record: 'roomUsedRecord',
  recordUpdate: 'roomUsedRecord/update',
};

const checked = $('.checkbox1').prop('checked');
const roomStyle = document.querySelector('#roomStyle');
const roomUsedRecordTableEl = document.querySelector('#roomUsedRecordTable');
let a = null,
  curId,
  roomId;
let dateInput =
  ' <input type="date" class="dateInput form-control form-control-sm-3"  style="width: 120px; display:inline;font-family:inherit;font-weight: 400;color: #6e707e;background-color: #fff;background-clip: padding-box; border: 1px solid #d1d3e2;transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;">';
$(function () {
  //修改
  //先讓後端資料顯現前端
  $(document).on('click', '.edit', openEditRoomModal);

  // 按出修正按鈕;
  $('#roomEdiBtn').on('click', editRoom);

  //全選checkbox
  $('#checkAll').on('click', function () {
    // alert('...');
    // console.log(this);
    // console.log($('.checkbox1').prop('checked'));
    // console.log(checked);
    if ($('.checkbox1').prop('checked')) {
      $('.checkbox1').prop('checked', false);
      return;
    }
    console.log(2);
    $('.checkbox1').prop('checked', true); //把所有的核取方框的property都變成勾選
  });
  //新增房型
  //送出新增鈕綁定
  $('#roomAddBtn').on('click', createRoom);
  //全選旁的刪除
  $('#boxDelete').on('click', deleteRoom);

  //篩選房型
  $('#selectRoom').on('click', getRoomByDateAndStyle);
});
//關房
$(document).on('click', '.closeRoom', function () {
  let roomId = $(this).parent().parent().prop('id');
  let btn = $(this).text();
  console.log(btn);
  if (btn == '關房') {
    // console.log(roomId);
    updateDate(roomId);
  }
  if (btn == '儲存') {
    saveDate(roomId);
  }
});

async function refreshRoomStyle() {
  //這邊要清空目前所有的資料
  roomStyle.innerHTML = '';
  await init();
  // 然後再重新執行一次 init()
}

// ROOM_STYLE_ID, ROOM_NAME, ROOM_QUANTITY, BED_TYPE, ROOM_TYPE, ORDER_ROOM_PRICE, ROOM_DESCRIPTION
function addRoom({
  roomName,
  roomQuantity,
  bedType,
  roomType,
  orderRoomPrice,
  roomStyleId,
}) {
  return `
  <tr class="item1">
  <td>
    <input
    type="checkbox"
    data-id="${roomStyleId}" 
    class="checkbox1"
    value="item1"
  />
  </td>
  <td>${roomName}</td>
  <td>${bedType}</td>
  <td>${roomType}</td>
  <td>${orderRoomPrice}</td>
  <td>${roomQuantity}</td>
  <td>
  <button type="button" class="btn btn-link edit" data-id="${roomStyleId}" data-toggle="modal"
  data-target=".bd-example-modal-lg-2 " 
  data-whatever="@mdo">編輯</button> 
  </td>
</tr>`;
}
//刪除房型
async function deleteRoom() {
  // alert('...');
  let r = confirm('確認移除？');
  if (r) {
    //刪除已勾選的checkbox
    // $('.item1 :checked').parent().parent().remove();
    //蒐集資料
    let checkboxArr = [];
    $('.checkbox1:checkbox:checked').each((index, element) => {
      console.log(element);
      checkboxArr.push($(element).data('id'));
    });
    console.log(checkboxArr);
    await ajax(API_URL.style, 'DELETE', checkboxArr);
    //當刪除之後就會refresh
    await refreshRoomStyle();
  }
}

function roomUsedRecord({
  roomStyleId,
  roomId,
  orderStartDate,
  orderEndDate,
  roomName,
  userName,
  source,
}) {
  return `
  <tr class="downTable" id="${roomId}" >
     <td style="vertical-align:middle;" >${roomId}${
    orderStartDate
      ? ''
      : '<button type="button" id="roomControlBtn_' +
        roomId +
        '" class="btn btn-link closeRoom" >關房</button>'
  }</td>
     <td style="vertical-align:middle;">${roomName}</td>
     <td style="vertical-align:middle;">${userName}</td>
     <td style="vertical-align:middle;">
     ${orderStartDate ? orderStartDate : ''}
     <div style="display: inline-block;">
     <input type="date" id="${
       'startDate_' + roomId
     }" class="dateInput form-control form-control-sm-3"  style="width: 120px; display: none;font-family:inherit;font-weight: 400;color: #6e707e;background-color: #fff;background-clip: padding-box; border: 1px solid #d1d3e2;transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;"></div>
     </td>
     <td style="vertical-align:middle;">
     ${orderEndDate ? orderEndDate : ''}
     <div style="display: inline-block;">
     <input type="date" id="${
       'endDate_' + roomId
     }" class="dateInput form-control form-control-sm-3"  style="width: 120px;display: none;font-family:inherit;font-weight: 400;color: #6e707e;background-color: #fff;background-clip: padding-box; border: 1px solid #d1d3e2;transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;"></div>
     </td>
  </tr>
  `;
}

//新增房型方法
async function createRoom() {
  // console.log('我是按鈕開頭');
  // alert('...');

  //取每個表格的值
  const roomName = $('#exampleFormControlInput1').val();
  const roomDescribe = $('#exampleFormControlTextarea1').val();
  const roomTypeSelect = $('#roomTypeSelect1').val();
  const roomPrice = $('#roomPrice1').val();
  const roomCount = $('#roomCount1').val();
  const bedTypeSelect = $('#bedTypeSelect1').val();
  const roomFacilityCheck = $('input[name="roomFacility[]"]:checked');

  //  取圖片的值
  $('.room-file-input').on('change', function () {
    $('#roomFile').val();
  });

  // console.log(roomFacilityCheck);
  //將物件放入陣列內
  let roomFacility = [];
  roomFacilityCheck.each(function () {
    roomFacility.push($(this).val());
  });

  console.log('roomName :' + roomName);
  console.log('roomDescribe :' + roomDescribe);
  console.log('roomTypeSelect :' + roomTypeSelect);
  console.log('roomPrice :' + roomPrice);
  console.log('roomCount :' + roomCount);
  console.log('bedTypeSelect :' + bedTypeSelect);
  console.log('roomFacility :' + roomFacility);
  //ajax打api &拿到api的資料

  const roomStyleData = await ajax(API_URL.style, 'POST', {
    roomName: roomName,
    roomQuantity: roomCount,
    bedType: bedTypeSelect,
    roomType: roomTypeSelect,
    orderRoomPrice: roomPrice,
    roomDescription: roomDescribe,
    roomFacilitiesIdList: roomFacility,
  });

  //清空所有值
  $('#exampleFormControlInput1').val('');
  $('#exampleFormControlTextarea1').val('');
  $('#roomTypeSelect1').val('');
  $('#roomPrice1').val('');
  $('#roomCount1').val('');
  $('#bedTypeSelect1').val('');
  if (roomFacilityCheck) {
    roomFacilityCheck.each(function () {
      $(this).prop('checked', false);
    });
  }
  //當新增之後就會refresh
  await refreshRoomStyle();
}
// 取得時間房型
async function getRoomByDateAndStyle() {
  // alert('.....');
  let startDate = $('#searchStart').val();
  let roomStyleName = $('input:radio[name=roomStyleName]:checked').val();

  console.log('startDate =' + startDate);
  console.log('roomStyleName =' + roomStyleName);

  const selectedRoomData = await ajax(API_URL.record, 'POST', {
    orderStartDate: startDate,
    roomName: roomStyleName,
  });

  $('#roomUsedRecordTable').html('');
  let roomRecord = '';
  selectedRoomData.data.forEach(console.log);
  selectedRoomData.data.forEach((e, i) => {
    roomRecord += e ? roomUsedRecord(e) : '';
  });

  $('#roomUsedRecordTable').html(roomRecord);
  $('#searchStart').val('');
  if ($('input:radio[name=roomStyleName]:checked')) {
    $('input:radio[name=roomStyleName]').prop('checked', false);
  }
}
// 修改房
async function editRoom() {
  console.log(curId);

  const roomName = $('#roomname').val();
  const roomDescribe = $('#roomdescription').val();
  const roomTypeSelect = $('#roomTypeSelect').val();
  const roomPrice = $('#roomPrice').val();
  const roomCount = $('#roomCount').val();
  const bedTypeSelect = $('#bedTypeSelect').val();
  const roomFacilityCheck = $('input[name="roomFacility1[]"]:checked');
  let roomFacility = [];
  roomFacilityCheck.each(function () {
    roomFacility.push($(this).val());
  });
  console.log('roomName=' + roomName);
  console.log('roomDescribe=' + roomDescribe);
  console.log('roomTypeSelect=' + roomTypeSelect);
  console.log('roomPrice=' + roomPrice);
  console.log('roomCount=' + roomCount);
  console.log('bedTypeSelect=' + bedTypeSelect);
  console.log('roomFacility=' + roomFacility);

  await ajax(API_URL.update, 'POST', {
    roomStyleId: curId,
    roomName: roomName,
    roomQuantity: roomCount,
    bedType: bedTypeSelect,
    roomType: roomTypeSelect,
    orderRoomPrice: roomPrice,
    roomDescription: roomDescribe,
    roomFacilitiesIdList: roomFacility,
  });

  //當修改之後就會refresh
  await refreshRoomStyle();
}
//將後端彈窗資料放進Modal
async function openEditRoomModal() {
  // alert('....');
  // console.log($(this));
  // console.log($(this).data('id'));
  let id = $(this).data('id');
  curId = id;

  const url = API_URL.style + '/' + id; //這邊我傾向用concat寫 但算了沒差lol
  const roomstyle = await ajax(url, 'GET', null); // GET方法不能塞body所以給null

  console.log(`roomstyle: ${roomstyle}`);
  $('#roomname').val(roomstyle.roomName);
  // console.log($('#roomname').val());
  $('#roomdescription').val(roomstyle.roomDescription);
  $('#roomTypeSelect').val(roomstyle.roomType);
  $('#roomPrice').val(roomstyle.orderRoomPrice);
  $('#roomCount').val(roomstyle.roomQuantity);
  $('#bedTypeSelect').val(roomstyle.bedType);
  console.log(roomstyle.roomFacilitiesIdList);

  $('input[name="roomFacility1[]"]').each((i, e) => {
    let checkbox = $(e);
    checkbox.prop('checked', false);

    let list = roomstyle.roomFacilitiesIdList;
    for (let i = 0; i < list.length; i++) {
      const roomFacilitiesId = list[i];
      console.log(checkbox.val() + ' vs ' + roomFacilitiesId);
      if (checkbox.val() == roomFacilitiesId) {
        checkbox.prop('checked', true);
        console.log(checkbox.val() + ' checked');
      }
    }
  });

  for (let index = 0; index < roomstyle.roomFacilitiesIdList.length; index++) {
    const roomFacilitiesId = roomstyle.roomFacilitiesIdList[index];
    console.log(roomFacilitiesId);
  }
}
//關房後的選擇日期

async function updateDate(roomId) {
  console.log(roomId);
  let startDateId = 'startDate_' + roomId;
  let endDateId = 'endDate_' + roomId;
  let roomControlBtn = 'roomControlBtn_' + roomId;
  let r = confirm('確認要關房?');
  if (r) {
    $('#' + startDateId).show();
    $('#' + endDateId).show();
    //將關房按紐改成儲存
    $('#' + roomControlBtn).text('儲存');
  }
}
//關房後的儲存日期
async function saveDate(roomId) {
  console.log(roomId);
  let startDateId = 'startDate_' + roomId;
  let endDateId = 'endDate_' + roomId;
  let btnId = 'roomControlBtn_' + roomId;
  let startDateData = $('#' + startDateId).val();
  let endDateData = $('#' + endDateId).val();
  roomControlBtn_202;
  console.log(startDateData);
  console.log(endDateData);
  const saveData = await ajax(API_URL.recordUpdate, 'POST', {
    roomId: roomId,
    orderStartDate: startDateData,
    orderEndDate: endDateData,
  });
  $('#' + startDateId).hide();
  $('#' + endDateId).hide();
  $('#' + startDateId)
    .parent()
    .parent()
    .text(startDateData);
  $('#' + endDateId)
    .parent()
    .parent()
    .text(endDateData);
  $('#' + btnId).hide();
}

/**
 * init
 */
async function init() {
  let roomStyleHtml = '';
  let roomRecordHtml = '';
  const roomStyleData = await ajax(API_URL.style);
  const roomRecordData = await ajax(API_URL.record);

  roomStyleData.forEach((e, i) => {
    roomStyleHtml += addRoom(e);
  });
  roomRecordData.forEach((e, i) => {
    roomRecordHtml += roomUsedRecord(e);
  });
  roomStyle.innerHTML += roomStyleHtml;
  roomUsedRecordTableEl.innerHTML += roomRecordHtml;
}
init(); //這個只會做一次 除非我手動=
async function ajax(url, method, data) {
  //http常用的方法有四種 get/post/delete/put
  switch (method) {
    case 'GET':
      return await fetch(url).then((res) => res.json());
    case 'POST':
      return await postData(url, method, data).then((res) => res.json());
    case 'DELETE':
      return await postData(url, method, data);
  }
  // 因為api有四種
  // get/post/delete/put 所以我把這四種功能都寫在這個function裡面，之後你要呼叫資料就直接找這個ajax function
  return (data = await fetch(url).then((res) => res.json()));
}
// 接著就把postData 跟ajax接上
async function postData(url, method, data) {
  return fetch(url, {
    method: method, //然後delete 跟 post 只要改這裡就會打到不一樣的mapping ，所以我把它寫在參數內讓他去修改, data 也是一樣的處理方式
    body: JSON.stringify(data),

    headers: {
      'Content-Type': 'application/json; charset=utf-8',
    },
  });
}
