<template>
  <div>
    <Banner />
    <div class="list-container">
      <div class="phone">
        <h3 class="title">手机</h3>
        <div class="phone-box">
          <div class="phone-box-left">
            <a href="#">
              <img src="../assets/img/phone_left.jpg" alt="" />
            </a>
          </div>
          <div class="phone-box-right">
            <div class="item" @click="createOrder({name:'电话卡',price:10})">
              <a href="#">
                <img
                  class="item-img"
                  src="../assets/img/phone-right.jpg"
                  alt=""
                />
                <p class="item-name">小米10至尊纪念版</p>
                <p class="item-desc">120X 变焦/120W秒充/120Hz屏幕</p>
                <p class="item-price">5299元起</p>
              </a>
            </div>
            <div class="item" @click="createOrder({name:'苹果',price:10})">
              <a href="#">
                <img
                  class="item-img"
                  src="../assets/img/phone-right.jpg"
                  alt=""
                />
                <p class="item-name">红米</p>
                <p class="item-desc">120X 变焦/120W秒充/120Hz屏幕</p>
                <p class="item-price">5299元起</p>
              </a>
            </div>
            <div class="item" @click="createOrder({name:'红米',price:10})">
              <a href="#">
                <img
                  class="item-img"
                  src="../assets/img/phone-right.jpg"
                  alt=""
                />
                <p class="item-name">扫地机</p>
                <p class="item-desc">120X 变焦/120W秒充/120Hz屏幕</p>
                <p class="item-price">5299元起</p>
              </a>
            </div>
            <div class="item" @click="createOrder({name:'扫地机',price:10})">
              <a href="#">
                <img
                  class="item-img"
                  src="../assets/img/phone-right.jpg"
                  alt=""
                />
                <p class="item-name">Pad</p>
                <p class="item-desc">120X 变焦/120W秒充/120Hz屏幕</p>
                <p class="item-price">5299元起</p>
              </a>
            </div>
            <div class="item" @click="createOrder({name:'Pad',price:10})">
              <a href="#">
                <img
                  class="item-img"
                  src="../assets/img/phone-right.jpg"
                  alt=""
                />
                <p class="item-name">电视</p>
                <p class="item-desc">120X 变焦/120W秒充/120Hz屏幕</p>
                <p class="item-price">5299元起</p>
              </a>
            </div>
            <div class="item" @click="createOrder({name:'电视',price:10})">
              <a href="#">
                <img
                  class="item-img"
                  src="../assets/img/phone-right.jpg"
                  alt=""
                />
                <p class="item-name">电脑</p>
                <p class="item-desc">120X 变焦/120W秒充/120Hz屏幕</p>
                <p class="item-price">5299元起</p>
              </a>
            </div>
            <div class="item" @click="createOrder({name:'电脑',price:10})">
              <a href="#">
                <img
                  class="item-img"
                  src="../assets/img/phone-right.jpg"
                  alt=""
                />
                <p class="item-name">手机</p>
                <p class="item-desc">120X 变焦/120W秒充/120Hz屏幕</p>
                <p class="item-price">5299元起</p>
              </a>
            </div>
            <div class="item" @click="createOrder({name:'手机',price:10})">
              <a href="#">
                <img
                  class="item-img"
                  src="../assets/img/phone-right.jpg"
                  alt=""
                />
                <p class="item-name">小米10至尊纪念版</p>
                <p class="item-desc">120X 变焦/120W秒充/120Hz屏幕</p>
                <p class="item-price">5299元起</p>
              </a>
            </div>
          </div>
        </div>
        <div class="big-img">
          <a href="#">
            <img src="../assets/img/phone_ad.jpg" alt="" />
          </a>
        </div>
      </div>
    </div>

    <!-- <div class="bg-fa of">
      <div class="container">
        <h3>商品列表</h3>
        <el-table :data="goods_list" border style="width: 100%">
          <el-table-column type="index"> </el-table-column>
          <el-table-column prop="name" label="商品名称" width="180">
          </el-table-column>
          <el-table-column prop="price" label="价格" width="180">
          </el-table-column>
          <el-table-column label="操作" width="100">
            <template slot-scope="scope">
              <el-button @click="createOrder(scope)" type="text" size="small"
                >购买</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div> -->
  </div>
</template>

<script>
import orderInfoApi from "../api/orderInfo";
import Banner from "../components/Banner.vue";

export default {
  data() {
    return {
      goods_list: [
        { name: "苹果13", price: 10 },
        { name: "拯救者电脑", price: 10 },
        { name: "ipad", price: 10 },
      ],
    };
  },
  components: {
    Banner,
  },
  methods: {
    handleBuy(index, row) {
      console.log(index, row);
    },
    gotoPayPage() {
      this.$router.replace({ path: "/" });
    },
    createOrder(scope) {
      orderInfoApi
        .createOrder(scope.name, scope.price)
        .then((response) => {
          if (response.code == 200) {
            alert("订单创建成功");
            this.$router.push("/orders");
          }
        });
    },
  },
};
</script>

<style>
.list-container {
  width: 1226px;
  margin: 0 auto;
}
h3 {
  font-size: 24px;
  line-height: 30px;
  margin: 40px auto;
  font-weight: normal;
}
#goods table {
  width: 600px;
  border: 1px solid #000;
  border-collapse: collapse;
}

#goods td,
#goods th {
  border: 1px solid #000;
}

#goods .box {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
  background-color: #eee;
  width: 280px;
  height: 160px;
  padding: 40px 80px;
}

.title {
  text-align: left;
  color: #333;
  font-size: 22px;
  font-weight: 200;
  line-height: 58px;
}
.phone-box {
  width: 100%;
  height: 614px;
}
.phone-box-left {
  width: 234px;
  height: 100%;
  float: left;
}
.phone-box-right {
  width: 992px;
  height: 614px;
  float: left;
}

.item {
  width: 234px;
  height: 300px;
  background: #fff;
  padding: 20px 0;
  margin: 0 0 14px 14px;
  float: left;
  box-sizing: border-box;
  transition: all 0.2s linear;
  text-align: center;
}
.item:hover {
  /* transform: translateY(-2px); */
  transform: translate3d(0, -2px, 0);
  box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
}

.item-img {
  width: 160px;
  margin-bottom: 18px;
}

.item-name {
  width: 214px;
  height: 21px;
  color: #333;
  font-size: 14px;
  line-height: 21px;
  margin: 0 auto 2px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.item-desc {
  width: 214px;
  height: 18px;
  color: #b0b0b0;
  font-size: 12px;
  line-height: 18px;
  margin: 0 auto 10px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.item-price {
  color: #ff6700;
  font-size: 14px;
}

.big-img {
  width: 100%;
  height: 120px;
  margin: 22px 0;
}
</style>