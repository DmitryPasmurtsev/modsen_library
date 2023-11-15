package com.modsen.library.repositories;

import com.modsen.library.model.LibraryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<LibraryEntry,Long> {
    List<LibraryEntry> findAllByIssueDateIsNull();
}
