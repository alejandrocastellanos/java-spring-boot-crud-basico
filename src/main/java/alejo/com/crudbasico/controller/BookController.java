package alejo.com.crudbasico.controller;

import alejo.com.crudbasico.model.Book;
import alejo.com.crudbasico.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class BookController {


    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/api/books/{uuid}")
    public List<Book> getByTitle(@PathVariable String uuid){
        UUID newUuid = UUID.fromString(uuid);
        return bookService.getByUuid(newUuid);
    }

    @PostMapping("/api/books")
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @DeleteMapping("/api/books/{uuid}")
    public String DeleteBook(@PathVariable String uuid){
        UUID newUuid = UUID.fromString(uuid);
        List<Book> book = bookService.getByUuid(newUuid);
        if(book.isEmpty()){
            return "Uuid does not exists";
        }
        Long id = book.get(0).getId();
        bookService.deleteBook(id);
        return "Deleted";
    }

    @PutMapping("/api/books/{uuid}")
    public ResponseEntity<Book> updateBook(@PathVariable String uuid, @RequestBody Book bookDetails) {
        UUID newUuid = UUID.fromString(uuid);
        List<Book> book = bookService.getByUuid(newUuid);
        if(book.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Book existingBook = book.get(0);
        Book updatedBook = bookService.updateBook(existingBook.getId(), bookDetails);
        return ResponseEntity.ok(updatedBook);
    }
}
