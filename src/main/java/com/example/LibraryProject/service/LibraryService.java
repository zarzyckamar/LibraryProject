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

    public BookByISBN getBookByISBN(String isbn, ArrayList<BookByISBN> books){
        for(int i=0; i<books.size(); i++)
            if(isbn.equals(books.get(i).getIsbn())) {
                return books.get(i);
            }
        return null;
}


}
