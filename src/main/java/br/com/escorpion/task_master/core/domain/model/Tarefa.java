package br.com.escorpion.task_master.core.domain.model;

import br.com.escorpion.task_master.core.domain.enums.Prioridade;
import br.com.escorpion.task_master.core.domain.enums.StatusTarefa;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Tarefa {
    private Long id;
    private String titulo;
    private String descricao;
    private StatusTarefa status = StatusTarefa.PENDENTE;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    private LocalDateTime dataConclusao;
    private Prioridade prioridade = Prioridade.MEDIA;
    private Long usuarioId;
}