package com.example.app.mapper;

import com.example.app.domain.dto.LendDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LendMapper {

    // 대여도서 조회(대여번호로 조회)
    public LendDTO selectByLendSeq(Long lendSeq);

    // 대여도서 조회(userId으로 조회)
    public List<LendDTO> selectByUserId(String userId);

    // 대여 조회(isbn으로 조회)
    public List<LendDTO> selectByIsbn(String isbn);

    // 연체 도서 조회
    public List<LendDTO> selectOverDue();

    // 모든 도서 대여 조회
    public List<LendDTO> selectAll();


    // 도서 대여 추가
    public void insert(LendDTO lendDTO);
    /*
    * public void insert(@Param("userId) String userId, @Param("isbn") String isbn);
    * */

    // 도서 반납
    public void delete(Long lendSeq);
    /*
    * public void delete(@Param("userId") String userId, @Param("isbn") String isbn);
    * */

    // 도서 연장
    public void extend(String isbn, String userId);
    /*
     * public void update(@Param("userId") String userId, @Param("isbn") String isbn);
     * */


}
