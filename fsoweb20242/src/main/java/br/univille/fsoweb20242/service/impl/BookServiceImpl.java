package br.univille.fsoweb20242.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.fsoweb20242.entity.Book;
import br.univille.fsoweb20242.entity.Review;
import br.univille.fsoweb20242.repository.BookRepository;
import br.univille.fsoweb20242.repository.ReviewRepository;
import br.univille.fsoweb20242.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Book> getAll() {
        return repository.findAll();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            book.setAverageRating(0);
        }
        return repository.save(book);
    }

    @Override
    public Book delete(long id) {
        var book = getById(id);
        repository.delete(book);
        return book;
    }

    @Override
    public Book getById(long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public boolean hasUserRatedBook(long userId, long bookId) {
        return reviewRepository.existsByUserIdAndBookId(userId, bookId);
    }

    @Override
    public void updateBookRating(long bookId) {
        Book book = repository.getReferenceById(bookId);
        double newRating = calculateNewRating(bookId);
        book.setAverageRating((float) newRating);
        repository.save(book);
    }

    private double calculateNewRating(long bookId) {
        List<Review> reviews = reviewRepository.findAllByBookId(bookId);
        return reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }
}