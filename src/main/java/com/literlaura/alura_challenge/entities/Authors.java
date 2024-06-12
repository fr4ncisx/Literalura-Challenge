package com.literlaura.alura_challenge.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Author")
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long birthYear;
    private Long deathYear;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Books> books = new ArrayList<>();

    public Authors() {
    }

    public Authors(String name, Long birthYear, Long deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Long birthYear) {
        this.birthYear = birthYear;
    }

    public Long getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Long deathYear) {
        this.deathYear = deathYear;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    private String filterNullYears(){
        if(birthYear == null || deathYear == null){
            return "No hay datos de fecha de nacimiento o muerte";
        } else {
            return getBirthYear() + "-" + getDeathYear();
        }
    }
    @Override
    public String toString() {
            return "Nombre del autor: " + getName() + ", " + "\nFecha de nacimiento: " + filterNullYears();
        }
}
