package com.literlaura.alura_challenge.menu;

public class MenuList {
    private static String welcome = """
            ├─────────────────────────────────────────────────┤
                  BIENVENIDOS A LA BIBLIOTECA LITERALURA
            ├─────────────────────────────────────────────────┤ 
            """;
    private static String menu = """
                    ──────────────────────────────────────────────────
                                    MENU PRINCIPAL  
                    ────────────────────────────────────────────────── 
                        1. Buscar libros por título
                        2. Mostrar todos los libros   
                        3. Mostrar todos los autores
                        4. Mostrar autores vivos en un año 
                        5. Mostrar libros por idioma 
                        6. Mostrar el top 10 libros más descargados
                        0. Salir 
                    ├─────────────────────────────────────────────────┤    
                    """;

    public static String showMenu() {
        return menu;
    }
    public static String welcomeMsg(){
        return welcome;
    }

}
