package br.usjt;

import javax.swing.*;

import br.usjt.ui.AvaliarMusicasTela;
import br.usjt.ui.GenerosTelaNovo;
import br.usjt.ui.LoginTela;
import br.usjt.ui.RecomendacoesTela;

public class ProgramaAcademico {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginTela lt = new LoginTela();
            lt.criarTela();
//           RecomendacoesTela cb = new RecomendacoesTela();
//           GenerosTelaNovo gt = new GenerosTelaNovo();
//            AvaliarMusicasTela avl = new AvaliarMusicasTela();
        });
    }


    }

