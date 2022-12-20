package kz.akzhol.libraryBootProject.controllers;

import kz.akzhol.libraryBootProject.model.Librarian;
import kz.akzhol.libraryBootProject.services.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final LibrarianService librarianService;

    @Autowired
    public AuthController(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("librarian")Librarian librarian){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String save(@ModelAttribute("librarian") Librarian librarian){
        librarianService.save(librarian);
        return "redirect:auth/login";
    }
}
