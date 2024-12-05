package br.univille.fsoweb20242.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.univille.fsoweb20242.entity.Review;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByBookId(long bookId);
    Optional<Review> findByUserIdAndBookId(long userId, long bookId);
    boolean existsByUserIdAndBookId(long userId, long bookId);
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.book.id = :bookId")
    Float calcAvgRatingByBookId(long bookId);
}
