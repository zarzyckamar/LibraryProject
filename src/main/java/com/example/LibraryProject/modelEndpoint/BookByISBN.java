package com.example.LibraryProject.modelEndpoint;

import lombok.Data;

import java.util.ArrayList;

@Data
public class BookByISBN {
    private String isbn;
    private String title;
    private String subtitle;
    private int publishedDate;
    private String description;
    private int pageCount;
    private String thumbnailUrl;
    private String language;
    private String previewLink;
    private ArrayList<String> authors;
    private ArrayList<String> categories;
}
