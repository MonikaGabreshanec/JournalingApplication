package com.finki.journalingapplication.repository;

import com.finki.journalingapplication.model.ToDo;
import com.finki.journalingapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo,Long> {
    List<ToDo> findByUser(User user);
    List<ToDo> findByUserAndCompleted(User user,boolean completed);
}
