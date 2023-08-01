package com.muke.service;

import com.muke.entity.SkillGood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    @Autowired
    private RestTemplate restTemplate;

    public SkillGood getGoodById(Long productId) {
        return restTemplate.getForObject("http://product-serv/product/" + productId, SkillGood.class);
    }

    public void update(SkillGood skillGood) {
       ResponseEntity<String> result= restTemplate.postForEntity("http://product-serv/product/",skillGood,String.class);
       System.out.println(result.getBody());
    }
}
