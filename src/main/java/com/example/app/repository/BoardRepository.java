package com.example.app.repository;

import com.example.app.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
    public interface BoardRepository extends JpaRepository<Board, Long> {
    @Modifying
    @Query("UPDATE Board b SET b.anViewCnt = b.anViewCnt + 1 WHERE b.anId = :anId")
//    void incrementViewCount(@Param("anId") Long anId);
    int incrementViewCount(Long anId);

}
