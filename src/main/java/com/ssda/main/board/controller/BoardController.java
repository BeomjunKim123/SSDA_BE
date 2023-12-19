package com.ssda.main.board.controller;

import com.ssda.main.board.entity.Board;
import com.ssda.main.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;
    @PostMapping("/diary")
    public ResponseEntity<Void> makeBoardTitle(@RequestBody String title) {
        boardService.insertBoardTitle(title);
        return ResponseEntity.ok().build();
    }
}
