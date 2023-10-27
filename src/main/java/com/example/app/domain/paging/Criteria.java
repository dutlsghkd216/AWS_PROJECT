package com.example.app.domain.paging;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Criteria {
    //현재 페이지
    private int pageNum;
    //한페이지당 보여질 게시물 갯수
    private int amount;
    //스킵 할 게시물 수 ((pageNum -1) * amount)
    private int skip;


    //기본 생성자
    public Criteria(){
        this(1,10);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
        this.skip = (pageNum -1) * amount;
    }

    public void setPageNum(int pageNum) {
        this.skip = (pageNum -1 )*this.amount;
        this.pageNum = pageNum;
    }
    public void setAmount(int amount){
        this.skip = (pageNum -1) * amount;
        this.amount = amount;
    }

}
