package br.com.escorpion.task_master.adapters.web.dto;

import br.com.escorpion.task_master.core.domain.enums.Prioridade;
import br.com.escorpion.task_master.core.domain.enums.StatusTarefa;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TarefaDTO {
    private String titulo;
    private String descricao;
    private StatusTarefa status;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataConclusao;
    private Prioridade prioridade;
    private Long usuarioId;
}
