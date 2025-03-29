package br.com.escorpion.task_master.adapters.persistence.repository;

import br.com.escorpion.task_master.adapters.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByEmail(String email);
    Optional<UsuarioEntity> findByNomeUsuario(String nomeUsuario);
}
