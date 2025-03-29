package br.com.escorpion.task_master.adapters.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private String nomeUsuario;
    private String email;
    private String senha;
}
