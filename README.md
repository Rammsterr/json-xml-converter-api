# JSON/XML Converter API

## Beskrivning
Det här är ett REST-API byggt med Spring Boot som konverterar data mellan JSON och XML. 
API:et tillhandahåller fyra stycken endpoints som klarar av både direkt konvertering och att hämta data från externa
källor. Det är ett vanligt förekommande behov/scenario i systemintegration när olika system använder sig utav olika 
dataformat.

## Komma igång
### Förutsättningar
- Java 21
- Maven
- Docker (för containerisering)
### Körning lokalt
1. Klona repot:
   ```bash
   git clone
   # Bygg applikationen:
   mvn clean package
   # Kör lokalt:
   java -jar target/json-xml-converter-0.0.1-SNAPSHOT.jar
   ```
### Docker
# Bygg docker-image:
```bash
   docker build -t json-xml-converter .
```
# Starta containern:
```bash
   docker run -p 8080:8080 json-xml-converter
```

## API Dokumentation
- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

## API Endpoints
### POST /api/convert/json-to-xml 
Tar JSON i body och returnerar XML
### POST /api/convert/xml-to-json 
Tar XML i body och returnerar JSON
### POST /api/convert/fetch-json-to-xml 
Tar en URL som returnerar JSON och konverterar den till XML
### POST /api/convert/fetch-xml-to-json
Tar en URL som returnerar XML och konverterar den till JSON

## Testning
Se requests.http för färdiga test-requests.

# Exempeldata :
JSON till XML:
```json
{
  "name": "John Doe",
  "age": 30,
  "city": "New York"
}
```
XML till JSON:
```<person>
  <name>Anna</name>
  <age>30</age>
  <city>Stockholm</city>
  <hobbies>
    <hobby>läsa</hobby>
    <hobby>cykla</hobby>
  </hobbies>
</person>
```
# Extern JSON (via URL):
{
  "url": "https://jsonplaceholder.typicode.com/users/1"
}
# Extern XML (via URL):
{
  "url": "https://httpbin.org/xml"
}


### Felhantering
API:et har en global felhanterare som fångar upp undantag och returnerar ett standardiserat felmeddelande i JSON-format.
### Felmeddelandeformat

```
{
"timestamp": "2025-06-05T12:00:00",
"status": 400,
"error": "Invalid XML format"
}
```
Feltyper som hanteras:
- Ogiltig JSON eller XML
- Saknad eller felaktig URL
- Problem med externa anrop

### Reflektioner

Jag har byggt upp applikationen enligt clean code-principer med tydlig separation av ansvar mellan controller,
service och felhantering. Swagger ger användaren en enkel ingång till API:et och möjliggör direkt testning.
Jag har fokuserat på att göra konverteringslogiken robust och återanvändbar, samt på att dokumentera API:et tydligt
för andra utvecklare.
Projektet är containeriserat med Docker för enkel distribution och körning i olika miljöer.
Jag har även lagt till validering för att skydda mot ogiltiga dataflöden. Det var en hyfsat straightforward uppgift i sig.
Jag behövde kika lite på tidigare lektioner och tidigare exempelkod, chatGPT står som vanligt vid min sida och hjälper till att
lösa problem samt påminna mig om saker jag glömt bort hur man gör, och lär mig nya saker. Jag har lärt mig mycket om 
meddelandekonvertering, felhantering och hur man dockeriserar en Spring Boot-applikation.

[Johan Ramsenius]  

JSON/XML Converter API

[2025-06-05]

📂 GitHub-repo: https://github.com/Rammsterr/json-xml-converter-api

