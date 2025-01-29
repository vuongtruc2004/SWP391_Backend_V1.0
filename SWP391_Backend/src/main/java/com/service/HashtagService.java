package com.service;

import com.dto.response.HashtagResponse;
import com.entity.HashtagEntity;
import com.repository.HashtagRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
