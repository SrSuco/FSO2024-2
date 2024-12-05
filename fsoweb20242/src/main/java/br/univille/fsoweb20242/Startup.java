package br.univille.fsoweb20242;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


import br.univille.fsoweb20242.entity.Book;
import br.univille.fsoweb20242.entity.ClientUser;
import br.univille.fsoweb20242.entity.Review;
import br.univille.fsoweb20242.entity.Library;
import br.univille.fsoweb20242.service.BookService;
import br.univille.fsoweb20242.service.ClientUserService;
import br.univille.fsoweb20242.service.ReviewService;
import br.univille.fsoweb20242.service.LibraryService;

import java.util.Date;

@Component
public class Startup {

    @Autowired
    private BookService bookService;
    @Autowired
    private ClientUserService userService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private LibraryService libraryService;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event){
        // Populate Books
        var lotr = new Book();
        lotr.setTitle("The Lord of the Rings");
        lotr.setAuthor("J.R.R. Tolkien");
        lotr.setGenre("Fantasy");
        lotr.setPublishDate(new Date());
        lotr.setSummary("An epic high-fantasy novel.");
        lotr.setAverageRating(5.0f);
        bookService.save(lotr);

        var hobbit = new Book();
        hobbit.setTitle("The Hobbit");
        hobbit.setAuthor("J.R.R. Tolkien");
        hobbit.setGenre("Fantasy");
        hobbit.setPublishDate(new Date());
        hobbit.setSummary("A fantasy novel and children's book.");
        hobbit.setAverageRating(4.8f);
        bookService.save(hobbit);

        // Populate User
        var user = new ClientUser();
        user.setUsername("frodo");
        user.setEmail("frodo@shire.com");
        user.setCreationDate(new Date());
        userService.save(user);

        // Populate Reviews
        var review1 = new Review();
        review1.setBook(lotr);
        review1.setUser(user);
        review1.setRating(5);
        review1.setDescription("Amazing book!");
        review1.setCreationDate(new Date());
        reviewService.save(review1);

        var review2 = new Review();
        review2.setBook(hobbit);
        review2.setUser(user);
        review2.setRating(4);
        review2.setDescription("Great read!");
        review2.setCreationDate(new Date());
        reviewService.save(review2);

        // Populate Library
        var library1 = new Library();
        library1.setBook(lotr);
        library1.setUser(user);
        library1.setStatus(1); // 1 for read
        library1.setCreationDate(new Date());
        libraryService.save(library1);

        var library2 = new Library();
        library2.setBook(hobbit);
        library2.setUser(user);
        library2.setStatus(0); // 0 for unread
        library2.setCreationDate(new Date());
        libraryService.save(library2);
    }   
}
