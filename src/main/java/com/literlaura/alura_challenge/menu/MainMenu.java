package com.literlaura.alura_challenge.menu;

import com.literlaura.alura_challenge.repository.AuthorRepository;
import com.literlaura.alura_challenge.repository.BookRepository;
import com.literlaura.alura_challenge.service.ConnectionAPI;
import com.literlaura.alura_challenge.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Component
public class MainMenu {
    private static Scanner sc = new Scanner(System.in);
    private static String api_url = "https://gutendex.com/books/";

    @Autowired
    private RepositoryService service;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public void execApp() {
        int option = -1;
        System.out.print(MenuList.welcomeMsg());
        while (option != 0) {
            System.out.println(MenuList.showMenu());
            System.out.print("> ");
            option = InputValidator.validateOption(sc);
            sc.nextLine();
            switch (option) {
                case 1:
                    findBook();
                    break;
                case 2:
                    findListedBooks();
                    break;
                case 3:
                    findListedAuthors();
                    break;
                case 4:
                    findAuthorsByYear();
                    break;
                case 5:
                    findBooksByLanguage();
                    break;
                case 6:
                    findTopBooks();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida");
                    System.out.println();
            }
        }
    }

    private void findBook() {
        System.out.println("Ingresar el título del libro que deseas buscar: ");
        String bookTitle = sc.nextLine().toLowerCase();
        String encodedURL = api_url + "?search=" + URLEncoder.encode(bookTitle, StandardCharsets.UTF_8);
        String json = ConnectionAPI.getJsonData(encodedURL);
        service.findFirstBook(json);
    }

    private void findListedBooks() {
        service.findAllBooks();
    }

    private void findListedAuthors() {
        service.findAllAuthors();
    }
    private void findAuthorsByYear() {
        System.out.println("Ingresa el año que deseas saber si un autor estuvo vivo: ");
        int year = InputValidator.validateOption(sc);
        service.getAuthorsByYear(year);
    }

    private void findBooksByLanguage() {
        System.out.println("Por favor ingrese el idioma que desee: ");
        String lang = InputValidator.validateLanguage(sc);
        if (!lang.equals("N/A")) {
            service.getBooksByLanguage(lang);
        } else {
            System.out.println("──────────────────────────────────────────────────");
            System.out.println("Ese idioma no está registrado en nuestro programa");
        }
    }
    private void findTopBooks(){
        service.getTopBooks();
    }
}
