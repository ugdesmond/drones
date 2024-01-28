package com.musalasoft.drones.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@PropertySource("classpath:application.properties")
public class CloudinaryConfig {

    @Value("${cloud_name}")
    private String cloudName;

    @Value("${api_key}")
    private String apiKey;

    @Value("${api_secret}")
    private String apiSecret;

    @PostConstruct
    public void init() {
        if (cloudName == null || apiKey == null || apiSecret == null) {
            throw new RuntimeException("Cloudinary configuration properties are not properly set");
        }
    }

    public String getCloudName() {
        return cloudName;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }
}
