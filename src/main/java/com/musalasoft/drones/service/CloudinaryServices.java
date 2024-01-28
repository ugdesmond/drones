package com.musalasoft.drones.service;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.musalasoft.drones.util.CloudinaryConfig;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServices {

    private final Cloudinary cloudinary;

    public CloudinaryServices(CloudinaryConfig cloudinaryConfig) {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudinaryConfig.getCloudName(),
                "api_key", cloudinaryConfig.getApiKey(),
                "api_secret", cloudinaryConfig.getApiSecret()
        ));
    }

    public String uploadBase64Image(String base64Image) throws IOException {
        Map<?, ?> uploadResult = cloudinary.uploader().upload(base64Image, ObjectUtils.emptyMap());
        return (String) uploadResult.get("url");
    }
}

