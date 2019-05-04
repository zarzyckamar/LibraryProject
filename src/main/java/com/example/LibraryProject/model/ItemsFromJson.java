package com.example.LibraryProject.model;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ItemsFromJson {
    private String kind;
    private String id;
    private String etag;
    private String selfLink;
    private VolumeInfoFromJson volumeInfo;
    private SaleInfoFromJson saleInfo;
    private AccessInfoFromJson accessInfo;
    private SearchInfoFromJson searchInfo;

}
