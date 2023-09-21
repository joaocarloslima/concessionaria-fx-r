package com.example.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "pf1389";
    private static final String PASS = "fiap23";

    private static Connection conexao;

    public static Connection getConnection() throws SQLException{
        if(conexao == null || conexao.isClosed()){
            conexao = DriverManager.getConnection(URL, USER, PASS);
        }
        return conexao;
    }
    
}
