package com.project.projectaquiler.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class CloudinaryService {

  @Value("${cloud.name}")
  private String cloudName;
  @Value("${cloud.api.key}")
  private String apiKey;
  @Value("${cloud.api.secrect}")
  private String apiSecret;

  private Cloudinary cloudinary;

  @PostConstruct
  public void init() {
    cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key", apiKey,
            "api_secret", apiSecret
    ));
    log.info("Cloudinary object created");
  }

  public String uploadImage(MultipartFile file) throws IOException {
    Map<?, ?> uploadResult = cloudinary
      .uploader()
      .upload(file.getBytes(), ObjectUtils.emptyMap());
    log.info("successfully uploaded image to Cloudinary");
    return uploadResult.get("secure_url").toString();
  }
}
