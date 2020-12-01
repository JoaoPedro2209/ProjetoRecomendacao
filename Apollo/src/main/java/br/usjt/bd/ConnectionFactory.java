package br.usjt.bd;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    private static final String USUARIO = "root";
    private static final String SENHA = "$Adriana02";
    private static final String HOST = "localhost";
    private static final String PORTA = "3306";
    private static final String BD = "db_pessoas";

    public static Connection obterConexao() throws Exception {
        String url = String.format("jdbc:mysql://%s:%s/%s?useTimezone=true&serverTimezone=America/Sao_Paulo", HOST,
                PORTA, BD);
        return DriverManager.getConnection(url, USUARIO, SENHA);
    }
}
