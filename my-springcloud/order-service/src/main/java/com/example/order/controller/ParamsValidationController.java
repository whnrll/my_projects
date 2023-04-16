package com.example.order.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.order.dto.OrderValidationDTO;
import com.exaple.common.vo.ResultResponse;

@Validated
@RequestMapping("/valid")
@RestController
public class ParamsValidationController {

    @GetMapping("/single")
    public ResultResponse single(
        @NotNull(message = "id 不能为 null") @Range(min = 101, max = 200, message = "编号不存在，范围：{min}-{max}") Long id,
        @NotBlank(message = "name 不能为空") String name) {
        return ResultResponse.success();
    }

    @PostMapping("/json")
    public ResultResponse json(@RequestBody @Validated OrderValidationDTO orderValidationDTO) {
        return ResultResponse.success();
    }
}
