package br.univille.fsoweb20242.service;

import java.util.List;
import br.univille.fsoweb20242.entity.Book;

public interface BookService {
    List<Book> getAll();
    Book save(Book produto);
    Book delete(long id);
    Book getById(long id);
    boolean hasUserRatedBook(long userId, long id);
    void updateBookRating(long bookId);
}
