package com.ssda.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "diary_img")
@Getter
@Setter
public class DiaryImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "diary_id", referencedColumnName = "id")
    private Diary diary;

    @Column(name = "diary_img", nullable = true)
    private String diaryImg;
}
