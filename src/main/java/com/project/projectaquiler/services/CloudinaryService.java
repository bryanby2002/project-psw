package com.project.projectaquiler.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class CloudinaryService {

  private final Cloudinary cloudinary;

  public CloudinaryService() {
    final String CLOUD_NAME = System.getenv("CLOUD_NAME");
    final String API_KEY = System.getenv("API_KEY");
    final String API_SECRET = System.getenv("API_SECRECT");

    cloudinary =
      new Cloudinary(
        ObjectUtils.asMap(
          "cloud_name",CLOUD_NAME,
          "api_key",API_KEY,
          "api_secret",API_SECRET
        )
      );
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
