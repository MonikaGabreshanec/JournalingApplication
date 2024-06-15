package com.finki.journalingapplication.service.impl;

import com.finki.journalingapplication.model.Notes;
import com.finki.journalingapplication.model.User;
import com.finki.journalingapplication.repository.NotesRepository;
import com.finki.journalingapplication.service.NotesService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class NotesServiceImpl implements NotesService {
    private final NotesRepository notesRepository;

    public NotesServiceImpl(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    @Override
    public void deleteNotes(Long noteId) {
        this.notesRepository.deleteById(noteId);
    }

    @Override
    public List<Notes> getAllNotes(User user) {
        return notesRepository.findByUser(user);
    }

    @Override
    public Optional<Notes> findById(Long id) {
        return this.notesRepository.findById(id);
    }

    @Override
    public Optional<Notes> save(String title, String description, User user) {
        return Optional.of(this.notesRepository.save(new Notes(title, description, user)));
    }

    @Override
    public Optional<Notes> edit(Long id, String title, String description, User user) {
        Notes n = notesRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        n.setTitle(title);
        n.setDescription(description);
        LocalDateTime existingCreatedAt = n.getCreatedAt();
        n.setCreatedAt(existingCreatedAt);
        n.setUser(user);
        Notes updatedNote = notesRepository.save(n);
        return Optional.of(updatedNote);
    }
}
