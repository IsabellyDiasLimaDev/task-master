package br.com.escorpion.task_master.adapters.web.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.escorpion.task_master.adapters.web.dto.UsuarioDTO;
import br.com.escorpion.task_master.adapters.web.mapper.UsuarioDTOMapper;
import br.com.escorpion.task_master.core.domain.model.Usuario;
import br.com.escorpion.task_master.core.usecase.AutenticarUsuarioUseCase;
import br.com.escorpion.task_master.core.usecase.ListarInformacoesUsuarioUseCase;
import br.com.escorpion.task_master.core.usecase.RegistrarUsuarioUseCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

class UsuarioControllerTest {

    @Mock
    private RegistrarUsuarioUseCase registrarUsuarioUseCase;

    @Mock
    private AutenticarUsuarioUseCase autenticarUsuarioUseCase;

    @Mock
    private ListarInformacoesUsuarioUseCase listarInformacoesUsuarioUseCase;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarUsuario() {
        // Arrange
        UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioTeste", "teste@email.com", "senha123");
        Usuario usuario = new Usuario("usuarioTeste", "teste@email.com", "senha123");

        try (var mockedMapper = mockStatic(UsuarioDTOMapper.class)) {
            mockedMapper.when(() -> UsuarioDTOMapper.toDTO(usuario)).thenReturn(usuarioDTO);
            when(registrarUsuarioUseCase.executar("usuarioTeste", "teste@email.com", "senha123")).thenReturn(usuario);

            // Act
            ResponseEntity<UsuarioDTO> response = usuarioController.criarUsuario(usuarioDTO);

            // Assert
            assertNotNull(response);
            assertEquals(200, response.getStatusCode().value());
            assertEquals(usuarioDTO, response.getBody());
        }
    }

    @Test
    void testLogin() {
        // Arrange
        UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioTeste", "teste@email.com", "senha123");
        String token = "token123";

        when(autenticarUsuarioUseCase.autenticarUsuario("usuarioTeste", "senha123")).thenReturn(token);

        // Act
        ResponseEntity<String> response = usuarioController.login(usuarioDTO);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(token, response.getBody());
    }

    @Test
    void testBuscarUsuario() {
        // Arrange
        UsuarioDTO usuarioDTO = new UsuarioDTO("usuarioTeste", "teste@email.com", "senha123");
        Usuario usuario = new Usuario("usuarioTeste", "teste@email.com", "senha123");

        try (var mockedMapper = mockStatic(UsuarioDTOMapper.class)) {
            mockedMapper.when(() -> UsuarioDTOMapper.toDTO(usuario)).thenReturn(usuarioDTO);
            when(listarInformacoesUsuarioUseCase.listarInformacoes("teste@email.com")).thenReturn(usuario);

            // Act
            ResponseEntity<UsuarioDTO> response = usuarioController.buscarUsuario("teste@email.com");

            // Assert
            assertNotNull(response);
            assertEquals(200, response.getStatusCode().value());
            assertEquals(usuarioDTO, response.getBody());
        }
    }
}
