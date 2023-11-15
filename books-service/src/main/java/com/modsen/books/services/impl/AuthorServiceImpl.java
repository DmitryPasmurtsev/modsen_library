package com.modsen.books.services.impl;

import com.modsen.books.dto.AuthorDTO;
import com.modsen.books.exceptions.NotCreatedException;
import com.modsen.books.exceptions.NotFoundException;
import com.modsen.books.model.Author;
import com.modsen.books.repositories.AuthorRepository;
import com.modsen.books.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    private AuthorDTO toDTO(Author author){
        return modelMapper.map(author, AuthorDTO.class);
    }

    private Author toAuthor(AuthorDTO dto){
        return modelMapper.map(dto, Author.class);
    }

    @Override
    public void add(AuthorDTO dto) {
        Author author = toAuthor(dto);
        if (authorRepository.findByNameAndSurnameAndPatronymic(author.getName(),
                author.getSurname(), author.getPatronymic()).isPresent()) {
            throw new NotCreatedException("Автор с таким ФИО уже существует");
        }
        authorRepository.save(author);
    }

    @Override
    public AuthorDTO findById(Long id) {
        if (authorRepository.findById(id).isEmpty()){
            throw new NotFoundException("Автор с id "+id+" не найден");
        }
        Author author=authorRepository.findById(id).get();
        return toDTO(author);
    }

    @Override
    public List<AuthorDTO> findAll() {
        List<Author> authors=authorRepository.findAll();
        return authors.stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public void update(AuthorDTO dto, Long id) {
        if (authorRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Автор с id "+id+" не найден");
        }
        Author author = toAuthor(dto);
        author.setId(id);
        authorRepository.save(author);
    }

    @Override
    public void delete(Long id) {
        if (authorRepository.findById(id).isEmpty()){
            throw new NotFoundException("Автор с id "+id+" не найден");
        }
        authorRepository.deleteById(id);
    }
}
