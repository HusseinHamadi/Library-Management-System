

# Library Management System API

This repository contains the OpenAPI definition for a Library Management System API. The API allows users to manage books, patrons, and borrowing records in a library.

## Getting Started

To get started with using this API, follow these steps:

1. Clone this repository to your local machine.
2. Make sure you have Java Development Kit (JDK) installed on your machine.
3. Open the project in your preferred Integrated Development Environment (IDE) such as IntelliJ IDEA or Eclipse.
4. Build the project to resolve dependencies and compile the code.
5. Start the Spring Boot application by running the main class `LibraryManagementSystemApplication` or using the provided Maven command `mvn spring-boot:run`.
6. The API will be accessible at `http://localhost:8081` by default.

## API Endpoints

The API provides the following endpoints:

### Book:
- `GET /api/books`: Retrieves a list of all books.
- `GET /api/books/{id}`: Retrieves a specific book by its ID.
- `POST /api/books`: Creates a new book.
- `PUT /api/books/{id}`: Updates an existing book by its ID.
- `DELETE /api/books/{id}`: Deletes a book by its ID.

### Patron:
- `GET /api/patrons`: Retrieves a list of all patrons.
- `GET /api/patrons/{id}`: Retrieves a specific patron by their ID.
- `POST /api/patrons`: Creates a new patron.
- `PUT /api/patrons/{id}`: Updates an existing patron by their ID.
- `DELETE /api/patrons/{id}`: Deletes a patron by their ID.

### BorrowRecord:
- `POST /api/borrow/{bookId}/patron/{patronId}`: Borrows a book for a patron.
- `POST /api/return/{bookId}/patron/{patronId}`: Returns a borrowed book.

## How to Interact with the API

You can interact with the API using tools like Postman, or Swagger UI.


### Using Postman

1. Import the provided OpenAPI specification (`openapi.yaml`) into Postman to generate API documentation and requests.
2. Use the generated endpoints in Postman to interact with the API.

### Using Swagger UI

1. Run the Spring Boot application and navigate to `http://localhost:8081/swagger-ui.html` in your web browser.
2. Explore and interact with the API endpoints using the Swagger UI interface.

