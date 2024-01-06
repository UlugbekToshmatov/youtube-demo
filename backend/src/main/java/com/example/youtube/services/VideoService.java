package com.example.youtube.services;

import com.example.youtube.dtos.VideoResponse;
import com.example.youtube.dtos.VideoUpdateForm;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {
    String uploadVideo(MultipartFile multipartFile);

    String uploadThumbnail(MultipartFile file, String id);

    VideoResponse getVideoDetails(String id);

    VideoResponse editVideoMetadata(String id, VideoUpdateForm form);
}
