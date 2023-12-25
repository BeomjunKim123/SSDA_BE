package com.ssda.board.controller;

import com.ssda.board.dto.BoardDto;
import com.ssda.board.dto.DiaryDto;
import com.ssda.board.entity.Board;
import com.ssda.board.entity.Diary;
import com.ssda.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin("https://editor.swagger.io")
public class BoardController {

    private final BoardService boardService;
    @PostMapping("/addBoard")
    public ResponseEntity<Void> makeBoardTitle(@RequestBody BoardDto boardName) {
        String name = boardName.getBoardName();
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
    @GetMapping("getDiary")
    public ResponseEntity<List<Diary>> getDiary() {
        List<Diary> diaries = boardService.getDiary();
        return ResponseEntity.ok(diaries);
    }
    @PostMapping("addDiary")
    public ResponseEntity<Diary> addDiary(@ModelAttribute DiaryDto diaryDto) {
        System.out.println(diaryDto.getDiaryName());
        System.out.println(diaryDto.getDiaryContent());
        System.out.println(diaryDto.getDiaryImgs());
        System.out.println(diaryDto.getRegDate());
        boardService.addDiary(diaryDto);
        return ResponseEntity.ok().build();
    }
}
