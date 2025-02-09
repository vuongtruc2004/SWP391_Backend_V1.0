package com.controller.api.v1;

import com.dto.response.ApiResponse;
import com.exception.custom.StorageException;
import com.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
public class FileController {

    @Value("${assets.uri}")
    private String assetURI;

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/image")
    public ResponseEntity<ApiResponse<String>> uploadImage(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam(name = "folder") String folder
    ) throws URISyntaxException, IOException {
        if (file == null || file.isEmpty()) {
            throw new StorageException("File bị rỗng");
        }
        String fileName = file.getOriginalFilename();
        List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png");
        boolean isValid = allowedExtensions.stream().anyMatch(item -> {
            assert fileName != null;
            return fileName.toLowerCase().endsWith(item);
        });
        if (!isValid) {
            throw new StorageException("File không hợp lệ, chọn một trong các loại:  " + allowedExtensions.toString());
        }
        fileService.createDirectory(assetURI + folder);
        return ResponseEntity.ok().body(fileService.uploadImage(file, folder));
    }
    
}
