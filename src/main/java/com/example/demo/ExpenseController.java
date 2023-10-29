package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
public class ExpenseController {

    private final ExpenseRepository repository;

    public ExpenseController(ExpenseRepository repository) {
        this.repository = repository;
    }

    Expense newExpense = new Expense(new Random().nextLong(),"Test New", 10.0F, "01/01/2023");

    @GetMapping("/expenses")
    public List<Expense> expensesAll() {
        return repository.findAll();
    }

    @GetMapping("/expenses/{id}")
    public Optional<Expense> findExpenseByID(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping("/expenses/add")
    public Expense addExpense(@RequestBody Expense newExpense) {
        return repository.save(newExpense);
    }
}