package com.br.api_tarefas.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.api_tarefas.models.Tarefas;

public interface TarefasRep extends JpaRepository<Tarefas, Long>{}
