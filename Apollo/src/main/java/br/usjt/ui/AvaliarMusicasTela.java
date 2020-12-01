package br.usjt.ui;

import br.usjt.dao.GeneroDAO;
import br.usjt.dao.MusicaDAO;
import br.usjt.model.Genero;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AvaliarMusicasTela extends JFrame {
    JFrame telaAvaliarMusicas = new JFrame("Apollo");
    JButton avaliarBT = new JButton("Confirmar avaliações");
    JButton voltarBT = new JButton("Voltar");
    JButton sairBT = new JButton("Sair");
    JTable tabela;
    JPanel painel = new JPanel();
    private final DefaultTableModel tblAvaliacaoMusicas = new DefaultTableModel();
    javax.swing.JComboBox<Genero> comboBoxGeneros;

    private void initComponents() {
        comboBoxGeneros = new JComboBox<>();
        comboBoxGeneros.setModel(new DefaultComboBoxModel<>());

        voltarBT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                abrirActionPerformed(evt);
            }
        });

        sairBT.addActionListener((e) -> {
            telaAvaliarMusicas.dispose();
        });
    }

    public AvaliarMusicasTela() {
        initComponents();
        criarTabela();
        preencherTabelaMusicas();
        Container principal = getContentPane();
        principal.setLayout(new BorderLayout());
        principal.setBackground(Color.white);
        principal.add(criarAcoes(), BorderLayout.CENTER);
        principal.add(criarBotoes(), BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
//        telaAvaliarMusicas.getContentPane().setLayout(new BorderLayout());
//        telaAvaliarMusicas.getContentPane().setBackground(Color.white);
//        telaAvaliarMusicas.getContentPane().add(criarAcoes(), BorderLayout.SOUTH);
//        telaAvaliarMusicas.pack();
//        telaAvaliarMusicas.setLocationRelativeTo(null);
//        telaAvaliarMusicas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        telaAvaliarMusicas.setVisible(true);
    }

    private Container criarBotoes() {
        Container botoesContainer = new Container();
        GridLayout gridBotoes = new GridLayout(1, 3, 20, 10);

        botoesContainer.setLayout(gridBotoes);

        voltarBT.setBackground(Color.black);
        voltarBT.setForeground(Color.white);
        sairBT.setBackground(Color.black);
        sairBT.setForeground(Color.white);
        avaliarBT.setBackground(Color.black);
        avaliarBT.setForeground(Color.white);

        voltarBT.setPreferredSize(new Dimension(100, 30));
        sairBT.setPreferredSize(new Dimension(100, 30));
        avaliarBT.setPreferredSize(new Dimension(100, 30));

        // TODO: precisa adicionar o que o botão "voltar" faz
        sairBT.addActionListener((e) -> this.dispose());
        voltarBT.addActionListener((e) -> telaAvaliarMusicas.dispose());
        voltarBT.addActionListener((e) -> new DashboardTela());

        botoesContainer.add(voltarBT);
        botoesContainer.add(sairBT);
        botoesContainer.add(avaliarBT);
        return botoesContainer;
    }

    public void criarTabela() {
        DefaultTableModel tabelaMusicas = new DefaultTableModel(conteudoMusicas(false), nomeColunas());
        tabela = new JTable(tabelaMusicas);
//        tabela.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(tabela);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(200);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
        painel.setLayout(new BorderLayout());
        painel.add(BorderLayout.CENTER, scrollPane);
        painel.setPreferredSize(new Dimension(300, 100));
    }

    private String[] nomeColunas() {
        return new String[] {
                "Música", "Minha Nota"
        };
    }

    private String[][] conteudoMusicas(boolean inserir) {
        MusicaDAO musicaDAO = new MusicaDAO();
        return musicaDAO.obterMusicas(inserir);
    }
    private void buscarCursos() {
        try {
            GeneroDAO generoDAO = new GeneroDAO();
            String[][] generos = generoDAO.obterGeneros();
            preencherActionPerformed(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void preencherActionPerformed(ActionEvent evt) {
            Genero generoSelecionado = (Genero) comboBoxGeneros.getSelectedItem();
        }
//        nomeCursoTextField.setText(generoSelecionado.getNome());

    private void preencherTabelaMusicas() {
        preencherTabelaMusicas(false);
    }

    private void preencherTabelaMusicas(boolean inserir) {
        tabela.setModel(new DefaultTableModel(
                conteudoMusicas(inserir),
                nomeColunas()
        ));
    }

    private Container criarAcoes() {
        Container acoes = new Container();
        Container acoes2 = new Container();
        Container acoes3 = new Container();
        GridLayout gridPrincipal = new GridLayout(1, 1, 20, 20);
//        GridLayout gridTerciario = new GridLayout(1, 3, 20, 20);
        acoes.setLayout(gridPrincipal);
        acoes2.setLayout(new BorderLayout());
        acoes2.add(tabela.getTableHeader(), BorderLayout.PAGE_START);
        acoes2.add(tabela, BorderLayout.CENTER);
        acoes.add(acoes2);
//        acoes3.setLayout(gridTerciario);
//        acoes.add(acoes3);
        return acoes;
    }

    private void abrirActionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == voltarBT) {
            new DashboardTela();
            telaAvaliarMusicas.dispose();

        }
    }


}
