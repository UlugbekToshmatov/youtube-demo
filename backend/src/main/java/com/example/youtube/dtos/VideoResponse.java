package com.example.youtube.dtos;

import com.example.youtube.entities.Comment;
import com.example.youtube.enums.VideoStatus;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VideoResponse {
    private String id;
    private String title;
    private String description;
    private String userId;
    private Integer likes;
    private Integer dislikes;
    private List<String> tags;
    private String uploadUrl;
    private VideoStatus videoStatus;
    private Integer viewCount;
    private String thumbnailUrl;
    private List<Comment> comments;
    private Date createdDate;
}
