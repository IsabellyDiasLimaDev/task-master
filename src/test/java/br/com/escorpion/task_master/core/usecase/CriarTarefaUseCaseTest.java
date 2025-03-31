package br.com.escorpion.task_master.core.usecase;

import br.com.escorpion.task_master.adapters.persistence.entity.TarefaEntity;
import br.com.escorpion.task_master.adapters.persistence.repository.TarefaRepository;
import br.com.escorpion.task_master.core.domain.enums.StatusTarefa;
import br.com.escorpion.task_master.core.domain.model.Tarefa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarTarefaUseCaseTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @InjectMocks
    private CriarTarefaUseCase criarTarefaUseCase;

    @Test
    void testCriarTarefa() {
        // Arrange
        Tarefa tarefaASerCriada = new Tarefa();
        tarefaASerCriada.setTitulo("Tarefa 1");
        tarefaASerCriada.setDescricao("Descrição da tarefa 1");

        TarefaEntity tarefaSalva = new TarefaEntity();
        tarefaSalva.setTitulo("Tarefa 1");
        tarefaSalva.setDescricao("Descrição da tarefa 1");
        tarefaSalva.setStatus(StatusTarefa.PENDENTE);
        tarefaSalva.setDataCriacao(LocalDateTime.now());

        when(tarefaRepository.save(any(TarefaEntity.class))).thenReturn(tarefaSalva);

        // Act
        Tarefa tarefaCriada = criarTarefaUseCase.criarTarefa(tarefaASerCriada);

        // Assert
        assertNotNull(tarefaCriada);
        assertEquals("Tarefa 1", tarefaCriada.getTitulo());
        assertEquals("Descrição da tarefa 1", tarefaCriada.getDescricao());
        assertEquals(StatusTarefa.PENDENTE, tarefaCriada.getStatus());
        assertNotNull(tarefaCriada.getDataCriacao());
        assertNull(tarefaCriada.getDataConclusao());
        assertNull(tarefaCriada.getPrioridade());
        assertNull(tarefaCriada.getUsuarioId());

        verify(tarefaRepository, times(1)).save(any(TarefaEntity.class));
    }

}