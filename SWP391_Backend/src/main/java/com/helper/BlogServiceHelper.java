package com.helper;

import com.dto.response.BlogResponse;
import com.dto.response.PageDetailsResponse;
import com.entity.BlogEntity;
import com.util.BuildResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BlogServiceHelper {

    private final ModelMapper modelMapper;

    public PageDetailsResponse<List<BlogResponse>> getListPageDetailsResponse(Page<BlogEntity> page, BlogEntity currentBlogEntity) {
        List<BlogResponse> blogResponseList = new ArrayList<>(page.getContent().stream()
                .map(blogEntity -> {
                    BlogResponse blogResponse = modelMapper.map(blogEntity, BlogResponse.class);
                    blogResponse.setTotalLikes(blogEntity.getLikes().size());
                    blogResponse.setTotalComments(blogEntity.getComments().size());
                    return blogResponse;
                })
                .toList());

        if (currentBlogEntity != null) {
            blogResponseList.add(modelMapper.map(currentBlogEntity, BlogResponse.class));
        }

        return BuildResponse.buildPageDetailsResponse(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                blogResponseList
        );
    }

    public BlogResponse convertToBlogResponse(BlogEntity blogEntity) {
        BlogResponse blogResponse = modelMapper.map(blogEntity, BlogResponse.class);
        blogResponse.setTotalLikes(blogEntity.getLikes().size());
        blogResponse.setTotalComments(blogEntity.getComments().size());
        return blogResponse;
    }

}
