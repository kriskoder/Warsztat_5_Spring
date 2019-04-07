package pl.coderslab.servis;
import pl.coderslab.model.Book;
import java.util.List;

public interface BookService {
    List<Book> getList();

    void setList(List<Book> list);

    Book getBookById(long id);

    void addBook(Book book);

    void editBook(long id, Book book);

    void delete(long id);
}
