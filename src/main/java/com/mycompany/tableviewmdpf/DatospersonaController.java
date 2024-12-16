/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tableviewmdpf;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author matdipfor
 */
public class DatospersonaController implements Initializable {

    @FXML
    private TextField nombrepersona;
    @FXML
    private TextField apellidopersona;

    boolean cancelar;
    @FXML
    private TextField ciudadpersona;
    @FXML
    private TextField provinciapersona;
    @FXML
    private TextField rutaimagentextfield;
   

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initPersona(Persona p) {
        nombrepersona.setText(p.getNombre());
        apellidopersona.setText(p.getApellidos());
        ciudadpersona.setText(p.getResidencia().getValue().getCiudad());
        provinciapersona.setText(p.getResidencia().getValue().getProvincia());
        rutaimagentextfield.setText(p.getImagen());
    }

    @FXML
    private void cerraraction(ActionEvent event) {
        Node n = (Node) event.getSource();
        n.getScene().getWindow().hide();

    }

    public boolean getCancelar() {
        return cancelar;
    }

    public void setCancelar(boolean cancelar) {
        this.cancelar = cancelar;
    }

 

   

}
