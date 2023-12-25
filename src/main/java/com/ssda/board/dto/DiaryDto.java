package com.ssda.board.dto;

import com.ssda.board.entity.DiaryImg;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class DiaryDto {
    private Long id;
    private String diaryName;
    private String diaryContent;
    private String regDate;
    private List<MultipartFile> diaryImgs;
}
