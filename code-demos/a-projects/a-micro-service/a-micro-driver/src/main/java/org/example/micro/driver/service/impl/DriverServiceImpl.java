package org.example.micro.driver.service.impl;

import org.example.micro.driver.mapper.DriverMapper;
import org.example.micro.driver.model.Driver;
import org.example.micro.driver.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DriverServiceImpl  implements DriverService {

    @Autowired
    private DriverMapper driverMapper;

    /***
     * 根据ID查询司机信息
     * @param id
     * @return
     */
    @Override
    public Driver findById(String id) {
        return driverMapper.findById(id);
    }

    /***
     * 修改司机状态信息
     * @param id
     * @param status
     */
    @Override
    public void update(String id, Integer status) {
        driverMapper.update(id,status);
    }
}
