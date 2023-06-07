package com.ricardo.sistema.sistemaapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ricardo.sistema.sistemaapi.model.Task;
import com.ricardo.sistema.sistemaapi.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController implements IController<Task>{

    private TaskService service;

    public TaskController (TaskService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<Task>> get() {
        List<Task> registros = service.get();
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Task> get(@PathVariable("id") Long id) {
        Task registro = service.get(id);
        return new ResponseEntity<>(registro, HttpStatus.OK);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Task> insert(@RequestBody Task objeto) {
        Task registro = service.save(objeto);
        return new ResponseEntity<>(registro, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<Task> update(@RequestBody Task objeto) {
        Task registro = service.save(objeto);
        return new ResponseEntity<>(registro, HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}