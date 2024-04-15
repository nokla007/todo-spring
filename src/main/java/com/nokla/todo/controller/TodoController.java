package com.nokla.todo.controller;


import com.nokla.todo.model.Todo;
import com.nokla.todo.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller()
@RequestMapping("/todo")
public class TodoController {
    Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoService todoService;
//    @GetMapping("/{id}")
//    public String getTodo(@PathVariable("id") int id, Model model){
//
//        return "add";
//    }



    @GetMapping
    public String findAll(Model model) {
        List<Todo> todos = todoService.findAll("id");
        model.addAttribute("todos", todos);
        return "todo/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable("id") int id) {
        logger.info("deleting todo: " + id);
        todoService.delete(id);
        return "redirect:/todo";
    }

//    @PutMapping("/update/complete/{id}")
    @PostMapping("/update/complete/{id}")
    public String updateComplete(@PathVariable("id") int id) {
        logger.info("updating todo complete: " + id);
        Todo todo = todoService.find(id).orElse(null);
        if (todo != null) {
            todo.setCompleted(!todo.getCompleted()); // Toggle the completion status
            todoService.save(todo);
        }
        return "redirect:/todo"; // Redirect to the todos page after updating
    }
//    @PutMapping("/update/starred/{id}")
    @PostMapping("/update/starred/{id}")
    public String updateStarred(@PathVariable("id") int id) {
        logger.info("updating todo starred: " + id);
        Todo todo = todoService.find(id).orElse(null);
        if (todo != null) {
            todo.setStarred(!todo.getStarred()); // Toggle the completion status
            todoService.save(todo);
        }
        return "redirect:/todo"; // Redirect to the todos page after updating
    }

    @GetMapping("/add")
    public String addTodo(){
        return "/todo/add";
    }

    @PostMapping("/add")
    public String addTodo(@RequestParam("description") String description,
                          @RequestParam(value = "starred", required = false) boolean starred) {
        Todo todo = new Todo(description, starred);
        todoService.save(todo);
        logger.info("added new todo: "+todo.getId());
        return "redirect:/todo"; // Redirect to the todos page after adding the todo
    }

}
