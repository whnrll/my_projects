package org.example.micro.driver.service;

import org.example.micro.driver.model.Driver;

public interface DriverService {
    Driver findById(String id);

    void update(String id, Integer status);
}
