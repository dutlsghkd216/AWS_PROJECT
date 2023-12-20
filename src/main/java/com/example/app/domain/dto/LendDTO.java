package com.example.app.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LendDTO {
    private Long lendSeq;
    private String isbn;
    private String userId;
    private String lendDate;
    private String dueDate;
    private String extensionCnt;
    private String lendStatus;
    private String lateDays;

    private String bookTitle;
    private String author;
    private String publisher;
    private int pubYear;
    private String location;
    private String reserveDate;


}
