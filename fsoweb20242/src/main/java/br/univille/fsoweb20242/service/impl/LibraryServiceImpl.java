package br.univille.fsoweb20242.service.impl;

import java.util.List;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.univille.fsoweb20242.entity.Library;
import br.univille.fsoweb20242.entity.Book;
import br.univille.fsoweb20242.repository.LibraryRepository;
import br.univille.fsoweb20242.repository.BookRepository;
import br.univille.fsoweb20242.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryRepository repository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Library> getAll() {
        return repository.findAll();
    }

    @Override
    public Library save(Library library) {
        if (library.getId() != 0) {
            Library existingLibrary = repository.findById(library.getId()).orElse(null);
            if (existingLibrary != null) {
                existingLibrary.setBook(library.getBook());
                existingLibrary.setStatus(library.getStatus());
                return repository.save(existingLibrary);
            }
        }
        library.setCreationDate(new Date());
        return repository.save(library);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public Library findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Library> findByUserId(long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<Book> findBooksInfoByUserId(long userId) {
        List<Library> libraries = repository.findByUserId(userId);
        return libraries.stream()
                        .map(Library::getBook)
                        .collect(Collectors.toList());
    }

    @Override
    public List<Library> findByUserIdAndBookId(long userId, long bookId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUserIdAndBookId'");
    }
}