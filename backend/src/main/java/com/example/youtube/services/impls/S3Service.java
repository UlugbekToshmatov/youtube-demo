package com.example.youtube.services.impls;

import com.example.youtube.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service implements FileService {
    private final S3Client s3Client;
    private final String BUCKET_NAME = "youtube-demo";


    @Override
    public String uploadFile(MultipartFile multipartFile) {
        // prepare a key
        String filenameExtension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        String key = UUID.randomUUID() + "." + filenameExtension;

        try {
            putObject(key, multipartFile.getBytes());
        } catch (IOException e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, "Error while uploading file"
            );
        }

        // return file upload url
        return BUCKET_NAME + "/" + key;
    }

    private void putObject(String key, byte[] file) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
            .bucket(BUCKET_NAME)
            .key(key)
            .build();
        s3Client.putObject(objectRequest, RequestBody.fromBytes(file));
    }

    private byte[] getObject(String key) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
            .bucket(BUCKET_NAME)
            .key(key)
            .build();

        ResponseInputStream<GetObjectResponse> response = s3Client.getObject(getObjectRequest);
        try {
            return response.readAllBytes();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while reading data");
        }
    }
}
