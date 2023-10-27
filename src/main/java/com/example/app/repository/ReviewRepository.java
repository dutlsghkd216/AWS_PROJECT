package com.example.app.repository;

import com.example.app.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
    public interface ReviewRepository extends JpaRepository<Review, Long> {


    @Modifying
    @Query("UPDATE Review r SET r.revViewCnt = r.revViewCnt + 1 WHERE r.revId = :revId")
    int incrementViewCount(Long revId);
}
