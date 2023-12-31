package com.example.youtube.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String picture;
    private String sub;
    List<String> subscribedToUsers;
    List<String> subscribers;
    List<String> videoHistory;
    List<String> likedVideos;
    List<String> dislikedVideos;
}
