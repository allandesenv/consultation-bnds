package com.rosa.tes.bootingwebv2.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rosa.tes.bootingwebv2.api.azure.SentimentAnalysis;
import com.rosa.tes.bootingwebv2.api.azure.TextAnalyticsRequest;
import com.rosa.tes.bootingwebv2.api.azure.TextDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.ThemeTag;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class AzureSentimentService {
    @Value("${AZURE API KEY}")
    private String azureApiKey;

    @Autowired
    private ObjectMapper mapper;
    private static final String AZURE_ENDPOINT = "https://ti-solutions-landon-hotel-feedback.cognitiveservices.azure.com/";

    private static final String API_KEY_HEADER_NAME = "Ocp-Apim-Subscription-Key";

    private static final String CONTENT_TYPE = "Content-Type";

    private static final String APPLICATION_JSON = "application/json";

    public SentimentAnalysis requestSentimentAnalysis(String text, String language) throws IOException, InterruptedException {

        TextDocument document = new TextDocument("1", text, language);
        TextAnalyticsRequest requestBody = new TextAnalyticsRequest();
        requestBody.getDocuments().add(document);

        String endpoint = AZURE_ENDPOINT + "text/analytics/v3.0/sentiment";

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .proxy(ProxySelector.getDefault())
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header(API_KEY_HEADER_NAME, this.azureApiKey)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(requestBody)))
                .timeout(Duration.ofSeconds(5))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200) {
            System.out.println(response.body());
            throw new RuntimeException("An issue ocurred making the API call");
        }

        String sentimentValue = this.mapper
                .readValue(response.body(), JsonNode.class)
                .get("documents")
                .get(0)
                .get("sentiment")
                .asText();

        SentimentAnalysis analysis = new SentimentAnalysis(document, sentimentValue);

        return analysis;

    }




}
