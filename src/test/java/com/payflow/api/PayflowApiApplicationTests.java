package com.payflow.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payflow.api.entity.Transaction;
import com.payflow.api.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PayflowApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testFullControllerWorkflow() throws Exception {
        // 1. POST /users - Register User 1 (Priya) -> 201 Created
        User user1 = new User("Priya", "priya@okaxis", "9876543210", 1000.0);
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Priya")))
                .andExpect(jsonPath("$.upiId", is("priya@okaxis")))
                .andExpect(jsonPath("$.phoneNumber", is("9876543210")))
                .andExpect(jsonPath("$.balance", is(1000.0)));

        // 2. POST /users - Register User 2 (Rahul) -> 201 Created
        User user2 = new User("Rahul", "rahul@okicici", "9123456789", 500.0);
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user2)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.upiId", is("rahul@okicici")));

        // 3. GET /users - Get all users -> 200 OK
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        // 4. GET /users/{id} - Get user by ID -> 200 OK
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Priya")));

        // 5. GET /users/{id} - Get non-existent user -> 404 Not Found
        mockMvc.perform(get("/users/99"))
                .andExpect(status().isNotFound());

        // 6. GET /users/upi/{upiId} - Get user by UPI ID -> 200 OK
        mockMvc.perform(get("/users/upi/priya@okaxis"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));

        // 7. GET /users/upi/{upiId} - Get non-existent UPI -> 404 Not Found
        mockMvc.perform(get("/users/upi/unknown@upi"))
                .andExpect(status().isNotFound());

        // 8. GET /users/high-balance?amount=600 -> 200 OK
        mockMvc.perform(get("/users/high-balance?amount=600"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Priya")));

        // 9. POST /transactions - Record money transfer -> 201 Created
        Transaction tx = new Transaction("priya@okaxis", "rahul@okicici", 250.0);
        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tx)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.senderUpiId", is("priya@okaxis")))
                .andExpect(jsonPath("$.receiverUpiId", is("rahul@okicici")))
                .andExpect(jsonPath("$.amount", is(250.0)));
    }
}
