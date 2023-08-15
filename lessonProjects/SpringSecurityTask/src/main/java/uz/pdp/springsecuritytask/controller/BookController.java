package uz.pdp.springsecuritytask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecuritytask.model.Book;
import uz.pdp.springsecuritytask.repository.BookRepository;

import java.awt.print.PrinterGraphics;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @ModelAttribute("book")
    public Book  book(){
        return new Book();
    }

    @GetMapping()
    public String getAllBooks(Model model){
        List<Book> bookList = bookRepository.findAll();

        model.addAttribute("bookList", bookList);
        return "book/view-books";
    }

    @GetMapping("/add")
    public String addBookForm(){
        return "book/addBookForm";
    }

    @PostMapping("/add")
    public String saveBook(@ModelAttribute Book book){
        bookRepository.save(book);
        return "redirect:/book";
    }

    @GetMapping("/edit/{bookId}")
    public String editBookForm(@PathVariable Long bookId, Model model){
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (!optionalBook.isPresent()){
            return "redirect:/book";
        }
        Book book = optionalBook.get();
        model.addAttribute("book", book);
        return "book/editBookForm";
    }

    @PostMapping("/edit")
    public String updateBook(@ModelAttribute Book book){
        bookRepository.save(book);
        return "redirect:/book";
    }

    @GetMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable Long bookId){
        bookRepository.deleteById(bookId);
        return "redirect:/book";
    }






}
