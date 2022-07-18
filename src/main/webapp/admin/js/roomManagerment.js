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
