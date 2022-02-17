package com.jeremy.saveTravels.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;

import com.jeremy.saveTravels.models.Expense;

public interface ExpenseRepo extends CrudRepository<Expense, Long> {
	List<Expense> findAll();
	
}
