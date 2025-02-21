package com.service;

import com.entity.DocumentEntity;
import com.entity.LessonEntity;
import com.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;

    public void deleteDocument(Long id){
        documentRepository.deleteById(id);
    }

    public void saveDocumentsWithLesson(Set<DocumentEntity> documents, LessonEntity lesson) {
        documents.forEach(document -> {
            document.setLesson(lesson);
            String content=document.getContent();
            document.setPlainContent(Jsoup.parse(content).text());
            this.documentRepository.save(document);
        });
    }

}