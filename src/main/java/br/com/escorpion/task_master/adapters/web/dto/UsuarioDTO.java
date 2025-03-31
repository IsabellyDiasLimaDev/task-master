package br.com.escorpion.task_master.adapters.web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private String nomeUsuario;
    private String email;
    private String senha;
}
