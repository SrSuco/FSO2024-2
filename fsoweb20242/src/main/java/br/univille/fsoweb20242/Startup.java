package br.univille.fsoweb20242;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.univille.fsoweb20242.entity.Book;
import br.univille.fsoweb20242.entity.ClientUser;
import br.univille.fsoweb20242.entity.Library;
import br.univille.fsoweb20242.service.BookService;
import br.univille.fsoweb20242.service.ClientUserService;
import br.univille.fsoweb20242.service.LibraryService;

import java.util.Date;

@Component
public class Startup {

    @Autowired
    private BookService bookService;
    @Autowired
    private ClientUserService userService;
    @Autowired
    private LibraryService libraryService;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event){
        // Populate Books
        var lotr1 = new Book();
        lotr1.setTitle("The Fellowship of the Ring");
        lotr1.setAuthor("J.R.R. Tolkien");
        lotr1.setGenre("Fantasy");
        lotr1.setPublishDate(new Date());
        lotr1.setSummary("The first volume of The Lord of the Rings.");
        lotr1.setAverageRating(5.0f);
        lotr1.setImageUrl("https://images.booksense.com/images/210/928/9780547928210.jpg");
        bookService.save(lotr1);

        var lotr2 = new Book();
        lotr2.setTitle("The Two Towers");
        lotr2.setAuthor("J.R.R. Tolkien");
        lotr2.setGenre("Fantasy");
        lotr2.setPublishDate(new Date());
        lotr2.setSummary("The second volume of The Lord of the Rings.");
        lotr2.setAverageRating(5.0f);
        lotr2.setImageUrl("https://i.pinimg.com/564x/56/82/d1/5682d1a75da324bf5c429445f9c7ccc3.jpg");
        bookService.save(lotr2);

        var lotr3 = new Book();
        lotr3.setTitle("The Return of the King");
        lotr3.setAuthor("J.R.R. Tolkien");
        lotr3.setGenre("Fantasy");
        lotr3.setPublishDate(new Date());
        lotr3.setSummary("The third volume of The Lord of the Rings.");
        lotr3.setAverageRating(5.0f);
        lotr3.setImageUrl("https://upload.wikimedia.org/wikipedia/en/thumb/1/11/The_Return_of_the_King_cover.gif/220px-The_Return_of_the_King_cover.gif");
        bookService.save(lotr3);

        var hobbit = new Book();
        hobbit.setTitle("The Hobbit");
        hobbit.setAuthor("J.R.R. Tolkien");
        hobbit.setGenre("Fantasy");
        hobbit.setPublishDate(new Date());
        hobbit.setSummary("A fantasy novel and children's book.");
        hobbit.setAverageRating(4.8f);
        hobbit.setImageUrl("https://images.booksense.com/images/683/339/9780345339683.jpg");
        bookService.save(hobbit);

        // Populate Users
        String[] usernames = {"frodo", "gandalf", "saruman", "gimli", "legolas", "aragorn", "boromir", "sam", "merry", "pippin"};
        String[] emails = {"frodo@shire.com", "gandalf@middleearth.com", "saruman@isengard.com", "gimli@erebor.com", "legolas@mirkwood.com", "aragorn@gondor.com", "boromir@gondor.com", "sam@shire.com", "merry@shire.com", "pippin@shire.com"};

        for (int i = 0; i < usernames.length; i++) {
            var user = new ClientUser();
            user.setUsername(usernames[i]);
            user.setEmail(emails[i]);
            user.setCreationDate(new Date());
            userService.save(user);

            // Populate Libraries with different books and statuses
            if (i == 0) {
                var library1 = new Library();
                library1.setUserId(user.getId());
                library1.setBook(lotr1);
                library1.setStatus(1);
                libraryService.save(library1);

                var library2 = new Library();
                library2.setUserId(user.getId());
                library2.setBook(hobbit);
                library2.setStatus(2);
                libraryService.save(library2);

                var library3 = new Library();
                library3.setUserId(user.getId());
                library3.setBook(lotr2);
                library3.setStatus(3);
                libraryService.save(library3);

                var library4 = new Library();
                library4.setUserId(user.getId());
                library4.setBook(lotr3);
                library4.setStatus(1);
                libraryService.save(library4);
            } else if (i == 1) {
                var library1 = new Library();
                library1.setUserId(user.getId());
                library1.setBook(lotr2);
                library1.setStatus(3);
                libraryService.save(library1);
            } else if (i == 2) {
                var library1 = new Library();
                library1.setUserId(user.getId());
                library1.setBook(hobbit);
                library1.setStatus(2);
                libraryService.save(library1);

                var library2 = new Library();
                library2.setUserId(user.getId());
                library2.setBook(lotr1);
                library2.setStatus(3);
                libraryService.save(library2);
            } else if (i == 3) {
                var library1 = new Library();
                library1.setUserId(user.getId());
                library1.setBook(lotr3);
                library1.setStatus(1);
                libraryService.save(library1);

                var library2 = new Library();
                library2.setUserId(user.getId());
                library2.setBook(lotr2);
                library2.setStatus(2);
                libraryService.save(library2);
            } else if (i == 4) {
                var library1 = new Library();
                library1.setUserId(user.getId());
                library1.setBook(lotr1);
                library1.setStatus(3);
                libraryService.save(library1);

                var library2 = new Library();
                library2.setUserId(user.getId());
                library2.setBook(lotr3);
                library2.setStatus(1);
                libraryService.save(library2);
            } else if (i == 5) {
                var library1 = new Library();
                library1.setUserId(user.getId());
                library1.setBook(hobbit);
                library1.setStatus(2);
                libraryService.save(library1);

                var library2 = new Library();
                library2.setUserId(user.getId());
                library2.setBook(lotr2);
                library2.setStatus(3);
                libraryService.save(library2);
            } else if (i == 6) {
                var library1 = new Library();
                library1.setUserId(user.getId());
                library1.setBook(lotr3);
                library1.setStatus(1);
                libraryService.save(library1);

                var library2 = new Library();
                library2.setUserId(user.getId());
                library2.setBook(lotr1);
                library2.setStatus(2);
                libraryService.save(library2);
            } else if (i == 7) {
                var library1 = new Library();
                library1.setUserId(user.getId());
                library1.setBook(lotr2);
                library1.setStatus(3);
                libraryService.save(library1);

                var library2 = new Library();
                library2.setUserId(user.getId());
                library2.setBook(hobbit);
                library2.setStatus(1);
                libraryService.save(library2);
            } else if (i == 8) {
                var library1 = new Library();
                library1.setUserId(user.getId());
                library1.setBook(lotr1);
                library1.setStatus(2);
                libraryService.save(library1);

                var library2 = new Library();
                library2.setUserId(user.getId());
                library2.setBook(lotr2);
                library2.setStatus(3);
                libraryService.save(library2);
            } else if (i == 9) {
                var library1 = new Library();
                library1.setUserId(user.getId());
                library1.setBook(lotr3);
                library1.setStatus(1);
                libraryService.save(library1);

                var library2 = new Library();
                library2.setUserId(user.getId());
                library2.setBook(hobbit);
                library2.setStatus(2);
                libraryService.save(library2);
            }
        }
    }   
}
