const checked = $('.checkbox1').prop('checked');
const roomStyle = document.querySelector('#roomStyle');
let a = null,
  curId;
$(function () {
  //修改
  //先讓後端資料顯現前端
  $(document).on('click', '.edit', function () {
    // alert('....');
    // console.log($(this));
    // console.log($(this).data('id'));
    let id = $(this).data('id');
    curId = id;

    fetch(url + api.style + '/' + id)
      .then(function (response) {
        return response.json();
      })
      .then(function (roomstyle) {
        console.log(roomstyle);
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

        for (
          let index = 0;
          index < roomstyle.roomFacilitiesIdList.length;
          index++
        ) {
          const roomFacilitiesId = roomstyle.roomFacilitiesIdList[index];
          console.log(roomFacilitiesId);
        }
      });
  });

  // 按出修正按鈕;
  $('#roomEdiBtn').on('click', function () {
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

    fetch(url + api.update, {
      method: 'POST',
      body: JSON.stringify({
        roomStyleId: curId,
        roomName: roomName,
        roomQuantity: roomCount,
        bedType: bedTypeSelect,
        roomType: roomTypeSelect,
        orderRoomPrice: roomPrice,
        roomDescription: roomDescribe,
        roomFacilitiesIdList: roomFacility,
      }),

      headers: {
        'Content-Type': 'application/json; charset=utf-8',
      },
    })
      .then((res) => {
        return res.json(); // 使用 json() 可以得到 json 物件
      })
      .then((result) => {
        console.log(result);
        location.reload(true);
      });
  });

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

    fetch(url + api.style, {
      method: 'POST',
      body:
        // encodeURI(
        JSON.stringify({
          roomName: roomName,
          roomQuantity: roomCount,
          bedType: bedTypeSelect,
          roomType: roomTypeSelect,
          orderRoomPrice: roomPrice,
          roomDescription: roomDescribe,
          roomFacilitiesIdList: roomFacility,
        }),
      // )
      headers: {
        'Content-Type': 'application/json; charset=utf-8',
      },
    })
      .then((res) => {
        return res.json(); // 使用 json() 可以得到 json 物件
      })
      .then((result) => {
        console.log(result); // 得到 {name: "oxxo", age: 18, text: "你的名字是 oxxo，年紀 18 歲～"}
        location.reload(true);
      });

    //增加到表格內
    //   let print = '';
    //   print += `
    //   <tr class="item1">
    //   <td>
    //     <input
    //     type="checkbox"
    //     id="${roomStyleId}"
    //     class="checkbox1"
    //     value="item1"
    //   />
    //   </td>
    //   <td>${roomName}</td>
    //   <td>${bedTypeSelect}</td>
    //   <td>${roomTypeSelect}</td>
    //   <td>${roomPrice}</td>
    //   <td>${roomCount}</td>
    //   <td>
    //   <button type="button" class="btn btn-link " data-toggle="modal"
    //   data-target=".bd-example-modal-lg-2 "
    //   data-whatever="@mdo">編輯</button>
    //   </td>
    // </tr>
    //   `;

    //   console.log('print' + print);
    // roomStyle.append += print;
    // console.log('我是輸出到頁面');
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
  });
  //全選旁的刪除
  //   const checkboxChecked = $('.checkbox1').onclick();
  $('#boxDelete').on('click', function () {
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
      fetch(url + api.style, {
        method: 'DELETE',
        body: JSON.stringify(checkboxArr),
        headers: {
          'Content-Type': 'application/json; charset=utf-8',
        },
      }).then((result) => {
        console.log(result);
        location.reload(true);
      });
    }
  });

  /**
   * init
   */
  async function init() {
    let html = '';
    const data = await ajax(url + api.style);
    data.forEach((e, i) => {
      html += addRoom(e);
    });
    roomStyle.innerHTML += html;
  }
  init();
});
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

async function ajax(api) {
  return (data = await fetch(api).then((res) => res.json()));
}

const url = 'http://localhost:8081/AGJS';

const api = {
  style: '/roomStyle',
  update: '/roomStyle/update',
  management: '/roomManagement',
};
