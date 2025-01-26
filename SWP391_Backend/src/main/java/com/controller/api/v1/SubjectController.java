package com.controller.api.v1;

import com.dto.request.SubjectRequest;
import com.dto.response.ApiResponse;
import com.dto.response.SubjectResponse;
import com.exception.custom.NotFoundException;
import com.service.SubjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubjectController {

    SubjectService subjectService;

    // Post
    @PostMapping
    public ResponseEntity<ApiResponse<List<SubjectResponse>>> getAllSubject() throws NotFoundException {
        List<SubjectResponse> subjectResponse = subjectService.getAllSubject();
        ApiResponse<List<SubjectResponse>> responseRestResponse = ApiResponse.<List<SubjectResponse>>builder()
                .data(subjectResponse)
                .message("Validation!")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseRestResponse);
    }

    @PostMapping("/create-subject")
    public ResponseEntity<ApiResponse<SubjectResponse>> createSubject(@RequestBody SubjectRequest request) throws NotFoundException {
        SubjectResponse subjectResponse = subjectService.createSubject(request);
        ApiResponse<SubjectResponse> responseRestResponse = ApiResponse.<SubjectResponse>builder()
                .data(subjectResponse)
                .message("Validation!")
                .status(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(responseRestResponse);
    }

    // Delete
    @DeleteMapping("/delete/{subjectId}")
    public ResponseEntity<ApiResponse<String>> deleteSubject(@PathVariable Long subjectId) throws NotFoundException {
        String  message = subjectService.deleteSubject(subjectId);
        ApiResponse<String> responseRestResponse = ApiResponse.<String>builder()
                .data(message)
                .message("Validation!")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseRestResponse);
    }

    //Get
    @GetMapping("/{subjectId}")
    ResponseEntity<ApiResponse<SubjectResponse>> getSubjectById(@PathVariable Long subjectId) throws NotFoundException {
        SubjectResponse subjectResponse = subjectService.getSubjectById(subjectId);
        ApiResponse<SubjectResponse> responseRestResponse = ApiResponse.<SubjectResponse>builder()
                .data(subjectResponse)
                .message("Validation!")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseRestResponse);
    }

    @GetMapping("/searchByName/{subjectName}")
    ResponseEntity<ApiResponse<SubjectResponse>> getSubjectByName(@PathVariable String subjectName) throws NotFoundException {
        SubjectResponse subjectResponse = subjectService.getSubjectByName(subjectName);
        ApiResponse<SubjectResponse> responseRestResponse = ApiResponse.<SubjectResponse>builder()
                .data(subjectResponse)
                .message("Validation!")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseRestResponse);
    }

    //Put
    @PutMapping("update-subject/{subjectId}")
    ResponseEntity<ApiResponse<SubjectResponse>> updateSubject(@PathVariable String subjectId, @RequestBody SubjectRequest request) throws NotFoundException {
        SubjectResponse subjectResponse = subjectService.updateSubject(subjectId, request);
        ApiResponse<SubjectResponse> responseRestResponse = ApiResponse.<SubjectResponse>builder()
                .data(subjectResponse)
                .message("Validation!")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseRestResponse);
    }



}
