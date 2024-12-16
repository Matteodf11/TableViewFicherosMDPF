/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tableviewmdpf;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author matdipfor
 */
public class Persona {

    private final StringProperty Nombre = new SimpleStringProperty();
    private final StringProperty Apellidos = new SimpleStringProperty();
    private ObjectProperty<Residencia> residencia = new SimpleObjectProperty<>();
    private final StringProperty pathImagen = new SimpleStringProperty();

    public Persona(String nombre, String apellidos, Residencia residencia, String imagen) {
        this.Nombre.setValue(nombre);
        this.Apellidos.setValue(apellidos);
        this.residencia.setValue(residencia);
        this.pathImagen.setValue(imagen);
    }

    public final StringProperty NombreProperty() {
        return this.Nombre;
    }

    public final String getNombre() {
        return this.NombreProperty().get();
    }

    public final void setNombre(final java.lang.String Nombre) {
        this.NombreProperty().set(Nombre);
    }

    public final StringProperty ApellidosProperty() {
        return this.Apellidos;
    }

    public final String getApellidos() {
        return this.ApellidosProperty().get();
    }

    public final void setApellidos(final java.lang.String Apellidos) {
        this.ApellidosProperty().set(Apellidos);
    }

    public final ObjectProperty residenciaProperty() {
        return this.residencia;
    }

    public ObjectProperty<Residencia> getResidencia() {
        return residencia;
    }

    public void setResidencia(ObjectProperty<Residencia> residencia) {
        this.residencia = residencia;
    }

    public final StringProperty pathImagenProperty() {
        return this.pathImagen;
    }

    public StringProperty getPathImagen() {
        return pathImagen;
    }

    public void setPathImagen(String pathImagen) {
        this.pathImagenProperty().set(pathImagen);
    }
    
      public final String getImagen() {
        return this.pathImagenProperty().get();
    }

    @Override
    public String toString() {
        return "Persona{" + "Nombre=" + Nombre + ", Apellidos=" + Apellidos + ", residencia=" + residencia + ", pathImagen=" + pathImagen + '}';
    }
    
    
}
