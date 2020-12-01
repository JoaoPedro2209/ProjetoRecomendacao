package br.usjt.service;

import java.util.Optional;

import br.usjt.dao.UsuarioDAO;
import br.usjt.model.Usuario;

public class UsuarioService {
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario temUsuario(String login, String senha) {
        Optional<Usuario> opUsuario = usuarioDAO.obterUsuario(login, senha);
        if (opUsuario.isPresent()) {
            return opUsuario.get();
        } else {
            return null;
        }
    }

    public boolean cadastrar(String nome, String login, String senha) throws Exception {
       return usuarioDAO.cadastrar(nome, login, senha);
    }

    public boolean obterUsuario(String login) {
       return usuarioDAO.obterUsuario(login).isPresent();
    }
}

