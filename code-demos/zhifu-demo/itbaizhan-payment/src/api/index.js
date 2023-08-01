import axios from "../utils/request"
import base from "./base"

const api = {


 /**
     * 查询全部订单
     */
  getLogin(params){
    return axios.post(base.orders , params);
  }

}

export default api; 