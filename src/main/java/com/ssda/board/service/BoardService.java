package com.ssda.board.service;

import com.ssda.board.dto.BoardDto;
import com.ssda.board.dto.DiaryDto;
import com.ssda.board.entity.Board;
import com.ssda.board.entity.Diary;
import com.ssda.board.entity.DiaryImg;
import com.ssda.board.repository.BoardRepository;
import com.ssda.board.repository.DiaryImgRepository;
import com.ssda.board.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final DiaryRepository diaryRepository;
    private final DiaryImgRepository diaryImgRepository;


    public void insertBoardTitle(String title) {
//        BoardDto boardDto = new BoardDto();
//        boardDto.setBoardName(title);
        Board board = new Board();
        board.setTitle(title);
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
    public List<Diary> getDiary() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(23, 59, 59);
        List<Diary> diaries = diaryRepository.findByRegDateBetween(startOfDay, endOfDay);
        return diaries;
    }
    public void addDiary(DiaryDto diaryDto) {
        LocalDateTime dateTime = LocalDateTime.parse(diaryDto.getRegDate());
        Diary diary = new Diary();
        diary.setDiaryName(diaryDto.getDiaryName());
        diary.setDiaryContent(diaryDto.getDiaryContent());
        diary.setRegDate(dateTime);

        List<DiaryImg> diaryImgs = new ArrayList<>();
        for(MultipartFile file : diaryDto.getDiaryImgs()) {
            DiaryImg diaryImg = new DiaryImg();
            diaryImgs.add(diaryImg);
            diaryImgRepository.save(diaryImg);
        }
         diaryRepository.save(diary);

    }
}
