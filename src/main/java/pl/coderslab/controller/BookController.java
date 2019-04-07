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

    @GetMapping("/{id}")
    public Book getBook(@PathVariable long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public List<Book> editBook(@PathVariable long id, @RequestBody Book book) {
        bookService.editBook(id, book);
        return bookService.getList();
    }

    @PostMapping("")
    public List<Book> addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return bookService.getList();
    }

    @DeleteMapping("/{id}")
    public List<Book> deleteBook(@PathVariable long id) {
        bookService.delete(id);
        return bookService.getList();
    }
}
