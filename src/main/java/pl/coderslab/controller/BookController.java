package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.Book;
import pl.coderslab.servis.BookService;


import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    public BookService bookService;

    @RequestMapping("/hello")
    public String hello() {
        return "{hello: World}";
    }

    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }


    @GetMapping("")
    public List<Book> printBooks() {
        return bookService.getList();
    }

    @PostMapping("")
    public void addBook(@RequestBody Book book) { /*w widoku tabela nie jest odświeżana na bieżąco po dodaniu książki,
    funkcjonalność ta pojawia się dopiero po ustawieniu metod w bookController tak, aby zwracały List<Book>. Wygląda to tak,
    jakby wywołanie funkcji get() w JS w sekcji .done() po wykonanym ajaxie nie działało poprawnie.
    Plik JS jest w drugim repo Warsztat 4 app na gałęzi DEVELOP*/
        bookService.addBook(book);
//        return bookService.getList();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable long id) {
        return bookService.getBookById(id);
    }


    @PutMapping("/{id}")/*nasłuch do edycji jest ustawiony na PUT, formularz dodawania ksiązki, który służy do dodawania i edycji ksiazki jest nastwiony na POST,
    przez to nie działa edycja. Mogę to obejść ustawiając nowy przycisk w formularzu i powielić kod w JS, który będzie obsługiwał tylko PUT ale nie wydaje mi się aby to było poprawne rozwiązanie.
     Może da rade to obejść zmieniając BookController?*/
    public List<Book> editBook(@PathVariable long id, @RequestBody Book book) {
        bookService.editBook(id, book);
        return bookService.getList();
    }

    @DeleteMapping("/{id}")
    public List<Book> deleteBook(@PathVariable long id) {
        bookService.delete(id);
        return bookService.getList();
    }
}
