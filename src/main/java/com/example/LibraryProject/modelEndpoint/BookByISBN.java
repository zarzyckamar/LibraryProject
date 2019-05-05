package com.example.LibraryProject.modelEndpoint;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookByISBN {
    private String isbn;
    private String title;
    private String subtitle;
    private long publishedDate;
    private String description;
    private int pageCount;
    private String thumbnailUrl;
    private String language;
    private String previewLink;
    private ArrayList<String> authors;
    private ArrayList<String> categories;
}
