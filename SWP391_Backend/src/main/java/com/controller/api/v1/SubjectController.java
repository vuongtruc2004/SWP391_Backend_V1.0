package com.controller.api.v1;

import com.dto.request.SubjectRequest;
import com.dto.response.ApiResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.SubjectResponse;
import com.entity.SubjectEntity;
import com.service.SubjectService;
import com.turkraft.springfilter.boot.Filter;
import com.util.annotation.ApiMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @ApiMessage("Lấy các môn học thành công!")
    @GetMapping
    public ResponseEntity<PageDetailsResponse<List<SubjectResponse>>> getSubjects(Pageable pageable) {
        return ResponseEntity.ok(subjectService.getSubjects(pageable));
    }

    @ApiMessage("Lấy các môn học thành công không phân trang!")
    @GetMapping("/all-inpagination")
    public ResponseEntity<List<SubjectResponse>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @ApiMessage("Lấy các môn học thành công!")
    @GetMapping("/courses")
    public ResponseEntity<PageDetailsResponse<List<SubjectResponse>>> getSubjectsSortByNumberOfCourses(Pageable pageable) {
        return ResponseEntity.ok(subjectService.getSubjectsSortByNumberOfCourses(pageable));
    }

    @ApiMessage("Lấy các môn học thành công!")
    @GetMapping("/all")
    public ResponseEntity<PageDetailsResponse<List<SubjectResponse>>> getSubjectsWithFilter(
            Pageable pageable,
            @Filter Specification<SubjectEntity> specification
    ) {
        return ResponseEntity.ok(subjectService.getSubjectsWithFilter(pageable, specification));
    }

    @ApiMessage("Xóa môn học thành công!")
    @DeleteMapping("/delete/{subjectId}")
    public ResponseEntity<ApiResponse<String>> deleteSubject(@PathVariable Long subjectId) {
        return ResponseEntity.ok(subjectService.deleteSubject(subjectId));
    }

    @ApiMessage("Thay đổi thông tin môn học thành công!")
    @PatchMapping("/update/{subjectId}")
    public ResponseEntity<ApiResponse<SubjectResponse>> updateSubject(@PathVariable Long subjectId, SubjectRequest subjectRequest) {
        return ResponseEntity.ok(subjectService.updateSubject(subjectId, subjectRequest));
    }

}
