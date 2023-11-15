package com.modsen.books.controllers;

import com.modsen.books.dto.AuthorDTO;
import com.modsen.books.services.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authors")
@Tag(name = "Контроллер для работы с авторами")
public class AuthorController {
    private final AuthorService authorService;
    @Operation(
            summary = "Получение всех авторов"
    )
    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAll(){
        List<AuthorDTO> authors=authorService.findAll();
        return ResponseEntity.ok(authors);
    }
    @PostMapping
    @Operation(
            summary = "Добавление автора"
    )
    public ResponseEntity<String> createAuthor(@RequestBody AuthorDTO authorDTO) {
        authorService.add(authorDTO);
        return ResponseEntity.ok("Добавлен автор " + authorDTO.getName()+" "+authorDTO.getSurname());
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление автора по id"
    )
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id){
        authorService.delete(id);
        return ResponseEntity.ok("Удален автор с id " + id);
    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Получение информации об авторе по id"
    )
    public ResponseEntity<AuthorDTO> authorInfo(@PathVariable Long id){
        AuthorDTO author=authorService.findById(id);
        return ResponseEntity.ok(author);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Редактирование информации об авторе",
            description = "В тело запроса помещается id изменяемого автора и dto автора с обновленными полями"
    )
    public ResponseEntity<String> updateAuthor(@PathVariable Long id,@RequestBody AuthorDTO authorDTO)
    {
        authorService.update(authorDTO,id);
        return ResponseEntity.ok("Изменен автор с id " + id);
    }
}
