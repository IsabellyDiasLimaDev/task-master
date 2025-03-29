package br.com.escorpion.task_master.adapters.persistence.mapper;

import br.com.escorpion.task_master.adapters.persistence.entity.UsuarioEntity;
import br.com.escorpion.task_master.core.domain.model.Usuario;

public class UsuarioEntityMapper {
    public static Usuario toDomain(UsuarioEntity entity) {
        Usuario usuario = new Usuario();
        usuario.setId(entity.getId());
        usuario.setNomeUsuario(entity.getNomeUsuario());
        usuario.setSenha(entity.getSenha());
        usuario.setEmail(entity.getEmail());
        return usuario;
    }

    public static UsuarioEntity toEntity(Usuario usuario) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(usuario.getId());
        entity.setNomeUsuario(usuario.getNomeUsuario());
        entity.setSenha(usuario.getSenha());
        entity.setEmail(usuario.getEmail());
        return entity;
    }
}
