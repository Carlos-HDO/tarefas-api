package com.example.tarefas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome da tarefa é obrigatório")
    private String nome;

    @NotNull(message = "Data de entrega é obrigatória")
    private LocalDate dataEntrega;

    @NotBlank(message = "Responsável é obrigatório")
    private String responsavel;

    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataEntrega() { return dataEntrega; }
    public void setDataEntrega(LocalDate dataEntrega) { this.dataEntrega = dataEntrega; }

    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }
}
