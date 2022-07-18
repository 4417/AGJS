window.addEventListener("load", function (e) {
  
    // =================== 元素提出 ===================//
    var data_category_title_f1 = document.getElementById("data_category_title");  //問題選項
    var data_name_f1 = document.getElementById("data_name");                      //姓名欄位
    var data_telephone_f1 = document.getElementById("data_telephone");            //電話欄位
    var data_email_f1 = document.getElementById("data_email");                    //信箱欄位
    var data_exp_f1 = document.getElementById("data_exp");                        //內容說明
    var btn_submit_e1 = document.getElementById("btn_submit");                    //送出按鈕
  
    // =================== 送出資料 ===================//
    //設定按鈕點擊觸發
    btn_submit.addEventListener("click",function(e){
      var faqTypeName = data_category_title_f1.value;
      var userPhone = data_telephone_f1.value;
      var content_text = data_exp_f1.value;
      var userEmail = data_email_f1.value;
      var userName = data_name_f1.value;
      var emailRegxp = /^([\w]+)(.[\w]+)*@([\w]+)(.[\w]{2,3}){1,2}$/;   //信箱驗證
      var phoneregex = /^(09)[0-9]{8}$/; //電話驗證
      if(faqTypeName == ""){
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: '請選擇問題分類',
      })
        return false;
      }
      else if(userName == ""){
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: '請輸入姓名',
      })
        return false;
      }
      else if(userPhone == ""){
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: '請輸入電話',
      })
          return false;
      }
      else if (phoneregex.test(userPhone) != true){
              Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: '手機格式錯誤',
          })
        return false;
      }
  
      else if(userEmail == ""){      
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: '請輸入信箱',
      })
        return false;
      }
      else if (emailRegxp.test(userEmail) != true){
              Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: '信箱格式錯誤',
          })
        return false;
      }
      else if(content_text == ""){
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: '請輸入說明內容',
      })
        return false;
      }
      else {
  
        e.preventDefault(); //打包呼叫
        var send_data = {}; //設定變數存取寄出的資料
    
        send_data.faqTypeName = data_category_title_f1.value;
        send_data.userName = data_name_f1.value;
        send_data.userPhone = data_telephone_f1.value;
        send_data.userEmail = data_email_f1.value;
        send_data.content_text = data_exp_f1.value;
    
        console.log(send_data);
        
        //送出一包
        // sessionStorage.setItem("form_data", JSON.stringify(send_data)); //包裝成JSON
        // location.href = "./mail.html"; //到指定網頁
        Swal.fire(
          'Good job!',
          '我們已經收到您的回饋',
          'success'
        )
      
    }
    });
  });