package com.ssda.board.service;

import com.ssda.board.dto.BoardDto;
import com.ssda.board.entity.Board;
import com.ssda.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
        Optional<Board> existingBoard = boardRepository.findByTitle(boardDto.getBoardName());
        if(existingBoard.isPresent()) {
            Board board = existingBoard.get();
            Optional<Board> checkBoard = boardRepository.findByTitle(boardDto.getNewName());
            if(checkBoard.isPresent() && !checkBoard.get().getId().equals(board.getId())) {
                throw new IllegalStateException("새 제목은 이미 사용중입니다....");
            }
            board.setTitle(boardDto.getNewName());
            boardRepository.save(board);
            return board;
        } else {
            throw new IllegalStateException("해당 제목의 보드를 찾을 수 없음.....");
        }
    }
}
