package br.com.escorpion.task_master.adapters.persistence.repository;

import br.com.escorpion.task_master.adapters.persistence.entity.UsuarioEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioRepositoryTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void testFindByEmail() {
        // Given
        String email = "test@example.com";
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setEmail(email);

        // When
        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuarioEntity));
        Optional<UsuarioEntity> usuario = usuarioRepository.findByEmail(email);

        // Then
        assertNotNull(usuario);
        assertTrue(usuario.isPresent());
        assertEquals(email, usuario.get().getEmail());
    }

    @Test
    void testFindByNomeUsuario() {
        // Given
        String nomeUsuario = "John Doe";
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNomeUsuario(nomeUsuario);

        // When
        when(usuarioRepository.findByNomeUsuario(nomeUsuario)).thenReturn(Optional.of(usuarioEntity));
        Optional<UsuarioEntity> usuario = usuarioRepository.findByNomeUsuario(nomeUsuario);

        // Then
        assertNotNull(usuario);
        assertTrue(usuario.isPresent());
        assertEquals(nomeUsuario, usuario.get().getNomeUsuario());
    }
}