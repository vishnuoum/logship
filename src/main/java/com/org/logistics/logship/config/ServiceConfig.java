package com.org.logistics.logship.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ServiceConfig {

    @Bean
    public RestClient restClient(@Value("${logship.data.url}") String dataServiceURL) {
        return RestClient.create(dataServiceURL);
    }
}
