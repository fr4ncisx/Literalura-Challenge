package com.literlaura.alura_challenge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorsAPI(@JsonProperty("name") String name, @JsonProperty("birth_year") Long birthYear,
                         @JsonProperty("death_year") Long deathYear) {
}
