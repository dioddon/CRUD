/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.crud;

import Clases.Estudiante;
import Clases.EstudiantesDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author merad
 */
/**
 * Controlador JavaFX para la gestión de estudiantes en una aplicación CRUD.
 * 
 * Esta clase administra la interfaz gráfica, permitiendo crear, leer, actualizar y eliminar registros de estudiantes.
 * Utiliza un TableView para mostrar los estudiantes y campos de texto para editar sus datos.
 * 
 * Funcionalidades principales:
 * <ul>
 *   <li>Inicializa los componentes de la interfaz y carga los datos desde la base de datos.</li>
 *   <li>Permite agregar, editar y eliminar estudiantes mediante métodos anotados con {@code @FXML}.</li>
 *   <li>Sincroniza la selección del TableView con los campos de edición.</li>
 *   <li>Utiliza un ComboBox para seleccionar el sexo del estudiante.</li>
 * </ul>
 * 
 * Dependencias:
 * <ul>
 *   <li>{@link EstudiantesDAO}: Acceso a datos de estudiantes.</li>
 *   <li>{@link Estudiante}: Modelo de datos del estudiante.</li>
 * </ul>
 * 
 * Campos principales:
 * <ul>
 *   <li>{@code tfId}, {@code tfNombre}, {@code tfApellidos}: Campos de texto para los datos del estudiante.</li>
 *   <li>{@code cbsexo}: ComboBox para seleccionar el sexo.</li>
 *   <li>{@code tvUsuarios}: TableView para mostrar la lista de estudiantes.</li>
 * </ul>
 * 
 * Métodos principales:
 * <ul>
 *   <li>{@code initialize}: Inicializa la interfaz y carga los datos.</li>
 *   <li>{@code guardarUsuario}: Inserta un nuevo estudiante.</li>
 *   <li>{@code editarUsuario}: Actualiza los datos de un estudiante existente.</li>
 *   <li>{@code borrarUsuario}: Elimina un estudiante seleccionado.</li>
 *   <li>{@code recargarTabla}: Refresca los datos mostrados en la tabla.</li>
 *   <li>{@code limpiarCampos}: Limpia los campos de entrada y la selección.</li>
 * </ul>
 * 
 * @author TuNombre
 */
public class PrincipalFXController implements Initializable {

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidos;
    @FXML
    private ComboBox<String> cbsexo;

    @FXML
    private TableView<Estudiante> tvUsuarios;
    @FXML
    private TableColumn<Estudiante, Integer> colId;
    @FXML
    private TableColumn<Estudiante, String> colNombre;
    @FXML
    private TableColumn<Estudiante, String> colApellidos;
    @FXML
    private TableColumn<Estudiante, String> colSexo;

    private final EstudiantesDAO dao = new EstudiantesDAO();
    private ObservableList<Estudiante> data;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dao.mostrarSexoCombo(cbsexo);

        data = dao.listarUsuarios();
        tvUsuarios.setItems(data);

        colId.setCellValueFactory(c -> c.getValue().idProperty().asObject());
        colNombre.setCellValueFactory(c -> c.getValue().nombreProperty());
        colApellidos.setCellValueFactory(c -> c.getValue().apellidosProperty());
        colSexo.setCellValueFactory(c -> c.getValue().sexoProperty());

        tvUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                tfId.setText(String.valueOf(newSel.getId()));
                tfNombre.setText(newSel.getNombre());
                tfApellidos.setText(newSel.getApellidos());
                cbsexo.setValue(newSel.getSexo());
            }
        });
    }

    @FXML
    private void guardarUsuario() {
        String nom = tfNombre.getText();
        String ape = tfApellidos.getText();
        int fk = (int) cbsexo.getProperties().get(cbsexo.getValue());
        dao.insertarUsuario(nom, ape, fk);
        recargarTabla();
        limpiarCampos();
    }

    @FXML
    private void editarUsuario() {
        if (tfId.getText().isEmpty()) {
            return;
        }
        int id = Integer.parseInt(tfId.getText());
        String nom = tfNombre.getText();
        String ape = tfApellidos.getText();
        int fk = (int) cbsexo.getProperties().get(cbsexo.getValue());
        dao.actualizarUsuario(id, nom, ape, fk);
        recargarTabla();
        limpiarCampos();
    }

    @FXML
    private void borrarUsuario() {
        if (tfId.getText().isEmpty()) {
            return;
        }
        int id = Integer.parseInt(tfId.getText());
        dao.eliminarUsuario(id);
        recargarTabla();
        limpiarCampos();
    }

    private void recargarTabla() {
        data.setAll(dao.listarUsuarios());
    }

    private void limpiarCampos() {
        tfId.clear();
        tfNombre.clear();
        tfApellidos.clear();
        cbsexo.setValue("Seleccione sexo");
        tvUsuarios.getSelectionModel().clearSelection();
    }
}
