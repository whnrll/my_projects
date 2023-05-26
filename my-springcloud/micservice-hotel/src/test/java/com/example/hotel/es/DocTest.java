package com.example.hotel.es;

import com.example.hotel.BaseSpringTest;
import com.example.hotel.service.IHotelService;
import com.example.mybatisplus.entity.TbHotel;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@Slf4j
public class DocTest extends BaseSpringTest {
    @Autowired
    private IHotelService hotelService;

    private RestHighLevelClient restHighLevelClient;

    @BeforeEach
    void beforeEach() {
        restHighLevelClient = new RestHighLevelClient(RestClient.builder(HttpHost.create("http://docker_01:9200")));
    }

    @AfterEach
    void afterEach() throws IOException {
        restHighLevelClient.close();
    }

    @Test
    @DisplayName("增加文档")
    void testAddDoc() {
        // 1.查询文档
        TbHotel tbHotel = hotelService.getById(61083L);
        System.out.println(tbHotel);
    }
}
