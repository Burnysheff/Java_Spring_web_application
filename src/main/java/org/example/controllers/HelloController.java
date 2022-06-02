package org.example.controllers;

import org.example.model.Person;
import org.example.personDAO.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/person")
public class HelloController {

    private final PersonDAO personDAO;

    public HelloController() {
        this.personDAO = new PersonDAO();
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("personList", personDAO.getAll());
        return "people/showAll";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id,  Model model) {
        model.addAttribute("person", personDAO.getPerson(id));
        return "people/showPerson";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/newPerson";
    }

    @GetMapping("/patch/{id}")
    public String patchPerson(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.getPerson(id));
        return "people/changePerson";
    }

    @PatchMapping("/{id}")
    public String changePerson(@ModelAttribute("person") @Valid Person person, BindingResult result,
                               @PathVariable("id") int id) {
        if (result.hasErrors()) {
            return "people/changePerson";
        }
        personDAO.changePerson(id, person);
        return "redirect:/person";
    }

    @PostMapping()
    public String addPerson(@ModelAttribute("person") @Valid Person person, BindingResult result) {
        if (result.hasErrors()) {
            return "people/newPerson";
        }
        personDAO.addPerson(person);
        return "redirect:/person";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        this.personDAO.deletePerson(id);
        return "redirect:/person";
    }
}
