package com.in28.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerjpa {

	private TodoService todoservice;
	
	private TodoRepository todoRepository;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public TodoControllerjpa(TodoRepository todoRepository) {
		super();
	    this.todoRepository=todoRepository;
	}

	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap modelmap) {

		
//		List<Todo> todos = todoservice.findbyUsername(usernamefromspring());
		List<Todo> todos = todoRepository.findByUsername(usernamefromspring());
	
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
		todo.setUsername(username);
		logger.info("adding todo with details : "+todo);
		todoRepository.save(todo);
		return "redirect:list-todos";
	}

	@RequestMapping(value = "delete-todo", method = RequestMethod.GET)
	public String deleteTodoPage(@RequestParam int id) {
		todoRepository.deleteById(id);
		return "redirect:list-todos";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String updateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = todoRepository.findById(id).get();
		model.addAttribute("todo", todo);
		logger.info("todo before" + todo);
		return "todo";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String updateTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}

		logger.info(" todo is " + todo);
		String username =usernamefromspring();
		todo.setUsername(username);
		logger.info("adding todo with details : "+todo);
		todoRepository.save(todo);
		return "redirect:	list-todos";	
	}

	private String usernamefromspring()
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
	
	
}
