package br.usjt.ui;

import br.usjt.model.Usuario;
import br.usjt.service.UsuarioService;

import javax.swing.*;
import java.awt.*;

public class LoginTela extends JFrame {
    JFrame telaInicial = new JFrame("Apollo");
    JTextField loginTF = new JTextField(10);
    JPasswordField senhaPF = new JPasswordField(10);
    JLabel bemVindoLabel = new JLabel("<html> Para curtir, faça login no Apollo. <br/>  </html>");
    JButton sairBT = new JButton("Sair");
    JButton loginBT = new JButton("Login");
    JLabel cadastroLabel = new JLabel("<html> Ainda não possui cadastro? <br/> </html>");
    JButton cadastrarBT = new JButton("Cadastre-se Agora");

    public LoginTela() {
        initComponents();
    }

    private void initComponents() {
        cadastrarBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastroActionPerformed(evt);
                telaInicial.dispose();
            }
        });
    }

    private Container criarInput() {
        Container input = new Container();
        input.setPreferredSize(new Dimension(300, 150));
        loginTF.setSize(270, 70);
        input.add(bemVindoLabel);
        bemVindoLabel.setHorizontalAlignment(JLabel.CENTER);
        loginTF.setBorder(BorderFactory.createTitledBorder("Digite seu login"));
        senhaPF.setBorder(BorderFactory.createTitledBorder("Digite sua senha"));
        sairBT.addActionListener((e) -> {
            telaInicial.dispose();
        });
        input.setLayout(new GridLayout(3, 2, 30, 10));
        input.add(loginTF);
        input.add(senhaPF);
        return input;
    }

    private Container criarAcoes() {
        Container grid1 = new Container();
        Container grid2 = new Container();
        GridLayout gridMaior = new GridLayout(3, 1, 20, 20);
        grid1.setLayout(gridMaior);
        GridLayout gridMenor = new GridLayout(1, 2, 20, 20);
        grid2.setLayout(gridMenor);
        grid2.add(sairBT);
        sairBT.setBackground(Color.black);
        sairBT.setForeground(Color.white);
        grid2.add(loginBT);
        loginBT.setBackground(Color.black);
        loginBT.setForeground(Color.white);
        grid1.add(grid2);
        grid1.add(cadastroLabel);
        cadastroLabel.setHorizontalAlignment(JLabel.CENTER);
        grid1.add(cadastrarBT);
        cadastrarBT.setBackground(Color.black); cadastrarBT.setForeground(Color.white);
        loginBT.addActionListener((e) -> {
            UsuarioService usuarioService = new UsuarioService();
                Usuario usuario = usuarioService.temUsuario(loginTF.getText(), new String(senhaPF.getPassword()));
                if (usuario != null) {
                JOptionPane.showMessageDialog(null, "Olá, "+ usuario.getNome() + "!");
                new DashboardTela();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Nome de usuário ou senha incorretos.");
            }
        });

        return grid1;
    }

    public void criarTela() {
        telaInicial.getContentPane().setLayout(new BorderLayout());
        telaInicial.getContentPane().setBackground(Color.white);
        telaInicial.getContentPane().add(criarInput(), BorderLayout.CENTER);
        telaInicial.getContentPane().add(criarAcoes(), BorderLayout.SOUTH);
        telaInicial.pack();
        telaInicial.setLocationRelativeTo(null);
        telaInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaInicial.setVisible(true);
    }

    private void cadastroActionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == cadastrarBT) {
            new CadastroTela();
            telaInicial.dispose();
            this.dispose();

        }
    }
}



