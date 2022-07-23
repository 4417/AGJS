const checked = $('.checkbox1').prop('checked');
const roomStyle = document.querySelector('#roomStyle');
$(function () {
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
  $('#roomAddBtn').on('click', () => {
    // alert('...');
    //取每個表格的值
    const roomName = $('#exampleFormControlInput1').val();
    const roomDescribe = $('#exampleFormControlTextarea1').val();
    const roomTypeSelect = $('#roomTypeSelect').val();
    const roomPrice = $('#roomPrice').val();
    const roomCount = $('#roomCount').val();
    const bedTypeSelect = $('#bedTypeSelect').val();
    const roomFacilityCheck = $('input[name="roomFacility[]"]:checked');
    let roomFacility = '';
    roomFacilityCheck.each(function () {
      roomFacility += $(this).val() + ',';
    });

    console.log('roomName :' + roomName);
    console.log('roomDescribe :' + roomDescribe);
    console.log('roomTypeSelect :' + roomTypeSelect);
    console.log('roomPrice :' + roomPrice);
    console.log('roomCount :' + roomCount);
    console.log('bedTypeSelect :' + bedTypeSelect);
    console.log('roomFacility :' + roomFacility);

    //增加到表格內(尚未完成)
  });
  //全選旁的刪除
  //   const checkboxChecked = $('.checkbox1').onclick();
  $('#boxDelete').on('click', function () {
    // alert('...');
    let r = confirm('確認移除？');
    if (r) {
      //刪除已勾選的checkbox
      $('.item1 :checked').parent().parent().remove();
    }
    //列中的刪除
  });

  /**
   * init
   */
  async function init() {
    let html = '';
    const data = await ajax(url + api.style);
    data.forEach((e, i) => {
      html += addRoom(
        e.roomName,
        e.roomQuantity,
        e.bedType,
        e.roomType,
        e.orderRoomPrice
      );
    });
    roomStyle.innerHTML += html;
  }
  init();
});
// ROOM_STYLE_ID, ROOM_NAME, ROOM_QUANTITY, BED_TYPE, ROOM_TYPE, ORDER_ROOM_PRICE, ROOM_DESCRIPTION
function addRoom(name, quantity, bedType, roomType, price) {
  return `
  <tr class="item1">
  <td>
    <input
    type="checkbox"
    id="roomItem1"
    class="checkbox1"
    value="item1"
  />
  </td>
  <td>${name}</td>
  <td>${bedType}</td>
  <td>${roomType}</td>
  <td>${price}</td>
  <td>${quantity}</td>
  <td>
    <button type="button" class="btn btn-link ">修改</button> /
    <button type="button" class="btn btn-link ">刪除</button>
  </td>
</tr>`;
}

async function ajax(api) {
  return (data = await fetch(api).then((res) => res.json()));
}

const url = 'http://localhost:8081/AGJS';

const api = {
  style: '/roomStyle',
  management: '/roomManagement',
};
