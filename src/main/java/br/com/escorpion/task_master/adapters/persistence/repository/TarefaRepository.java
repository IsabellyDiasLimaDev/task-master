package br.com.escorpion.task_master.adapters.persistence.repository;

import br.com.escorpion.task_master.adapters.persistence.entity.TarefaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<TarefaEntity, Long> {
    List<TarefaEntity> findByUsuarioId(Long usuarioId);
}
