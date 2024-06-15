package com.finki.journalingapplication.repository;

import com.finki.journalingapplication.model.Diary;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary,Long> {
    List<Diary> findByUserId(Long userId);
    List<Diary> findByUserIdAndDate(Long userId, LocalDate date);

}
