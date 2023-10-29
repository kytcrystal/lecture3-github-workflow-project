package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
public class ExpenseController {

    private final ExpenseRepository repository;

    public ExpenseController(ExpenseRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/expenses")
    public List<Expense> expensesAll() {
        return repository.findAll();
    }

    @GetMapping("/expenses/{id}")
    public Optional<Expense> findExpenseByID(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping("/expenses")
    public Expense addExpense(@RequestBody Expense newExpense) {
        return repository.save(newExpense);
    }
}