package br.com.escorpion.task_master.adapters.web.mapper;

import br.com.escorpion.task_master.adapters.web.dto.TarefaDTO;
import br.com.escorpion.task_master.core.domain.model.Tarefa;

public class TarefaDTOMapper {

    public static Tarefa toDomain(TarefaDTO dto) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setStatus(dto.getStatus());
        tarefa.setDataCriacao(dto.getDataCriacao());
        tarefa.setDataConclusao(dto.getDataConclusao());
        tarefa.setPrioridade(dto.getPrioridade());
        tarefa.setUsuarioId(dto.getUsuarioId());
        return tarefa;
    }

    public static TarefaDTO toDTO(Tarefa tarefa) {
        TarefaDTO dto = new TarefaDTO();
        dto.setTitulo(tarefa.getTitulo());
        dto.setDescricao(tarefa.getDescricao());
        dto.setStatus(tarefa.getStatus());
        dto.setDataCriacao(tarefa.getDataCriacao());
        dto.setDataConclusao(tarefa.getDataConclusao());
        dto.setPrioridade(tarefa.getPrioridade());
        dto.setUsuarioId(tarefa.getUsuarioId());
        return dto;
    }
}
