package br.com.escorpion.task_master.adapters.persistence.mapper;

import br.com.escorpion.task_master.adapters.persistence.entity.TarefaEntity;
import br.com.escorpion.task_master.core.domain.model.Tarefa;

public class TarefaEntityMapper {

    public static Tarefa toDomain(TarefaEntity entity) {
        Tarefa tarefa = new Tarefa();
        tarefa.setId(entity.getId());
        tarefa.setTitulo(entity.getTitulo());
        tarefa.setDescricao(entity.getDescricao());
        tarefa.setStatus(entity.getStatus());
        tarefa.setDataCriacao(entity.getDataCriacao());
        tarefa.setDataConclusao(entity.getDataConclusao());
        tarefa.setPrioridade(entity.getPrioridade());
        tarefa.setUsuarioId(entity.getUsuarioId());
        return tarefa;
    }

    public static TarefaEntity toEntity(Tarefa tarefa) {
        TarefaEntity entity = new TarefaEntity();
        entity.setId(tarefa.getId());
        entity.setTitulo(tarefa.getTitulo());
        entity.setDescricao(tarefa.getDescricao());
        entity.setStatus(tarefa.getStatus());
        entity.setDataCriacao(tarefa.getDataCriacao());
        entity.setDataConclusao(tarefa.getDataConclusao());
        entity.setPrioridade(tarefa.getPrioridade());
        entity.setUsuarioId(tarefa.getUsuarioId());
        return entity;
    }
}
