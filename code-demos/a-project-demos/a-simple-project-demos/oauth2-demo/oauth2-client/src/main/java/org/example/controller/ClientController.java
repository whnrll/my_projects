package org.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.MapUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述：客户端控制器
 *
 * @author xutao
 * @Date 2023-03-08 19:56:35
 */
@Slf4j
@Controller
public class ClientController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/index.html")
    public String index(String code, Model model) {
        if (code != null) {
            // 授权码 code 存在时，去授权服务器获取 access_token，获取之后再重定向回到 client 首页
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("code", code);
            params.add("client_id", "client1");
            params.add("client_secret", "123456");
            params.add("redirect_uri", "http://localhost:9003/index.html");
            params.add("grant_type", "authorization_code");
            params.add("code", code);
            Map<String, String> resp =
                restTemplate.postForObject("http://localhost:9001/oauth/token", params, Map.class);
            if (MapUtils.isEmpty(resp)) {
                throw new RuntimeException("get access_token failed");
            }

            // 获取授权服务器返回的 access_token
            String accessToken = resp.get("access_token");
            log.info(">>>>>>>>>> access_token is: [{}]", accessToken);

            // 携带 access_token 访问资源服务器，获取请求数据
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + accessToken);
            HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
            ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:9002/resource/admin",
                HttpMethod.GET, httpEntity, String.class);

            // 使用 model 封装请求数据
            model.addAttribute("msg", responseEntity.getBody());
        }

        return "index";
    }
}
