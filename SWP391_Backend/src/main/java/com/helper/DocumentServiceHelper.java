package com.helper;

import com.dto.response.DocumentResponse;
import com.entity.DocumentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DocumentServiceHelper {

    public static List<DocumentResponse> mapToResponseList(List<DocumentEntity> documentEntities) {
        return documentEntities.stream()
                .map(document -> DocumentResponse.builder()
                        .documentId(document.getDocumentId())
                        .title(document.getTitle())
                        .content(document.getContent())
                        .plainContent(document.getPlainContent())
                        .createdAt(document.getCreatedAt())
                        .updatedAt(document.getUpdatedAt())
                        .build()
                )
                .toList();
    }
}