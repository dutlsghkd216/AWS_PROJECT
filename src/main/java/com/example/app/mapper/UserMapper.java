package com.example.app.mapper;

import com.example.app.domain.dto.Search;
import com.example.app.domain.dto.UserDTO;
import com.example.app.domain.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.List;

@Mapper

public interface UserMapper {


    // 회원 조회 및 아이디중복체크
    public UserDTO findByUid(String userId);

    // 회원 목록 조회
    public List<UserDTO> findAll(@Param("cri") Criteria criteria, @Param("search") Search search);

    // 전체 게시글 갯수
    public Long selectAllCount(@Param("search")Search search);

    // 회원 등록
    public void register(UserDTO userDTO);

    // 회원 삭제
    public void delete(String userId);

    // 회원 정보 수정
    public void update(UserDTO userDTO);

    //비밀번호 조회
    public String findPW(String userId);
    // 비밀번호 변경
    public void updatePW(String userId, String userPw);


}