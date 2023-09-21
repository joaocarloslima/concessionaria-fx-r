package com.example.data;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Cliente;
import com.example.model.Veiculo;

public class VeiculoDao {

    static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    static final String USER = "pf1389";
    static final String PASS = "fiap23";

    public static void inserir(Veiculo veiculo) throws SQLException {
        var conexao = DriverManager.getConnection(URL, USER, PASS);

        var sql = "INSERT INTO veiculos (marca, modelo, ano, valor, cliente_id) VALUES (?, ?, ?, ?, ?) ";
        var comando = conexao.prepareStatement(sql);
        comando.setString(1,veiculo.getMarca());
        comando.setString(2, veiculo.getModelo());
        comando.setInt(3, veiculo.getAno());
        comando.setBigDecimal(4, veiculo.getValor());
        comando.setLong(5, veiculo.getCliente().getId());
        comando.executeUpdate();

        conexao.close();

    }

    public static List<Veiculo> buscarTodos() throws SQLException{
        var lista = new ArrayList<Veiculo>();

        var conexao = DriverManager.getConnection(URL, USER, PASS);
        var comando = conexao.prepareStatement("SELECT veiculos.*, clientes.nome, clientes.email, clientes.telefone FROM veiculos INNER JOIN clientes ON veiculos.cliente_id=clientes.id");
        var resultado = comando.executeQuery();

        while(resultado.next()){
            lista.add (new Veiculo(
                resultado.getLong("id"), 
                resultado.getString("marca"), 
                resultado.getString("modelo"), 
                resultado.getInt("ano"), 
                resultado.getBigDecimal("valor"),
                new Cliente(
                    resultado.getLong("cliente_id"),
                    resultado.getString("nome"),
                    resultado.getString("email"),
                    resultado.getString("telefone")
                )
            ));
        }

        conexao.close();
        return lista;
    }

    public static void apagar(Long id) throws SQLException{
        var conexao = DriverManager.getConnection(URL, USER, PASS);
        var comando = conexao.prepareStatement("DELETE FROM veiculos WHERE id=?");
        comando.setLong(1, id);
        comando.executeUpdate();
        conexao.close();
    }

    public static void atualizar(Veiculo veiculo) throws SQLException{
        var conexao = DriverManager.getConnection(URL, USER, PASS);
        var comando = conexao.prepareStatement("UPDATE veiculos SET marca=?, modelo=?, ano=?, valor=? WHERE id=?");
        comando.setString(1, veiculo.getMarca());
        comando.setString(2, veiculo.getModelo());
        comando.setInt(3, veiculo.getAno());
        comando.setBigDecimal(4, veiculo.getValor());
        comando.setLong(5, veiculo.getId());
        comando.executeUpdate();
        conexao.close();
    }
}
