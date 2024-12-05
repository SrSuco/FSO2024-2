package br.univille.fsoweb20242.controller;

import java.util.Collections;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.univille.fsoweb20242.service.BookService;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private BookService bookService;

    @GetMapping    
    public ModelAndView index(@AuthenticationPrincipal OAuth2User principal){
        HashMap<String,Object> dados = new HashMap<>();
        var username = principal.getAttribute("preferred_username");
        var role = principal.getAttribute("roles") != null ? principal.getAttribute("roles").toString() : "";
        boolean isAdmin = role.indexOf("Admin") > 0;

        dados.put("isAdmin",isAdmin);
        dados.put("username",username != null ? username : "Guest");
        dados.put("bookList", bookService.getAll());
        dados.put("page", "home");

        return new ModelAndView("home/index",dados);
    }
}
