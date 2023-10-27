package com.example.app.service;

import com.example.app.domain.dto.ReviewDTO;
import com.example.app.domain.dto.Search;
import com.example.app.domain.paging.Criteria;
import com.example.app.mapper.ReviewMapper;
import com.example.app.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;

    // 리뷰 조회
    @Transactional
    public ReviewDTO getBoard(Long revId){
        //데이터페이스에서 게시물 조회
        ReviewDTO reviewDTO = reviewMapper.select(revId);
        // 조회수 증가
        reviewRepository.incrementViewCount(revId);
        return reviewDTO;
    }
    // 리뷰 목록
    public List<ReviewDTO> getList(Criteria criteria, Search search){
        return reviewMapper.selectAll(criteria, search);
    }
    // 리뷰 추가
    public void write(ReviewDTO reviewDTO){
        reviewMapper.insert(reviewDTO);
    }
    // 리뷰 삭제
    @Transactional
    public void delete(Long revId){
        reviewMapper.delete(revId);
    }
    // 리뷰 수정
    public void modify(ReviewDTO reviewDTO){
        reviewMapper.update(reviewDTO);
    }

    // 리뷰 전체 개수 조회
    public Long getTotal(Search search){
        return reviewMapper.selectCountAll(search);
    }
}
