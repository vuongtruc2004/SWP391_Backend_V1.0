package com.controller.api.v1;

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

}
