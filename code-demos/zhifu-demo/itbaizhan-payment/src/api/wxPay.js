// axios 发送ajax请求
import request from '@/utils/request'
import url from "./base"

export default{

  //Native下单
  nativePay(orderNo) {
    return request({
      url: url.wxNativePay + orderNo,
      method: 'post'
    })
  },

  cancel(orderNo) {
    return request({
      url: url.wxCancel + orderNo,
      method: 'post'
    })
  },

  refunds(orderNo, reason) {
    return request({
      url: url.wxRefund + orderNo + '/' + reason,
      method: 'post'
    })
  }
}
