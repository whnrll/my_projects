package com.example.order.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class Address {
    @NotNull
    private Integer id;

    @NotBlank
    private String userId;

    @NotBlank
    @Length(max = 200)
    private String detail;
}
