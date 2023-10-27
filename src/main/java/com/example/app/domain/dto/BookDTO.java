package com.example.app.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BookDTO {

    private String isbn;
    private String bookTitle;
    private String Author;
    private String publisher;
    private int pubYear;

}
