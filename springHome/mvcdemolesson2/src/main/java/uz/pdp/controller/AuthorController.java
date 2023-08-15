package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;
import uz.pdp.dto.CourseDto;
import uz.pdp.model.Author;
import uz.pdp.service.AuthorService;
import uz.pdp.service.CourseService;

import java.util.List;

@Controller
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @Autowired
    CourseService courseService;

    //READ
    @RequestMapping(path = "/authors", method = RequestMethod.GET)
    public String getAllCourses(Model model){
        List<Author> allAuthors = authorService.getAllAuthors();

        model.addAttribute("authorList", allAuthors);
        return "/authors/view-authors";
    }

    //CREATE (part-1)
    @RequestMapping("/authors/addForm")
    public String addCourseForm(Model model){
        return "/authors/add-author-form";
    }

    //CREATE (part-2)
    @RequestMapping(path = "/authors", method = RequestMethod.POST)
    public RedirectView addCourse(@ModelAttribute("author") Author author){
        authorService.saveAuthor(author);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/authors");
        return redirectView;
    }

    //UPDATE
    @RequestMapping("/authors/editForm/{id}")
    public String editCourseForm(Model model, @PathVariable Integer id){
        Author authorById = courseService.getAuthorById(id);

        if (authorById != null){
            model.addAttribute("selectedAuthor", authorById);
            return "authors/edit-author-form";
        } else {
            return "redirect:/authors";
        }
    }

    //DELETE
    @RequestMapping(path = "/authors/delete/{id}", method = RequestMethod.GET)
    public String deleteCourse(Model model, @PathVariable Integer id){
        authorService.deleteCourseById(id);
        return "redirect:/authors";
    }



}
