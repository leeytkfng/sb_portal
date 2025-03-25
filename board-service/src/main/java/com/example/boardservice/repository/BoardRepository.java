package com.example.boardservice.repository;

import com.example.boardservice.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {

    @Query("select b from Board b where b.email = :email")
    List<Board> findByEmail(@Param("email") String email);
}
