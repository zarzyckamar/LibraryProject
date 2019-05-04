package com.example.LibraryProject.model;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class VolumeInfoFromJson {
    private String title;
    private String subtitle;
    private ArrayList<String> authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private ArrayList<IndustryIdentifiersFromJson> industryIdentifiers;
    private ReadingsModesFromJson readingModes;
    private int pageCount;
    private String printType;
    private ArrayList<String> categories;
    private double averageRating;
    private int ratingsCount;
    private String maturityRating;
    private boolean allowAnonLogging;
    private String contentVersion;
    private PanelizationSummaryFromJson panelizationSummary;
    private ImageLinksFromJson imageLinks;
    private String language;
    private String previewLink;
    private String infoLink;
    private String canonicalVolumeLink;


}
