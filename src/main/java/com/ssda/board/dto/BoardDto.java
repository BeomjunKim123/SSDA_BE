package com.ssda.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
    private Long id;
    private String boardName;
    public Long getId() {
        return id;
    }
    public String getBoardName() {
        return boardName;
    }
    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }
    public void setId(Long id) {
        this.id  = id;
    }
}
