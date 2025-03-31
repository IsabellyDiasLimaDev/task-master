package br.com.escorpion.task_master.core.usecase;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.escorpion.task_master.adapters.persistence.entity.UsuarioEntity;
import br.com.escorpion.task_master.adapters.persistence.repository.UsuarioRepository;
import br.com.escorpion.task_master.config.security.jwt.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

class AutenticarUsuarioUseCaseTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private AutenticarUsuarioUseCase autenticarUsuarioUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAutenticarUsuario_ComCredenciaisValidas_DeveRetornarToken() {
        // Arrange
        String email = "teste@email.com";
        String senha = "senha123";
        String senhaCodificada = "senhaCodificada";
        String tokenEsperado = "tokenJWT";
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setEmail(email);
        usuario.setSenha(senhaCodificada);
        usuario.setNomeUsuario("usuarioTeste");

        UserDetails userDetails = mock(UserDetails.class);

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches(senha, senhaCodificada)).thenReturn(true);
        when(userDetailsService.loadUserByUsername("usuarioTeste")).thenReturn(userDetails);
        when(jwtService.generateToken(userDetails)).thenReturn(tokenEsperado);

        // Act
        String token = autenticarUsuarioUseCase.autenticarUsuario(email, senha);

        // Assert
        assertEquals(tokenEsperado, token);
    }

    @Test
    void testAutenticarUsuario_ComEmailInvalido_DeveLancarExcecao() {
        // Arrange
        String email = "email_invalido@email.com";
        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> autenticarUsuarioUseCase.autenticarUsuario(email, "senha"));
    }

    @Test
    void testAutenticarUsuario_ComSenhaInvalida_DeveLancarExcecao() {
        // Arrange
        String email = "teste@email.com";
        String senha = "senha_errada";
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setEmail(email);
        usuario.setSenha("senhaCorreta");

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches(senha, usuario.getSenha())).thenReturn(false);

        // Act & Assert
        assertThrows(BadCredentialsException.class, () -> autenticarUsuarioUseCase.autenticarUsuario(email, senha));
    }
}
