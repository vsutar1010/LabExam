# Online Examination Application

A small Java web application for taking multiple-choice exams and seeing an automatically graded result. It uses Spring Boot, Thymeleaf, Maven, and an in-memory H2 database. Sample exams are loaded each time the app starts.

## Requirements

- Java 17 or newer
- Maven 3.6 or newer
- A web browser

## Run the application

1. Clone the project and enter its folder:

   ```bash
   git clone https://github.com/vsutar1010/LabExam.git
   cd LabExam
   ```

2. Start the application:

   ```bash
   mvn spring-boot:run
   ```

3. Open [http://localhost:8081](http://localhost:8081), choose an exam, select an answer for each question, and submit to view your score and answer review.
4. Stop the application with `Ctrl+C`.

## Build and test

Run the tests:

```bash
mvn test
```

Create the executable Spring Boot JAR:

```bash
mvn clean package
```

Run the generated artifact:

```bash
java -jar target/online-examination-1.0.0.jar
```

The application listens on port `8081` by default.

## Project layout

- `src/main/java/com/labexam` — application, exam models, service, and web controller
- `src/main/resources/templates` — Thymeleaf pages
- `src/main/resources/static` — page styling
- `src/main/resources/schema.sql` — database tables
- `src/main/resources/data.sql` — sample exams and questions
- `src/test` — service integration test

The database is in memory, so exam data is reloaded from `data.sql` at startup and attempt results are not saved. This intentionally keeps the project easy to run without installing or configuring a separate database.