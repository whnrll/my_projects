import axios from 'axios'
import { Message } from 'element-ui'

// 创建axios实例
const service = axios.create({
  baseURL: 'http://localhost:9090', // api 的 base_url
  timeout: 20000 // 请求超时时间
})

// 错误信息整理
const errorHandle = (status, other) => {
  switch (status) {
      case 400:
          console.log("信息校验失败");
          break;
      case 401:
          console.log("认证失败");
          break;
      case 403:
          console.log("token校验失败");
          toLogin();
          break;
      case 404:
          console.log("请求的资源不存在");
          break;
      default:
          console.log(other);
          break;
  }
}

// request拦截器
service.interceptors.request.use(
  config => {
    return config
  },
  error => {
    // Do something with request error
    Promise.reject(error)
  }
)

// response 拦截器
service.interceptors.response.use(
  response => {
 
    const res = response.data
    if (res.code < 0) {
      Message({
        message: res.message,
        type: 'error',
        duration: 5 * 1000
      })

      return Promise.reject('error')
    } else {
      return response.data
    }
  },
  error => {
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
