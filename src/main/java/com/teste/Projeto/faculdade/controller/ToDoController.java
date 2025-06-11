package com.teste.Projeto.faculdade.controller;

import com.teste.Projeto.faculdade.model.ToDo;
import com.teste.Projeto.faculdade.service.ToDoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/todos")
public class ToDoController {
    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping
    public List<ToDo> getAll() {
        return toDoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getById(@PathVariable Long id) {
        return toDoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<ToDo> searchByTitle(@RequestParam String title) {
        return toDoService.findByTitle(title);
    }

    @PostMapping
    public ToDo create(@RequestBody ToDo toDo) {
        return toDoService.save(toDo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDo> update(@PathVariable Long id, @RequestBody ToDo toDo) {
        return toDoService.findById(id)
                .map(existing -> {
                    toDo.setId(id);
                    return ResponseEntity.ok(toDoService.save(toDo));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (toDoService.findById(id).isPresent()) {
            toDoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}