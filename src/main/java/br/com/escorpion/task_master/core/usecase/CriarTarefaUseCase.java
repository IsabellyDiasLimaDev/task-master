package br.com.escorpion.task_master.core.usecase;

import br.com.escorpion.task_master.adapters.persistence.entity.TarefaEntity;
import br.com.escorpion.task_master.adapters.persistence.mapper.TarefaEntityMapper;
import br.com.escorpion.task_master.adapters.persistence.repository.TarefaRepository;
import br.com.escorpion.task_master.core.domain.enums.StatusTarefa;
import br.com.escorpion.task_master.core.domain.model.Tarefa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CriarTarefaUseCase {
    private final TarefaRepository tarefaRepository;

    public Tarefa criarTarefa(Tarefa tarefaASerCriada) {
        tarefaASerCriada.setStatus(StatusTarefa.PENDENTE);
        tarefaASerCriada.setDataCriacao(LocalDateTime.now());
        TarefaEntity entity = tarefaRepository.save(TarefaEntityMapper.toEntity(tarefaASerCriada));
        return TarefaEntityMapper.toDomain(entity);
    }
}
