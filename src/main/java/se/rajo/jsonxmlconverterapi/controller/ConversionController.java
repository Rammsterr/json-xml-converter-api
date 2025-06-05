package se.rajo.jsonxmlconverterapi.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.rajo.jsonxmlconverterapi.dto.UrlRequest;
import se.rajo.jsonxmlconverterapi.service.ConversionService;

@RestController
@RequestMapping("/api/convert")
public class ConversionController {

    private ConversionService conversionService;

    public ConversionController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @PostMapping("/json-to-xml")
    @Operation(
            summary = "Convert JSON to XML",
            description = "Takes JSON input and converts it to XML format"
    )
    @ApiResponse(responseCode = "200", description = "Successfull conversion")
    @ApiResponse(responseCode = "400", description = "Invalid input")  // Swagger-dokumentation

    // POST request to convert raw JSON to XML
    public String convertJsonToXml(@Valid @RequestBody String json) {
        try {
            return conversionService.convertJsonToXml(json);
        } catch (Exception e) {
            return "<error>Invalid JSON</error>";
        }
    }

    @PostMapping(value = "/xml-to-json", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Convert XML to JSON",
            description = "Takes XML input and converts it to XML format"
    )
    @ApiResponse(responseCode = "200", description = "Successfull conversion")
    @ApiResponse(responseCode = "400", description = "Invalid input")

    // POST request to convert XML to JSON
    public String convertXmlToJson(@Valid @RequestBody String xml) {
        try{
            return conversionService.convertXmlToJson(xml);
        } catch (Exception e) {
            return "<error>Invalid XML</error>";
        }
    }

    @PostMapping("/fetch-json-to-xml")
    @Operation(
            summary = "Fetch JSON from URL and convert it into XML",
            description = "Fetches JSON from an external URL and converts it into XML"
    )
    @ApiResponse(responseCode = "200", description = "Successfull fetch and conversion")
    @ApiResponse(responseCode = "400", description = "Invalid input or URL error")

    public String fetchJsonToXml(@Valid @RequestBody UrlRequest request) {
        return conversionService.fetchJsonFromUrlAndConvertToXml(request.getUrl());
    }

    @PostMapping("/fetch-xml-to-json")
    @Operation(
            summary = "Fetch XML from URL and convert it into JSON",
            description = "Fetches XML from an external URL and converts it into JSON"
    )
    @ApiResponse(responseCode = "200", description = "Successfull fetch and conversion")
    @ApiResponse(responseCode = "400", description = "Invalid input or URL error")

    public String fetchXmlToJson(@Valid @RequestBody UrlRequest request) {
        return conversionService.fetchXmlFromUrlAndConvertToJson(request.getUrl());
    }
}
