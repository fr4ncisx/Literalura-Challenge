package com.literlaura.alura_challenge.service;

import com.literlaura.alura_challenge.entities.Authors;
import com.literlaura.alura_challenge.entities.Books;
import com.literlaura.alura_challenge.menu.InputValidator;
import com.literlaura.alura_challenge.models.BooksAPI;
import com.literlaura.alura_challenge.models.ResultsAPI;
import com.literlaura.alura_challenge.models.dto.AuthorsDTO;
import com.literlaura.alura_challenge.models.dto.BooksDTO;
import com.literlaura.alura_challenge.repository.AuthorRepository;
import com.literlaura.alura_challenge.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RepositoryService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private DataConverter dataConverter;

    public void findFirstBook(String json) {
        ResultsAPI results = dataConverter.convertData(json, ResultsAPI.class);
        if (!results.datos().isEmpty()) {
            BooksAPI booksAPI = results.datos().get(0);
            Optional<Books> existingBook = bookRepository.findByTitle(booksAPI.getTitle());
            if (existingBook.isPresent()) {
                System.out.println("El libro ya existe en la base de datos.");
                System.out.println(existingBook.get());
            } else {
                Books books = new Books(booksAPI);
                Authors author = books.getAuthor();
                Optional<Authors> existingAuthor = authorRepository.findByName(
                        author.getName());
                if (existingAuthor.isPresent()) {
                    books.setAuthor(existingAuthor.get());
                } else {
                    authorRepository.save(author);
                }
                bookRepository.save(books);
                System.out.printf("\nTitulo del libro: %s " +
                                "\nNombre del autor: %s " +
                                "\nFecha de nacimiento: %s " +
                                "\nIdioma: %s " +
                                "\nDescargas totales: %s\n",
                        books.getTitle(), books.getAuthor().getName(), books.getAuthor().getBirthAndDeathYear(),
                        InputValidator.getFullLanguageName(books.getLanguage()), books.getDownloads());

                System.out.println("──────────────────────────────────────────────────");
                System.out.println("El libro se ha guardado exitosamente en la base de datos.");
            }
        } else {
            System.out.println("No se encontraron libros en los datos proporcionados.");
        }
    }

    public void findAllAuthors() {
        if (!authorRepository.findAll().isEmpty()) {
            List<AuthorsDTO> author = authorsDTOconverter(authorRepository.findAll());
            System.out.println("LISTA DE AUTORES: ");
            author.forEach(a -> System.out.printf("──────────────────────────────────────────────────\n"
                    + "Nombre del autor: %s " +
                    "\nFecha de nacimiento: %s" +
                    "\nLibros escritos: %s\n", a.name(), a.getBirthAndDeathYear(), a.books().get(0).getTitle()));
        } else {
            System.out.println("──────────────────────────────────────────────────");
            System.out.println("No existen autores en nuestra base de datos :(");
            System.out.println("──────────────────────────────────────────────────");
        }
    }

    public void findAllBooks() {
        List<BooksDTO> books = booksDTOconverter(bookRepository.findAll());
        if (!books.isEmpty()) {
            books.forEach(b -> System.out.printf("──────────────────────────────────────────────────\n" +
                            "Titulo del libro: %s\nNombre del autor: %s\nFecha de nacimiento: %s-%s\nIdioma: %s \nDescargas Totales: %s\n" +
                            "──────────────────────────────────────────────────",
                    b.title(), b.author().getName(), b.author().getBirthYear(), b.author().getDeathYear(), InputValidator.getFullLanguageName(b.language()), b.downloads()));
        } else {
            System.out.println("──────────────────────────────────────────────────");
            System.out.println("Al parecer nuestra base de datos está vacia, que tal si intentas agregar libros en nuestra base de datos?");
        }
    }

    private void saveBook(BooksAPI books) {
        Books book = new Books(books);
        bookRepository.save(book);
    }

    public void getAuthorsByYear(int year) {
        if (!authorRepository.findAll().isEmpty()) {
            List<AuthorsDTO> authorList = authorsDTOconverter(authorRepository.findAll().stream()
                    .filter(a -> a.getBirthYear() != null && a.getBirthYear() <= year)
                    .filter(a -> a.getDeathYear() != null && a.getDeathYear() >= year)
                    .collect(Collectors.toList()));
            if (!authorList.isEmpty()) {
                System.out.println("Hemos obtenido " + authorList.size() + " autores en nuestra base de datos!");
                System.out.println("──────────────────────────────────────────────────");
                authorList.stream()
                        .forEach(a -> System.out.println("Nombre del autor: " + a.name() +
                                "\nFecha de nacimiento: " + a.getBirthAndDeathYear() +
                                "\nLibros escritos: " + a.books().get(0).getTitle() +
                                "\n──────────────────────────────────────────────────"));
            } else {
                System.out.println("──────────────────────────────────────────────────");
                System.out.println("No pudimos encontrar autores vivos en el año " + year);
            }
        } else {
            System.out.println("──────────────────────────────────────────────────");
            System.out.println("Al parecer nuestra base de datos está vacia, que tal si intentas agregar libros en nuestra base de datos?");
        }
    }

    public void getBooksByLanguage(String lang) {
        String uncodedLanguage = InputValidator.getFullLanguageName(lang);
        if (!bookRepository.findByLanguage(lang).isEmpty()) {
            bookRepository.findByLanguage(lang).stream()
                    .forEach(b -> System.out.printf("──────────────────────────────────────────────────\n" +
                                    "Titulo del libro: %s\nNombre del autor: %s\nFecha de nacimiento: %s-%s\nIdioma: %s \nDescargas Totales: %s\n" +
                                    "──────────────────────────────────────────────────",
                            b.getTitle(), b.getAuthor().getName(), b.getAuthor().getBirthYear(), b.getAuthor().getDeathYear(), uncodedLanguage, b.getDownloads()));
        } else {
            System.out.println("──────────────────────────────────────────────────");
            System.out.println("Libros no encontrados");
        }
    }

    public void getTopBooks() {
        if (!bookRepository.findAll().isEmpty()) {
            List<BooksDTO> books = booksDTOconverter(bookRepository.findAll())
                    .stream()
                    .sorted(Comparator.comparing(BooksDTO::downloads).reversed())
                    .limit(10)
                    .toList();
            if (books.size() == 1) {
                System.out.printf("Solo se encontró %s libro, pero no te preocupes te lo mostraré igual!\n", books.size());
                System.out.println("──────────────────────────────────────────────────");
            }
            if (books.size() >= 2 && books.size() <= 10) {
                System.out.printf("Solo se encontraron %s libros, pero no te preocupes te mostraremos los mejores!\n", books.size());
                System.out.println("──────────────────────────────────────────────────");
            }
            System.out.println("                TOP 10 LIBROS ENCONTRADOS                ");
            books.stream().forEach(b -> System.out.printf("──────────────────────────────────────────────────\n" +
                            "Titulo del libro: %s\nNombre del autor: %s\nFecha de nacimiento: %s\nIdioma: %s \nDescargas Totales: %s\n" +
                            "──────────────────────────────────────────────────",
                    b.title(), b.author().getName(), b.author().getBirthAndDeathYear(), InputValidator.getFullLanguageName(b.language()), b.downloads()));
        } else {
            System.out.println("──────────────────────────────────────────────────");
            System.out.println("Al parecer nuestra base de datos está vacia, que tal si intentas agregar libros en nuestra base de datos?");
        }
    }

    private List<BooksDTO> booksDTOconverter(List<Books> books) {
        return books.stream().map(b -> new BooksDTO(b.getTitle(), b.getAuthor(), b.getLanguage(), b.getDownloads())).collect(Collectors.toList());
    }

    private List<AuthorsDTO> authorsDTOconverter(List<Authors> authors) {
        return authors.stream().map(a -> new AuthorsDTO(a.getName(), a.getBirthYear(), a.getDeathYear(), a.getBooks())).collect(Collectors.toList());
    }
}

