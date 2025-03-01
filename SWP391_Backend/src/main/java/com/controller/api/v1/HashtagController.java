package com.controller.api.v1;

import com.dto.response.HashtagResponse;
import com.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hashtags")
@RequiredArgsConstructor
public class HashtagController {
    private final HashtagService hashtagService;
    @GetMapping("/all")
    public ResponseEntity<List<HashtagResponse>> getAllHashtags() {
        return ResponseEntity.ok(hashtagService.getAllHashtags());
    }
}
