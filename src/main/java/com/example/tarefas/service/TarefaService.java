package com.example.tarefas.service;

import com.example.tarefas.model.Tarefa;
import com.example.tarefas.repository.TarefaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Transactional
public class TarefaService {

    private final TarefaRepository repo;

    public TarefaService(TarefaRepository repo) {
        this.repo = repo;
    }

    public Tarefa criar(Tarefa t) {
        t.setId(null);
        return repo.save(t);
    }

    @Transactional(readOnly = true)
    public List<Tarefa> listar() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Tarefa obter(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Tarefa não encontrada"));
    }

    public Tarefa atualizar(Long id, Tarefa dados) {
        Tarefa existente = obter(id);
        existente.setNome(dados.getNome());
        existente.setDataEntrega(dados.getDataEntrega());
        existente.setResponsavel(dados.getResponsavel());
        return repo.save(existente);
    }

    public void remover(Long id) {
        Tarefa existente = obter(id);
        repo.delete(existente);
    }
}
