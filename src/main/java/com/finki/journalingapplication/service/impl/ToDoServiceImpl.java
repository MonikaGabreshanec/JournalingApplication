package com.finki.journalingapplication.service.impl;

import com.finki.journalingapplication.model.ToDo;
import com.finki.journalingapplication.model.User;
import com.finki.journalingapplication.repository.ToDoRepository;
import com.finki.journalingapplication.service.ToDoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService {
    private final ToDoRepository toDoRepository;

    public ToDoServiceImpl(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Override
    public List<ToDo> getAllTodos(User user) {
        return toDoRepository.findByUser(user);
    }

    @Override
    public ToDo save(String title,User user) {
        ToDo toDo = new ToDo();
        toDo.setTitle(title);
        toDo.setCompleted(false);
        toDo.setUser(user);

        return toDoRepository.save(toDo);
    }
}
