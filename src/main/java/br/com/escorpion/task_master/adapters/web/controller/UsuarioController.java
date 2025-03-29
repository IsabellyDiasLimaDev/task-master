package br.com.escorpion.task_master.adapters.web.controller;

import br.com.escorpion.task_master.adapters.web.dto.UsuarioDTO;
import br.com.escorpion.task_master.adapters.web.mapper.UsuarioDTOMapper;
import br.com.escorpion.task_master.core.domain.model.Usuario;
import br.com.escorpion.task_master.core.usecase.AutenticarUsuarioUseCase;
import br.com.escorpion.task_master.core.usecase.ListarInformacoesUsuarioUseCase;
import br.com.escorpion.task_master.core.usecase.RegistrarUsuarioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final RegistrarUsuarioUseCase registrarUsuarioUseCase;
    private final AutenticarUsuarioUseCase autenticarUsuarioUseCase;
    private final ListarInformacoesUsuarioUseCase listarInformacoesUsuarioUseCase;

    @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = registrarUsuarioUseCase.executar(usuarioDTO.getNomeUsuario(), usuarioDTO.getEmail(), usuarioDTO.getSenha());
        return ResponseEntity.ok(UsuarioDTOMapper.toDTO(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioDTO usuarioDTO) {
        String token = autenticarUsuarioUseCase.autenticarUsuario(usuarioDTO.getNomeUsuario(), usuarioDTO.getSenha());
        return ResponseEntity.ok(token);
    }

    @GetMapping
    public ResponseEntity<UsuarioDTO> buscarUsuario(@RequestParam String email) {
        Usuario usuario = listarInformacoesUsuarioUseCase.listarInformacoes(email);
        return ResponseEntity.ok(UsuarioDTOMapper.toDTO(usuario));
    }

    //TODO implementar metodo de recuperacao de senha
}
