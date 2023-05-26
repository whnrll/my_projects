package com.example.hotel.es;

import java.io.IOException;

import cn.hutool.core.io.FileUtil;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MappingsTest {
    private RestHighLevelClient restClient;

    @BeforeEach
    void beforeEach() {
        restClient = new RestHighLevelClient(RestClient.builder(HttpHost.create("http://docker_01:9200")));
    }

    @AfterEach
    void afterEach() throws IOException {
        restClient.close();
    }

    @Test
    @DisplayName("测试es连接")
    void testConn() throws IOException {
        boolean ping = restClient.ping(RequestOptions.DEFAULT);
        System.out.println(ping);
    }

    @Test
    @DisplayName("查询es索引库是否存在")
    void testMappingExist() throws IOException {
        GetIndexRequest request = new GetIndexRequest("hotel");
        boolean exists = restClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    @DisplayName("删除es索引库")
    void testDeleteMappings() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("hotel");
        AcknowledgedResponse response = restClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println(response.isAcknowledged());
    }

    @Test
    void testReadString() {
        System.out.println(FileUtil.readUtf8String("es-template/mappings_hotel.json"));
    }

    @Test
    @DisplayName("创建es索引库")
    void testCreateMappings() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("hotel");

        String hotelMappings = FileUtil.readUtf8String("es-template/mappings_hotel.json");
        createIndexRequest.source(hotelMappings, XContentType.JSON);

        CreateIndexResponse response = restClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println(response.isAcknowledged());
    }
}
