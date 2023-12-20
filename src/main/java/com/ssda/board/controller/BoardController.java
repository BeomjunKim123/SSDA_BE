package com.ssda.board.controller;

import com.ssda.board.dto.BoardDto;
import com.ssda.board.entity.Board;
import com.ssda.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;
    @PostMapping("/addBoard")
    public ResponseEntity<Void> makeBoardTitle(@RequestBody BoardDto boardName) {
        String name = boardName.getBoardName();
        System.out.println(name);
        boardService.insertBoardTitle(name);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/deleteBoard")
    public ResponseEntity<Void> deleteBoard(@RequestBody BoardDto boardDto) {
        String name = boardDto.getBoardName();
        boardService.deleteBoard(name);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/getBoard")
    public ResponseEntity<List<Board>> getBoard() {
        List<Board> boards = boardService.getBoard();
        return ResponseEntity.ok(boards);
    }
    @PostMapping("/updateBoard")
    public ResponseEntity<Board> updateBoard(@RequestBody BoardDto boardDto) {
        Board updatedBoard = boardService.updateBoard(boardDto);
        return ResponseEntity.ok(updatedBoard);
    }
}
