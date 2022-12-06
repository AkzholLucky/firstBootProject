package kz.akzhol.libraryBootProject.controllers;

import kz.akzhol.libraryBootProject.dao.BookDAO;
import kz.akzhol.libraryBootProject.model.Person;
import kz.akzhol.libraryBootProject.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;
    private final BookDAO bookDAO;

    @Autowired
    public PersonController(PersonService personService, BookDAO bookDAO) {
        this.personService = personService;
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("people", personService.getAllPerson());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personService.getOnePerson(id));
        model.addAttribute("books", bookDAO.findBookByPersonId(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") Person person){
        personService.savePerson(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        personService.deletePerson(id);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String updatePerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personService.getOnePerson(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String editPerson(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        personService.updatePerson(id, person);
        return "redirect:/people";
    }
}
