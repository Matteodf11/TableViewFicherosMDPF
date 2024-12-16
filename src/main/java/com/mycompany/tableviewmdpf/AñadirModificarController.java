/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tableviewmdpf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Matteo
 */
public class AñadirModificarController {
    
    public static final int ACCION_AÑADIR = 1;
    public static final int ACCION_MODIFICAR = 2;
    
    @FXML
    private TextField nombrepersona;
    @FXML
    private TextField apellidopersona;
    @FXML
    private Button salvar;
    @FXML
    private Button buttoncancel;
    
    private Persona persona;
    private Residencia residencia;
    private int accion;
    private boolean cancelar = false;
    @FXML
    private TextField ciudadpersona;
    @FXML
    private TextField provinciapersona;
    @FXML
    private TextField imagenpersona;
    
    @FXML
    private void salvarAction(ActionEvent event) {
        persona.setNombre(nombrepersona.getText());
        persona.setApellidos(apellidopersona.getText());
        residencia.setCiudad(ciudadpersona.getText());
        residencia.setProvincia(provinciapersona.getText());
        persona.setPathImagen(imagenpersona.getText());
        cancelar = false;
        Stage stage = (Stage) salvar.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void cancelarAction(ActionEvent event) {
        cancelar = true;
        Stage stage = (Stage) buttoncancel.getScene().getWindow();
        stage.close();
    }
    
    public void initPersona(Persona persona) {
        this.persona = persona;
        this.residencia = persona.getResidencia().getValue();
        nombrepersona.setText(persona.getNombre());
        apellidopersona.setText(persona.getApellidos());
        ciudadpersona.setText(residencia.getCiudad());
        provinciapersona.setText(residencia.getProvincia());
        imagenpersona.setText(persona.getImagen());
    }
    
    public Persona getPersona() {
        return persona;
    }
    
    public void setAccion(int accion) {
        this.accion = accion;
    }
    
    public int getAccion() {
        return accion;
    }
    
    public boolean getCancelar() {
        return cancelar;
    }
}
