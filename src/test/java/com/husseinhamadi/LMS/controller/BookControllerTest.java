package com.husseinhamadi.LMS.controller;

import com.husseinhamadi.LMS.dto.BookDTO;
import com.husseinhamadi.LMS.entity.Book;
import com.husseinhamadi.LMS.exception.NotFoundException;
import com.husseinhamadi.LMS.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;


@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;



    @BeforeEach
    void setUp() throws NotFoundException {
        BookDTO bookDTO = BookDTO.build("Test Book", "Test Author", 2022L, 123456789L);
        Book book = new Book(null, "Test Book", "Test Author", 2022L, 123456789L);


        Long id =1L;

        String deletionMessage = "Book deleted successfully";


        Mockito.when(bookService.getBookList())
                .thenReturn(List.of(bookDTO));

        Mockito.when(bookService.getBookById(id))
                .thenReturn(book);

        Mockito.when(bookService.createBook(Mockito.any(BookDTO.class)))
                .thenReturn(book);

        Mockito.when(bookService.updateBook(Mockito.anyLong(), Mockito.any(BookDTO.class)))
                .thenReturn(book);

        Mockito.when(bookService.deleteBook(Mockito.anyLong()))
                .thenReturn(deletionMessage);

    }

    @Test
    void testGetBookList() throws Exception {
        //using mockMvc to perform a request(get) then expect results(ok and content)
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void testGetBookById() throws Exception {
        Long bookId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/{id}", bookId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreateBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"isbn\": 2002,\n" +
                        "\t\"title\": \"Math\",\n" +
                        "\t\"author\": \"Jack\",\n" +
                        "    \"publicationYear\": 2002\n" +
                        "\t\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void updateBook() throws Exception {
        Long bookId=1L;
        mockMvc.perform(MockMvcRequestBuilders.put("/api/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"title\":\"abc\"\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteBook() throws Exception {
        String deletionMessage = "Book deleted successfully";
        Long bookId =1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/{id}", bookId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(deletionMessage));


    }
}