package com.example.hotel.dto;

import com.example.mybatisplus.entity.TbHotel;
import lombok.Data;

@Data
public class HotelDocDTO {
    private Long id;

    private String name;

    private String address;

    private Integer price;

    private Integer score;

    private String brand;

    private String city;

    private String starName;

    private String business;

    private String location;

    private String pic;

    public HotelDocDTO(TbHotel tbHotel) {
        this.id = tbHotel.getId();
        this.name = tbHotel.getName();
        this.address = tbHotel.getAddress();
        this.price = tbHotel.getPrice();
        this.score = tbHotel.getScore();
        this.brand = tbHotel.getBrand();
        this.city = tbHotel.getCity();
        this.starName = tbHotel.getStarName();
        this.business = tbHotel.getBusiness();
        this.location = tbHotel.getLatitude() + "," + tbHotel.getLongitude();
        this.pic = tbHotel.getPic();
    }
}
