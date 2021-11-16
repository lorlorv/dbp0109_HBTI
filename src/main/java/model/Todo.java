package model;

import java.util.Date;
import java.util.List;

public class Todo {
	private int todo_id;
	private String content;
	private Date todo_date;
	private String user_id;
	private int is_done;
	private List<Todo> todoList;

	public Todo() { }

	public Todo(int todo_id, String content, Date todo_date, String user_id, int is_done) {
		super();
		this.todo_id = todo_id;
		this.content = content;
		this.todo_date = todo_date;
		this.user_id = user_id;
		this.is_done = is_done;
	}
	
	public Todo(String content, String user_id) {
		this.content = content;
		this.user_id = user_id;
	}
	
	public Todo(int todo_id, String content, int is_done) {
		this.todo_id = todo_id;
		this.content = content;
		this.is_done = is_done;
	
	}
	
	public Todo(int todo_id, String content, Date todo_date, int is_done) {
		super();
		this.todo_id = todo_id;
		this.content = content;
		this.todo_date = todo_date;
		this.is_done = is_done;
	}


	public int getTodo_id() {
		return todo_id;
	}

	public void setTodo_id(int todo_id) {
		this.todo_id = todo_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTodo_date() {
		return todo_date;
	}

	public void setTodo_date(Date todo_date) {
		this.todo_date = todo_date;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getIs_done() {
		return is_done;
	}

	public void setIs_done(int is_done) {
		this.is_done = is_done;
	}

	public List<Todo> getTodoList() {
		return todoList;
	}

	public void setTodoList(List<Todo> todoList) {
		this.todoList = todoList;
	}

	@Override
	public String toString() {
		return "Todo [todo_id=" + todo_id + ", content=" + content + ", todo_date=" + todo_date + ", user_id=" + user_id
				+ ", is_done=" + is_done + ", todoList=" + todoList + "]";
	}	

	
	
}
	
	
