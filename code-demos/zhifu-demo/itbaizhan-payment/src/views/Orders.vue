<template>
  <div class="bg-fa of">
    <section id="index" class="container">
      <div class="white-container">
        <div class="white-nav">
          <div class="wrap">
            <div class="logo">
              <router-link to="/goods">
                <img class="mi-logo" src="../assets/img/mi-logo.png" alt="" />
              </router-link>
            </div>
            <div class="nav-bar">
              <ul>
                <li>
                  <a href="#"><img src="../assets/img/zzxsh.gif" alt="" /></a>
                </li>
                <li>
                  <a href="#">小米手机</a>
                  <div class="nav-bar-list">
                    <div class="wrap">
                      <ul>
                        <li>
                          <a href="#">
                            <div class="img-box">
                              <img src="../assets/img/phone.png" alt="" />
                            </div>
                            <p class="name">小米10至尊纪念版</p>
                            <p class="price">5200元</p>
                          </a>
                        </li>
                        <li>
                          <a href="#">
                            <div class="img-box">
                              <img src="../assets/img/phone.png" alt="" />
                            </div>
                            <p class="name">小米10至尊纪念版</p>
                            <p class="price">5200元</p>
                          </a>
                        </li>
                        <li>
                          <a href="#">
                            <div class="img-box">
                              <img src="../assets/img/phone.png" alt="" />
                            </div>
                            <p class="name">小米10至尊纪念版</p>
                            <p class="price">5200元</p>
                          </a>
                        </li>
                        <li>
                          <a href="#">
                            <div class="img-box">
                              <img src="../assets/img/phone.png" alt="" />
                            </div>
                            <p class="name">小米10至尊纪念版</p>
                            <p class="price">5200元</p>
                          </a>
                        </li>
                      </ul>
                    </div>
                  </div>
                </li>
                <li><a href="#">电视</a></li>
                <li><a href="#">笔记本</a></li>
                <li><a href="#">家电</a></li>
                <li><a href="#">路由器</a></li>
                <li><a href="#">智能硬件</a></li>
                <li><a href="#">服务</a></li>
                <li><a href="#">社区</a></li>
              </ul>
            </div>
            <div class="search">
              <input type="text" placeholder="元宇宙" />
              <button class="iconfont">&#xe63d;</button>
            </div>
          </div>
        </div>
      </div>
      <el-table :data="list" border style="width: 100%">
        <el-table-column type="index" width="50"></el-table-column>
        <el-table-column
          prop="orderNo"
          label="订单编号"
          width="230"
        ></el-table-column>
        <el-table-column prop="title" label="订单标题"></el-table-column>
        <el-table-column prop="totalFee" label="订单金额">
          <template slot-scope="scope">
            {{ scope.row.totalFee / 100 }} 元
          </template>
        </el-table-column>
        <el-table-column label="订单状态">
          <template slot-scope="scope">
            <el-button
              type="primary"
              @click="gotoPay(scope.row.orderNo)"
              v-if="scope.row.orderStatus === '未支付'"
              >未支付</el-button
            >
            <!-- <el-tag  @click="gotoPay" v-if="scope.row.orderStatus === '未支付'">
              {{scope.row.orderStatus}}
            </el-tag> -->
            <el-tag v-if="scope.row.orderStatus === '支付成功'" type="success">
              {{ scope.row.orderStatus }}
            </el-tag>
            <el-tag
              v-if="scope.row.orderStatus === '超时已关闭'"
              type="warning"
            >
              {{ scope.row.orderStatus }}
            </el-tag>
            <el-tag v-if="scope.row.orderStatus === '用户已取消'" type="info">
              {{ scope.row.orderStatus }}
            </el-tag>
            <el-tag v-if="scope.row.orderStatus === '退款中'" type="danger">
              {{ scope.row.orderStatus }}
            </el-tag>
            <el-tag v-if="scope.row.orderStatus === '已退款'" type="info">
              {{ scope.row.orderStatus }}
            </el-tag>
          </template>
        </el-table-column>
        <!-- <el-table-column prop="createTime" label="创建时间"></el-table-column> -->
        <el-table-column label="操作" width="100" align="center">
          <template slot-scope="scope">
            <el-button
              v-if="scope.row.orderStatus === '未支付'"
              type="text"
              @click="cancel(scope.row.orderNo)"
              >取消</el-button
            >
            <el-button
              v-if="scope.row.orderStatus === '支付成功'"
              type="text"
              @click="refund(scope.row.orderNo)"
              >退款</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </section>

    <!-- 退款对话框 -->
    <el-dialog
      :visible.sync="refundDialogVisible"
      @close="closeDialog"
      width="350px"
      center
    >
      <el-form>
        <el-form-item label="退款原因">
          <el-select v-model="reason" placeholder="请选择退款原因">
            <el-option label="不喜欢" value="不喜欢"></el-option>
            <el-option label="买错了" value="买错了"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button
          type="primary"
          @click="toRefunds()"
          :disabled="refundSubmitBtnDisabled"
          >确 定</el-button
        >
      </div>
    </el-dialog>
    <!-- <button @click="gotoPay">订单按钮</button> -->
  </div>
