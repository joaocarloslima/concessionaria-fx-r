package com.example.data;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Cliente;

public class ClienteDao {

    static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    static final String USER = "pf1389";
    static final String PASS = "fiap23";

    public static void inserir(Cliente cliente) throws SQLException {
        var conexao = DriverManager.getConnection(URL, USER, PASS);

        var sql = "INSERT INTO clientes (nome, email, telefone) VALUES ( ?, ?, ?) ";
        var comando = conexao.prepareStatement(sql);
        comando.setString(1, cliente.getNome());
        comando.setString(2, cliente.getEmail());
        comando.setString(3, cliente.getTelefone());
        comando.executeUpdate();

        conexao.close();

    }

    public static List<Cliente> buscarTodos() throws SQLException{
        var lista = new ArrayList<Cliente>();

        var conexao = DriverManager.getConnection(URL, USER, PASS);
        var comando = conexao.prepareStatement("SELECT * FROM clientes");
        var resultado = comando.executeQuery();

        while(resultado.next()){
            lista.add (new Cliente(
                resultado.getLong("id"), 
                resultado.getString("nome"), 
                resultado.getString("email"), 
                resultado.getString("telefone")
            ));
        }

        conexao.close();
        return lista;
    }
}
