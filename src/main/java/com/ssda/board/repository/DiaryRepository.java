package com.ssda.board.repository;

import com.ssda.board.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByRegDate(LocalDate regDate);
    List<Diary> findByRegDateBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
