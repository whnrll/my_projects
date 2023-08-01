// 创建应用程序的路由器
import Vue from 'vue'
import VueRouter from 'vue-router'
// 此时就可以在Vue实例中配置路由器了
Vue.use(VueRouter)

// 引入组件
import Index from '../views/index'
import Orders from '../views/Orders'
import Success from '../views/Success'
import Goods from "../views/goods"
import Pay from "../views/Pay"

// 创建并暴露一个路由器
export default new VueRouter({
    routes:[
        {
            path: '/',
            redirect:"/goods"
        },
        {
            path: '/orders',
            component: Orders
        },
        {
            path: '/goods',
            component: Goods
        },
        {
            name:"pay",
            path:"/pay/:id",
            component: Pay
        },
        {
            name:"success",
            component:Success,
            path:"/success"
        }
    ]
})