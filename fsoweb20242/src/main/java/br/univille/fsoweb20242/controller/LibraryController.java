package br.univille.fsoweb20242.controller;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import br.univille.fsoweb20242.entity.Library;
import br.univille.fsoweb20242.service.LibraryService;
import br.univille.fsoweb20242.service.ClientUserService;
import br.univille.fsoweb20242.service.BookService;
import br.univille.fsoweb20242.entity.Book;

@Controller
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService service;
    @Autowired
    private ClientUserService userService;
    @Autowired
    private BookService bookService;

    @GetMapping
    public ModelAndView index() {
        var clientuserlist = userService.getAll();
        if (clientuserlist.isEmpty()) {
            HashMap<String, Object> model = new HashMap<>();
            model.put("page", "libraries");
            return new ModelAndView("library/index", model);
        } else {
            return new ModelAndView("redirect:/library/user/" + clientuserlist.get(0).getId());
        }
    }

    @GetMapping("/user/{userId}")
    public ModelAndView viewUserLibrary(@PathVariable long userId) {
        var libraryList = service.findByUserId(userId);
        var clientuserlist = userService.getAll();
        var bookList = service.findBooksInfoByUserId(userId);
    
        HashMap<String, Object> model = new HashMap<>();
        model.put("libraryList", libraryList);
        model.put("userList", clientuserlist);
        model.put("bookList", bookList);
        model.put("selectedUserId", userId);
        model.put("userName", userService.getById(userId).getUsername());
        model.put("page", "libraries");
    
        return new ModelAndView("library/userLibrary", model);
    }

    @GetMapping("/new")
    public ModelAndView newLibrary(@RequestParam("userId") long userId) {
        var library = new Library();
        library.setUserId(userId);
        HashMap<String, Object> data = new HashMap<>();
        data.put("library", library);
        data.put("bookList", bookService.getAll());
        return new ModelAndView("library/form", data);
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute Library library, @RequestParam("book.id") long bookId) {
        library.setBook(bookService.getById(bookId));
        service.save(library);
        return new ModelAndView("redirect:/library/user/" + library.getUserId());
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") long id) {
        var library = service.findById(id);
        HashMap<String, Object> data = new HashMap<>();
        data.put("library", library);
        data.put("userList", userService.getAll());
        data.put("bookList", bookService.getAll());
        return new ModelAndView("library/form", data);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        Library library = service.findById(id);
        if (library != null) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Library> findById(@PathVariable long id) {
        Library library = service.findById(id);
        if (library != null) {
            return ResponseEntity.ok(library);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}/book/{bookId}")
    public ResponseEntity<List<Library>> findByUserIdAndBookId(@PathVariable long userId, @PathVariable long bookId) {
        List<Library> libraries = service.findByUserIdAndBookId(userId, bookId);
        return ResponseEntity.ok(libraries);
    }

    @GetMapping("/user/{userId}/books")
    public ResponseEntity<List<Book>> getBooksInfoByUserId(@PathVariable long userId) {
        List<Book> books = service.findBooksInfoByUserId(userId);
        return ResponseEntity.ok(books);
    }
}