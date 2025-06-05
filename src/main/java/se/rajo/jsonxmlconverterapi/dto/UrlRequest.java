package se.rajo.jsonxmlconverterapi.dto;

import jakarta.validation.constraints.NotBlank;

public class UrlRequest {
    @NotBlank(message = "URL must not be empty") // Input validation
    private String url;

    public UrlRequest() {
        //Needed for deserialization
    }

    public UrlRequest(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
