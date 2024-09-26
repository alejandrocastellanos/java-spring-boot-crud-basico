package alejo.com.crudbasico.repository;

import alejo.com.crudbasico.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByUuid(UUID uuid);
}
