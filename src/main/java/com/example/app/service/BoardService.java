package com.example.app.service;

import com.example.app.domain.dto.BoardDTO;
import com.example.app.domain.dto.Search;
import com.example.app.domain.paging.Criteria;
import com.example.app.mapper.BoardMapper;
import com.example.app.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;
    private final BoardRepository boardRepository;

    // 게시글 조회
    @Transactional
    public BoardDTO getBoard(Long anId){
        // 데이터베이스에서 게시물 조회
        BoardDTO boardDTO = boardMapper.select(anId);

        // 조회수 증가 (Repository를 통해 업데이트)
        boardRepository.incrementViewCount(anId);

        return boardDTO;
//        return boardmapper.select(anId);
    }

    // 게시글 목록
    public List<BoardDTO> getList(Criteria criteria, Search search){
        return boardMapper.selectAll(criteria, search);
    }
    // 게시글 추가
    public void write(BoardDTO boardDTO){
        boardMapper.insert(boardDTO);
    }
    // 게시글 삭제
    @Transactional
    public void delete(Long anId){
        boardMapper.delete(anId);
    }
    // 게시글 수정
    public void modify(BoardDTO boardDTO){
        boardMapper.update(boardDTO);
    }

    //    게시글 전체 개수 조회
    public Long getTotal(Search search){
        return boardMapper.selectCountAll(search);
    }
}
