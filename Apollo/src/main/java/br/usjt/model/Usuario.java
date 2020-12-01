package br.usjt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Usuario {
    private int id;
    private String login;
    private String senha;
    private String nome;
    //private List<Musica> musicas;
}
