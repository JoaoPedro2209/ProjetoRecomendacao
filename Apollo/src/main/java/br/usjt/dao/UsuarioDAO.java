package br.usjt.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import br.usjt.bd.ConnectionFactory;
import br.usjt.model.Usuario;

public class UsuarioDAO {
    public Optional<Usuario> obterUsuario(String loginProcurado, String senhaProcurada) {
        String sql = "SELECT * FROM tb_usuarios where login = ? and senha = ?";
        try (PreparedStatement ps = ConnectionFactory.obterConexao().prepareStatement(sql)) {
            ps.setString(1, loginProcurado);
            ps.setString(2, senhaProcurada);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                String nome = rs.getString("nome");
                return Optional.of(new Usuario(id, login, senha, nome));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Usuario> obterUsuario(String loginProcurado) {
        String sql = "SELECT * FROM tb_usuarios where login = ?";
        try (PreparedStatement ps = ConnectionFactory.obterConexao().prepareStatement(sql)) {
            ps.setString(1, loginProcurado);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                String nome = rs.getString("nome");
                return Optional.of(new Usuario(id, login, senha, nome));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean cadastrar(String nome, String login, String senha) throws Exception {
        String sql = "INSERT INTO tb_usuarios(nome, login, senha) VALUES (?,?,?)";
        PreparedStatement ps = ConnectionFactory.obterConexao().prepareStatement(sql);
        ps.setString(1, nome);
        ps.setString(2, login);
        ps.setString(3, senha);
        return ps.execute();
    }

}