package com.example.app.service;

import com.example.app.domain.dto.LendDTO;
import com.example.app.mapper.LendMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LendService {
    private  final LendMapper lendMapper;

    // 대여 조회
    public LendDTO getLendByLendSeq(Long lendSeq){
        return lendMapper.selectByLendSeq(lendSeq);
    }
    // 대여도서목록 조회(userId으로 조회)
    public List<LendDTO> getListUserId(String userId){
        return lendMapper.selectByUserId(userId);
    }
    // 대여도서목록 조회(isbn으로 조회)
    public List<LendDTO> getListIsbn(String isbn){
        return lendMapper.selectByUserId(isbn);
    }
    // 대여도서목록 조회(연체된 목록)
    public List<LendDTO> getListOverDue(){
        return lendMapper.selectOverDue();
    }
    // 대여도서목록 조회(전체 목록)
    public List<LendDTO> getListAll(){
        return lendMapper.selectAll();
    }

    // 대여하기
    public void write(LendDTO lendDTO){
        lendMapper.insert(lendDTO);
    }/*
    * public void write(String userId, String isbn){
    * lendMapper.insert(userId, isbn);}
    * */

    // 반납하기
    public void remove(Long lendSeq){
        lendMapper.delete(lendSeq);
    }/*
     * public void remove(String userId, String isbn){
     * lendMapper.delete(userId, isbn);}
     * */

    // 연장하기
    public void extend(String isbn, String userId){
        lendMapper.extend(isbn,userId);
    }/*
     * public void modify(String userId, String isbn){
     * lendMapper.update(userId, isbn);}
     * */

}
