package br.com.escorpion.task_master.core.usecase;

import br.com.escorpion.task_master.adapters.persistence.entity.UsuarioEntity;
import br.com.escorpion.task_master.adapters.persistence.mapper.UsuarioEntityMapper;
import br.com.escorpion.task_master.adapters.persistence.repository.UsuarioRepository;
import br.com.escorpion.task_master.core.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListarInformacoesUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public Usuario listarInformacoes(String email) {
        UsuarioEntity usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return UsuarioEntityMapper.toDomain(usuario);
    }
}