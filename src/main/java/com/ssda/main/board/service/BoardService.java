package com.ssda.main.board.service;

import com.ssda.main.board.entity.Board;
import com.ssda.main.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;



    public void insertBoardTitle(String title) {
        Board board = new Board();
        board.setTitle(title);
        boardRepository.save(board);
    }
}
