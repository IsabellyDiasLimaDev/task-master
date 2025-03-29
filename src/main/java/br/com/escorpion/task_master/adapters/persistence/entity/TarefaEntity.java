package br.com.escorpion.task_master.adapters.persistence.entity;

import br.com.escorpion.task_master.core.domain.enums.Prioridade;
import br.com.escorpion.task_master.core.domain.enums.StatusTarefa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tarefas")
@Getter
@Setter
public class TarefaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private StatusTarefa status;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataConclusao;
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;
}
