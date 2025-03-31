package br.com.escorpion.task_master.adapters.persistence.repository;

import br.com.escorpion.task_master.adapters.persistence.entity.TarefaEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TarefaRepositoryTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @Test
    void testFindByUsuarioId() {
        // Given
        Long usuarioId = 1L;
        TarefaEntity tarefaEntity = new TarefaEntity();
        tarefaEntity.setUsuarioId(usuarioId);

        // When
        when(tarefaRepository.findByUsuarioId(usuarioId)).thenReturn(List.of(tarefaEntity));
        List<TarefaEntity> tarefas = tarefaRepository.findByUsuarioId(usuarioId);

        // Then
        assertNotNull(tarefas);
        assertEquals(1, tarefas.size());
        assertEquals(usuarioId, tarefas.get(0).getUsuarioId());
    }
}