<template>
  <div class="black-nav">
    <div class="wrap">
      <ul class="black-nav-left">
        <li><router-link to="/goods">小米商城</router-link><span>|</span></li>
        <li><a href="#">MIUI</a><span>|</span></li>
        <li><a href="#">loT</a><span>|</span></li>
        <li><a href="#">云服务</a><span>|</span></li>
        <li><a href="#">天星数科</a><span>|</span></li>
        <li><a href="#">有品</a><span>|</span></li>
        <li><a href="#">小爱开放平台</a><span>|</span></li>
        <li><a href="#">企业团购</a><span>|</span></li>
        <li><a href="#">资质证照</a><span>|</span></li>
        <li><a href="#">协议规则</a><span>|</span></li>
        <li>
          <a href="#">下载APP</a>
          <span>|</span>
          <div class="download">
            <img src="../assets/img/download.png" alt="" />
            <p>小米商城APP</p>
          </div>
          <div class="stri"></div>
        </li>
        <li><a href="#">智能生活</a><span>|</span></li>
        <li><a href="#">SelectLocation</a><span>|</span></li>
      </ul>
      <ul class="black-nav-right">
        <li><a href="#">登录</a><span>|</span></li>
        <li><a href="#">注册</a><span>|</span></li>
        <li><router-link to="/orders">我的订单</router-link></li>
        <!-- <li class="cart">
          <a href="#">
            <i>购物车（0）</i>
          </a>
          <div class="cart-list">购物车中还没有商品，赶紧选购吧</div>
        </li> -->
      </ul>
    </div>
  </div>
</template>
<style>
.black-nav {
  width: 100%;
  height: 40px;
  line-height: 40px;
  background-color: #333;
  font-size: 12px;
}
.wrap {
  width: 1226px;
  margin: 0 auto;
}
.black-nav-left {
  float: left;
}
.black-nav-right {
  float: right;
}
.black-nav li {
  float: left;
  position: relative;
}
.black-nav a {
  color: #b0b0b0;
}
.black-nav span {
  color: #424242;
  margin: 0 3.6px;
}
.download {
  width: 124px;
  height: 0;
  background: #fff;
  box-shadow: 0 1px 5px #aaa;
  position: absolute;
  top: 40px;
  left: 50%;
  margin-left: -62px;
  overflow: hidden;
  transition: all 0.3s;
  z-index: 10;
}
.download img {
  width: 90px;
  margin: 18px auto 0;
}
.download p {
  color: #333;
  font-size: 14px;
  line-height: 14px;
}
.black-nav-left li:hover > .download {
  height: 148px;
}
.stri {
  width: 0;
  height: 0;
  border-bottom: 8px solid #fff;
  border-left: 8px solid transparent;
  border-right: 8px solid transparent;
  position: absolute;
  bottom: 0;
  left: 50%;
  margin-left: -8px;
  display: none;
}
.black-nav-left > li:hover > .stri {
  display: block;
}
.cart {
  width: 120px;
  height: 40px;
  background: #424242;
  margin-left: 25px;
  position: relative;
  z-index: 10;
}
.cart:hover {
  background: #fff;
}
.cart:hover > a {
  color: #ff6700;
}
.cart i {
  font-style: normal;
  margin-right: 8px;
}
.cart-list {
  width: 316px;
  height: 0;
  background: #fff;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.15);
  position: absolute;
  right: 0;
  top: 40px;
  color: #424242;
  overflow: hidden;
  transition: all 0.3s;
  line-height: 100px;
}
.cart:hover > .cart-list {
  height: 100px;
}
</style>