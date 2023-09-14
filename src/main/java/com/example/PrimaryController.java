package com.example;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.data.VeiculoDao;
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

    @FXML TableView<Veiculo> tabela;

    @FXML TableColumn<Veiculo, String> colMarca;
    @FXML TableColumn<Veiculo, String> colModelo;
    @FXML TableColumn<Veiculo, Integer> colAno;
    @FXML TableColumn<Veiculo, BigDecimal> colValor;

    public void adicionar(){
        var veiculo = new Veiculo(
            null, 
            txtMarca.getText(), 
            txtModelo.getText(), 
            Integer.valueOf( txtAno.getText() ), 
            new BigDecimal( txtValor.getText() )
        );

        try{
            VeiculoDao.inserir(veiculo);
            tabela.getItems().add(veiculo);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void carregar(){
        tabela.getItems().clear();
        try {
            var veiculos = VeiculoDao.buscarTodos();
            veiculos.forEach(veiculo -> tabela.getItems().add(veiculo));
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

        carregar();
    }

   
}
