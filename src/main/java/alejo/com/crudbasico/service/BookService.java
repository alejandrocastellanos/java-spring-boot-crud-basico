package alejo.com.crudbasico.service;

import alejo.com.crudbasico.model.Book;
import alejo.com.crudbasico.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class BookService {


    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getByUuid(UUID uuid) {
        return bookRepository.findByUuid(uuid);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(Long id, Book bookDetails){
        Book book = bookRepository.findById(id).orElseThrow();

        if (bookDetails.getTitle() != null){
            book.setTitle(bookDetails.getTitle());
        }
        if (bookDetails.getAuthor() != null){
            book.setAuthor(bookDetails.getAuthor());
        }
        return bookRepository.save(book);
    }
}
