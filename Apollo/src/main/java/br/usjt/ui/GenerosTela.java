/*package br.usjt.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;

import br.usjt.dao.GeneroDAO;
import br.usjt.model.Genero;


public class GenerosTela extends javax.swing.JFrame {
    JFrame telaGeneros = new JFrame("Apollo");
    JButton voltarBT = new JButton("Voltar");

    private static final String CONFIRMAR_CRIACAO = "Confirmar criação?";
    private static final String VOLTAR = "Voltar";
    private static final String NOVO = "Novo";
    private static final String SALVAR_ALTERAÇÃO = "Salvar alteração?";
    private static final String ATUALIZAR = "Atualizar";
    private static final String CONFIRMAR_REMOCAO = "Confirma remoção?";
    private static final String REMOVER = "Remover";

    private javax.swing.JButton atualizarButton;
    private javax.swing.JButton voltarButton;
    private javax.swing.JButton novoButton;
    private javax.swing.JComboBox<Genero> generosComboBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nomeGeneroTextField;
    private javax.swing.JButton removerButton;
    JButton sairBT = new JButton("Sair");

    public GenerosTela() {
        initComponents();
        buscarGeneros();
//        telaGeneros.getContentPane().setLayout(new BorderLayout());
//        telaGeneros.getContentPane().setBackground(Color.white);
//        telaGeneros.getContentPane().add(criarAcoes(), BorderLayout.CENTER);
//        telaGeneros.pack();
//        telaGeneros.setLocationRelativeTo(null);
//        telaGeneros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        telaGeneros.setVisible(true);
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        generosComboBox = new javax.swing.JComboBox<>();
        nomeGeneroTextField = new javax.swing.JTextField();
        novoButton = new javax.swing.JButton();
        atualizarButton = new javax.swing.JButton();
        removerButton = new javax.swing.JButton();
        voltarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory
                .createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Meus Gêneros Preferidos")));
        jPanel1.setPreferredSize(new java.awt.Dimension(322, 287));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setVerifyInputWhenFocusTarget(false);

        generosComboBox.setModel(new DefaultComboBoxModel<>());

        generosComboBox.addActionListener(evt -> preencherActionPerformed(evt));
        removerButton.addActionListener(evt -> removerActionPerformed(evt));
        voltarButton.addActionListener(evt -> voltarActionPerformed(evt));
        atualizarButton.addActionListener(evt -> atualizarActionPerformed(evt));
        novoButton.addActionListener(evt -> novoActionPerformed(evt));

        nomeGeneroTextField.setEditable(false);
        nomeGeneroTextField.setBorder(javax.swing.BorderFactory.createTitledBorder("nome"));
        nomeGeneroTextField.setPreferredSize(new java.awt.Dimension(56, 20));

        novoButton.setText(NOVO);
        atualizarButton.setText(ATUALIZAR);
        removerButton.setText(REMOVER);
        voltarButton.setText(VOLTAR);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout
                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(nomeGeneroTextField, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                        .addComponent(generosComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(novoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(removerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45,
                                        Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(atualizarButton, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.PREFERRED_SIZE, 170,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(voltarButton, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.PREFERRED_SIZE, 170,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap()));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup().addGap(19, 19, 19)
                        .addComponent(generosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)

                        .addGap(18, 18, 18)
                        .addComponent(nomeGeneroTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 49,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(novoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(atualizarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(removerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(voltarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(15, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout
                        .createSequentialGroup().addGap(39, 39, 39).addComponent(jPanel1,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(44, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addContainerGap(26, Short.MAX_VALUE).addComponent(jPanel1,
                        javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)));
        novoButton.setBackground(Color.black);
        novoButton.setForeground(Color.white);
        atualizarButton.setBackground(Color.black);
        atualizarButton.setForeground(Color.white);
        removerButton.setBackground(Color.black);
        removerButton.setForeground(Color.white);
        voltarBT.setBackground(Color.black);
        voltarBT.setForeground(Color.white);
        pack();

        voltarBT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                abrirActionPerformed(evt);
            }
        });
        sairBT.addActionListener((e) -> {
            telaGeneros.dispose();
        });

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GenerosTela.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenerosTela.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenerosTela.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerosTela.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenerosTela().setVisible(true);
            }
        });
    }

    private void abrirActionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == voltarBT) {
            new DashboardTela();
            telaGeneros.dispose();
        }
    }
    /*private void buscarGeneros() {
        try {
            GeneroDAO cursoDAO = new GeneroDAO();
            Genero[] generos = cursoDAO.obterGeneros();
            generosComboBox.setModel(new DefaultComboBoxModel<>(generos));
            preencherActionPerformed(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gêneros indisponíveis, tente novamente mais tarde.");
            e.printStackTrace();
        }
    }

    private void preencherActionPerformed(ActionEvent evt) {
        Genero generoSelecionado = (Genero) generosComboBox.getSelectedItem();
        nomeGeneroTextField.setText(generoSelecionado.getNomeGenero());
        removerButton.setText(REMOVER);
        atualizarButton.setText(ATUALIZAR);
        nomeGeneroTextField.setText(NOVO);
    }

    private void removerActionPerformed(ActionEvent evt) {
        if (removerButton.getText().equals(REMOVER)) {
            removerButton.setText(CONFIRMAR_REMOCAO);
        } else {
            Genero generoSelecionado = (Genero) generosComboBox.getSelectedItem();
            GeneroDAO generoDAO = new GeneroDAO();
            try {
                generoDAO.remover(generoSelecionado.getId());
                buscarGeneros();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao apagar");
                e.printStackTrace();
            }
            removerButton.setText(REMOVER);
        }
    }

    private void voltarActionPerformed(ActionEvent evt) {
        if (evt.getSource() == voltarBT) {
            new DashboardTela();
            telaGeneros.dispose();
        }
    }

    private void atualizarActionPerformed(ActionEvent evt) {
        if (atualizarButton.getText().equals(ATUALIZAR)) {
            atualizarButton.setText(SALVAR_ALTERAÇÃO);
            nomeGeneroTextField.setEditable(true);
        } else {
            Genero genero = new Genero(String.valueOf(nomeGeneroTextField.getText()));
            GeneroDAO generoDAO = new GeneroDAO();
            try {
                generoDAO.atualizar(genero);
                buscarGeneros();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar");
                e.printStackTrace();
            }
            atualizarButton.setText(ATUALIZAR);
        }
    }

    private void novoActionPerformed(ActionEvent evt) {
        if (novoButton.getText().equals(NOVO)) {
            novoButton.setText(CONFIRMAR_CRIACAO);
            GeneroDAO cursoDAO = new GeneroDAO();
            try {
                Genero[] generos = cursoDAO.obterGeneros();
                Genero[] novosGeneros = Arrays.copyOf(generos, generos.length + 1);
                novosGeneros[novosGeneros.length - 1] = new Genero("");
                generosComboBox.setModel(new DefaultComboBoxModel<>(novosGeneros));
                generosComboBox.setSelectedIndex(novosGeneros.length - 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar gêneros no banco");
                e.printStackTrace();
            }
            nomeGeneroTextField.setEditable(true);
        } else {
            Genero genero = new Genero(nomeGeneroTextField.getText());
            GeneroDAO cursoDAO = new GeneroDAO();
            try {
                cursoDAO.criar(genero);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao criar curso no banco");
                e.printStackTrace();
            }
            buscarGeneros();
            novoButton.setText(NOVO);
            nomeGeneroTextField.setEditable(false);
        }
    }

} */
