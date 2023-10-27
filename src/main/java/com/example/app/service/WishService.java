package com.example.app.service;

import com.example.app.domain.dto.WishDTO;
import com.example.app.mapper.WishMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishService {
    private final WishMapper wishMapper;

    // 신청 도서 정보 추가
    public void write(WishDTO wishDTO){
        wishMapper.insert(wishDTO);
    }

    // 신청 도서 조회
    public List<WishDTO> getWish(String userId){
        return wishMapper.select(userId);
    }
    // 신청 도서 목록 조회
    public List<WishDTO> getList(){return wishMapper.selectAll();}

    // 신청 도서 삭제
    public void remove(Long wishId){wishMapper.delete(wishId);}

    // 신청 도서 수정
    public void modify(WishDTO wishDTO){wishMapper.update(wishDTO);}

}
