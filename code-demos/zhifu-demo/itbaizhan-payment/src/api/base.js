const base = {
    orders:'/api/order/findAll',//查询订单
    findByOrderNo:'/api/order/findByOrderNo/',//根据订单编号查询订单信息
    createOrder:'/api/order/createOrder',//创建订单
    wxNativePay:'/api/wx-pay/native/',//native支付
    queryOrderStatus:'/api/wx-pay/queryOrder/',//查询订单状态
    wxCancel:'/api/wx-pay/cancel/',//取消订单
     wxRefund:'/api/wx-pay/refund/'// 退款
    //wxRefund:'/api/zfb-pay/refund/'// 退款
}

export default base;