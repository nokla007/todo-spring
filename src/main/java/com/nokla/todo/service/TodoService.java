package com.nokla.todo.service;

import com.nokla.todo.model.Todo;
import com.nokla.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Optional<Todo> find(int id){
        return todoRepository.findById(id);
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public List<Todo> findAll(String sortBy) {
        return todoRepository.findAll(Sort.by(Sort.Direction.ASC, sortBy));
    }

    public List<Todo> findAll(String sortBy, Boolean desc) {
        if (desc) {
            return todoRepository.findAll(Sort.by(Sort.Direction.DESC, sortBy));
        }
        return todoRepository.findAll(Sort.by(Sort.Direction.ASC, sortBy));
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public void delete(Todo todo){
        todoRepository.delete(todo);
    }
    public void delete(int id){
        todoRepository.deleteById(id);
    }
}
