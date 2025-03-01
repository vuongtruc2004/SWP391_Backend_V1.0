package com.service;

import com.dto.request.BlogRequest;
import com.dto.response.HashtagResponse;
import com.entity.HashtagEntity;
import com.repository.HashtagRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class HashtagService {

    private final HashtagRepository hashtagRepository;
    private final ModelMapper modelMapper;

    public HashtagService(HashtagRepository hashtagRepository, ModelMapper modelMapper) {
        this.hashtagRepository = hashtagRepository;
        this.modelMapper = modelMapper;
    }

    public List<HashtagResponse> getAllHashtags() {
        List<HashtagEntity> hashtagEntityList = hashtagRepository.findAll();
        return hashtagEntityList.stream()
                .map(hashtagEntity -> modelMapper.map(hashtagEntity, HashtagResponse.class))
                .toList();
    }

    public Set<HashtagEntity> saveAllHashtagsOfBlog(BlogRequest blogRequest) {
        Set<HashtagEntity> hashtagEntities = new HashSet<>();
        for (String hashTag : blogRequest.getHashtags()) {
            HashtagEntity hashtagEntity = hashtagRepository.findByTagName(hashTag);
            hashtagEntities.add(hashtagEntity);

        }
        return hashtagEntities;
    }
}
