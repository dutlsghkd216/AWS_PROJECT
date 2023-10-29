package com.example.app.mapper;

import com.example.app.domain.dto.WishDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WishMapper {

    // 신청 도서 정보 추가
    public void insert(WishDTO wishDTO);

    // 신청 내역 조회
    public List<WishDTO> select(String userId);

    // 신청 내역 목록 조회
    public List<WishDTO> selectAll();

    // 신청 도서 삭제
    public void delete(Long wishId);

    // 신청 도서 수정
    public void update(WishDTO wishDTO);

}
