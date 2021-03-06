package com.example.LibraryProject.controller;

import com.example.LibraryProject.model.LibraryFromJson;
import com.example.LibraryProject.modelEndpoint.AverageRatingModel;
import com.example.LibraryProject.modelEndpoint.BookByISBN;
import com.example.LibraryProject.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;


@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:8080")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public LibraryFromJson getAll() throws FileNotFoundException {
        LibraryFromJson library = libraryService.getJsonToObject();
        return library;
    }

    @RequestMapping(value = "/byISBN", method = RequestMethod.GET)
    public BookByISBN getBookByISBN(@RequestParam(name = "isbn") String isbn) throws FileNotFoundException, ParseException {
        return libraryService.getBookByISBN(isbn, libraryService.creatOutputModel());
    }

    @RequestMapping(value = "/byCategory", method = RequestMethod.GET)
    public ArrayList<BookByISBN> getBookByCategory(@RequestParam(name = "category") String category) throws FileNotFoundException, ParseException {

        return libraryService.getBookByCategory(category, libraryService.creatOutputModel());
    }

    @RequestMapping(value = "/byAverageRating", method = RequestMethod.GET)
    public ArrayList<AverageRatingModel> getByAverageRating() {
        return libraryService.createListWithAverageRating();

    }
}
