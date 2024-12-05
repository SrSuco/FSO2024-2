package br.univille.fsoweb20242.service;

import java.util.List;
import br.univille.fsoweb20242.entity.Library;
import br.univille.fsoweb20242.entity.Book;

public interface LibraryService {
    List<Library> getAll();
    Library save(Library library);
    void delete(long id);
    Library findById(long id);
    List<Library> findByUserIdAndBookId(long userId, long bookId);
    List<Library> findByUserId(long userId);
    List<Book> findBooksInfoByUserId(long userId);
}