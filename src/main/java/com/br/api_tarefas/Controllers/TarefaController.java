package com.br.api_tarefas.Controllers;

import java.util.List;

import com.br.api_tarefas.Services.TarefasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.br.api_tarefas.models.Tarefas;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

	@Autowired
	private TarefasService ts;

	public TarefaController(TarefasService ts) {
		this.ts = ts;
	}
	
	@PostMapping(value = "/criar")
	public ResponseEntity<Tarefas> createTarefas(@RequestBody Tarefas t) {
		ts.criarTarefa(t);

		return ResponseEntity.ok().body(t);
	}

	@GetMapping
	public ResponseEntity<List<Tarefas>> getTarefas() {
		List<Tarefas> tarefas = ts.getTarefas();
		return ResponseEntity.ok().body(tarefas);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Tarefas> getTarefaPorId(@PathVariable Long id) {
		Tarefas tarefa = ts.buscarTarefaPorId(id);
		if (tarefa != null) {
			return ResponseEntity.ok().body(tarefa);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deleteTarefa(@PathVariable Long id) {
		Tarefas tarefa = ts.buscarTarefaPorId(id);
		if (tarefa != null) {
			ts.deletarTarefa(id);
			return ResponseEntity.ok("Tarefa deletada com sucesso");
		} else {
			return ResponseEntity.ok("Tarefa não foi encontrada");
		}
	}

	@PutMapping(value = "/atualizar/{id}")
	public ResponseEntity<Tarefas> updateTarefa(@PathVariable Long id, @RequestBody Tarefas tarefaAtualizada) {
		Tarefas tarefa = ts.atualizarTarefa(id, tarefaAtualizada);
		if (tarefa != null) {
			return ResponseEntity.ok().body(tarefa);
		} else {
			return ResponseEntity.notFound().build();
		}
	}


}
