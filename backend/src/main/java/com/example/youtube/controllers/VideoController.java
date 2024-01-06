package com.example.youtube.controllers;

import com.example.youtube.dtos.VideoResponse;
import com.example.youtube.dtos.VideoUpdateForm;
import com.example.youtube.services.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/video")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(videoService.uploadVideo(file));
    }

    @PostMapping("/thumbnail")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> uploadThumbnail(@RequestParam("file") MultipartFile file, @RequestParam("videoId") String id) {
        return ResponseEntity.ok(videoService.uploadThumbnail(file, id));
    }

    @GetMapping("{video-id}")
    public ResponseEntity<VideoResponse> getVideoDetails(@PathVariable("video-id") String id) {
        return ResponseEntity.ok(videoService.getVideoDetails(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<VideoResponse> editVideoMetadata(@PathVariable String id, @RequestBody VideoUpdateForm form) {
        return ResponseEntity.ok(videoService.editVideoMetadata(id, form));
    }
}
