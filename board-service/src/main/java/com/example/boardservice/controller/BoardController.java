package com.example.boardservice.controller;

import com.example.boardservice.entity.Board;
import com.example.boardservice.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final StringRedisTemplate redisTemplate;

    @GetMapping
    public ResponseEntity<List<Board>> getBoard(@RequestHeader("Authorization") String token) {
        System.out.println(token);

        if(token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        String email = redisTemplate.opsForValue().get(token);

        if(email == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


        List<Board> boards = boardService.getBoardByEmail(email);

        return ResponseEntity.ok(boards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.getBoardById(id));
    }

    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody Board board,
                                             @RequestHeader("Authorization") String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String pureToken = token.substring(7);

        //Redis에서 이메일 탐색
        String email = redisTemplate.opsForValue().get(pureToken);
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        board.setEmail(email);

        return ResponseEntity.ok(boardService.createBoard(board));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long id ,@RequestBody Board updatedBoard){
        return ResponseEntity.ok(boardService.updateBoard(id, updatedBoard));
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }
}
