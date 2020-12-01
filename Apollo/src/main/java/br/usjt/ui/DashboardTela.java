package br.usjt.ui;

import javax.swing.*;
import java.awt.*;

public class DashboardTela extends javax.swing.JFrame {
    JFrame telaDashboard = new JFrame("Apollo");
    JButton generosPreferidosBT = new JButton("Meus Gêneros Preferidos");
    JButton sertanejoBT = new JButton("Sertanejo");
    JButton indieBT = new JButton("Indie");
    JButton rockBT = new JButton("Rock");
    JButton popBT = new JButton("Pop");
    JButton avaliarMusicasBT = new JButton("Avaliar Músicas");
    JButton recomendacoesBT = new JButton("Receber Recomendações");
    JLabel desejaFazerLabel = new JLabel("O que iremos fazer hoje? ");
    JLabel musicasLabel = new JLabel("Músicas por Gênero");

    public DashboardTela() {
        initComponents();
        setLocationRelativeTo(null);
        telaDashboard.getContentPane().setLayout(new BorderLayout());
        telaDashboard.getContentPane().setBackground(Color.white);
        telaDashboard.getContentPane().add(criarAcoes(), BorderLayout.CENTER);
        telaDashboard.pack();
        telaDashboard.setLocationRelativeTo(null);
        telaDashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaDashboard.setVisible(true);
    }

    private void initComponents() {
        generosPreferidosBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generosPreferidosActionPerformed(evt);
            }
        });

        avaliarMusicasBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avaliarMusicasActionPerformed(evt);
            }
        });

        recomendacoesBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recomendacoesActionPerformed(evt);
            }
        });
    }

    private Container criarAcoes() {
        Container acoes = new Container();
        Container acoes2 = new Container();
        GridLayout gridPrincipal = new GridLayout(4, 1, 20, 20);
        GridLayout gridSecundario = new GridLayout(1, 4, 20, 20);
        acoes.setLayout(gridPrincipal);
        acoes2.setLayout(gridSecundario);
        acoes.add(desejaFazerLabel);
        desejaFazerLabel.setHorizontalAlignment(JLabel.CENTER);
        acoes.add(generosPreferidosBT);
        generosPreferidosBT.setBackground(Color.black);
        generosPreferidosBT.setForeground(Color.white);
        generosPreferidosBT.setPreferredSize(new Dimension(300,50));

//        acoes2.add(musicasLabel);
//        acoes2.add(indieBT);
//        indieBT.setBackground(Color.green);
//        indieBT.setForeground(Color.white);
//        acoes2.add(popBT);
//        popBT.setBackground(Color.pink);
//        popBT.setForeground(Color.white);
//        acoes2.add(rockBT);
//        rockBT.setBackground(Color.blue);
//        rockBT.setForeground(Color.white);
//        acoes2.add(sertanejoBT);
//        sertanejoBT.setBackground(Color.orange);
//        sertanejoBT.setForeground(Color.white);
//        acoes.add(acoes2);

        acoes.add(avaliarMusicasBT);
        avaliarMusicasBT.setBackground(Color.black);
        avaliarMusicasBT.setForeground(Color.white);
        avaliarMusicasBT.setPreferredSize(new Dimension(300,50));
        acoes.add(recomendacoesBT);
        recomendacoesBT.setBackground(Color.black);
        recomendacoesBT.setForeground(Color.white);
        recomendacoesBT.setPreferredSize(new Dimension(300,50));
        return acoes;
    }

    private void generosPreferidosActionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == generosPreferidosBT) {
            new GenerosTelaNovo();
            telaDashboard.dispose();


        }
    }

    private void avaliarMusicasActionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == avaliarMusicasBT) {
            new AvaliarMusicasTela();
            telaDashboard.dispose();
        }
    }

    private void recomendacoesActionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == recomendacoesBT) {
            new RecomendacoesTela();
            telaDashboard.dispose();
        }
    }

}
