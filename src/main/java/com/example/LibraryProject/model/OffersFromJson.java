package com.example.LibraryProject.model;

import lombok.Data;

@Data
public class OffersFromJson {
    private int finskyOfferType;
    private ListPriceFromJson listPrice;
    private ListPriceFromJson retailPrice;
}
