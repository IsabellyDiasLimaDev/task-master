package br.com.escorpion.task_master.core.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Usuario {
    private Long id;
    private String nomeUsuario;
    private String email;
    private String senha;

    public Usuario(String nomeUsuario,  String email, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.email = email;
    }
}
