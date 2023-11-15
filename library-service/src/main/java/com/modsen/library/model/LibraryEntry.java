package com.modsen.library.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class LibraryEntry {
    @Id
    @Column(name = "id")
    Long id;
    @Column(name = "issueDate")
    LocalDateTime issueDate;
    @Column(name = "acceptanceDate")
    LocalDateTime acceptanceDate;

    public LibraryEntry(Long id) {
        this.id = id;
    }
}
