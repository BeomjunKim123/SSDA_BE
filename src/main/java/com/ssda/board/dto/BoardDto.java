package com.ssda.board.dto;

import lombok.Getter;
import lombok.Setter;

public class BoardDto {
    private Long id;
    private String boardName;
    private String newName;
    public Long getId() {
        return id;
    }
    public String getBoardName() {
        return boardName;
    }
    public String getNewName() {
        return newName;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }
    public void setNewName(String newName) {
        this.newName = newName;
    }
}
