package com.example.app.domain.paging;

import lombok.Data;

@Data
public class PageMakerDTO {

    //시작 페이지
    private int startPage;
    //끝 페이지
    private int endPage;
    //이전 페이지, 다음 페이지 존재 유무
    private boolean prev, next;
    //전체 게시물 수
    private Long total;
    //현재 페이지, 페이지 당 게시물 표시수 정보
    private Criteria criteria;


    public PageMakerDTO(Criteria criteria, Long total){
        this.criteria = criteria;
        this.total = total;

        //마지막 페이지
        this.endPage = (int) (Math.ceil(criteria.getPageNum()/10.0) * 10);
        //시작 페이지
        this.startPage = this.endPage - 9;
        //전체 마지막 페이지
        int realEnd = (int) Math.ceil(total * 1.0/criteria.getAmount());
        //전체 마지막 페이지가 화면에 보이는 마지막페이지보다 작은 경우, 보이는 페이지 값 조정
        if(realEnd < this.endPage){
            this.endPage = realEnd;
        }

        //시작 페이지 값이 1보다 큰경우 true
        this.prev = this.startPage > 1;
        //마지막 페이지 값이 1보다 큰 경우 true
        this.next = this.endPage < realEnd;
    }
}
