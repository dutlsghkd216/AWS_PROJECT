package com.example.app.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class WishDTO {
    private Long wishId;
    private String wishTitle;
    private String wishAuthor;
    private String wishPublisher;
    private int wishYear;
    private String wishIsbn;
    private String wishNote;
    private String userId;

    private String wishRegdate;
}
