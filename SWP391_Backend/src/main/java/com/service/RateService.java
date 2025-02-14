package com.service;

import com.dto.response.PageDetailsResponse;
import com.dto.response.RateResponse;
import com.entity.RateEntity;
import com.exception.custom.CourseException;
import com.repository.RateRepository;
import com.util.BuildResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RateService {

    private final RateRepository rateRepository;
    private final ModelMapper modelMapper;

    public PageDetailsResponse<List<RateResponse>> getRateWithFilters(
            Long courseId,
            Pageable pageable,
            Specification<RateEntity> specification
    ) {
        if (courseId != null && courseId > 0) {
            specification = specification.and(((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("course").get("courseId"), courseId)));
        } else {
            throw new CourseException("ID của khóa học không hợp lệ!");
        }
        Page<RateEntity> page = rateRepository.findAll(specification, pageable);
        List<RateResponse> rateResponses = page.getContent()
                .stream().map(rateEntity -> modelMapper.map(rateEntity, RateResponse.class)).toList();
        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                rateResponses
        );
    }

    public Map<Integer, Integer> getNumberOfCoursesOfEachRate(Long courseId) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < 6; i++) {
            map.put(i, rateRepository.countRateEntitiesByCourse_CourseIdAndStars(courseId, i));
        }
        return map;
    }
}
