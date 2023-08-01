import request from '@/utils/request'
import url from "./base"

export default{

  //创建订单
  createOrder(name,price){
    return request({
      url:  url.createOrder,
      method: 'post',
      data: {
        title: name,
        totalFee: price
      }
    })
  },
  // 根据订单ID查询订单信息
  findByOrderNo(orderNo){
    return request({
      url: url.findByOrderNo + orderNo,
      method: 'get'
    
    })
  },

  //查询订单列表
  list() {
    return request({
      url: url.orders,
      method: 'get'
    })
  },

  //查询订单状态
  queryOrderStatus(orderNo) {
    return request({
      url: url.queryOrderStatus + orderNo,
      method: 'get'
    })
  }
}
