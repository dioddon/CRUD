/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;


/**
 *
 * @author merad
 */
/**
 * EstudiantesDAO is a Data Access Object (DAO) class responsible for managing
 * CRUD operations related to the "estudiantes" (students) table in the
 * database.
 * It provides methods to list, insert, update, and delete student records,
 * as well as to populate UI components with data from related tables such as
 * "sexo".
 *
 * Main responsibilities:
 * <ul>
 * <li>Retrieve and display available "sexo" (gender) options in a
 * ComboBox.</li>
 * <li>List all students along with their associated gender information.</li>
 * <li>Insert new student records into the database.</li>
 * <li>Update existing student records.</li>
 * <li>Delete student records by ID.</li>
 * <li>Display alerts for operation results and errors.</li>
 * </ul>
 *
 * Dependencies:
 * <ul>
 * <li>Conexion: Handles database connection management.</li>
 * <li>Estudiante: Model class representing a student entity.</li>
 * <li>JavaFX components: ComboBox, ObservableList, Alert, etc.</li>
 * </ul>
 *
 * Note: This class uses JavaFX for UI feedback and expects the database schema
 * to include "estudiantes" and "sexo" tables with appropriate fields.
 */
public class EstudiantesDAO {

    public void mostrarSexoCombo(ComboBox<String> comboSexo) {
        Conexion objCon = new Conexion();
        comboSexo.getItems().clear();
        comboSexo.setValue("Seleccione sexo");
        String sql = "SELECT * FROM sexo;";
        try (
                Connection cn = objCon.estableceConexion();
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int idSexo = rs.getInt("id");
                String nombreSexo = rs.getString("sexo");
                comboSexo.getItems().add(nombreSexo);
                comboSexo.getProperties().put(nombreSexo, idSexo);
            }
        } catch (Exception e) {
            showAlert("ERROR", "Error al mostrar sexos: " + e);
        } finally {
            objCon.cerrarConexion();
        }
    }

    public ObservableList<Estudiante> listarUsuarios() {
        ObservableList<Estudiante> lista = FXCollections.observableArrayList();
        String sql = "SELECT u.id, u.nombre, u.apellidos, u.id_sexo, s.sexo FROM estudiantes u JOIN sexo s ON u.id_sexo = s.id;";
        Conexion objCon = new Conexion();
        try (
                Connection cn = objCon.estableceConexion();
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getInt("id_sexo"),
                        rs.getString("sexo")));
            }
        } catch (Exception e) {
            showAlert("ERROR", "Error al listar usuarios: " + e);
        } finally {
            objCon.cerrarConexion();
        }
        return lista;
    }

    public void insertarUsuario(String nombre, String apellidos, int fksexo) {
        String sql = "INSERT INTO estudiantes(nombre, apellidos, id_sexo) VALUES(?, ?, ?);";
        Conexion objCon = new Conexion();
        try (
                Connection cn = objCon.estableceConexion();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setInt(3, fksexo);
            ps.executeUpdate();
            showAlert("OK", "Usuario guardado");
        } catch (Exception e) {
            showAlert("ERROR", "Error al insertar usuario: " + e);
        } finally {
            objCon.cerrarConexion();
        }
    }

    public void actualizarUsuario(int id, String nombre, String apellidos, int fksexo) {
        String sql = "UPDATE estudiantes SET nombre=?, apellidos=?, id_sexo=? WHERE id=?;";
        Conexion objCon = new Conexion();
        try (
                Connection cn = objCon.estableceConexion();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setInt(3, fksexo);
            ps.setInt(4, id);
            ps.executeUpdate();
            showAlert("OK", "Usuario actualizado");
        } catch (Exception e) {
            showAlert("ERROR", "Error al actualizar usuario: " + e);
        } finally {
            objCon.cerrarConexion();
        }
    }

    public void eliminarUsuario(int id) {
        String sql = "DELETE FROM estudiantes WHERE id=?;";
        Conexion objCon = new Conexion();
        try (
                Connection cn = objCon.estableceConexion();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            showAlert("OK", "Usuario eliminado");
        } catch (Exception e) {
            showAlert("ERROR", "Error al eliminar usuario: " + e);
        } finally {
            objCon.cerrarConexion();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
