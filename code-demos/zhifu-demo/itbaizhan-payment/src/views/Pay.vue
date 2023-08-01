<template>
  <div class="bg-fa of">
    <section id="index" class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">订单列表{{ orderNo }}</span>
        </h2>
        <div>
          <el-descriptions>
            <el-descriptions-item label="商品名字">{{
              title
            }}</el-descriptions-item>
            <el-descriptions-item label="金额"
              >{{ totalFee }}分</el-descriptions-item
            >
            <el-descriptions-item label="订单状态">{{
              orderStatus
            }}</el-descriptions-item>
          </el-descriptions>
        </div>
      </header>
      <ul>
        <li v-for="product in productList" :key="product.id">
          <a
            :class="[
              'orderBtn',
              { current: payOrder.productId === product.id },
            ]"
            @click="selectItem(product.id)"
            href="javascript:void(0);"
          >
            {{ product.title }}
            ¥{{ product.price / 100 }}
          </a>
        </li>
      </ul>

      <div class="PaymentChannel_payment-channel-panel">
        <h3 class="PaymentChannel_title">选择支付方式</h3>
        <div class="PaymentChannel_channel-options">
          <!-- 选择微信 -->
          <div
            :class="[
              'ChannelOption_payment-channel-option',
              { current: payOrder.payType === 'wxpay' },
            ]"
            @click="selectPayType('wxpay')"
          >
            <div class="ChannelOption_channel-icon">
              <img src="../assets/img/wxpay.png" class="ChannelOption_icon" />
            </div>
            <div class="ChannelOption_channel-info">
              <div class="ChannelOption_channel-label">
                <div class="ChannelOption_label">微信支付</div>
                <div class="ChannelOption_sub-label"></div>
                <div class="ChannelOption_check-option"></div>
              </div>
            </div>
          </div>

          <!-- 选择支付宝 -->
          <div
            :class="[
              'ChannelOption_payment-channel-option',
              { current: payOrder.payType === 'alipay' },
            ]"
            @click="selectPayType('alipay')"
          >
            <div class="ChannelOption_channel-icon">
              <img src="../assets/img/alipay.png" class="ChannelOption_icon" />
            </div>
            <div class="ChannelOption_channel-info">
              <div class="ChannelOption_channel-label">
                <div class="ChannelOption_label">支付宝</div>
                <div class="ChannelOption_sub-label"></div>
                <div class="ChannelOption_check-option"></div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="goods-info">
        <div class="local"></div>
        <div class="conetnt">
          <div class="goods-desc">
            <p>商品件数：</p>
            <p>商品总价：</p>
            <p>活动优惠：</p>
            <p>优惠券抵扣：</p>
            <p>运费：</p>
            <p>应付总额：</p>
          </div>
          <div class="goods-num">
            <p>1件</p>
            <p>{{ totalFee }}元</p>
            <p>-0元</p>
            <p>-0元</p>
            <p>0元</p>
            <p>{{ totalFee }}元</p>
          </div>
        </div>
      </div>

      <div class="payButtom">
        <el-button
          :disabled="payBtnDisabled"
          type="warning"
          round
          style="width: 180px; height: 44px; font-size: 18px"
          @click="toPay()"
        >
          确认支付
        </el-button>
      </div>
    </section>

    <!-- 微信支付二维码 -->
    <el-dialog
      :visible.sync="codeDialogVisible"
      :show-close="false"
      @close="closeDialog"
      width="350px"
      center
    >
      <qriously :value="codeUrl" :size="300" />
      <!-- <img src="../assets/img/code.png" alt="" style="width:100%"><br> -->
      使用微信扫码支付
    </el-dialog>
  </div>
</template>

<script>
import wxPayApi from "../api/wxPay";
import zfbApi from "../api/zfbPay";
import orderInfoApi from "../api/orderInfo";

export default {
  data() {
    return {
      currentId: 0,
      payBtnDisabled: false, //确认支付按钮是否禁用
      codeDialogVisible: false, //微信支付二维码弹窗
      productList: [], //商品列表
      payOrder: {
        //订单信息
        orderNo: "", //商品id
        payType: "wxpay", //支付方式
      },
      codeUrl: "", // 二维码
      orderNo: "", //订单号
      timer: null, // 定时器
      orderNo: "",
      orderStatus: "",
      title: "",
      totalFee: "",
      orderStatus: "",
    };
  },

  //页面加载时执行
  created() {
    this.orderNo = this.$route.params.id;

    orderInfoApi.findByOrderNo(this.orderNo).then((response) => {
      console.log(response);
      if (response.code == 200) {
        this.orderNo = response.data.orderNo;
        this.title = response.data.title;
        this.orderStatus = response.data.orderStatus;
        this.totalFee = response.data.totalFee;
      }
    });
  },

  methods: {
    //选择商品
    selectItem(productId) {
      console.log("商品id：" + productId);
      this.payOrder.productId = productId;
      console.log(this.payOrder);
      //this.$router.push({ path: '/order' })
    },

    //选择支付方式
    selectPayType(type) {
      console.log("支付方式：" + type);
      this.payOrder.payType = type;
      //this.$router.push({ path: '/order' })
    },

    //确认支付
    toPay() {
      //禁用按钮，防止重复提交
      this.payBtnDisabled = true;

      //微信支付
      if (this.payOrder.payType === "wxpay") {
        //调用统一下单接口
        wxPayApi.nativePay(this.orderNo).then((response) => {
          console.log(response);
          this.codeUrl = response.data.codeUrl;
          this.orderNo = response.data.orderNo;
          // //打开二维码弹窗
          this.codeDialogVisible = true;

          //启动定时器
          this.timer = setInterval(() => {
            //查询订单是否支付成功
            this.queryOrderStatus();
          }, 1000);
        });
      }

      if (this.payOrder.payType === "alipay") {
        //调用统一下单接口
        zfbApi.pcPay(this.orderNo).then((response) => {
          console.log(response);
          this.codeUrl = response.data.codeUrl;
          this.orderNo = response.data.orderNo;
          // //打开二维码弹窗
          this.codeDialogVisible = true;

          //启动定时器
          this.timer = setInterval(() => {
            //查询订单是否支付成功
            // this.queryOrderStatus();
          }, 3000);
        });
      }
    },

    //关闭微信支付二维码对话框时让“确认支付”按钮可用
    closeDialog() {
      console.log("close.................");
      this.payBtnDisabled = false;
      console.log("清除定时器");
      clearInterval(this.timer);
    },

    // 查询订单状态
    queryOrderStatus() {
      orderInfoApi.queryOrderStatus(this.orderNo).then((response) => {
        console.log("查询订单状态：");
        // 支付成功后的页面跳转
        if (response.code === 200) {
          if (JSON.parse(response.data).trade_state === "SUCCESS") {
            clearInterval(this.timer);
            // 三秒后跳转到订单列表
            this.$router.push({ path: "/success" });
          }
        }
      });
    },
  },
};
</script>
<style scoped>
.goods-info{
  overflow: hidden;
  clear: both;
  margin-top: 20px;
}
.local{
  width: 1050px;
  height: 120px;
  float: left;
}
.content {
  margin-top: 20px;
  width: 100%;
  overflow: hidden;
  clear: both;
  width: 140px;
  height: 120px;
  left: right;
}

.goods-info .goods-desc {
  float: left;
  font-size: 15px;
  color: #757575;
  text-align: right;
}
.goods-info .goods-num {
  float: right;
  text-align: right;
  color: #ff6700;
  font-size: 15px;
}
</style>