package com.rosa.tes.bootingwebv2.api.azure;

public class SentimentAnalysis {

    private TextDocument document;
    private String sentiment;
    public SentimentAnalysis() {

    }
    public SentimentAnalysis(TextDocument document) {

    }

    public SentimentAnalysis(TextDocument document, String sentiment) {

    }

    public TextDocument getDocument() {
        return document;
    }

    public void setDocument(TextDocument document) {
        this.document = document;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }
}
