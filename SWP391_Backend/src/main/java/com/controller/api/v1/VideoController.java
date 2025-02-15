package com.controller.api.v1;


import com.service.VideoService;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/videos")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;
    @DeleteMapping("/{id}")
    @ApiMessage("Xoá video thành công")
    public ResponseEntity<Void> deleteVideo(@PathVariable Long id) {
        this.videoService.deleteVideo(id);
        return ResponseEntity.ok().build();
    }
}
