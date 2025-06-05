package se.rajo.jsonxmlconverterapi.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.rajo.jsonxmlconverterapi.exception.ConversionException;

import java.time.Duration;

@Service
public class ConversionService {

    private final ObjectMapper jsonMapper;
    private final XmlMapper xmlMapper;
    private final RestTemplate restTemplate;

    public ConversionService(RestTemplateBuilder builder) {
        this.jsonMapper = new ObjectMapper();
        this.xmlMapper = new XmlMapper();
        this.restTemplate = builder
                .setConnectTimeout(Duration.ofSeconds(3)) // Waiting 3000 ms to connect
                .setReadTimeout(Duration.ofSeconds(5)) // Waiting 5000 ms for answer
                .build();
    }


    // Converts a raw JSON string to an XML string
    // Uses Jackson ObjectMapper and XmlMapper to parse and serialize data
    public String convertJsonToXml(String json) {
        try {
            JsonNode jsonNode = jsonMapper.readTree(json);
            return xmlMapper.writeValueAsString(jsonNode);
        } catch (Exception e) {
            throw new ConversionException("Failed to convert JSON to XML", e);
        }
    }
    // Converts XML --> JSON
    public String convertXmlToJson(String xml) {
        try{
            JsonNode jsonNode = xmlMapper.readTree(xml.getBytes());
            return jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
        } catch (Exception e) {
            throw new ConversionException("Failed to convert XML to JSON", e);
        }
    }
    // Fetches JSON from URL and converts it to XML
    public String fetchJsonFromUrlAndConvertToXml(String url) {
        try{
            String json = restTemplate.getForObject(url, String.class);
            return convertJsonToXml(json); // Reuse existing logic
        } catch (Exception e) {
            throw new ConversionException("Failed to fetch or convert JSON from URL: " + url, e);
        }
    }
    // Fetches XML --converts--> JSON
    public String fetchXmlFromUrlAndConvertToJson(String url) {
        try {
            String xml = restTemplate.getForObject(url,String.class);
            return convertXmlToJson(xml);
        } catch (Exception e) {
            throw new ConversionException("Failed to fetch or convert XML from URL: " + url, e);
        }
    }

}
