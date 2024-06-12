package com.literlaura.alura_challenge.models.dto;

import com.literlaura.alura_challenge.entities.Books;
import java.util.List;

public record AuthorsDTO(String name,
                         Long birthYear,
                         Long deathYear,
                         List<Books> books) {
}
