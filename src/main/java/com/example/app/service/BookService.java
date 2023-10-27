package com.example.app.service;

import com.example.app.domain.dto.BookDTO;
import com.example.app.domain.dto.Search;
import com.example.app.domain.paging.Criteria;
import com.example.app.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookMapper bookMapper;

    //    도서 조회
    public BookDTO getBook(String isbn){
        return bookMapper.select(isbn);
    }
    //    도서 목록 조회
    public List<BookDTO> getList(Criteria criteria, Search search){
        return bookMapper.selectAll(criteria, search);
    }
    //    도서 추가
    public void write(BookDTO bookDTO){
        bookMapper.insert(bookDTO);
    }
    //    도서 삭제
    public void remove(String isbn){
        bookMapper.delete(isbn);
    }
    //    도서 수정
    public void modify(BookDTO bookDTO){
        bookMapper.update(bookDTO);
    }
    // 총 도서 수
    public Long getListCount(Search search){
        return bookMapper.selectAllCount(search);
    }
}
