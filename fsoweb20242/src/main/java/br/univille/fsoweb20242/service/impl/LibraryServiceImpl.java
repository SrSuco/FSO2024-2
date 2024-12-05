package br.univille.fsoweb20242.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.univille.fsoweb20242.entity.Library;
import br.univille.fsoweb20242.repository.LibraryRepository;
import br.univille.fsoweb20242.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryRepository repository;

    @Override
    public List<Library> getAll() {
        return repository.findAll();
    }

    @Override
    public Library save(Library library) {
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
    public List<Library> findByUserIdAndBookId(long userId, long bookId) {
        return repository.findByUserIdAndBookId(userId, bookId);
    }
}