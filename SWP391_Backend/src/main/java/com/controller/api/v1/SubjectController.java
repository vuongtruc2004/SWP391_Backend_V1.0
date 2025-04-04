package com.controller.api.v1;

import com.dto.request.SubjectRequest;
import com.dto.response.ApiResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.SubjectResponse;
import com.entity.SubjectEntity;
import com.service.SubjectService;
import com.turkraft.springfilter.boot.Filter;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @ApiMessage("Lấy các lĩnh vực thành công!")
    @GetMapping
    public ResponseEntity<PageDetailsResponse<List<SubjectResponse>>> getSubjects(Pageable pageable) {
        return ResponseEntity.ok(subjectService.getSubjects(pageable));
    }

    @ApiMessage("Lấy các lĩnh vực thành công!")
    @GetMapping("/courses")
    public ResponseEntity<PageDetailsResponse<List<SubjectResponse>>> getSubjectsSortByNumberOfCourses(Pageable pageable) {
        return ResponseEntity.ok(subjectService.getSubjectsSortByNumberOfCourses(pageable));
    }

    @ApiMessage("Lấy các lĩnh vực thành công!")
    @GetMapping("/all")
    public ResponseEntity<PageDetailsResponse<List<SubjectResponse>>> getSubjectsWithFilter(
            Pageable pageable,
            @Filter Specification<SubjectEntity> specification
    ) {
        return ResponseEntity.ok(subjectService.getSubjectsWithFilter(pageable, specification));
    }

    @ApiMessage("Xóa lĩnh vực thành công!")
    @DeleteMapping("/delete/{subjectId}")
    public ResponseEntity<ApiResponse<String>> deleteSubject(@PathVariable Long subjectId) {
        return ResponseEntity.ok(subjectService.deleteSubject(subjectId));
    }

    @ApiMessage("Thay đổi thông tin công nghệ thành công!")
    @PatchMapping("/update")
    public ResponseEntity<SubjectResponse> updateSubject(@RequestBody SubjectRequest subjectRequest) {
        return ResponseEntity.ok(subjectService.updateSubject(subjectRequest));
    }

    @ApiMessage("Tạo công nghệ thành công!")
    @PostMapping
    public ResponseEntity<ApiResponse<SubjectResponse>> createSubject(@RequestBody SubjectRequest subjectRequest) {
        return ResponseEntity.ok(subjectService.createSubject(subjectRequest));
    }

    @ApiMessage("Cập nhật ảnh bìa công nghệ thành công")
    @PostMapping("/thumbnail")
    public ResponseEntity<ApiResponse<String>> uploadAvatar(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam(name = "folder") String folder) throws URISyntaxException, IOException {
        return ResponseEntity.ok(subjectService.updateThumbnail(file, folder));
    }

    @GetMapping("/all-inpagination")
    public ResponseEntity<List<SubjectResponse>> getSubjectsWithInPagination() {
        return ResponseEntity.ok().body(this.subjectService.getAllSubjectInPagination());
    }
}
