Insurance Report API
Spring Boot REST API to generate Eligibility Reports in PDF and Excel format.

Features
Get Unique Plan Names
Get Unique Plan Status
Search Eligibility Records
Export Report as PDF
Export Report as Excel
Technologies Used
Java 17
Spring Boot
Spring Data JPA
MySQL
Apache POI
OpenPDF
Lombok
API Endpoints
Get Plans
GET /plans

Get Plan Status
GET /statues

Search Records
POST /searches

Export Excel
GET /excel

Export PDF
GET /pdf


** Run Project
Shell command
mvn clean install
mvn spring-boot:run
Swagger UI
Plain Text
http://localhost:8080/swagger-ui/index.html