</template>

<script>
import orderInfoApi from "../api/orderInfo";
import wxPayApi from "../api/wxPay";

export default {
  data() {
    return {
      list: [], //订单列表
      refundDialogVisible: false, //退款弹窗
      orderNo: "", //退款订单号
      reason: "", //退款原因,
      refundSubmitBtnDisabled: false, //防止重复提交
    };
  },

  created() {
    this.showOrderList();
  },

  methods: {
    // 跳转订单
    gotoPay(orderNo) {
      // restfull API
      this.$router.push("/pay/" + orderNo);
      // this.$router.push({name:"pay",params:{id:orderNo}})
    },

    //显示订单列表
    // 网络请求拿到的数据list：订单
    showOrderList() {
      orderInfoApi.list().then((response) => {
        this.list = response.data;
      });
    },

    //用户取消订单
    cancel(orderNo) {
      wxPayApi.cancel(orderNo).then((response) => {
        this.$message.success(response.message);
        //刷新订单列表
        this.showOrderList();
      });
    },

    //退款对话框
    refund(orderNo) {
      this.refundDialogVisible = true;
      this.orderNo = orderNo;
    },

    //关闭退款对话框
    closeDialog() {
      console.log("close.................");
      this.refundDialogVisible = false;
      //还原组件状态
      this.orderNo = "";
      this.reason = "";
      this.refundSubmitBtnDisabled = false;
    },

    //确认退款
    toRefunds() {
      this.refundSubmitBtnDisabled = true; //禁用按钮，防止重复提交
      wxPayApi.refunds(this.orderNo, this.reason).then((response) => {
        console.log("response", response);
        this.closeDialog();
        this.showOrderList();
      });
    },
  },
};
</script>

<style scoped>
.logo {
  width: 49px;
  height: 49px;
  background: #ff6700;
  margin: 10px 0;
  border-radius: 15px;
  line-height: 49px;
  text-align: center;
}
.white-container {
  width: 1226px;
  margin: 0 auto;
}
.white-nav {
  width: 100%;
  height: 100px;
  background: #fff;
  position: relative;
}
.logo {
  width: 55px;
  height: 55px;
  background: #ff6700;
  float: left;
  margin-top: 22.5px;
  overflow: hidden;
  position: relative;
  border-radius: 20px;
}
.logo > img {
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  transition: all 0.2s;
}
.mi-home {
  left: -55px;
}
.mi-logo {
  left: 0;
}
.logo:hover > .mi-home {
  left: 0;
}
.logo:hover > .mi-logo {
  left: 55px;
}
.nav-bar {
  width: 875px;
  height: 100px;
  line-height: 100px;
  float: left;
}
.search {
  width: 296px;
  height: 100px;
  float: right;
}
.nav-bar li {
  float: left;
}
.nav-bar > ul > li > a {
  color: #333;
  padding: 0 10px;
}

.nav-bar > ul > li > a:hover {
  color: #ff6700;
}

.nav-bar > ul > li:first-child img {
  margin-top: 22.5px;
}

.search input {
  width: 244px;
  height: 50px;
  border: 1px solid #e0e0e0;
  margin-top: 25px;
  /* 去掉选中的边框 */
  outline: none;
  padding: 0 10px;
  box-sizing: border-box;
  border-right: 0;
}

.search button {
  width: 52px;
  height: 50px;
  background: #fff;
  border: 1px solid #e0e0e0;
  float: right;
  margin-top: 25px;
}
.search button:hover {
  background: #ff6700;
  color: #fff;
}

.nav-bar-list {
  width: 100%;
  height: 229px;
  background: #fff;
  border-top: 1px solid #eee;
  box-shadow: 0 3px 4px rgba(0, 0, 0, 0.1);
  position: absolute;
  left: 0;
  top: 100px;
  display: none;
  z-index: 10;
}

.nav-bar li:hover .nav-bar-list {
  display: block;
}

.img-box {
  width: 100%;
  height: 110px;
  margin-top: 35px;
  border-right: 1px solid #eee;
  box-sizing: border-box;
  margin-bottom: 16px;
}

.nav-bar-list li:last-child .img-box {
  border-right: 0;
}

.nav-bar-list p {
  font-size: 12px;
  line-height: 20px;
}

.name {
  color: #333;
}

.price {
  color: #ff6700;
}
</style>