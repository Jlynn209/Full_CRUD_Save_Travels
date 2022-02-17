package com.jeremy.saveTravels.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jeremy.saveTravels.models.Expense;
import com.jeremy.saveTravels.repo.ExpenseRepo;

@Service
public class ExpenseService {
	private final ExpenseRepo expenseRepo;
	
	public ExpenseService(ExpenseRepo expenseRepo) {
		this.expenseRepo = expenseRepo;
	}
	
	public List<Expense> allExpenses() {
		return expenseRepo.findAll();
	}
	
	public Expense showOne(Long id) {
		Optional<Expense> optionalExpense = expenseRepo.findById(id);
		if(optionalExpense.isPresent()) {
			return optionalExpense.get();
		}else {
			return null;
		}
	}
	
	public Expense createExpense(Expense e) {
		return expenseRepo.save(e);
	}
	
	public Expense updateExpense(Expense e, Long id) {
		Optional<Expense> optionaExpense = expenseRepo.findById(id);
		if (optionaExpense.isPresent()) {
			Expense expense = optionaExpense.get();
			expense.setExpenseName(e.getExpenseName());
			expense.setVendor(e.getVendor());
			expense.setAmount(e.getAmount());
			expense.setDescription(e.getDescription());
			return expenseRepo.save(expense);
		} else {
			return null;
		}
	}
	
	public void delete(Long id) {
		expenseRepo.deleteById(id);
	}
}
