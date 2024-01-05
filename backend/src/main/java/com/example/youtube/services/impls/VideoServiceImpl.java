package com.example.youtube.services.impls;

import com.example.youtube.entities.Video;
import com.example.youtube.enums.VideoStatus;
import com.example.youtube.repositories.VideoRepository;
import com.example.youtube.services.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final S3Service s3Service;
    private final VideoRepository videoRepository;

    @Override
    public String uploadVideo(MultipartFile multipartFile) {
        // upload video to aws s3
        String videoUrl = s3Service.uploadFile(multipartFile);

        // save video to repository
        Video savedVideo = videoRepository.save(
            Video.builder()
                .videoStatus(VideoStatus.PUBLIC)
                .url(videoUrl)
                .build()
        );

        return savedVideo.getId();
    }
}
