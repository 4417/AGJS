var header = `    <header>

<div class="wrapper row1">
  <header id="header" class="hoc clear">
    <!-- ################################################################################################ -->
    <div id="LogoImg">
      <img id="LOGO" src="image/logo_v2.png" alt="">
    </div>
    <div id="logo" class="fl_left">
      <h1><a href="index.html">A GooD Journey SySTem </a></h1>
    </div>
    <nav id="mainav" class="fl_right">
      <ul class="clear">
        <li class="active"><a href="index.html">最新消息</a></li>

        <li><a class="drop" href="#">關於我們</a>
          <ul>
            <li><a href="pages/gallery.html">Gallery</a></li>
            <li><a href="pages/full-width.html">Full Width</a></li>
            <li><a href="pages/sidebar-left.html">Sidebar Left</a></li>
            <li><a href="pages/sidebar-right.html">Sidebar Right</a></li>
            <li><a href="pages/basic-grid.html">Basic Grid</a></li>
          </ul>
        </li>

        <li><a class="drop" href="#">房型介紹</a>
          <ul>
            <li><a href="#">山景標準房</a></li>
            <li><a href="#">山景雅致房</a></li>
            <li><a href="#">海景標準房</a></li>
            <li><a href="#">海景雅致房</a></li>
          </ul>
        </li>

        <li><a href="#">行程介紹</a></li>
        <li><a href="#">美食饗宴</a>
          <ul>
            <li><a href="#">Java Steak House</a></li>
            <li><a href="#">Momohiya</a></li>
            <li><a href="#">102 BAR</a></li>
          </ul>
        </li>
        <li><a href="">聯絡我們</a></li>
        <!-- <li><a href="">立即訂位</a></li> -->
      </ul>
</div>

</header>`;

var footer = `<footer>
<div class="bgded overlay"> 
  <!-- ################################################################################################ -->
  <div class="wrapper row4">
    <footer id="footer" class="hoc clear"> 
      <!-- ################################################################################################ -->
      <div class="one_quarter first">
        <h6 class="heading">FOLLOW US</h6>
        <ul class="faico clear">
          <li><a class="faicon-facebook" href="#"><i class="fa fa-facebook"></i></a></li>
          <li><a class="faicon-twitter" href="#"><i class="fa fa-twitter"></i></a></li>
          <li><a class="faicon-dribble" href="#"><i class="fa fa-dribbble"></i></a></li>
          <li><a class="faicon-linkedin" href="#"><i class="fa fa-linkedin"></i></a></li>
        </ul>
        <br>
        <ul class="nospace btmspace-30 linklist contact">
          <li><i class="fa fa-phone"></i> 收到最新消息</li>
          <input class="btmspace-15" type="text" value="" placeholder="Name">
          <button type="submit" value="submit">Submit</button>
        </ul>
      </div>
      <div id="middle" class="one_quarter">
        <img id="f_logo" src="/image/logo_v2.png">
        <br>
        <br>
        <ul id="text" class="nospace linklist">
          <li>A GooD Journey SySTem</a></li>
          <li>連絡電話:(02)2222-1122</a></li>
          <li>地址:台北市中山區XX南路XX號</a></li>
        </ul>
      </div>
      <div class="one_quarter">
        <h6 class="heading">交通位置</h6>
        <img src="/image/map.png">
      </div>
      <!-- ################################################################################################ -->
    </footer>
  </div>
  <!-- ################################################################################################ -->
  <!-- ################################################################################################ -->
  <!-- ################################################################################################ -->
  <div class="wrapper row5">
    <div id="copyright" class="hoc clear"> 
      <!-- ################################################################################################ -->
      <p class="fl_left">Copyright &copy; 2022 - All Rights Reserved - <a href="#">A GooD Journey SySTem, Inc.或其附屬公司</a></p>
      <!-- ################################################################################################ -->
    </div>
  </div>
  <!-- ################################################################################################ -->
</div>
</footer>`;

window.onload = function () {
  $("main").prepend(header);

  $("main").after(footer);
};
