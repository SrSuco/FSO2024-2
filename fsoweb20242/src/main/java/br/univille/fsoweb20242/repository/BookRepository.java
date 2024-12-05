package br.univille.fsoweb20242.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.univille.fsoweb20242.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book getReferenceById(long id);
}
