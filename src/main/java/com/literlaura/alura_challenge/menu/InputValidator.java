package com.literlaura.alura_challenge.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidator {
    public static int validateOption(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Solo se aceptan números para operar el menu");
                sc.nextLine();
                System.out.println("Vuelve a ingresar la opción: ");
                System.out.print("> ");
            }
        }
    }

    public static int validate(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Solo se aceptan números para operar");
                sc.nextLine();
                System.out.println("Vuelve a ingresar el número: ");
                System.out.print("> ");
            }
        }
    }

    public static String validateLanguage(Scanner sc) {
        String lang = sc.nextLine();
        while (true) {
            if (!lang.isBlank()) {
                switch (lang.toLowerCase().replace(" ", "")) {
                    case "español":
                        return "es";
                    case "ingles":
                        return "en";
                    case "frances":
                        return "fr";
                    default:
                        return "N/A";
                }
            } else {
                System.out.println("Debes escribir algo para obtener los libros listados por idioma");
                lang = sc.nextLine();
            }
        }
    }
    public static String getFullLanguageName(String langCode){
        switch (langCode.toLowerCase().replace(" ", "")) {
            case "es":
                return "Español";
            case "en":
                return "Inglés";
            case "fr":
                return "Francés";
            default:
                return "N/A";
        }
    }
}
