package com.example.app.mapper;

import com.example.app.domain.dto.BookDTO;
import com.example.app.domain.dto.Search;
import com.example.app.domain.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {

    //    도서 조회
    public BookDTO select(String isbn);
    //    도서 목록 조회
    public List<BookDTO> selectAll(@Param("cri")Criteria criteria, @Param("search")Search search);
    //    도서 추가
    public void insert(BookDTO bookDTO);
    //    도서 삭제
    public void delete(String isbn);
    //    도서 수정
    public void update(BookDTO bookDTO);

    public Long selectAllCount(@Param("search")Search search);
}
