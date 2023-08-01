// axios 发送ajax请求
import request from '@/utils/request'

export default{

  //Native下单
  pcPay(orderNo) {
    return request({
      url: '/api/zfb-pay/pcPay/' + orderNo,
      method: 'post'
    })
  },

  cancel(orderNo) {
    return request({
      url: '/api/wx-pay/cancel/' + orderNo,
      method: 'post'
    })
  },

  refunds(orderNo, reason) {
    return request({
      url: '/api/wx-pay/refund/' + orderNo + '/' + reason,
      method: 'post'
    })
  }
}
