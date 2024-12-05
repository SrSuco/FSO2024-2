
package br.univille.fsoweb20242.service;

import java.util.List;
import br.univille.fsoweb20242.entity.Review;

public interface ReviewService {
    List<Review> getAll();
    Review save(Review review);
    Review delete(long id);
    Review getById(long id);
    List<Review> getAllByBookId(long bookId);
    boolean hasUserRatedBook(long userId, long bookId);
}