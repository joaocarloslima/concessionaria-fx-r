package com.example;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.data.ClienteDao;
import com.example.data.VeiculoDao;
import com.example.model.Cliente;
import com.example.model.Veiculo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController implements Initializable {

    @FXML TextField txtMarca;
    @FXML TextField txtModelo;
    @FXML TextField txtAno;
    @FXML TextField txtValor;

    @FXML TableView<Veiculo> tabelaVeiculo;

    @FXML TableColumn<Veiculo, String> colMarca;
    @FXML TableColumn<Veiculo, String> colModelo;
    @FXML TableColumn<Veiculo, Integer> colAno;
    @FXML TableColumn<Veiculo, BigDecimal> colValor;

    @FXML TextField txtNome;
    @FXML TextField txtEmail;
    @FXML TextField txtTelefone;

    @FXML TableView<Cliente> tabelaCliente;

    @FXML TableColumn<Cliente, String> colNome;
    @FXML TableColumn<Cliente, String> colEmail;
    @FXML TableColumn<Cliente, String> colTelefone;

    public void adicionarVeiculo(){
        var veiculo = new Veiculo(
            null, 
            txtMarca.getText(), 
            txtModelo.getText(), 
            Integer.valueOf( txtAno.getText() ), 
            new BigDecimal( txtValor.getText() )
        );

        try{
            VeiculoDao.inserir(veiculo);
            tabelaVeiculo.getItems().add(veiculo);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void carregarVeiculos(){
        tabelaVeiculo.getItems().clear();
        try {
            var veiculos = VeiculoDao.buscarTodos();
            veiculos.forEach(veiculo -> tabelaVeiculo.getItems().add(veiculo));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adicionarCliente(){
        var cliente = new Cliente(
            null, 
            txtNome.getText(), 
            txtEmail.getText(), 
            txtTelefone.getText()
        );

        try{
            ClienteDao.inserir(cliente);
            tabelaCliente.getItems().add(cliente);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void carregarClientes(){
        tabelaCliente.getItems().clear();
        try {
            var clientes = ClienteDao.buscarTodos();
            clientes.forEach(cliente -> tabelaCliente.getItems().add(cliente));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        carregarVeiculos();
        carregarClientes();
    }

   
}
