package com.literlaura.alura_challenge.models.dto;

import com.literlaura.alura_challenge.entities.Authors;

public record BooksDTO(String title,
                       Authors author,
                       String language,
                       Long downloads) {
}
