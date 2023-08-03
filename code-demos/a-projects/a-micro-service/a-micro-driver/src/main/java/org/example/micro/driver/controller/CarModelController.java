package org.example.micro.driver.controller;

import java.util.ArrayList;
import java.util.List;

import org.example.micro.driver.model.CarModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/car/model")
public class CarModelController {

    /****
     * 车型列表
     */
    @GetMapping(value = "/list")
    public List<CarModel> list(){
        List<CarModel> carModels = new ArrayList<>();
        carModels.add(new CarModel(0,"快车"));
        carModels.add(new CarModel(1,"专车"));
        carModels.add(new CarModel(2,"出租车"));
        carModels.add(new CarModel(3,"六座商务车"));
        return carModels;
    }
}
