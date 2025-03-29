package br.com.escorpion.task_master.adapters.web.controller;

import br.com.escorpion.task_master.adapters.web.dto.TarefaDTO;
import br.com.escorpion.task_master.adapters.web.mapper.TarefaDTOMapper;
import br.com.escorpion.task_master.core.domain.model.Tarefa;
import br.com.escorpion.task_master.core.usecase.CriarTarefaUseCase;
import br.com.escorpion.task_master.core.usecase.ListarTarefasUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final CriarTarefaUseCase criarTarefaUseCase;
    private final ListarTarefasUseCase listarTarefasUseCase;

    @PostMapping
    public ResponseEntity<TarefaDTO> criarTarefa(@RequestBody TarefaDTO tarefaDTO) {
        Tarefa novaTarefa = criarTarefaUseCase.criarTarefa(TarefaDTOMapper.toDomain(tarefaDTO));
        return ResponseEntity.ok(TarefaDTOMapper.toDTO(novaTarefa));
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> listarTarefas(@RequestParam Long usuarioId) {
        List<TarefaDTO> tarefas = listarTarefasUseCase.listarTarefas(usuarioId)
                .stream()
                .map(TarefaDTOMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tarefas);
    }
}
