package br.com.escorpion.task_master.core.usecase;

import br.com.escorpion.task_master.adapters.persistence.entity.TarefaEntity;
import br.com.escorpion.task_master.adapters.persistence.mapper.TarefaEntityMapper;
import br.com.escorpion.task_master.adapters.persistence.repository.TarefaRepository;
import br.com.escorpion.task_master.core.domain.model.Tarefa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarTarefasUseCase {
    private final TarefaRepository tarefaRepository;

    public List<Tarefa> listarTarefas(Long usuarioId) {
        // Recupera a lista de tarefas do usuário
        List<TarefaEntity> tarefasEntityList = tarefaRepository.findByUsuarioId(usuarioId);

        // Converte a lista de TarefaEntity para Tarefa (domínio)
        List<Tarefa> tarefas = new ArrayList<>();
        for (TarefaEntity tarefaEntity : tarefasEntityList) {
            tarefas.add(TarefaEntityMapper.toDomain(tarefaEntity));
        }

        // Retorna a lista de tarefas no formato de domínio
        return tarefas;
    }
}
