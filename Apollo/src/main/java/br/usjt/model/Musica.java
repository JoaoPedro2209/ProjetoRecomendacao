package br.usjt.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Musica {

    private int id;
    String nomeMusica;
    int notaMusica;
    private List<Genero> generos;

}
