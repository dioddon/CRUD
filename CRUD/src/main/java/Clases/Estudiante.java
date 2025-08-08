/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author merad
 */
/**
 * La clase {@code Estudiante} representa un estudiante con propiedades observables para su uso en aplicaciones JavaFX.
 * Incluye información como el identificador, nombre, apellidos, identificador de sexo y descripción del sexo.
 * 
 * <p>
 * Cada campo está implementado como una propiedad observable de JavaFX, permitiendo la vinculación de datos en interfaces gráficas.
 * </p>
 * 
 * <ul>
 *   <li>{@code id}: Identificador único del estudiante.</li>
 *   <li>{@code nombre}: Nombre del estudiante.</li>
 *   <li>{@code apellidos}: Apellidos del estudiante.</li>
 *   <li>{@code id_sexo}: Identificador del sexo del estudiante (clave foránea).</li>
 *   <li>{@code sexo}: Descripción textual del sexo del estudiante.</li>
 * </ul>
 * 
 * <p>
 * Proporciona métodos getter y setter para cada propiedad, así como métodos para acceder a las propiedades observables.
 * </p>
 * 
 * @author TuNombre
 */
public class Estudiante {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty apellidos = new SimpleStringProperty();
    private final IntegerProperty id_sexo = new SimpleIntegerProperty();
    private final StringProperty sexo = new SimpleStringProperty();

    public Estudiante(int id, String nombre, String apellidos, int id_sexo, String sexo) {
        this.id.set(id);
        this.nombre.set(nombre);
        this.apellidos.set(apellidos);
        this.id_sexo.set(id_sexo);
        this.sexo.set(sexo);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int v) {
        id.set(v);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String v) {
        nombre.set(v);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos.get();
    }

    public void setApellidos(String v) {
        apellidos.set(v);
    }

    public StringProperty apellidosProperty() {
        return apellidos;
    }

    public int getFksexo() {
        return id_sexo.get();
    }

    public void setFksexo(int v) {
        id_sexo.set(v);
    }

    public IntegerProperty fksexoProperty() {
        return id_sexo;
    }

    public String getSexo() {
        return sexo.get();
    }

    public void setSexo(String v) {
        sexo.set(v);
    }

    public StringProperty sexoProperty() {
        return sexo;
    }

}
