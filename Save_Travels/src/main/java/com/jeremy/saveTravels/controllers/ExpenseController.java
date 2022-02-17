package com.jeremy.saveTravels.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.jeremy.saveTravels.models.Expense;
import com.jeremy.saveTravels.services.ExpenseService;

@Controller
public class ExpenseController {
	
	@Autowired
	ExpenseService expenseService;

//	GET SHOWALL
	@GetMapping("/")
	public String index(@ModelAttribute("expense") Expense expense, Model model) {
		List<Expense> expenses = expenseService.allExpenses();
		model.addAttribute("expenses", expenses);
		return "index.jsp";
	}

// GET SHOW ONE
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		Expense expense = expenseService.showOne(id);
		model.addAttribute("expense", expense);
		return "edit.jsp";
	}
	
	@GetMapping("/expenses/{id}")
	public String details(@PathVariable("id") Long id, Model model) {
		Expense expense = expenseService.showOne(id);
		model.addAttribute("expense", expense);
		return "showone.jsp";
	}
	
	
//	POST CREATE
	@PostMapping("/")
	public String create (@Valid @ModelAttribute("expense") Expense expense, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Expense> expenses = expenseService.allExpenses();
			model.addAttribute("expenses", expenses);
			return "index.jsp";
		} else {
			expenseService.createExpense(expense);
			return "redirect:/";
		}
	}
// PUT EDIT
	@PutMapping("/{id}/edit")
	public String update(@Valid @ModelAttribute("expense") Expense expense, BindingResult result, @PathVariable("id")Long id) {
		if (result.hasErrors()) {		
			System.out.println("error");
			return "edit.jsp";
		} else {
			expenseService.updateExpense(expense, id);
			return "redirect:/";
		}
	}
	
	@DeleteMapping("/{id}/delete")
	public String delete(@PathVariable("id")Long id) {
		expenseService.delete(id);
		return "redirect:/";
	}
}
