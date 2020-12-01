package br.usjt.dao;

import br.usjt.bd.ConnectionFactory;
import br.usjt.model.Genero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAO {
    public String[][] obterGeneros() {
        String sql = "SELECT * FROM tb_generos";
        try (Connection conn = ConnectionFactory.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = ps.executeQuery()) {
            int totalGeneros = rs.last() ? rs.getRow() : 0;
            String[][] genero = new String[totalGeneros][2];
            rs.beforeFirst();
            int contador = 0;
            while (rs.next()) {
                int id = rs.getInt("id");
                String nomeGenero = rs.getString("nomeGenero");
                genero[contador++] = new String[]{""+id, nomeGenero};
            }
            return genero;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void remover(int id) throws Exception {
        String sql = "DELETE from tb_generos where id = ?";
        try (Connection conn = ConnectionFactory.obterConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
        }
    }

    public void atualizar(Genero genero) throws Exception {
        String sql = "UPDATE tb_generos set nomeGenero = ? where id = ? ";
        try (Connection conn = ConnectionFactory.obterConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, genero.getNomeGenero());
            ps.setInt(2, genero.getId());
            ps.executeUpdate();
        }
    }

    public void criar(Genero genero) throws Exception {
        String sql = "INSERT into tb_generos (nomeGenero) values (?)";
        try (Connection conn = ConnectionFactory.obterConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, genero.getNomeGenero());
            ps.execute();
        }
    }
}




