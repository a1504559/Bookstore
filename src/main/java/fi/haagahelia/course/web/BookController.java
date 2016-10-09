package fi.haagahelia.course.web;

import java.util.List;

import javax.validation.Valid;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestParam;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.course.BookstoreApplication;
import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookRepository;
import fi.haagahelia.course.domain.Category;
import fi.haagahelia.course.domain.CategoryRepository;

@Controller
public class BookController {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository; 
	
	// Show all books
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
	
    @RequestMapping(value="/booklist")
    public String bookList(Model model) {	
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }
  
	// RESTful service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) repository.findAll();
    }
    
	// RESTful service to get book by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Book findBookRest(@PathVariable("id") Long bookId) {	
    	return repository.findOne(bookId);
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	log.info("XX1 " + crepository.findAll());
    	model.addAttribute("book", new Book());
    	log.info("XX2 " + crepository.findAll());
    	model.addAttribute("categories", crepository.findAll());
        return "addbook";
    }     
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    }    
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        //log.info("BZZZZZ " + bookId.toString());
    	repository.delete(bookId);
        return "redirect:/booklist";
    }     
}
   
/*
 	http://localhost:8080/booklist
 	http://localhost:8080/books
 	http://localhost:8080/book/2

rest	
 	http://localhost:8080/books
 	
 	curl http://localhost:8080/books
*/