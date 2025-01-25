package com.service;


import com.dto.Meta;
import com.dto.ResultPaginationDTO;
import com.entity.CourseEntity;
import com.repository.custom.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    public ResultPaginationDTO findAllCourses(Specification<CourseEntity> courseSpecification, Pageable pageable) {
        Page<CourseEntity> coursePage = this.courseRepository.findAll(courseSpecification,pageable);
        ResultPaginationDTO resultPaginationDTO=new ResultPaginationDTO();
        Meta meta=new Meta();
        meta.setPage(pageable.getPageNumber()+1);
        meta.setPageSize(pageable.getPageSize());
        meta.setPages(coursePage.getTotalPages());
        meta.setTotal(coursePage.getTotalElements());
        resultPaginationDTO.setMeta(meta);
        resultPaginationDTO.setResult(coursePage.getContent());
        return resultPaginationDTO;
    }


}
