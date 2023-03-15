package com.in28.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private static List<Todo> todos = new ArrayList<>();

	private static int todocount = 0;

	static {

		todos.add(new Todo(++todocount, "krishna", "AWS", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todocount, "krishna", "DEVOPS", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todocount, "krishna", "CLOUD", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todocount, "krishna", "JAVA", LocalDate.now().plusYears(1), false));

	}

	public List<Todo> findbyUsername(String username) {

		
		logger.info(" username " + username);
		Predicate<? super Todo> predicate= todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();	
	}

	public void addToDo(String username, String Description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(++todocount, username, Description, targetDate, done);
		todos.add(todo);
	}
	
	public void deleteToDo(int id)
	{
		Predicate<? super Todo> predicate=todo->todo.getId()==id ;
		todos.removeIf(predicate);
	}
	public void updateToDo(int id)
	{
		Predicate<? super Todo> predicate=todo->todo.getId()==id ;
		todos.removeIf(predicate);
//		todos.iterator()
	}

	public Todo getToDo(int id) {
		// TODO Auto-generated method stub
		Predicate<? super Todo> predicate=todo->todo.getId()==id;		
		Todo todo=todos.stream().filter(predicate).findFirst().get();
	return todo;
	}

}
