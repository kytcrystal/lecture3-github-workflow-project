package com.example.demo;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class ExpenseControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Test
    public void getExpense() throws Exception {
        String expenseString = "{\"description\":\"Test New 1\",\"amount\":50.0,\"date\":\"02/01/2023\"}";

        mvc.perform(MockMvcRequestBuilders.get("/expenses/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expenseString));
    }

    @Test
    public void addExpenseShouldCreateANewExpenseWithCorrectFields() throws Exception {
        String expenseString = "{\"description\":\"Test New TEST-CREATE\",\"amount\":10.0,\"date\":\"01/01/2023\"}";

        mvc.perform(MockMvcRequestBuilders.post("/expenses")
                        .content(expenseString)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        List<Expense> expenses = expenseRepository.findAll();
        Optional<Expense> createdExpense = expenses.stream().filter(expense -> {
            return expense.description != null && expense.description.contains("TEST-CREATE");
        }).findFirst();

        Assert.isTrue(createdExpense.isPresent());
    }

}