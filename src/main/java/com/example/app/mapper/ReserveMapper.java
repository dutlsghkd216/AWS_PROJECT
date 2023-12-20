package com.example.app.mapper;

import com.example.app.domain.dto.LendDTO;
import com.example.app.domain.dto.ReserveDTO;
import com.example.app.domain.dto.Search;
import com.example.app.domain.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReserveMapper {

    //도서 예약 조회
    public ReserveDTO selectByReserveSeq(Long reserveSeq);

    // 내 예약도서 조회
    public List<ReserveDTO> selectByUserid(String userId);

    //도서 예약 특정 날짜 & 특정 위치 전체 조회
    public List<ReserveDTO> selectAllByReserveDate(@Param("cri") Criteria criteria, @Param("search") Search search, String reserveDate);

    //도서 예약(insert)
    public void insert(ReserveDTO reserveDTO);

    //도서 예약 취소(update) 예약, 취소, 완료
    public void update(Long reserveSeq);



    // 예약시 제약사항
    public int countByIsbnAndUserId(String isbn, String userId);
    public int countByUserId(String userId);

    // 총 예약건수 조회
    public Long selectCountAll(@Param("search")Search search, String reserveDate);



}
