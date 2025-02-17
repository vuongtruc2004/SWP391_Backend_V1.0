package com.helper;


import com.dto.response.DocumentResponse;
import com.entity.DocumentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DocumentServiceHelper {
    public Set<DocumentResponse> mapToResponseSet(Set<DocumentEntity> documentEntities) {
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
                .collect(Collectors.toSet());
    }
}
