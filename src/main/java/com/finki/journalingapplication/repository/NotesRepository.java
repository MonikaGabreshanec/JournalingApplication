package com.finki.journalingapplication.repository;

import com.finki.journalingapplication.model.Notes;
import com.finki.journalingapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotesRepository extends JpaRepository<Notes,Long> {
    List<Notes> findByUser(User user);
}
