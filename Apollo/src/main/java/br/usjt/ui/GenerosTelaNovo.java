package br.usjt.ui;

import br.usjt.dao.GeneroDAO;
import br.usjt.model.Genero;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GenerosTelaNovo extends JFrame {
    JFrame telaGenero = new JFrame("Apollo");
    JButton voltarBT = new JButton("Voltar");
    JButton sairBT = new JButton("Sair");
    JButton novoBT = new JButton("Novo");
    JButton atualizarBT = new JButton("Atualizar");
    JButton removerBT = new JButton("Remover");
    JTextField nomeGeneroTF = new JTextField(10);
    JTable tabela;
    JPanel painel = new JPanel();

    private static final String CONFIRMAR_CRIACAO = "Confirmar criação?";
    private static final String VOLTAR = "Voltar";
    private static final String NOVO = "Novo";
    private static final String SALVAR_ALTERACAO = "Salvar alteração?";
    private static final String ATUALIZAR = "Atualizar";
    private static final String CONFIRMAR_REMOCAO = "Confirma remoção?";
    private static final String REMOVER = "Remover";

    public GenerosTelaNovo() {
        initComponents();
        criarTabela();
        Container principal = getContentPane();
        principal.setLayout(new BorderLayout());
        principal.setBackground(Color.white);
        principal.add(criarAcoes(), BorderLayout.NORTH);
        principal.add(criarTF(), BorderLayout.CENTER);
        principal.add(criarBotoes(), BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initComponents() {
        removerBT.addActionListener(evt -> removerActionPerformed(evt));
        voltarBT.addActionListener(evt -> voltarActionPerformed(evt));
        atualizarBT.addActionListener(evt -> atualizarActionPerformed(evt));
        novoBT.addActionListener(evt -> novoActionPerformed(evt));
        voltarBT.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                abrirActionPerformed(evt);
            }
        });
        sairBT.addActionListener((e) -> {
            telaGenero.dispose();
        });
    }

    private void novoActionPerformed(ActionEvent evt) {
        if (novoBT.getText().equals(NOVO)) {
            novoBT.setText(CONFIRMAR_CRIACAO);
            GeneroDAO generoDAO = new GeneroDAO();
            try {
                String[][] generos = generoDAO.obterGeneros();
                String[][] novosGeneros = Arrays.copyOf(generos, generos.length + 1);
                novosGeneros[novosGeneros.length - 1] = new String[0];
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar gêneros no banco");
                e.printStackTrace();
            }
            nomeGeneroTF.setEditable(true);
        } else {
            Genero genero = new Genero(String.valueOf(nomeGeneroTF.getText()));
            GeneroDAO generoDAO = new GeneroDAO();
            try {
                generoDAO.criar(genero);
                JOptionPane.showMessageDialog(null, "Gênero novo criado");
                DefaultTableModel tabelaGeneros = new DefaultTableModel(conteudoGeneros(), nomeColunas());
                tabela = new JTable(tabelaGeneros);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao criar gênero no banco");
                e.printStackTrace();
            }
            buscarGeneros();
            novoBT.setText(NOVO);
            nomeGeneroTF.setEditable(false);
        }
    }

    private void atualizarActionPerformed(ActionEvent evt) {
        TableModel model = tabela.getModel();
        int linhaSel = tabela.getSelectedRow();
        if (atualizarBT.getText().equals(ATUALIZAR)) {
            atualizarBT.setText(SALVAR_ALTERACAO);
            nomeGeneroTF.setEditable(true);
        } else {
            Genero genero = new Genero(Integer.parseInt("" + model.getValueAt(linhaSel, 0)),
                    (String.valueOf(model.getValueAt(linhaSel, 1))));
            GeneroDAO generoDAO = new GeneroDAO();
            try {
                generoDAO.atualizar(genero);
                buscarGeneros();
                JOptionPane.showMessageDialog(null, "Atualizado com Sucesso");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar");
                e.printStackTrace();
            }
            atualizarBT.setText(ATUALIZAR);
        }
    }

    private Genero obterGeneroSelecionado() {
        TableModel model = tabela.getModel();
        int linhaSel = tabela.getSelectedRow();
        return new Genero(Integer.parseInt("" + model.getValueAt(linhaSel, 0)),
                (String.valueOf(model.getValueAt(linhaSel, 1))));
    }

    private void removerActionPerformed(ActionEvent evt) {
        if (removerBT.getText().equals(REMOVER)) {
            removerBT.setText(CONFIRMAR_REMOCAO);
        } else {
            System.out.println(tabela.getSelectedRow());
            Genero genero = obterGeneroSelecionado();
            GeneroDAO generoDAO = new GeneroDAO();

            try {
                generoDAO.remover(genero.getId());
                buscarGeneros();
                JOptionPane.showMessageDialog(null, "Gênero removido");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao apagar");
                e.printStackTrace();
            }
            removerBT.setText(REMOVER);
        }
    }

    private void voltarActionPerformed(ActionEvent evt) {
        if (evt.getSource() == voltarBT) {
            new DashboardTela();
            telaGenero.dispose();
        }
    }

    private void abrirActionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == voltarBT) {
            new DashboardTela();
            telaGenero.dispose();
        }
    }

    private Container criarAcoes() {
        Container acoes = new Container();
        GridLayout gridPrincipal = new GridLayout(2, 1, 50, 50);
        acoes.setLayout(gridPrincipal);
        acoes.setLayout(new BorderLayout());
        acoes.add(tabela.getTableHeader(), BorderLayout.PAGE_START);
        acoes.add(tabela, BorderLayout.CENTER);
        return acoes;
    }

    private Container criarTF() {
        Container acoes2 = new Container();
        GridLayout gridSecundario = new GridLayout(1, 1, 20, 10);
        nomeGeneroTF.setSize(100, 70);
        nomeGeneroTF.setBorder(BorderFactory.createTitledBorder("Novo Gênero"));
        acoes2.setLayout(gridSecundario);
        acoes2.add(nomeGeneroTF);
        return acoes2;
    }

    private Container criarBotoes() {
        Container botoesContainer = new Container();
        Container botaoSair = new Container();
        GridLayout gridBotoes = new GridLayout(3, 2, 20, 10);
        GridLayout gridBotaoSair = new GridLayout(1, 1, 20, 10);
        botoesContainer.setLayout(gridBotoes);
        botaoSair.setLayout(gridBotaoSair);

        voltarBT.setBackground(Color.black);
        voltarBT.setForeground(Color.white);
        sairBT.setBackground(Color.black);
        sairBT.setForeground(Color.white);
        novoBT.setBackground(Color.black);
        novoBT.setForeground(Color.white);
        removerBT.setBackground(Color.black);
        removerBT.setForeground(Color.white);
        atualizarBT.setBackground(Color.black);
        atualizarBT.setForeground(Color.white);

        novoBT.setPreferredSize(new Dimension(150, 30));
        atualizarBT.setPreferredSize(new Dimension(150, 30));
        removerBT.setPreferredSize(new Dimension(150, 30));
        voltarBT.setPreferredSize(new Dimension(150, 30));
        voltarBT.setPreferredSize(new Dimension(150, 30));

        sairBT.addActionListener((e) -> this.dispose());
        voltarBT.addActionListener((e) -> telaGenero.dispose());
        voltarBT.addActionListener((e) -> new DashboardTela());

        botoesContainer.add(novoBT);
        botoesContainer.add(atualizarBT);
        botoesContainer.add(removerBT);
        botoesContainer.add(voltarBT);

        botaoSair.add(sairBT);
        voltarBT.setHorizontalAlignment(JButton.CENTER);
        botoesContainer.add(botaoSair);

        return botoesContainer;
    }

    public void criarTabela() {
        DefaultTableModel tabelaGeneros = new DefaultTableModel(conteudoGeneros(), nomeColunas());
        tabelaGeneros.fireTableDataChanged();
        tabela = new JTable(tabelaGeneros);
        JScrollPane scrollPane = new JScrollPane(tabela);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(280);
        painel.setLayout(new BorderLayout());
        painel.add(BorderLayout.CENTER, scrollPane);
        painel.setPreferredSize(new Dimension(300, 100));
    }

    private String[] nomeColunas() {
        return new String[]{
                "Id", "Gênero"
        };
    }

    private String[][] conteudoGeneros() {
        GeneroDAO generoDAO = new GeneroDAO();
        return generoDAO.obterGeneros();
    }

    private void buscarGeneros() {
        try {
            GeneroDAO generoDAO = new GeneroDAO();
            String[][] generos = generoDAO.obterGeneros();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gêneros indisponíveis, tente novamente mais tarde.");
            e.printStackTrace();
        }
    }



}
