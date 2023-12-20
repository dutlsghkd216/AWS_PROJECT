package com.example.app.service;

import com.example.app.domain.dto.ReserveDTO;
import com.example.app.domain.dto.Search;
import com.example.app.domain.paging.Criteria;
import com.example.app.mapper.ReserveMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReserveService {

    private final ReserveMapper reserveMapper;

    //도서 예약 조회
    public ReserveDTO selectByReserveSeq(Long reserveSeq){
        return reserveMapper.selectByReserveSeq(reserveSeq);
    }
    public List<ReserveDTO> getListUserId(String userId){
        return reserveMapper.selectByUserid(userId);
    }

    //도서 예약 전체 조회
    public List<ReserveDTO> selectAll(Criteria criteria,Search search, String reserveDate){
        return reserveMapper.selectAllByReserveDate(criteria, search, reserveDate);
    }

    //도서 예약(insert)
    public void insert(ReserveDTO reserveDTO){
        reserveMapper.insert(reserveDTO);
        System.out.println("Inserting reservation: " + reserveDTO);
    }

    //도서 예약 취소(delete)
    public void delete(Long reserveSeq){
        reserveMapper.update(reserveSeq);
    }

    // 예약 제약사항 확인
    public boolean isDuplicateReservation(ReserveDTO reserveDTO) {
        // 중복 예약 확인 로직
        int count = reserveMapper.countByIsbnAndUserId(reserveDTO.getIsbn(), reserveDTO.getUserId());
        return count > 0;
    }

    public int getReservationCountByUsername(String username) {
        // 해당 사용자의 예약 개수 조회 로직
        return reserveMapper.countByUserId(username);
    }

    public Long selectCountAll(@Param("search")Search search, String reserveDate){
        return reserveMapper.selectCountAll(search, reserveDate);
    }

}
