package kz.akzhol.libraryBootProject.controllers;

import kz.akzhol.libraryBootProject.dao.BookDAO;
import kz.akzhol.libraryBootProject.model.Book;
import kz.akzhol.libraryBootProject.model.Person;
import kz.akzhol.libraryBootProject.services.BookService;
import kz.akzhol.libraryBootProject.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final PersonService personService;
    private final BookDAO bookDAO;

    @Autowired
    public BookController(BookService bookService, PersonService personService, BookDAO bookDAO) {
        this.bookService = bookService;
        this.personService = personService;
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("books", bookService.indexBooks());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("personForSelect") Person person){
        model.addAttribute("book", bookService.findBookById(id));
        model.addAttribute("person", personService.findPerson(id));
        model.addAttribute("people", personService.getAllPerson());
        return "books/show";
    }

    @GetMapping("/new")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @PostMapping
    public String createBook(@ModelAttribute("book") Book book){
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookService.findBookById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") Book book, @PathVariable("id") int id){
        bookService.updateBook(book, id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteBook(@PathVariable("id") int id){
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/set")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String setBook(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        bookDAO.setPersonId(id, person.getId());
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/release")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String releaseBook(@PathVariable("id") int id){
        bookDAO.releasePersonId(id);
        return "redirect:/books/" + id;
    }
}
