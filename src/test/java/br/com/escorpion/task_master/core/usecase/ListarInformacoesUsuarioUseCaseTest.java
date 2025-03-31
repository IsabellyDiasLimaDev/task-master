package br.com.escorpion.task_master.core.usecase;

import br.com.escorpion.task_master.adapters.persistence.entity.UsuarioEntity;
import br.com.escorpion.task_master.adapters.persistence.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarInformacoesUsuarioUseCaseTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private ListarInformacoesUsuarioUseCase listarInformacoesUsuarioUseCase;

    @Test
    void deveListarInformacoesDoUsuario() {

        String nomeUsuario = "John Doe";
        String email = "test@example.com";
        String senha = "password";

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(new UsuarioEntity(1L, nomeUsuario, email, senha)));

        br.com.escorpion.task_master.core.domain.model.Usuario usuario = listarInformacoesUsuarioUseCase.listarInformacoes(email);

        assertEquals(email, usuario.getEmail());
        assertEquals(nomeUsuario, usuario.getNomeUsuario());
        assertEquals(senha, usuario.getSenha());
    }

}