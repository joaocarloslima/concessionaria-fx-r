package com.example.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Cliente;

public class ClienteDao {

    private Connection conexao;

    public ClienteDao() throws SQLException {
        conexao = ConnectionFactory.getConnection();
    }

    public void inserir(Cliente cliente) throws SQLException {
        var sql = "INSERT INTO clientes (nome, email, telefone) VALUES ( ?, ?, ?) ";
        var comando = conexao.prepareStatement(sql);
        comando.setString(1, cliente.getNome());
        comando.setString(2, cliente.getEmail());
        comando.setString(3, cliente.getTelefone());
        comando.executeUpdate();

    }

    public List<Cliente> buscarTodos() throws SQLException{
        var lista = new ArrayList<Cliente>();

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

        return lista;
    }
}
