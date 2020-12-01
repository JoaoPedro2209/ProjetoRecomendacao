package br.usjt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Genero {
    private int id;
    private String nomeGenero;

    public Genero(String nomeGenero) {
        this.nomeGenero = nomeGenero;
    }
}
