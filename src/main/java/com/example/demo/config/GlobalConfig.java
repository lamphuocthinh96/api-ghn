package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class GlobalConfig {

    @Value("${GHN.url}")
    public String ghnUrl;
    @Value("${GHN.token}")
    public String ghnToken;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("token", ghnToken);
        List<ClientHttpRequestInterceptor> interceptors
                = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new GHNRestInterceptor(ghnToken));
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }
}
