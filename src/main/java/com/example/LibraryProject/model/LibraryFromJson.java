package com.example.LibraryProject.model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Data
public class LibraryFromJson {
   private String requestedUrl;
   private ArrayList<ItemsFromJson> items;

}
