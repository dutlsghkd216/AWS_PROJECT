package com.example.app.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReserveDTO {

    private Long reserveSeq;
    private String isbn;
    private String bookTitle;
    private String author;
    private String publisher;
    private int pubYear;
    private String userId;
    private String reserveDate;
    private String reserveStatus;
    private String location;
    private String reserveMakeDate;

}
