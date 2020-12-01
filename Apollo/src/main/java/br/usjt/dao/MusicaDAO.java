package br.usjt.dao;

import br.usjt.bd.ConnectionFactory;
import br.usjt.model.Musica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MusicaDAO {
    public String[][] obterMusicas(boolean inserir) {
        try {
            String sql = "SELECT * FROM tb_musicas";
            try (Connection conn = ConnectionFactory.obterConexao();
                 PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                 ResultSet rs = ps.executeQuery()) {
                int totalMusicas = rs.last() ? rs.getRow() : 0;
                String[][] musicas = new String[(inserir ? totalMusicas + 1 : totalMusicas) ][2];
                rs.beforeFirst();
                int contador = 0;
                while (rs.next()) {
                    String nomeMusica = rs.getString("nomeMusica");
                    String notaMusica = rs.getString("notaMusica");
                    musicas[contador++] = new String[] {nomeMusica, notaMusica};
                }
                return musicas;
            }
        } catch (Exception e) {
            return null;
        }
    }
    }

