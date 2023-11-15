package com.modsen.books.repositories;

import com.modsen.books.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    Optional<Author> findByNameAndSurnameAndPatronymic(String name, String surname, String patronymic);
}
