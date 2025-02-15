package com.controller.api.v1;



import com.service.DocumentService;
import com.util.annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/documents")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;
    @DeleteMapping("/{id}")
    @ApiMessage("Xoá tài liệu thành công")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        this.documentService.deleteDocument(id);
        return ResponseEntity.ok().build();
    }
}
