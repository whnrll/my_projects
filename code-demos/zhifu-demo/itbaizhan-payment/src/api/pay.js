import request from '@/utils/request'
import url from "./base"

export default{
 
  //查询订单列表
  findByOrderNo(orderNo) {
    return request({
      url: '/api/orderInfo/findByOrderNo/'+orderNo,
      method: 'get'
    })
  }
}
