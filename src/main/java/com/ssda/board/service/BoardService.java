package com.ssda.board.service;

import com.ssda.board.dto.BoardDto;
import com.ssda.board.entity.Board;
import com.ssda.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;



    public void insertBoardTitle(String title) {
        BoardDto boardDto = new BoardDto();
        boardDto.setBoardName(title);
        Board board = new Board();
        board.setTitle(boardDto.getBoardName());
        System.out.println(board);
        boardRepository.save(board);
    }
    public void deleteBoard(String name) {
        Optional<Board> deleteName = boardRepository.findByTitle(name);
        if(deleteName.isPresent()) {
            boardRepository.delete(deleteName.get());
        } else {
            throw new IllegalStateException("해당 일기장 제목 없잖아 ㅡㅡ");
        }
    }
    public List<Board> getBoard() {
        return boardRepository.findAll();
    }
    public Board updateBoard(BoardDto boardDto) {
        Optional<Board> checkName = boardRepository.findByTitle(boardDto.getBoardName());
        if(checkName.isPresent()) {
            throw new IllegalStateException("해당 일기장 이름은 이미 존재하는 이름이다.");
        }
        Optional<Board> updateName = boardRepository.findById(boardDto.getId());
        if (updateName.isPresent()) {
            Board board = updateName.get();
            board.setTitle(boardDto.getBoardName());
            boardRepository.save(board);
            return board;
        } else {
            throw new IllegalArgumentException("해당 일기장을 찾을 수 없습니다.");
        }
    }
}
