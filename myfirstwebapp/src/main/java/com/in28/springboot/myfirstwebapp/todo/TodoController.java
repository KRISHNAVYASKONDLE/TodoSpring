package com.in28.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class TodoController {

	private TodoService todoservice;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public TodoController(TodoService todoservice) {
		super();
		this.todoservice = todoservice;
	}

	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap modelmap) {

		
		List<Todo> todos = todoservice.findbyUsername(usernamefromspring());
		modelmap.addAttribute("todos", todos);
		return "listtodos";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String shownewTodoPage(ModelMap model) {
		
		Todo todo = new Todo(0, usernamefromspring(), "", LocalDate.now(), false);
		model.put("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		String username =usernamefromspring();
		logger.info("adding todo with details : "+todo);
		todoservice.addToDo(username, todo.getDescription(), todo.getTargetDate(), false);
		return "redirect:list-todos";
	}

	@RequestMapping(value = "delete-todo", method = RequestMethod.GET)
	public String deleteTodoPage(@RequestParam int id) {
		todoservice.deleteToDo(id);
		return "redirect:list-todos";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String updateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = todoservice.getToDo(id);
		model.put("todo", todo);
		logger.info("todo before" + todo);
		return "todo";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String updateTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}

		logger.info(" todo is " + todo);

		Todo td = todoservice.getToDo(todo.getId());
		
		td.setDescription(todo.getDescription());
		td.setTargetDate(todo.getTargetDate());
		List<Todo> todos = todoservice.findbyUsername(usernamefromspring());
		model.addAttribute("todos", todos);
		return "listtodos";	
	}

	private String usernamefromspring()
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
	
	
}
