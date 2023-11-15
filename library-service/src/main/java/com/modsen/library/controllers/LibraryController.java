package com.modsen.library.controllers;

import com.modsen.library.dto.BookResponse;
import com.modsen.library.service.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "BearerAuth", scheme = "bearer")
@RequestMapping("/api/library")
@Tag(name = "Контроллер для учета свободных и занятых книг")
public class LibraryController {
    private final LibraryService libraryService;

    @PostMapping
    @Operation(summary = "Добавить запись с новой книгой")
    public ResponseEntity<String> createLibraryEntry(@RequestBody Long id) {
        libraryService.add(id);
        return ResponseEntity.ok("Добавлена книга с id " + id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить запись по id")
    public ResponseEntity<String> deleteLibraryEntry(@PathVariable Long id){
        libraryService.delete(id);
        return ResponseEntity.ok("Удалена книга с id " + id);
    }
    @GetMapping("/available")
    @Operation(
            summary = "Получение информации о доступных книгах"
    )
    @SecurityRequirement(name = "BearerAuth")
    public ResponseEntity<List<BookResponse>> availableBooks(@Parameter(hidden = true) @RequestHeader("Authorization") String token){
        libraryService.setToken(token);
        return ResponseEntity.ok(libraryService.findAvailableBooks());
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение доступности книги по id",
            description = "В зависимости от доступности книги значения полей с датами взятия и сдачи либо устанавливаются либо сбрасываются"
    )
    public ResponseEntity<String> changeAvailability(@PathVariable Long id){
        libraryService.changeAvailability(id);
        return ResponseEntity.ok("Статус книги с id " + id + " изменен");
    }

}
