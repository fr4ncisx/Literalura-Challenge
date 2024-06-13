package com.literlaura.alura_challenge.models.dto;

import com.literlaura.alura_challenge.entities.Books;

import java.util.List;

public record AuthorsDTO(String name,
                         Long birthYear,
                         Long deathYear,
                         List<Books> books) {
    public String getBirthAndDeathYear() {
        return filterNullYears();
    }

    private String filterNullYears() {
        if (birthYear == null || deathYear == null) {
            return "No hay datos de fecha de nacimiento o muerte";
        } else {
            return birthYear() + "-" + deathYear();
        }
    }
}
