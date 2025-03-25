package com.example.boardservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private LocalDateTime created_at;

    @Column(name = "email")
    private String email;

    @PrePersist //트랜잭션 이전에 작성일시는 초기화하기.
    public void onCreate(){
        this.created_at = LocalDateTime.now();
    }
}
