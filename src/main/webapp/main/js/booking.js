var currentDateTime = new Date();
var year = currentDateTime.getFullYear();
var month = currentDateTime.getMonth() + 1;
var date = currentDateTime.getDate() + 1;

if (date < 10) {
  date = "0" + date;
}
if (month < 10) {
  month = "0" + month;
}

var dateTomorrow = year + "-" + month + "-" + date;
var checkinElem = document.querySelector("#checkin-date");
var checkoutElem = document.querySelector("#checkout-date");

checkinElem.setAttribute("min", dateTomorrow);

checkinElem.onchange = function () {
  checkoutElem.setAttribute("min", this.value);
};
$(document).ready(function () {
	$(".btn_book").on("click", () => {
    let restaurant = $(".restaurant").val().trim();
    let name = $(".name").val().trim();
    let phone = $(".phone").val().trim();
    let people = $(".people").val().trim();
    let date = $(".date").val().trim();
    let note = $(".note").val().trim();
    //==============會員登入AJAX============================
    const url = "login";
    //JSON.stringify：物件變 JSON
    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
       restId: restaurant,
       userName: name,
       restTel:phone,
       restNum:people,
       restDate:date,
       restNote:note
       
      }),
    })
      .then((res) => {
        return res.json();
      })
      .then((res) => {
        const msg = res.errorMsg ?? "訂位成功！";

        if (msg === "訂位成功！") {
          alert(res.userName + "," + msg);
        } else {
          alert(msg);
        }
      });
      }
      }