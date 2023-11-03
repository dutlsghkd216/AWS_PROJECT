package com.example.app.mapper;

import com.example.app.domain.dto.ReviewDTO;
import com.example.app.domain.dto.Search;
import com.example.app.domain.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {

    // 리뷰 조회
    public ReviewDTO select(Long revId);
    // 리뷰 목록
    public List<ReviewDTO> selectAll(@Param("cri")Criteria criteria, @Param("search")Search search);
    // 리뷰 추가
    public void insert(ReviewDTO reviewDTO);
    // 리뷰 삭제
    public void delete(Long revId);
    // 리뷰 수정
    public void update(ReviewDTO reviewDTO);
    // 전체 리뷰 갯수
    public Long selectCountAll(@Param("search")Search search);


}
