package com.example.youtube.dtos;

import com.example.youtube.enums.VideoStatus;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VideoUpdateForm {
    private String title;
    private String description;
    private List<String> tags;
    private String uploadUrl;
    private VideoStatus videoStatus;
    private String thumbnailUrl;
}
