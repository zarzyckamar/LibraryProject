package com.example.LibraryProject.service;

import com.example.LibraryProject.model.*;
import com.example.LibraryProject.modelEndpoint.BookByISBN;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

@Service
public class LibraryService {

    public LibraryFromJson getJsonToObject() throws FileNotFoundException {

        Gson gson = new Gson();
        LibraryFromJson library = gson.fromJson(new FileReader("C:/Users/Admin/IdeaProjects/LibraryProject/books.json"), LibraryFromJson.class);
        System.out.print(library.getItems());
        return library;
    }

    public BookByISBN getBookByISBN(String isbn, ArrayList<BookByISBN> books) {
        for (int i = 0; i < books.size(); i++)
            if (isbn.equals(books.get(i).getIsbn())) {
                return books.get(i);
            }
        return null;
    }

    public ArrayList<BookByISBN> creatOutputModel() {

        LibraryFromJson libraryFromJson = null;
        try {
            libraryFromJson= getJsonToObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
        return listofBookByISBNS;
    }

}
