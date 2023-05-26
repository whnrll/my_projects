package com.example.hotel.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotel.service.IHotelService;
import com.example.mybatisplus.entity.TbHotel;
import com.example.mybatisplus.mapper.TbHotelMapper;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl extends ServiceImpl<TbHotelMapper, TbHotel> implements IHotelService {}
