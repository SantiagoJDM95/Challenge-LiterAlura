package com.literalura_Challenge.Challenge.LiterAlura.Main;

import com.literalura_Challenge.Challenge.LiterAlura.Model.*;
import com.literalura_Challenge.Challenge.LiterAlura.Repository.BookRepository;
import com.literalura_Challenge.Challenge.LiterAlura.Services.ConversorData;
import com.literalura_Challenge.Challenge.LiterAlura.Services.APIService;

import java.util.*;

public class Main {

    private Scanner scanner = new Scanner(System.in);
    private APIService requestAPI = new APIService();
    private ConversorData dataConvert = new ConversorData();
    private BookRepository repository;
    private final String BASE_URL = "https://gutendex.com/books/";
    private List<Book> books;
    private String bookSelected;

    public Main(BookRepository repository ) {
        this.repository = repository;
    }

    public void showMenu () {
        var option = - 1;
        while ( option != 0 ) {
            var menu = """
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    getBookData();
                    break;
                case 2:
                    showStoredBooks();
                    break;
                case 3:
                    authorsListStored();
                    break;
                case 4:
                    getAuthorYear();
                    break;
                case 5:
                    findBooksByLanguages();
                    break;
            }
        }
    }


    private String getDataFromUser () {
        System.out.println("Introduzca el nombre del libro que desea buscar");
        bookSelected = scanner.nextLine();
        return bookSelected;
    }

    // Función para obtener los datos del libro de la API
    private Data getBookDataFromAPI ( String bookTitle ) {
        var json = requestAPI.getData(BASE_URL + "?search=%20" + bookTitle.replace(" ", "+"));
        var data = dataConvert.getData(json, Data.class);

        return data;
    }

    private Optional<Book> getBookData(Data bookData, String bookTitle ) {
        Optional<Book> books = bookData.results().stream()
                .filter(l -> l.title().toLowerCase().contains(bookTitle.toLowerCase()))
                .map(b -> new Book(b.title(), b.languages(), b.downloads(), b.authors()))
                .findFirst();
        return books;
    }

    private void buscarSerieWeb () {
        String title = getDataFromUser();
        Data datos = getBookDataFromAPI(title);
        Book book = new Book(datos.results());
        repository.save(book);

        System.out.println(book);
    }

    private Optional<Book> getBookData () {
        String bookTitle = getDataFromUser();
        Data bookInfo = getBookDataFromAPI(bookTitle);
        Optional<Book> book = getBookData(bookInfo, bookTitle);

        if ( book.isPresent() ) {
            var b = book.get();
            repository.save(b);
            System.out.println(b);
        } else {
            System.out.println("\nLibro no encontrado\n");
        }

        return book;
    }

    private void showStoredBooks () {
        books = repository.findAll();

        books.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(System.out::println);
    }


    private void authorsListStored () {
        List<AuthorData> authors = repository.getAuthorsInfo();

        authors.stream()
                .sorted(Comparator.comparing(AuthorData::getName))
                .forEach(a -> System.out.printf("Author: %s Born: %s Death: %s\n",
                        a.getName(), a.getBirthYear(), a.getDeathYear()));
    }


    public void getAuthorYear () {
        System.out.println("Ingrese el año");
        int date = scanner.nextInt();
        scanner.nextLine();

        List<AuthorData> authorInfos = repository.getAuthorLiveAfter(date);

        authorInfos.stream()
                .sorted(Comparator.comparing(AuthorData::getName))
                .forEach(a -> System.out.printf("Author: %s Born: %s Death: %s\n",
                        a.getName(), a.getBirthYear(), a.getDeathYear()));
    }

    public void findBooksByLanguages () {
        String languagesList = """
                Elija entre las opciones del idioma del libro que desea buscar
                
                en - Inglés
                es - Español
                fr - Francés
                pt - Portugués
                
                """;
        System.out.println(languagesList);
        String text =  scanner.nextLine();

        var language = Language.fromString(text);

        List<Book> bookLanguage = repository.findByLanguages(language);

        bookLanguage.stream()
                .forEach(System.out::println);
    }
}
