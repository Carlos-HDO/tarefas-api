package com.example.tarefas.controller;

import com.example.tarefas.model.Tarefa;
import com.example.tarefas.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    // Criar
    @PostMapping
    public ResponseEntity<Tarefa> criar(@Valid @RequestBody Tarefa tarefa,
                                        UriComponentsBuilder uriBuilder) {
        Tarefa criada = service.criar(tarefa);
        URI location = uriBuilder.path("/api/tarefas/{id}").buildAndExpand(criada.getId()).toUri();
        return ResponseEntity.created(location).body(criada); // 201 + Location
    }

    // Listar todas
    @GetMapping
    public List<Tarefa> listar() {
        return service.listar(); // 200
    }

    // Obter por ID
    @GetMapping("/{id}")
    public Tarefa obter(@PathVariable Long id) {
        return service.obter(id); // 200 / 404 no service
    }

    // Atualizar (PUT = substituição total)
    @PutMapping("/{id}")
    public Tarefa atualizar(@PathVariable Long id, @Valid @RequestBody Tarefa tarefa) {
        return service.atualizar(id, tarefa); // 200 / 404
    }

    // Remover
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build(); // 204
    }
}
