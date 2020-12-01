package br.usjt.ui;

import br.usjt.dao.MusicaDAO;
import br.usjt.model.Genero;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecomendacoesTela extends JFrame {
    JFrame telaRecomendacoes = new JFrame("Apollo");
    JLabel recomendacoesLabel = new JLabel("<html> <i/> <b/> Estas são as nossas recomendações para você! <br/> </html>");
    JButton voltarBT = new JButton("Voltar");
    JButton sairBT = new JButton("Sair");
    JTable tabela;
    JPanel painel = new JPanel();
    javax.swing.JComboBox<Genero> generosComboBox;


    private void initComponents() {
        generosComboBox = new javax.swing.JComboBox<>();
        generosComboBox.setModel(new DefaultComboBoxModel<>());


//        voltarBT.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                abrirActionPerformed(evt);
//            }
//        });
        sairBT.addActionListener((e) -> {
            telaRecomendacoes.dispose();
        });
    }

    public RecomendacoesTela() {

        initComponents();
        criarTabela();
        Container principal = getContentPane();
        principal.setLayout(new BorderLayout());
        principal.setBackground(Color.white);
        principal.add(criarInput(), BorderLayout.NORTH);
        principal.add(criarAcoes(), BorderLayout.CENTER);
        principal.add(criarBotoes(), BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void criarTabela() {
        DefaultTableModel tabelaMusicasRecomendadas = new DefaultTableModel(conteudoMusicas(false), nomeColunas());
        tabela = new JTable(tabelaMusicasRecomendadas);
        tabela.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(tabela);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(200);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
        painel.setLayout(new BorderLayout());
        painel.add(BorderLayout.CENTER, scrollPane);
        painel.setPreferredSize(new Dimension(300, 100));
    }

    private String[] nomeColunas() {
        return new String[]{
                "Música", "Nota"
        };
    }

    private String[][] conteudoMusicas(boolean inserir) {
        MusicaDAO musicaDAO = new MusicaDAO();
        return musicaDAO.obterMusicas(inserir);
    }

    private Container criarInput() {
        Container input = new Container();
        input.setPreferredSize(new Dimension(300, 40));
        input.add(recomendacoesLabel);
        recomendacoesLabel.setHorizontalAlignment(JLabel.CENTER);
        sairBT.addActionListener((e) -> {
            telaRecomendacoes.dispose();
        });
        input.setLayout(new GridLayout(2, 2, 30, 0));
        return input;
    }

    private Container criarAcoes() {
        Container acoes = new Container();
        acoes.setLayout(new BorderLayout());
        acoes.add(tabela.getTableHeader(), BorderLayout.PAGE_START);
        acoes.add(tabela, BorderLayout.CENTER);

        return acoes;
    }

    private Container criarBotoes() {
        Container botoesContainer = new Container();
        GridLayout gridBotoes = new GridLayout(1, 2, 20, 10);

        botoesContainer.setLayout(gridBotoes);

        voltarBT.setBackground(Color.black);
        voltarBT.setForeground(Color.white);
        sairBT.setBackground(Color.black);
        sairBT.setForeground(Color.white);

        voltarBT.setPreferredSize(new Dimension(150, 30));
        sairBT.setPreferredSize(new Dimension(150, 30));

        sairBT.addActionListener((e) -> this.dispose());
        voltarBT.addActionListener((e) -> telaRecomendacoes.dispose());
        voltarBT.addActionListener((e) -> new DashboardTela());


        botoesContainer.add(voltarBT);
        botoesContainer.add(sairBT);

        return botoesContainer;
    }

//        private void abrirActionPerformed (java.awt.event.ActionEvent evt){
//            if (evt.getSource() == voltarBT) {
//                new DashboardTela();
//                telaRecomendacoes.dispose();
//            }
//        }

}
