package uz.pdp.springsecuritytask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import uz.pdp.springsecuritytask.model.Book;
import uz.pdp.springsecuritytask.repository.BookRepository;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    BookRepository bookRepository;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @PostMapping("/user/books")
    public String showBooks(Model model){
        List<Book> bookList = bookRepository.findAll();
        model.addAttribute("bookList", bookList);
        return "user/books";
    }

    @GetMapping("/user/books")
    public String showBooksWithGetMethod(Model model){
        List<Book> bookList = bookRepository.findAll();
        model.addAttribute("bookList", bookList);
        return "user/books";
    }

    @GetMapping("/my-error-page")
    public String showErrorPage(){
        return "user/accessDenied";
    }

}
