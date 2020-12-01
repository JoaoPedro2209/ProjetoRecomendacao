package br.usjt.ui;

import br.usjt.model.Usuario;
import br.usjt.service.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class CadastroTela extends JFrame implements ActionListener {
    JFrame telaCadastro = new JFrame("Apollo");
    JLabel inscrevaLabel = new JLabel("<html> <i/> <b/> Inscreva-se Grátis. <br/> </html>");
    JPasswordField senhaCadastroPF = new JPasswordField(10);
    JTextField loginCadastroTF = new JTextField(10);
    JTextField nome = new JTextField(10);
    JButton sairBT = new JButton("Sair");
    JButton cadastrarBT = new JButton("Cadastrar");
    JTextArea senha = new JTextArea("A senha deve conter oito dígitos, letras maiúsculas e minúsculas, número e caracter especial");

    private Container criarInput2() {
        Container input = new Container();
        input.setPreferredSize(new Dimension(300, 150));
        input.add(inscrevaLabel);
        inscrevaLabel.setHorizontalAlignment(JLabel.CENTER);
        loginCadastroTF.setSize(270, 70);
        nome.setBorder(BorderFactory.createTitledBorder("Qual o seu nome? "));
        loginCadastroTF.setBorder(BorderFactory.createTitledBorder("Digite o seu melhor e-mail: "));
        senhaCadastroPF.setBorder(BorderFactory.createTitledBorder("Crie uma senha forte: "));
        sairBT.addActionListener((e) -> {
            telaCadastro.dispose();
        });
        input.setLayout(new GridLayout(3, 1, 30, 10));
        input.add(inscrevaLabel);
        input.add(nome);
        input.add(loginCadastroTF);
        input.add(senhaCadastroPF);
        //input.add(senha);
        return input;
    }

    private Container criarAcoes() {
        Container acoes = new Container();
        Container acoesSecundario = new Container();
        GridLayout gridPrincipal = new GridLayout(2, 1, 20, 20);
        acoes.setLayout(gridPrincipal);
        acoes.add(inscrevaLabel);
        inscrevaLabel.setHorizontalAlignment(JLabel.CENTER);
        GridLayout gridSecundario = new GridLayout(1, 2, 20, 20);
        acoesSecundario.setLayout(gridSecundario);
        acoesSecundario.add(sairBT);
        sairBT.setBackground(Color.black);
        sairBT.setForeground(Color.white);
        acoesSecundario.add(cadastrarBT);
        cadastrarBT.setBackground(Color.black);
        cadastrarBT.setForeground(Color.white);
        acoes.add(acoesSecundario);
        cadastrarBT.addActionListener((e) -> {
            if (loginCadastroTF.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo de login deve ser preenchido.");
            }
            if (senhaCadastroPF.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo de senha deve ser preenchido.");
            }
            if (nome.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O seu nome deve ser informado.");
            } else {
                UsuarioService usuarioService = new UsuarioService();
                if(usuarioService.obterUsuario(loginCadastroTF.getText())){
                    JOptionPane.showMessageDialog(null, "Já existe um usuário cadastrado para o login " + loginCadastroTF.getText() + ".");
                } else {
                    try {
                        usuarioService.cadastrar(nome.getText(), loginCadastroTF.getText(), senhaCadastroPF.getText());
                        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso.");
                        Usuario usuario = usuarioService.temUsuario(loginCadastroTF.getText(), new String(senhaCadastroPF.getPassword()));
                        if (usuario != null) {
                            JOptionPane.showMessageDialog(null, "Bem vindo, " + usuario.getNome() + "!");
                            new DashboardTela();
                            telaCadastro.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Nome de usuário ou senha incorretos.");
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Não foi possível realizar o cadastro. Tente novamente.");
                    }
                }
            }
        });
        return acoes;
    }

    public CadastroTela() {
        telaCadastro.getContentPane().setLayout(new BorderLayout());
        telaCadastro.getContentPane().setBackground(Color.white);
        telaCadastro.getContentPane().add(criarInput2(), BorderLayout.CENTER);
        telaCadastro.getContentPane().add(criarAcoes(), BorderLayout.SOUTH);
        telaCadastro.pack();
        telaCadastro.setLocationRelativeTo(null);
        telaCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaCadastro.setVisible(true);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        CadastroTela cadastro = new CadastroTela();
        cadastro.setVisible(true);
        telaCadastro.dispose();
    }
}


