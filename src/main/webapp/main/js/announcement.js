$(window).on("load", function () {
  
  $(document).on("click", ".link_title", function(e){
    e.preventDefault();
    $(this).closest("li").toggleClass("-on");
    $(this).closest("li").find(".anmDate").slideToggle();
    $(this).closest("li").find(".anmContent").slideToggle();
  });
  
  // 抓取資料庫type & title & content
  var count = 0;
  $.ajax({
    url: "announcement/published",           // 資料請求的網址
    method: "POST",                                // GET | POST | PUT | DELETE | PATCH 
    data: JSON.stringify({
      "count": count
    }),
    contentType: "application/json; charset=utf-8",
    dataType: "json",                           // 預期會接收到回傳資料的格式： json | xml | html
    success: function(response){                // request 成功取得回應後執行
      for(var i = 0; i < response.length; i++) {
        var anmTitle = response[i].anmTitle;
        var anmContent = response[i].anmContent;
        var anmStartYear = new Date(response[i].anmStartDate).getFullYear();
        var anmStartMonth = new Date(response[i].anmStartDate).getMonth() + 1;
        var anmStartDate = new Date(response[i].anmStartDate).getDate();
        var anmType = response[i].anmTypeId;
        if(anmType === 1){
          anmType = "住房優惠";
        }
        else if(anmType === 2) {
          anmType = "餐飲優惠";
        }
        else{
          anmType = "其他";
        }
        
        var html = `
          <li class="btmspace-15">
            <article><i class="fa fa-fort-awesome"></i>
              <h6 class="heading font-x1">
                <a class="link_title" href="#">
                  <div class="anmType">${anmType}</div>
                  <div class="anmTitle d_inline">${anmTitle}</div>
                </a>
              </h6>
              <div class="inner_block ml-5 pl-5">
                <div class="anmDate mb-2 ml-2"><span name="anmDateYear">${anmStartYear}</span> 年 <span name="anmDateMonth">${anmStartMonth}</span> 月 <span name="anmDateDate">${anmStartDate}</span> 日</div>
                <div class="anmContent ml-2">${anmContent}</div>
              </div>
            </article>
          </li>
        `;

        $("ul.list").append(html);
      }
    },error: function(xhr){         // request 發生錯誤的話執行
      console.log("xhr", xhr);
    }
  });
  
  // 點選顯示更多公告
  $("#list_more").on("click", function(){
    count++
    $.ajax({
      url: "announcement/published",           // 資料請求的網址
      type: "POST",                                // GET | POST | PUT | DELETE | PATCH 
      data: JSON.stringify({
        "count": count
      }),                                         // 將物件資料(不用雙引號) 傳送到指定的 url  
      contentType: "application/json; charset=utf-8",
      dataType: "json",                           // 預期會接收到回傳資料的格式： json | xml | html
      success: function(response){                // request 成功取得回應後執行
        for(var i = 0; i < response.length; i++) {
          var anmTitle = response[i].anmTitle;
          var anmContent = response[i].anmContent;
          var anmStartYear = new Date(response[i].anmStartDate).getFullYear();
          var anmStartMonth = new Date(response[i].anmStartDate).getMonth() + 1;
          var anmStartDate = new Date(response[i].anmStartDate).getDate();
          var anmType = response[i].anmTypeId;
          if(anmType === 1){
            anmType = "住房優惠";
          }
          else if(anmType === 2) {
            anmType = "餐飲優惠";
          }
          else{
            anmType = "其他";
          }
          
          var html = `
            <li class="btmspace-15">
              <article><i class="fa fa-fort-awesome"></i>
                <h6 class="heading font-x1">
                  <a class="link_title" href="#">
                    <div class="anmType">${anmType}</div>
                    <div class="anmTitle d_inline">${anmTitle}</div>
                  </a>
                </h6>
                <div class="inner_block ml-5 pl-5">
                  <div class="anmDate mb-2 ml-2"><span name="anmDateYear">${anmStartYear}</span> 年 <span name="anmDateMonth">${anmStartMonth}</span> 月 <span name="anmDateDate">${anmStartDate}</span> 日</div>
                  <div class="anmContent ml-2">${anmContent}</div>
                </div>
              </article>
            </li>
          `;
  
          $("ul.list").append(html);
        }
      }
    })
  });
});