package com.example.LibraryProject.model;
import lombok.Data;

import java.util.ArrayList;

@Data
public class AccessInfoFromJson {
    private String country;
    private String viewability;
    private boolean embeddable;
    private boolean publicDomain;
    private String textToSpeechPermission;
    private EpubFromJson epub;
    private PdfFromJson pdf;
    private String webReaderLink;
    private String accessViewStatus;
    private boolean quoteSharingAllowed;

}
