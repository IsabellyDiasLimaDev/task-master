package br.com.escorpion.task_master.core.usecase;

import br.com.escorpion.task_master.adapters.persistence.entity.TarefaEntity;
import br.com.escorpion.task_master.adapters.persistence.repository.TarefaRepository;
import br.com.escorpion.task_master.core.domain.model.Tarefa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListarTarefasUseCaseTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @InjectMocks
    private ListarTarefasUseCase listarTarefasUseCase;

    @Test
    public void testListarTarefas_DeveRetornarListaDeTarefas() {
        // Arrange
        Long usuarioId = 1L;
        TarefaEntity tarefaEntity = new TarefaEntity();
        tarefaEntity.setId(1L);
        tarefaEntity.setDescricao("Tarefa 1");
        tarefaEntity.setUsuarioId(usuarioId);

        List<TarefaEntity> tarefasEntityList = new ArrayList<>();
        tarefasEntityList.add(tarefaEntity);

        when(tarefaRepository.findByUsuarioId(usuarioId)).thenReturn(tarefasEntityList);

        // Act
        List<Tarefa> tarefas = listarTarefasUseCase.listarTarefas(usuarioId);

        // Assert
        assertEquals(1, tarefas.size());
        assertEquals("Tarefa 1", tarefas.get(0).getDescricao());
    }

    @Test
    public void testListarTarefas_DeveRetornarListaVazia() {
        // Arrange
        Long usuarioId = 1L;

        when(tarefaRepository.findByUsuarioId(usuarioId)).thenReturn(new ArrayList<>());

        // Act
        List<Tarefa> tarefas = listarTarefasUseCase.listarTarefas(usuarioId);

        // Assert
        assertEquals(0, tarefas.size());
    }
}