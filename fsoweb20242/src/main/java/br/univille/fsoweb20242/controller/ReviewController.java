package br.univille.fsoweb20242.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.univille.fsoweb20242.entity.Review;
import br.univille.fsoweb20242.service.ReviewService;
import br.univille.fsoweb20242.service.BookService;

import java.net.URI;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Review review) {
        Review savedReview = reviewService.save(review);
        long bookId = savedReview.getBook().getId();
        return ResponseEntity.created(URI.create("/book/" + bookId)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        try {
            Review review = reviewService.getById(id);
            if (review != null) {
                long bookId = review.getBook().getId();
                reviewService.delete(id);
                bookService.updateBookRating(bookId);
            }
        } catch (Exception e) {
            // Handle exception
        }
        return ResponseEntity.noContent().build();
    }
}