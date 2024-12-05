package br.univille.fsoweb20242.controller;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.univille.fsoweb20242.entity.Book;
import br.univille.fsoweb20242.service.BookService;
import br.univille.fsoweb20242.service.ClientUserService;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService service;
    @Autowired
    private ClientUserService userService;

    @GetMapping
    public ModelAndView index(){
        var bookList = service.getAll();
        HashMap<String, Object> model = new HashMap<>();
        model.put("bookList", bookList);
        model.put("page", "books");
        return new ModelAndView("book/index", model);
    }

    @GetMapping("/new")
    public ModelAndView newBook(){
        var book = new Book();
        HashMap<String,Object> data = new HashMap<>();
        data.put("book", book);
        data.put("userList", userService.getAll());
        return new ModelAndView("book/form", data);
    }

    @PostMapping
    public ModelAndView save(Book book){
        service.save(book);
        return new ModelAndView("redirect:/book");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") long id){
        var book = service.getById(id);
        HashMap<String,Object> data = new HashMap<>();
        data.put("book", book);
        data.put("userList", userService.getAll());
        return new ModelAndView("book/form", data);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
