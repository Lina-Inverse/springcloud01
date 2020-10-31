package com.feiyu.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

    @Bean       //注入溶器
    @LoadBalanced   //负载均衡
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
