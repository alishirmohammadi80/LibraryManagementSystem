package com.alishirmohammadi.librarymanagementsystem.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.alishirmohammadi.librarymanagementsystem.entity.Book;
import com.alishirmohammadi.librarymanagementsystem.pdfExporter.BookPdfExporter;
import com.alishirmohammadi.librarymanagementsystem.repository.BookRepository;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.alishirmohammadi.librarymanagementsystem.service.MemberService;
import com.alishirmohammadi.librarymanagementsystem.service.BookService;
import com.alishirmohammadi.librarymanagementsystem.service.CategoryService;
import com.alishirmohammadi.librarymanagementsystem.service.PublisherService;

import javax.servlet.http.HttpServletResponse;

@Controller
public class BookController {
@Autowired
BookService bookService;
@Autowired
MemberService memberService;
@Autowired
CategoryService categoryService;
@Autowired
PublisherService publisherService;

	public BookController(BookService bookService, MemberService memberService, CategoryService categoryService,
						  PublisherService publisherService) {
		this.bookService = bookService;
		this.memberService = memberService;
		this.categoryService = categoryService;
		this.publisherService = publisherService;
	}
@GetMapping("/books")
	//@RequestMapping(value = "/books",method = RequestMethod.GET)
	public String findAllBooks(Model model) {

		 List<Book> books = bookService.findAllBooks();

		model.addAttribute("books", books);
		return "list-books";
	}
@GetMapping("/searchBook")
	//@RequestMapping(value = "/searchBook",method = RequestMethod.GET)
	public String searchBook(@Param("keyword") String keyword, Model model) {

		 List<Book> books = bookService.searchBooks(keyword);

		model.addAttribute("books", books);
		model.addAttribute("keyword", keyword);
		return "list-books";
	}
@GetMapping("/book/{id}")
	//@RequestMapping(value = "/book/{id}",method = RequestMethod.GET)
	public String findBookById(@PathVariable("id") Long id, Model model) {

		 Book book = bookService.findBookById(id);

		model.addAttribute("book", book);
		return "list-book";
	}

	@GetMapping("/add")
	public String showCreateForm(Book book, Model model) {
		model.addAttribute("categories", categoryService.findAllCategories());
		model.addAttribute("members", memberService.findAllMembers());
		model.addAttribute("publishers", publisherService.findAllPublishers());
		return "add-book";
	}
@PostMapping("/add-book")
	//@RequestMapping(value = "/add-book",method = RequestMethod.POST)
	public String createBook(Book book, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-book";
		}

		bookService.createBook(book);
		model.addAttribute("book", bookService.findAllBooks());
		return "redirect:/books";
	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {

		 Book book = bookService.findBookById(id);

		model.addAttribute("book", book);
		return "update-book";
	}
@PostMapping(value = "/update-book/{id}")
	//@RequestMapping(value = "/update-book/{id}")
	public String updateBook(@PathVariable("id") Long id, Book book, BindingResult result, Model model) {
		if (result.hasErrors()) {
			book.setId(id);
			return "update-book";
		}

		bookService.updateBook(book);
		model.addAttribute("book", bookService.findAllBooks());
		return "redirect:/books";
	}
@GetMapping("/remove-book/{id}")
	//@RequestMapping(value = "/remove-book/{id}",method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		bookService.deleteBook(id);

		model.addAttribute("book", bookService.findAllBooks());
		return "redirect:/books";
	}
	@Autowired
    BookRepository repository;
	@GetMapping("/book/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Books_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<Book> listbooks = bookService.findAllBooks();

		BookPdfExporter exporter = new BookPdfExporter(listbooks);
		exporter.export(response);

	}
}
