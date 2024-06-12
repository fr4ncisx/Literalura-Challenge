package com.literlaura.alura_challenge.entities;

import com.literlaura.alura_challenge.models.BooksAPI;
import jakarta.persistence.*;

@Entity
@Table(name = "Books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Authors author;
    private String language;
    private Long downloads;

    public Books() {
    }

    public Books(BooksAPI booksAPI) {
        this.title = booksAPI.getTitle();
        this.author = convertListToAuthor(booksAPI);
        this.language = booksAPI.getLanguage().get(0);
        this.downloads = booksAPI.getDownloads();
    }

    public Books(String title, Authors author, String language, Long downloads) {
        this.title = title;
        this.author = author;
        this.language = language;
        this.downloads = downloads;
    }

    public Authors convertListToAuthor(BooksAPI booksAPI){
        return new Authors(booksAPI.getAuthor().get(0).name(),
                booksAPI.getAuthor().get(0).birthYear(),
                booksAPI.getAuthor().get(0).deathYear());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Authors getAuthor() {
        return author;
    }

    public void setAuthor(Authors author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Long getDownloads() {
        return downloads;
    }

    public void setDownloads(Long downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        return "───────────────────────────────────────────────── \n" +
                "Titulo del libro: " + getTitle()+
                "\n" + getAuthor()
                /*+"\nNombre del autor: " + getAuthor().getName() +
                "\nFecha de nacimiento: " + getAuthor().getBirthYear()+"-"+getAuthor().getDeathYear()*/ +
                "\nIdioma: " + getLanguage() +
                "\nDescargas: " + getDownloads() +"\n"+
                "─────────────────────────────────────────────────";
    }
}
