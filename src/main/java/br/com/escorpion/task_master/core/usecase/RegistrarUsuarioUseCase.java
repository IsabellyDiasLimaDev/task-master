package br.com.escorpion.task_master.core.usecase;

import br.com.escorpion.task_master.adapters.persistence.entity.UsuarioEntity;
import br.com.escorpion.task_master.adapters.persistence.mapper.UsuarioEntityMapper;
import br.com.escorpion.task_master.adapters.persistence.repository.UsuarioRepository;
import br.com.escorpion.task_master.core.domain.model.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrarUsuarioUseCase(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario executar(String username, String email, String senha) {
        // Validações (por exemplo, se o email já existe)
        if (usuarioRepository.findByEmail(email).isPresent()) {
            //TODO MELHORAR RETORNO DE EXCEÇÕES E MENSAGENS
            throw new RuntimeException("Email já está em uso");
        }

        // Criptografar senha
        String senhaCriptografada = passwordEncoder.encode(senha);

        // Criar usuário
        Usuario usuario = new Usuario(username, email, senhaCriptografada);

        // Salvar no banco
        UsuarioEntity entity = usuarioRepository.save(UsuarioEntityMapper.toEntity(usuario));
        return UsuarioEntityMapper.toDomain(entity);
    }
}
