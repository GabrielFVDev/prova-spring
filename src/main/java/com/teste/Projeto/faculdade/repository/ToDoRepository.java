package com.teste.Projeto.faculdade.repository;

import com.teste.Projeto.faculdade.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    List<ToDo> findByTitleContainingIgnoreCase(String title);
}
