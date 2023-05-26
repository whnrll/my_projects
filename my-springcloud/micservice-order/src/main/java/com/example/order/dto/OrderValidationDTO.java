package com.example.order.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.*;

import lombok.Data;

@Data
public class OrderValidationDTO {
    @NotNull
    @Max(value = 200, message = "id 最大值：{value}")
    private Integer id;

    @NotBlank(message = "name 不能为空")
    @Pattern(regexp = "^[\\w\\-]{0,20}$", message = "name 必须符合：{regexp}")
    private String name;

    @Size(max = 1, message = "goodIds 最大数量：{max}")
    private List<@NotBlank(message = "goodId 不能为空") @Pattern(regexp = "^[a-zA-Z0-9]{0,20}$",
        message = "goodId 必须符合：{regexp}") String> goodIds;

    @Valid
    private Address address;
}
