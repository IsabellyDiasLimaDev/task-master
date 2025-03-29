package br.com.escorpion.task_master.adapters.web.mapper;

import br.com.escorpion.task_master.adapters.web.dto.UsuarioDTO;
import br.com.escorpion.task_master.core.domain.model.Usuario;

public class UsuarioDTOMapper {
    public static Usuario toDomain(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setSenha(dto.getSenha());
        usuario.setEmail(dto.getEmail());
        return usuario;
    }

    public static UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNomeUsuario(usuario.getNomeUsuario());
        dto.setEmail(usuario.getEmail());
        dto.setSenha(usuario.getSenha());
        return dto;
    }
}
