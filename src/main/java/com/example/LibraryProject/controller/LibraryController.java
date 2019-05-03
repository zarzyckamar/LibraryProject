package com.example.LibraryProject.controller;

import com.example.LibraryProject.model.LibraryFromJson;
import com.example.LibraryProject.modelEndpoint.BookByISBN;
import com.example.LibraryProject.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;


@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:8080")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;


    @RequestMapping(value = "/byISBN", method = RequestMethod.GET)
    public BookByISBN getBookByISBN(@RequestParam(name = "isbn") String isbn) throws FileNotFoundException {

        LibraryFromJson libraryFromJson = libraryService.getJsonToObject();
        ArrayList<BookByISBN> listofBookByISBNS = new ArrayList<>();

        for (int i = 0; i < libraryFromJson.getItems().size(); i++) {

            BookByISBN bookByISBN = new BookByISBN();
            for (int n = 0; n < libraryFromJson.getItems().get(i).getVolumeInfo().getIndustryIdentifiers().size(); n++) {
                if (libraryFromJson.getItems().get(i).getVolumeInfo().getIndustryIdentifiers().get(n).getType().equals("ISBN_13")) {
                    bookByISBN.setIsbn(libraryFromJson.getItems().get(i).getVolumeInfo().getIndustryIdentifiers().get(n).getIdentifier());
                } else {
                    bookByISBN.setIsbn(libraryFromJson.getItems().get(i).getId());
                }
            }
            bookByISBN.setTitle(libraryFromJson.getItems().get(i).getVolumeInfo().getTitle());
            bookByISBN.setSubtitle(libraryFromJson.getItems().get(i).getVolumeInfo().getSubtitle());
            try {
                bookByISBN.setPublishedDate(Integer.parseInt(libraryFromJson.getItems().get(i).getVolumeInfo().getPublishedDate()));
            } catch (NumberFormatException e) {
                continue;
            }
            bookByISBN.setDescription(libraryFromJson.getItems().get(i).getVolumeInfo().getDescription());
            bookByISBN.setPageCount(libraryFromJson.getItems().get(i).getVolumeInfo().getPageCount());
            bookByISBN.setThumbnailUrl(libraryFromJson.getItems().get(i).getVolumeInfo().getImageLinks().getThumbnail());
            bookByISBN.setLanguage(libraryFromJson.getItems().get(i).getVolumeInfo().getLanguage());
            bookByISBN.setPreviewLink(libraryFromJson.getItems().get(i).getVolumeInfo().getPreviewLink());
            try {
                bookByISBN.setAuthors(libraryFromJson.getItems().get(i).getVolumeInfo().getAuthors());
            } catch (NullPointerException e) {
                System.out.print("null");
                continue;
            }
            bookByISBN.setCategories(libraryFromJson.getItems().get(i).getVolumeInfo().getCategories());
            listofBookByISBNS.add(bookByISBN);

        }
        return libraryService.getBookByISBN(isbn, listofBookByISBNS);
    }

}
