package br.com.escorpion.task_master.core.usecase;

import br.com.escorpion.task_master.adapters.persistence.entity.UsuarioEntity;
import br.com.escorpion.task_master.adapters.persistence.repository.UsuarioRepository;
import br.com.escorpion.task_master.core.domain.model.Usuario;
import br.com.escorpion.task_master.config.security.jwt.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    // Injeção via construtor
    public AutenticarUsuarioUseCase(UsuarioRepository usuarioRepository,
                                    PasswordEncoder passwordEncoder,
                                    JwtService jwtService,
                                    UserDetailsService userDetailsService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    public String autenticarUsuario(String email, String senha) {
        // Verifica se o usuário existe
        UsuarioEntity usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Verifica se a senha é válida
        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new BadCredentialsException("Senha inválida");
        }

        try {
            // Carregar detalhes do usuário
            UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getNomeUsuario());

            // Gerar token JWT
            return jwtService.generateToken(userDetails);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar token JWT", e);
        }
    }
}
