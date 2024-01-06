package com.example.youtube.services.impls;

import com.example.youtube.dtos.VideoResponse;
import com.example.youtube.dtos.VideoUpdateForm;
import com.example.youtube.entities.Video;
import com.example.youtube.enums.VideoStatus;
import com.example.youtube.repositories.VideoRepository;
import com.example.youtube.services.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.MessageFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final S3Service s3Service;
    private final VideoRepository videoRepository;

    @Override
    @Transactional
    public String uploadVideo(MultipartFile multipartFile) {
        // upload video to aws s3
        String uploadUrl = s3Service.uploadFile(multipartFile);

        // save video to repository
        Video savedVideo = videoRepository.save(
            Video.builder()
                .videoStatus(VideoStatus.PUBLIC)
                .uploadUrl(uploadUrl)
                .deleted(Boolean.FALSE)
                .createdDate(new Date())
                .modifiedDate(new Date())
                .build()
        );

        return savedVideo.getId();
    }

    @Override
    public String uploadThumbnail(MultipartFile file, String id) {
        String uploadUrl = s3Service.uploadFile(file);

        Video video = findVideo(id);
        video.setThumbnailUrl(uploadUrl);
        videoRepository.save(video);

        return uploadUrl;
    }

    @Override
    public VideoResponse getVideoDetails(String id) {
        return toResponse(findVideo(id));
    }

    @Override
    public VideoResponse editVideoMetadata(String id, VideoUpdateForm form) {
        Video video = findVideo(id);

        video.setTitle(form.getTitle());
        video.setDescription(form.getDescription());
        video.setTags(form.getTags());
        video.setUploadUrl(form.getUploadUrl());
        video.setVideoStatus(form.getVideoStatus());
        video.setThumbnailUrl(form.getThumbnailUrl());

        Video updatedVideo = videoRepository.save(video);

        return toResponse(updatedVideo);
    }

    private Video findVideo(String id) {
        return videoRepository.findByIdAndDeletedFalse(id).orElseThrow(
            () -> new RuntimeException(MessageFormat.format("Video with id='{0}' does not exist", id))
        );
    }

    private VideoResponse toResponse(Video video) {
        return VideoResponse.builder()
            .id(video.getId())
            .title(video.getTitle())
            .description(video.getDescription())
            .userId(video.getUserId())
            .likes(video.getLikes())
            .dislikes(video.getDislikes())
            .tags(video.getTags())
            .uploadUrl(video.getUploadUrl())
            .videoStatus(video.getVideoStatus())
            .viewCount(video.getViewCount())
            .thumbnailUrl(video.getThumbnailUrl())
            .comments(video.getComments())
            .createdDate(video.getCreatedDate())
            .build();
    }
}
