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
        var libraryList = service.getAll();
        return new ModelAndView("library/index", "libraryList", libraryList);
    }

    @GetMapping("/new")
    public ModelAndView newLibrary() {
        var library = new Library();
        HashMap<String, Object> data = new HashMap<>();
        data.put("library", library);
        data.put("userList", userService.getAll());
        data.put("bookList", bookService.getAll());
        return new ModelAndView("library/form", data);
    }

    @PostMapping
    public ModelAndView save(Library library) {
        service.save(library);
        return new ModelAndView("redirect:/library");
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
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Library> findById(@PathVariable long id) {
        Library library = service.findById(id);
        if (library != null) {
            return ResponseEntity.ok(library);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public List<Library> findByUserId(@PathVariable long userId) {
        return service.findByUserId(userId);
    }

    @GetMapping("/user/{userId}/book/{bookId}")
    public List<Library> findByUserIdAndBookId(@PathVariable long userId, @PathVariable long bookId) {
        return service.findByUserIdAndBookId(userId, bookId);
    }
}