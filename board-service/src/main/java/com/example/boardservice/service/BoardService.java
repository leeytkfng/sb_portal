package com.example.boardservice.service;

import com.example.boardservice.client.UserClient;
import com.example.boardservice.dto.UserDto;
import com.example.boardservice.entity.Board;
import com.example.boardservice.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserClient userClient;

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board getBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재치 않습니다."));
    }

    public List<Board> getBoardByEmail(String email) {
        System.out.println(email);
        UserDto user = userClient.getUserByEmail(email); //Feign 호출
        System.out.println(user);
        List<Board> boards = boardRepository.findByEmail(user.getEmail());
        System.out.println(boards.size());

        return boards;
    }

    @Transactional
    public Board createBoard(Board board){
        return boardRepository.save(board);
    }

    @Transactional
    public Board updateBoard(Long id,Board updatedBoard){
        Board board = getBoardById(id);

        board.setTitle(updatedBoard.getTitle());
        board.setContent(updatedBoard.getContent());

        return boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Long id){
        Board board = getBoardById(id);
        boardRepository.delete(board);
    }
}
