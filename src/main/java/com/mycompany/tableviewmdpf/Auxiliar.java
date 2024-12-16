/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tableviewmdpf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author matdipfor
 */
public class Auxiliar {

    public static ArrayList<Persona> leerPersonas(String filePath) {
        ArrayList<Persona> personas = new ArrayList<>();
        
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] datos = line.split(",");
                if (datos.length == 5) {
                    String nombre = datos[0];
                    String apellido = datos[1];
                   String imagen = datos[4];
                    personas.add(new Persona(nombre, apellido, new Residencia(datos[2], datos[3]), imagen));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return personas;
    }

    public static void guardarPersonas(List<Persona> personas) {
        

        String archivo = "Personas.txt";
        try {
            FileWriter fw = new FileWriter(archivo);
            for (Persona persona : personas) {
                fw.write(persona.getNombre() + "," + persona.getApellidos() + "," + persona.getResidencia().getValue().getCiudad() + ","
                        + persona.getResidencia().getValue().getProvincia() + "," + persona.getImagen());
                fw.write("\n"); // escribimos nueva línea
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
