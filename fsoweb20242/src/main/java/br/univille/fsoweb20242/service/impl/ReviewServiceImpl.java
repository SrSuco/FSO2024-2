
package br.univille.fsoweb20242.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.fsoweb20242.entity.Review;
import br.univille.fsoweb20242.repository.ReviewRepository;
import br.univille.fsoweb20242.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Override
    public List<Review> getAll() {
        return repository.findAll();
    }

    @Override
    public Review save(Review review) {
        return repository.save(review);
    }

    @Override
    public Review delete(long id) {
        var review = getById(id);
        if (review != null) {
            repository.delete(review);
        }
        return review;
    }

    @Override
    public Review getById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Review> getAllByBookId(long bookId) {
        return repository.findAllByBookId(bookId);
    }

    @Override
    public boolean hasUserRatedBook(long userId, long bookId) {
        return repository.existsByUserIdAndBookId(userId, bookId);
    }
}