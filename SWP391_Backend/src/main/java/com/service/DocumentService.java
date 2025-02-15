package com.service;

import com.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;

    public void deleteDocument(Long id){
        documentRepository.deleteById(id);
    }

}
