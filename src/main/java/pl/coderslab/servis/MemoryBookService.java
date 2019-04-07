package pl.coderslab.servis;

import org.springframework.stereotype.Service;
import pl.coderslab.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemoryBookService implements BookService{

    private List<Book> list;

    public MemoryBookService() {
        list = new ArrayList<>();
        list.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel",
                "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz glowa, Java.",
                "Sierra Kathy, Bates Bert", "Helion", "programming"));
        list.add(new Book(3L, "9780130819338", "Java 2. Podstawy",
                "Cay Horstmann, Gary Cornell", "Helion", "programming"));
    }

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

    public Book getBookById(long id) {
        Optional<Book> first = list.stream()
                .filter(b -> b.getId() == id)
                .findFirst();
        return first.orElse(null);
    }

    public void addBook(Book book) {
        list.add(book);
    }

    public void editBook(long id, Book book) {
        int index = list.indexOf(getBookById(id));
        list.add(index, book);
        list.remove(index +1);
    }

    public void delete(long id){
        list.remove(getBookById(id));
    }
}
