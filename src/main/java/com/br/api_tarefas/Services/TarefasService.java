package com.br.api_tarefas.Services;

import com.br.api_tarefas.Repositories.TarefasRep;
import com.br.api_tarefas.models.Tarefas;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefasService {

    private TarefasRep tr;

    public TarefasService(TarefasRep tr) {
        this.tr = tr;
    }

    public void criarTarefa(Tarefas tc) {
        tr.save(tc);
    }

    public void deletarTarefa(Long id) {
        tr.deleteById(id);
    }

    public Tarefas buscarTarefaPorId(Long id) {
        return tr.findById(id).orElse(null);
    }

    public List<Tarefas> getTarefas() {
        return tr.findAll();
    }

    public Tarefas atualizarTarefa(Long id, Tarefas tarefaAtualizada) {
        // Verifica se a tarefa com o ID fornecido existe
        Boolean tarefasAtual = tr.existsById(id);

        // Se a tarefa existe, atualiza os campos desejados
        if (tarefasAtual) {
            Tarefas tarefa = tr.findById(id).orElse(null);
            if (tarefa != null) {
                tarefa.setTitulo(tarefaAtualizada.getTitulo());
                tarefa.setDescricao(tarefaAtualizada.getDescricao());
                tarefa.setConcluido(tarefaAtualizada.getConcluido());
                return tr.save(tarefa);
            }
        }
        return null;
    }

}
