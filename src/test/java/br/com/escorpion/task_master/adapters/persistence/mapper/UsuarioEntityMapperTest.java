package br.com.escorpion.task_master.adapters.persistence.mapper;

import br.com.escorpion.task_master.adapters.persistence.entity.UsuarioEntity;
import br.com.escorpion.task_master.core.domain.model.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioEntityMapperTest {

    @Test
    void testToDomain() {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(1L);
        entity.setNomeUsuario("John Doe");
        entity.setSenha("password");
        entity.setEmail("QWdPn@example.com");

        Usuario usuario = UsuarioEntityMapper.toDomain(entity);

        assertEquals(entity.getId(), usuario.getId());
        assertEquals(entity.getNomeUsuario(), usuario.getNomeUsuario());
        assertEquals(entity.getSenha(), usuario.getSenha());
        assertEquals(entity.getEmail(), usuario.getEmail());
    }

    @Test
    void testToEntity() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNomeUsuario("John Doe");
        usuario.setSenha("password");
        usuario.setEmail("QWdPn@example.com");

        UsuarioEntity entity = UsuarioEntityMapper.toEntity(usuario);

        assertEquals(usuario.getId(), entity.getId());
        assertEquals(usuario.getNomeUsuario(), entity.getNomeUsuario());
        assertEquals(usuario.getSenha(), entity.getSenha());
        assertEquals(usuario.getEmail(), entity.getEmail());
    }
}