/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author merad
 */
import java.sql.Connection;
import java.sql.DriverManager;
import javafx.scene.control.Alert;

/**
 * La clase Conexion gestiona la conexión a una base de datos MySQL.
 * Proporciona métodos para establecer y cerrar la conexión, así como para mostrar alertas informativas.
 * 
 * <p>
 * Utiliza los siguientes parámetros de conexión:
 * <ul>
 *   <li>usuario: Usuario de la base de datos</li>
 *   <li>constrasenia: Contraseña del usuario</li>
 *   <li>db: Nombre de la base de datos</li>
 *   <li>ip: Dirección IP del servidor de base de datos</li>
 *   <li>puerto: Puerto de conexión</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Métodos principales:
 * <ul>
 *   <li>{@link #estableceConexion()} - Establece y retorna la conexión a la base de datos.</li>
 *   <li>{@link #cerrarConexion()} - Cierra la conexión si está abierta.</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Nota: Utiliza la clase Alert de JavaFX para mostrar mensajes informativos al usuario.
 * </p>
 */
public class Conexion {

    Connection conectar = null;

    String usuario = "root";
    String constrasenia = "1234";
    String db = "bdusuarios";
    String ip = "127.0.0.1";
    String puerto = "3306";

    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + db;

    public Connection estableceConexion() {

        try {

            Class.forName("com.mysql.jdbc.Driver");

            conectar = DriverManager.getConnection(cadena, usuario, constrasenia);
            //showAlert("Mensaje", "Se conecto a la base de datos");

        } catch (Exception e) {
            showAlert("Mensaje", "Nos es pudo conectar a la base de datos, error:" + e.toString());
        }

        return conectar;

    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();

    }

    public void cerrarConexion() {
        try {
            if (conectar != null && !conectar.isClosed()) {
                conectar.close();
               // showAlert("Mensaje", "Conexon cerrada");

            }
        } catch (Exception e) {
            
            showAlert("Mensaje", "Error al cerrar la conexion" + e.toString());
            
        }
    }

}
