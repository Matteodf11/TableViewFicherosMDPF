/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tableviewmdpf;

/**
 *
 * @author matdipfor
 */
public class Residencia {

    private String ciudad;
    private String provincia;

    public Residencia(String ciudad, String provincia) {
        this.ciudad = ciudad;
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return getCiudad() + " (" + getProvincia() + ") ";
    }

}
