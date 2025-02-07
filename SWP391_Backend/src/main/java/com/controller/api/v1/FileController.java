package com.controller.api.v1;

import com.dto.response.ApiResponse;
import com.exception.custom.StorageException;
import com.service.FileService;
import com.util.annotation.ApiMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FileController {
    @Value("${assets.uri}")
    private String assetURI;
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/files")

    public ResponseEntity<ApiResponse<String>> upload(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam("folder") String folder) throws URISyntaxException, IOException {
        if(file == null || file.isEmpty()){
            throw new StorageException("File bị rỗng");
        }
        String fileName = file.getOriginalFilename();
        List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png");
        boolean isValid = allowedExtensions.stream().anyMatch(item -> {
            assert fileName != null;
            return fileName.toLowerCase().endsWith(item);
        });
        if(!isValid){
            throw new StorageException("File không hợp lệ, chọn một trong các loại:  " + allowedExtensions.toString());
        }
        // create directory if not exist
        this.fileService.createDirectory(assetURI + folder);

        // storage file

        return ResponseEntity.ok().body(this.fileService.upload(file, folder));
    }



}
