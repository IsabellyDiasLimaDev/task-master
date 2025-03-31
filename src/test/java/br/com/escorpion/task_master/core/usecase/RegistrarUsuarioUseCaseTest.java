package br.com.escorpion.task_master.core.usecase;

import br.com.escorpion.task_master.adapters.persistence.entity.UsuarioEntity;
import br.com.escorpion.task_master.adapters.persistence.repository.UsuarioRepository;
import br.com.escorpion.task_master.core.domain.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegistrarUsuarioUseCaseTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegistrarUsuarioUseCase registrarUsuarioUseCase;


    @Test
    void testExecutar_ComDadosValidos_DeveRegistrarUsuario() {
        // Arrange
        String username = "usuarioTeste";
        String email = "teste@email.com";
        String senha = "senha123";

        UsuarioEntity entity = new UsuarioEntity();
        entity.setNomeUsuario(username);
        entity.setEmail(email);
        entity.setSenha(passwordEncoder.encode(senha));

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(entity);

        // Act
        Usuario usuario = registrarUsuarioUseCase.executar(username, email, senha);

        // Assert
        assertNotNull(usuario);
        assertEquals(username, usuario.getNomeUsuario());
        assertEquals(email, usuario.getEmail());
    }

    @Test
    void testExecutar_ComEmailJaExistente_DeveLancarExcecao() {
        // Arrange
        String username = "usuarioTeste";
        String email = "teste@email.com";
        String senha = "senha123";

        UsuarioEntity entity = new UsuarioEntity();
        entity.setNomeUsuario(username);
        entity.setEmail(email);
        entity.setSenha(passwordEncoder.encode(senha));

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(entity));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> registrarUsuarioUseCase.executar(username, email, senha));
    }
}
