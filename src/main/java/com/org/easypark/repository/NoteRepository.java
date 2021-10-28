package com.org.easypark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.easypark.model.Note;


@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}
