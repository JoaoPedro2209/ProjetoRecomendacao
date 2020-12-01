import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Teste extends JFrame {
    JLabel recomendacoesLabel = new JLabel("<html> <i/> <b/> Estas são as nossas recomendações para você! <br/> </html>");
    JButton voltarBT = new JButton("Voltar");
    JButton sairBT = new JButton("Sair");
    JTable tabela;
    JPanel painel = new JPanel();
    javax.swing.JComboBox<String> generosComboBox;

    public Teste() {
        super("Apollo");
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

    private void initComponents() {
        generosComboBox = new JComboBox<>();
        String[] generos = {"Rock"};
        generosComboBox.setModel(new DefaultComboBoxModel<>(generos));
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
        return new String[] {
                "Música", "Nota"
        };
    }

    private String[][] conteudoMusicas(boolean inserir) {
        String[][] teste = {{"Musica 1", "5"}, {"Musica 2", "4"}, {"Musica 3", "4.5"}};
        return teste;
    }

    private Container criarInput() {
        Container input = new Container();
        input.setLayout(new GridLayout(2, 2, 30, 0));
        // Tenta deixar sempre o mesmo padrão de tamanhos das colunas.
        // Por exemplo, se tudo vai ficar enfileirado, tenta deixar tudo com
        // o mesmo comprimento, alterando apenas as larguras.
        input.setPreferredSize(new Dimension(300, 40));
        input.add(recomendacoesLabel);
        recomendacoesLabel.setHorizontalAlignment(JLabel.CENTER);
        return input;
    }

    private Container criarAcoes() {
        Container acoes = new Container();

        acoes.setLayout(new BorderLayout());
        // Adiciona o painel onde você colocou a tabela. 
        // Aí você controla o tamanho pelo painel.
        acoes.add(painel, BorderLayout.CENTER);

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

        // Aqui você controla o tamanho dos botões
        // Mas tenta fazer com que a soma dos comprimento dos botões + 
        // o gap entre eles resulte no comprimento total da pagina que você quer
        voltarBT.setPreferredSize(new Dimension(150, 30));
        sairBT.setPreferredSize(new Dimension(150, 30));

        // TODO: precisa adicionar o que o botão "voltar" faz
        sairBT.addActionListener((e) -> this.dispose());

        botoesContainer.add(voltarBT);
        botoesContainer.add(sairBT);

        return botoesContainer;
    }
}