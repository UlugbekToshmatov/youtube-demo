package com.example.youtube.entities;

import com.example.youtube.enums.VideoStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "Videos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Video {
    @Id
    private String id;
    private String description;
    private String title;
    private String userId;
    private Integer likes;
    private Integer dislikes;
    private List<String> tags;
    private String url;
    private VideoStatus videoStatus;
    private Integer viewCount;
    private String thumbnailUrl;
    private List<Comment> comments;
}
