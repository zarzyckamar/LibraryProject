package com.example.LibraryProject.service;

import com.example.LibraryProject.model.*;
import com.example.LibraryProject.modelEndpoint.AverageRatingModel;
import com.example.LibraryProject.modelEndpoint.BookByISBN;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

@Service
public class LibraryService {

    public LibraryFromJson getJsonToObject() throws FileNotFoundException {

        Gson gson = new Gson();
        LibraryFromJson library = gson.fromJson(new FileReader("C:/Users/Admin/IdeaProjects/LibraryProject/books.json"), LibraryFromJson.class);
        return library;
    }

    public BookByISBN getBookByISBN(String isbn, ArrayList<BookByISBN> books) {
        for (int i = 0; i < books.size(); i++)
            if (isbn.equals(books.get(i).getIsbn())) {
                return books.get(i);
            }
        return null;
    }

    public ArrayList<BookByISBN> getBookByCategory(String category, ArrayList<BookByISBN> books) {
        ArrayList<BookByISBN> listOfFilteredCategoryBooks = new ArrayList<>();
        for (int t = 0; t < books.size(); t++) {
            if (books.get(t).getCategories() != null) {
                for (int n = 0; n < books.get(t).getCategories().size(); n++) {
                    if (category.equals(books.get(t).getCategories().get(n)))
                        listOfFilteredCategoryBooks.add(books.get(t));

                }
            } else continue;
        }
        return listOfFilteredCategoryBooks;
    }

    private LibraryFromJson getData() {
        LibraryFromJson libraryFromJson = new LibraryFromJson();
        try {
            libraryFromJson = getJsonToObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return libraryFromJson;
    }

    public ArrayList<BookByISBN> creatOutputModel() throws ParseException {

        LibraryFromJson libraryFromJson = getData();
        ArrayList<BookByISBN> listofBookByISBNS = new ArrayList<>();

        for (int i = 0; i < libraryFromJson.getItems().size(); i++) {

            BookByISBN bookByISBN = new BookByISBN();
            bookByISBN.setTitle(libraryFromJson.getItems().get(i).getVolumeInfo().getTitle());
            bookByISBN.setSubtitle(libraryFromJson.getItems().get(i).getVolumeInfo().getSubtitle());
            if (libraryFromJson.getItems().get(i).getVolumeInfo().getPublishedDate() != null) {
                String dateString = libraryFromJson.getItems().get(i).getVolumeInfo().getPublishedDate();

                if (libraryFromJson.getItems().get(i).getVolumeInfo().getPublishedDate().length() > 4) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = format.parse(dateString);
                    long unixTime = (long) date.getTime();
                    bookByISBN.setPublishedDate(unixTime);
                } else {
                    SimpleDateFormat formatYear = new SimpleDateFormat("YYYY");
                    Date dateYear = formatYear.parse(dateString);
                    long unixTimeYear = (long) dateYear.getTime();
                    bookByISBN.setPublishedDate(unixTimeYear);
                }
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
            for (int n = 0; n < libraryFromJson.getItems().get(i).getVolumeInfo().getIndustryIdentifiers().size(); n++) {
                if (libraryFromJson.getItems().get(i).getVolumeInfo().getIndustryIdentifiers().get(n).getType().equals("ISBN_13")) {
                    bookByISBN.setIsbn(libraryFromJson.getItems().get(i).getVolumeInfo().getIndustryIdentifiers().get(n).getIdentifier());

                    listofBookByISBNS.add(bookByISBN);
                    break;
                } else {
                    bookByISBN.setIsbn(libraryFromJson.getItems().get(i).getId());

                }

                listofBookByISBNS.add(bookByISBN);
            }
        }
        removeDuplicates(listofBookByISBNS);
        return listofBookByISBNS;
    }

    public void removeDuplicates(ArrayList<BookByISBN> listofBooks) {
        for (int i = 1; i < listofBooks.size(); i++) {
            if (listofBooks.get(i - 1).getIsbn() == listofBooks.get(i).getIsbn()) listofBooks.remove(i);
        }
    }

    public ArrayList<AverageRatingModel> createListWithAverageRating() {

        LibraryFromJson libraryFromJson = getData();
        ArrayList<AverageRatingModel> listOfAvarageRating = new ArrayList<>();
        for (int i = 0; i < libraryFromJson.getItems().size(); i++) {
            AverageRatingModel averageRatingModel = new AverageRatingModel();
            if (libraryFromJson.getItems().get(i).getVolumeInfo().getAuthors() != null) {
                if (libraryFromJson.getItems().get(i).getVolumeInfo().getAuthors().size() == 1) {
                    averageRatingModel.setAverageRating(libraryFromJson.getItems().get(i).getVolumeInfo().getAverageRating());
                    averageRatingModel.setAuthor(libraryFromJson.getItems().get(i).getVolumeInfo().getAuthors().get(0));
                    if (averageRatingModel.getAverageRating() != 0) {
                        listOfAvarageRating.add(averageRatingModel);
                    }
                } else {
                    for (int j = 0; j < libraryFromJson.getItems().get(i).getVolumeInfo().getAuthors().size(); j++) {

                        AverageRatingModel averageRatingModel2 = new AverageRatingModel();
                        averageRatingModel2.setAuthor(libraryFromJson.getItems().get(i).getVolumeInfo().getAuthors().get(j));
                        averageRatingModel2.setAverageRating(libraryFromJson.getItems().get(i).getVolumeInfo().getAverageRating());
                        if (averageRatingModel2.getAverageRating() != 0) {
                            listOfAvarageRating.add(averageRatingModel2);
                        }

                    }
                }
            }
        }
        getAverageRaitingOfAuthor(listOfAvarageRating);
        Collections.sort(listOfAvarageRating, averageRatingComparator);
        Collections.reverse(listOfAvarageRating);
        return listOfAvarageRating;
    }

    public ArrayList<AverageRatingModel> getAverageRaitingOfAuthor(ArrayList<AverageRatingModel> list) {
        Collections.sort(list, averageRatingAuthorComparator);
        for (int i = 1; i < list.size(); i++) {

            if (list.get(i).getAuthor().equals(list.get(i - 1).getAuthor())) {
                list.get(i).setAverageRating((list.get(i).getAverageRating() + list.get(i - 1).getAverageRating()) / 2);
                list.remove(i - 1);
            }
        }
        return list;
    }

    Comparator<AverageRatingModel> averageRatingAuthorComparator = new Comparator<AverageRatingModel>() {
        @Override
        public int compare(AverageRatingModel o1, AverageRatingModel o2) {
            return o1.getAuthor().compareTo(o2.getAuthor());
        }

    };
    Comparator<AverageRatingModel> averageRatingComparator = new Comparator<AverageRatingModel>() {
        @Override
        public int compare(AverageRatingModel o1, AverageRatingModel o2) {
            if (o1.getAverageRating() < o2.getAverageRating()) return -1;
            else return 1;
        }

    };
}
