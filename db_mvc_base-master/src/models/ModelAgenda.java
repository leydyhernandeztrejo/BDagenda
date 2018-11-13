/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import views.ViewAgenda;

/**
 *
 * @author Zeo
 */
public class ModelAgenda {

    private Connection conexion;
    private Statement st;
    private ResultSet rs;

    private String nombre;
    private String correo;
    private int id;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    

    /**
     * Método que realiza las siguietnes acciones:
     * 1.- Conecta con la base agenda_mvc.
     * 2.- Consulta todo los registros de la tabla contactos.
     * 3.- Obtiene el nombre y el email y los guarda en las variables globales
     * nombre y email.
     */
    public void conectarDB() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_mvc", "user_mvc", "pass_mvc.2018");
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM contactos;");
            rs.next();
            nombre = rs.getString("nombre");
            correo = rs.getString("email");
            
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error ModelAgenda 001: " + err.getMessage());
        }

    }
    
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al primer registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverPrimerRegistro(){
        try {
            rs.first();
            nombre = rs.getString("nombre");
            correo = rs.getString("email");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 001" + ex.getMessage());
        }
        System.out.print("Programa accion moverPrimerRegistro");
    }
    
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al siguiente registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverSiguienteRegistro(){
        try {
            if (!rs.isLast()) {
                rs.next();
                nombre = rs.getString("nombre");
                correo = rs.getString("email");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 001" + ex.getMessage());
        }
        System.out.print("Programa accion moverSiguienteRegistro");
    }
    
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al anterior registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverAnteriorRegistro(){
        try {
            if (!rs.isFirst()) {
                rs.previous();
                nombre = rs.getString("nombre");
                correo = rs.getString("email");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 001" + ex.getMessage());
        }
        System.out.print("Programa accion moverAnteriorRegistro");
    }
    
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al ultimo registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverUltimoRegistro(){
        try {
            rs.last();
            nombre = rs.getString("nombre");
            correo = rs.getString("email");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
   
        System.out.print("Programa accion moverUltimoRegistro");
    }
    public void guardarRegistro(){
        try {
            nombre = this.getNombre();
            correo = this.getCorreo();
            st.executeUpdate("INSERT INTO contactos (nombre, email)" + " VALUES ('"+ nombre +"','"+ correo +"');");
            this.conectarDB();
            this.moverUltimoRegistro();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar" + ex.getMessage());
        }
        System.out.print("Programa insertar");
        }
    public void guardarCambios(){
        try {
            id = rs.getInt("id_contacto");
            nombre = this.getNombre();
            correo = this.getCorreo();
            st.executeUpdate("UPDATE contactos SET nombre = '"+ nombre +"', email = '"+ correo +"' WHERE id_contacto = "+ id +"; ");
            this.conectarDB();
            this.moverUltimoRegistro();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar" + ex.getMessage());
        }
        System.out.print("Programa modificar");
    }
    public void borrarRegistro(){
        try {
            id = rs.getInt("id_contacto");
            st.executeUpdate("DELETE FROM contactos WHERE id_contacto = "+ id +"; ");
            this.conectarDB();
            this.moverUltimoRegistro();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al borrar" + ex.getMessage());
        }
        System.out.print("Programa borrar");
    }
}

