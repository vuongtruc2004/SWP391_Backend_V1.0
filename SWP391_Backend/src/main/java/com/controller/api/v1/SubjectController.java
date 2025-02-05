package com.controller.api.v1;

import com.dto.response.CourseResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.SubjectResponse;
import com.service.SubjectService;
import com.util.annotation.ApiMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<PageDetailsResponse<List<SubjectResponse>>> getAllSubjects(
            Pageable pageable
    ) {
        return ResponseEntity.ok(subjectService.getAllSubject(pageable));
    }
}
