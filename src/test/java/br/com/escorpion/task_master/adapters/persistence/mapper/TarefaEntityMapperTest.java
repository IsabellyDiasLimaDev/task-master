package br.com.escorpion.task_master.adapters.persistence.mapper;

import br.com.escorpion.task_master.adapters.persistence.entity.TarefaEntity;
import br.com.escorpion.task_master.core.domain.enums.Prioridade;
import br.com.escorpion.task_master.core.domain.enums.StatusTarefa;
import br.com.escorpion.task_master.core.domain.model.Tarefa;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TarefaEntityMapperTest {
    @Test
    public void testToDomain() {
        // Arrange
        TarefaEntity entity = new TarefaEntity();
        entity.setId(1L);
        entity.setTitulo("Tarefa 1");
        entity.setDescricao("Descrição da tarefa 1");
        entity.setStatus(StatusTarefa.PENDENTE);
        entity.setDataCriacao(LocalDateTime.now());
        entity.setDataConclusao(LocalDateTime.now());
        entity.setPrioridade(Prioridade.ALTA);
        entity.setUsuarioId(1L);

        // Act
        Tarefa tarefa = TarefaEntityMapper.toDomain(entity);

        // Assert
        assertEquals(entity.getId(), tarefa.getId());
        assertEquals(entity.getTitulo(), tarefa.getTitulo());
        assertEquals(entity.getDescricao(), tarefa.getDescricao());
        assertEquals(entity.getStatus(), tarefa.getStatus());
        assertEquals(entity.getDataCriacao(), tarefa.getDataCriacao());
        assertEquals(entity.getDataConclusao(), tarefa.getDataConclusao());
        assertEquals(entity.getPrioridade(), tarefa.getPrioridade());
        assertEquals(entity.getUsuarioId(), tarefa.getUsuarioId());
    }

    @Test
    public void testToEntity() {
        // Arrange
        Tarefa tarefa = new Tarefa();
        tarefa.setId(1L);
        tarefa.setTitulo("Tarefa 1");
        tarefa.setDescricao("Descrição da tarefa 1");
        tarefa.setStatus(StatusTarefa.PENDENTE);
        tarefa.setDataCriacao(LocalDateTime.now());
        tarefa.setDataConclusao(LocalDateTime.now());
        tarefa.setPrioridade(Prioridade.ALTA);
        tarefa.setUsuarioId(1L);

        // Act
        TarefaEntity entity = TarefaEntityMapper.toEntity(tarefa);

        // Assert
        assertEquals(tarefa.getId(), entity.getId());
        assertEquals(tarefa.getTitulo(), entity.getTitulo());
        assertEquals(tarefa.getDescricao(), entity.getDescricao());
        assertEquals(tarefa.getStatus(), entity.getStatus());
        assertEquals(tarefa.getDataCriacao(), entity.getDataCriacao());
        assertEquals(tarefa.getDataConclusao(), entity.getDataConclusao());
        assertEquals(tarefa.getPrioridade(), entity.getPrioridade());
        assertEquals(tarefa.getUsuarioId(), entity.getUsuarioId());
    }
}
