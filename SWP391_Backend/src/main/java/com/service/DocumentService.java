package com.service;

import com.entity.DocumentEntity;
import com.entity.ChapterEntity;
import com.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;

    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }

    public void saveDocumentsWithChapter(List<DocumentEntity> documents, ChapterEntity chapter) {
        documents.forEach(document -> {
            document.setChapter(chapter);
            String content = document.getContent();
            document.setPlainContent(Jsoup.parse(content).text());
            this.documentRepository.save(document);
        });
    }

}