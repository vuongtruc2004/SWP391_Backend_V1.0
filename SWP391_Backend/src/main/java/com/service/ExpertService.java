package com.service;

import com.dto.response.ExpertResponse;
import com.dto.response.PageDetailsResponse;
import com.dto.response.details.ExpertDetailsResponse;
import com.entity.ExpertEntity;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.exception.custom.UserException;
import com.helper.ExpertServiceHelper;
import com.helper.UserServiceHelper;
import com.repository.ExpertRepository;
import com.util.BuildResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ExpertService {
    private final ExpertRepository expertRepository;
    private final ModelMapper modelMapper;
    private final ExpertServiceHelper expertServiceHelper;
    private final UserServiceHelper userServiceHelper;

    public PageDetailsResponse<List<ExpertResponse>> getExpertsHaveCourses(Pageable pageable) {
        Page<ExpertEntity> page = expertRepository.findAllByCoursesIsNotEmpty(pageable);
        List<ExpertResponse> expertResponses = page.getContent()
                .stream().map(expertEntity -> {
                    ExpertResponse expertResponse = modelMapper.map(expertEntity, ExpertResponse.class);
                    expertResponse.setTotalCourses(expertEntity.getCourses().size());
                    return expertResponse;
                })
                .toList();

        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                expertResponses
        );
    }

    public ExpertDetailsResponse getExpertById(Long userId) {
        ExpertEntity expertEntity = expertRepository.findByUser_UserId(userId).
                orElseThrow(() -> new NotFoundException("Expert không tìm thấy"));
        return expertServiceHelper.convertToExpertDetailsResponse(expertEntity);

    }
    public void followExpert(Long expertId){
        UserEntity follower=userServiceHelper.extractUserFromToken();
        if(follower==null){
            throw new UserException("Bạn cần đăng nhập để thực hiện chức năng này!");
        }
        ExpertEntity expert=this.expertRepository.findById(expertId).orElseThrow(()->new NotFoundException("Không tìm thấy chuyên gia!"));
        Set<UserEntity> currentFollowers=expert.getUsers();
        if(currentFollowers.contains(follower)){
            throw new UserException("Bạn đã theo dõi chuyên gia này!");
        }
        currentFollowers.add(follower);
        expert.setUsers(currentFollowers);
        expertRepository.save(expert);
    }
}
