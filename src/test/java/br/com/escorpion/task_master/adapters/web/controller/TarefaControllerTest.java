package br.com.escorpion.task_master.adapters.web.controller;

import br.com.escorpion.task_master.adapters.web.dto.TarefaDTO;
import br.com.escorpion.task_master.adapters.web.mapper.TarefaDTOMapper;
import br.com.escorpion.task_master.core.domain.model.Tarefa;
import br.com.escorpion.task_master.core.usecase.CriarTarefaUseCase;
import br.com.escorpion.task_master.core.usecase.ListarTarefasUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TarefaControllerTest {

    @Mock
    private CriarTarefaUseCase criarTarefaUseCase;

    @Mock
    private ListarTarefasUseCase listarTarefasUseCase;

    @InjectMocks
    private TarefaController tarefaController;

    @Test
    void testCriarTarefa() {
        // Arrange
        TarefaDTO tarefaDTO = new TarefaDTO();
        tarefaDTO.setTitulo("Teste");
        tarefaDTO.setDescricao("Descrição do teste");

        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Teste");
        tarefa.setDescricao("Descrição do teste");

        try (var mockedMapper = mockStatic(TarefaDTOMapper.class)) {
            mockedMapper.when(() -> TarefaDTOMapper.toDomain(tarefaDTO)).thenReturn(tarefa);
            mockedMapper.when(() -> TarefaDTOMapper.toDTO(tarefa)).thenReturn(tarefaDTO);

            when(criarTarefaUseCase.criarTarefa(tarefa)).thenReturn(tarefa);

            // Act
            ResponseEntity<TarefaDTO> response = tarefaController.criarTarefa(tarefaDTO);

            // Assert
            assertNotNull(response);
            assertEquals(200, response.getStatusCode().value());
            assertEquals(tarefaDTO, response.getBody());
        }
    }

    @Test
    void listarTarefas_DeveRetornarListaDeTarefasDTO() {
        // Arrange
        Long usuarioId = 1L;
        var tarefa = new Tarefa(); // Supondo que Tarefa seja sua entidade
        var tarefaDTO = new TarefaDTO();
        List<Tarefa> tarefas = Arrays.asList(tarefa);
        List<TarefaDTO> tarefasDTO = Arrays.asList(tarefaDTO);

        when(listarTarefasUseCase.listarTarefas(usuarioId)).thenReturn(tarefas);
        mockStatic(TarefaDTOMapper.class).when(() -> TarefaDTOMapper.toDTO(tarefa)).thenReturn(tarefaDTO);

        // Act
        ResponseEntity<List<TarefaDTO>> response = tarefaController.listarTarefas(usuarioId);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(tarefasDTO, response.getBody());
    }
}