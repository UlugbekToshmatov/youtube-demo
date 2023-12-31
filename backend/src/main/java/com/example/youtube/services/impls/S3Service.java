package com.example.youtube.services.impls;

import com.example.youtube.services.VideoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class S3Service implements VideoService {
    @Override
    public String uploadVideo(MultipartFile multipartFile) {
        return null;
    }
}
