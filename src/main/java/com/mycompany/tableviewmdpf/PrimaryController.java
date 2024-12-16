package com.mycompany.tableviewmdpf;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {

    @FXML
    private Button botonverdatos;
    @FXML
    private Button botonañadir;
    @FXML
    private Button botonborrar;
    @FXML
    private Button botonmodificar;

    private ObservableList<Persona> datosObservableList;

    @FXML
    private TableView<Persona> vistaTabla;
    @FXML
    private TableColumn<Persona, String> nombrecolumna;
    @FXML
    private TableColumn<Persona, String> apellidoscolumna;
    @FXML
    private TableColumn<Persona, Residencia> residenciacolumna;
    @FXML
    private TableColumn<Persona, String> imagencolumna;
    @FXML
    private Button botonguardarcambios;
    @FXML
    private ImageView imagenconjunta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        vistaTabla.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                String imagePath = newSelection.getImagen();
                if (imagePath != null && !imagePath.isEmpty()) {
                    File imageFile = new File(imagePath);
                    
                        String fileLocation = imageFile.toURI().toString();
                        Image image = new Image(fileLocation);
                        imagenconjunta.setImage(image);
                    
                }
            }
        });

        ArrayList<Persona> datosArrayList = new ArrayList<>();
        datosArrayList = Auxiliar.leerPersonas("Personas.txt");

        nombrecolumna.setCellValueFactory(new PropertyValueFactory<Persona, String>("Nombre"));
        apellidoscolumna.setCellValueFactory(new PropertyValueFactory<Persona, String>("Apellidos"));
        residenciacolumna.setCellValueFactory(cellData -> cellData.getValue().residenciaProperty());
        residenciacolumna.setCellFactory(v -> {
            return new TableCell<Persona, Residencia>() {
                @Override
                protected void updateItem(Residencia item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.getCiudad() + " --> " + item.getProvincia());
                    }
                }
            };
        });
        imagencolumna.setCellValueFactory(cellData -> cellData.getValue().pathImagenProperty());
        imagencolumna.setCellFactory(columna -> {
            return new TableCell<Persona, String>() {
                private ImageView view = new ImageView();

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        File imageFile = new File(item);
                        String fileLocation = imageFile.toURI().toString();
                        Image image = new Image(fileLocation, 80, 80, true, true);
                        view.setImage(image);
                        setGraphic(view);
                    }
                }
            };
        });

        //nombreColumna.setCellValueFactory(cellData -> cellData.getValue().NombreProperty());
        //apellidoColumna.setCellValueFactory(cellData -> cellData.getValue().ApellidosProperty());
        datosObservableList = FXCollections.observableArrayList(datosArrayList);
        vistaTabla.setItems(datosObservableList);
    }

    @FXML
    private void verdatosAction(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getClassLoader().getResource("com/mycompany/tableviewmdpf/datospersona.fxml"));
        Parent root = miCargador.load();
        DatospersonaController controladorpersona = miCargador.<DatospersonaController>getController();
        Persona persona = vistaTabla.getSelectionModel().getSelectedItem();
        if (persona == null) {
            return;
        }

        controladorpersona.initPersona(persona);
        Scene scene = new Scene(root, 500, 300);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Ver Datos Persona");

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void borrarAction(ActionEvent event) {
        datosObservableList.remove(vistaTabla.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void añadirmodificarAction(ActionEvent event) throws IOException {
        Button clickedButton = (Button) event.getSource();

        FXMLLoader miCargador = new FXMLLoader(getClass().getClassLoader().getResource("com/mycompany/tableviewmdpf/AñadirModificar.fxml"));
        Parent root = miCargador.load();

        AñadirModificarController controladorPersona = miCargador.<AñadirModificarController>getController();

        Persona persona;
        Residencia residencia = new Residencia("", "");
        if (clickedButton.getId().equals("botonañadir")) {
            persona = new Persona("", "", residencia, "");
            controladorPersona.initPersona(persona);
            controladorPersona.setAccion(AñadirModificarController.ACCION_AÑADIR);

        } else {
            persona = vistaTabla.getSelectionModel().getSelectedItem();
            if (persona == null) {
                return;
            }
            controladorPersona.initPersona(persona);
            controladorPersona.setAccion(AñadirModificarController.ACCION_MODIFICAR);

        }

        Stage stage = new Stage();
        if (clickedButton.getId().equals("botonañadir")) {
            stage.setTitle("Añadir Persona");
        } else {
            stage.setTitle("Modificar Persona");
        }
        stage.setScene(new Scene(root, 500, 300));

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        if (!controladorPersona.getCancelar()) {
            if (controladorPersona.getAccion() == AñadirModificarController.ACCION_AÑADIR) {
                if (!controladorPersona.getPersona().getNombre().isEmpty() && !controladorPersona.getPersona().getApellidos().isEmpty() && !controladorPersona.getPersona().getResidencia().getValue().getCiudad().isEmpty() && !controladorPersona.getPersona().getResidencia().getValue().getProvincia().isEmpty()) {
                    datosObservableList.add(controladorPersona.getPersona());
                }
            } else if (controladorPersona.getAccion() == AñadirModificarController.ACCION_MODIFICAR) {
                int indice = datosObservableList.indexOf(persona);
                Persona p = controladorPersona.getPersona();
                datosObservableList.set(indice, p);
            }
        }
    }

    @FXML
    private void guardarcambiosaction(ActionEvent event) {
        Auxiliar.guardarPersonas(datosObservableList);
        Platform.exit();
    }
}
