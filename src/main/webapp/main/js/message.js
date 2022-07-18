// 僅供測試
var send = (function (e) {
  var Words = document.getElementById("words");
  var Who = document.getElementById("who");
  var talkWords = document.getElementById("talkwords");
  var talkSub = document.getElementById("talksub");
  return {
    init: function () {
      this.event();
    },
    event: function () {
      var that = this;
      talkSub.onclick = function () {
        that.chart();
      };
      talkWords.onkeydown = function (event) {
        if (event.keyCode == 13) {
          that.chart();
          event.preventDefault();
        }

      };
    },
    chart: function () {
      var str = "";
      if (talkWords.value.trim() === "") {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: '請輸入文字內容',
          })
          talkWords.value=""; //清空訊息
        return;
      }
      if (Who.value == 0) {
        str =
          '<div class="atalk" style="text-align: left;"><span>' + talkWords.value + "</span></div>";
      } else {
        str =
          '<div class="btalk"><span>' + talkWords.value + "</span></div>";
      }
      Words.innerHTML = Words.innerHTML + str;
      talkWords.value = "";

      var ele = document.querySelector(".talk_show");

      jsonObj = {
				"userName" : Who,
				"message" : talkWords
			};
      var send = {};

      send.Who = Who.value;
      send.talkWords = talkWords.value;
      
      console.log(send);

      //判斷元素是否出現滾動條
      if (ele.scrollHeight > ele.clientHeight) {
        //設置滾動條到最底部
        ele.scrollTop = ele.scrollHeight;
      }

    },
  };
})();

send.init();