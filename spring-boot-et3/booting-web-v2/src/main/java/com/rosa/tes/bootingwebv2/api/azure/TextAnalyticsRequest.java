package com.rosa.tes.bootingwebv2.api.azure;

import java.util.ArrayList;
import java.util.List;

public class TextAnalyticsRequest {

    private List<TextDocument> documents = new ArrayList<>();

    public List<TextDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<TextDocument> documents) {
        this.documents = documents;
    }
}
