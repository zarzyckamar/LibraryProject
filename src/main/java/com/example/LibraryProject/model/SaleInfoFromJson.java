package com.example.LibraryProject.model;
import lombok.Data;

import java.util.ArrayList;

@Data
public class SaleInfoFromJson {
    private String country;
    private String saleability;
    private boolean isEbook;
    private ListPriceAmountFromJson listPrice;
    private ListPriceAmountFromJson retailPrice;
    private String buyLink;
    private ArrayList<OffersFromJson> offers;
}
