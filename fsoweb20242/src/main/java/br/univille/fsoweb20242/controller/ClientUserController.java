package br.univille.fsoweb20242.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import br.univille.fsoweb20242.entity.ClientUser;
import br.univille.fsoweb20242.service.ClientUserService;

@Controller
@RequestMapping("/users")
public class ClientUserController {

    @Autowired
    private ClientUserService userService;

    @GetMapping
    public ModelAndView index() {
        var userList = userService.getAll();
        return new ModelAndView("clientuser/index", "userList", userList);
    }

    @GetMapping("/new")
    public ModelAndView newUser() {
        var user = new ClientUser();
        return new ModelAndView("clientuser/form", "user", user);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editUser(@PathVariable("id") long id) {
        var user = userService.getById(id);
        return new ModelAndView("clientuser/form", "user", user);
    }

    @PostMapping
    public ModelAndView save(ClientUser user) {
        userService.save(user);
        return new ModelAndView("redirect:/users");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}