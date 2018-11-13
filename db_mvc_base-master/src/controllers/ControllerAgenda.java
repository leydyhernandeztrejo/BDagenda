/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.ModelAgenda;
import views.ViewAgenda;

/**
 *
 * @author Zeo
 */
public class ControllerAgenda {

    ModelAgenda modelAgenda;
    ViewAgenda viewAgenda;

    /**
     * Objeto de tipo ActionListener para atrapar los eventos actionPerformed y
     * llamar a los metodos para ver los registros almacenados en la bd.
     */
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewAgenda.jbtn_primero) {
                jbtn_primero_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_anterior) {
                jbtn_anterior_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_siguiente) {
                jbtn_siguiente_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_ultimo) {
                jbtn_ultimo_actionPerformed();
            }else if (e.getSource() == viewAgenda.jbtn_nuevo) {
                limpiar();
            }else if (e.getSource() == viewAgenda.jbtn_insertar) {
                jbtn_insertar();
                jbtn_primero_actionPerformed();
            }else if (e.getSource() == viewAgenda.jbtn_modificar) {
                jbtn_guardarCambio();
            }else if (e.getSource() == viewAgenda.jbtn_eliminar) {
                jbtn_eliminar();
    
            }
        }
    };

    /**
     * Constructor de Controlador para unir el ModelAgenda y ViewAgenda
     *
     * @param modelAgenda objeto de tipo ModelAgenda
     * @param viewAgenda objeto de tipo ViewAgenda
     */
    public ControllerAgenda(ModelAgenda modelAgenda, ViewAgenda viewAgenda) {
        this.modelAgenda = modelAgenda;
        this.viewAgenda = viewAgenda;
        initComponents();
        setActionListener();
        initDB();
    }

    /**
     * Método que llama al método conectarBD del modelo y muestra el nombre y
     * email del primer registro en las cajas de texto de ViewAgenda.
     */
    public void initDB(){
        modelAgenda.conectarDB();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_correo.setText(modelAgenda.getCorreo());
    }
    /**
     * Metodo para inicializar la ViewAgenda
     */
    public void initComponents() {
        viewAgenda.setLocationRelativeTo(null);
        viewAgenda.setTitle("Agenda MVC");
        viewAgenda.setVisible(true);
    }

    /**
     * Método para agregar el actionListener a cada boton de la vista
     */
    public void setActionListener() {
        viewAgenda.jbtn_primero.addActionListener(actionListener);
        viewAgenda.jbtn_anterior.addActionListener(actionListener);
        viewAgenda.jbtn_siguiente.addActionListener(actionListener);
        viewAgenda.jbtn_ultimo.addActionListener(actionListener);
        viewAgenda.jbtn_nuevo.addActionListener(actionListener);
        viewAgenda.jbtn_insertar.addActionListener(actionListener);
        viewAgenda.jbtn_modificar.addActionListener(actionListener);
        viewAgenda.jbtn_eliminar.addActionListener(actionListener);
    }

    /**
     * Método para ver el primer registro de la tabla contactos
     */
    private void jbtn_primero_actionPerformed() {
        modelAgenda.moverPrimerRegistro();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_correo.setText(modelAgenda.getCorreo());
        System.out.println("Action del boton jbtn_primero");
        //invocar al metodo moverPrimerRegistro
        //mostrar nombre en la vista
        //mostar email en la vista
    }

    /**
     * Método para ver el registro anterior de la tabla contactos
     */
    private void jbtn_anterior_actionPerformed() {
        modelAgenda.moverAnteriorRegistro();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_correo.setText(modelAgenda.getCorreo());
        System.out.println("Action del boton jbtn_anterior");
        //invocar al metodo moverAnteriorRegistro
        //mostrar nombre en la vista
        //mostar email en la vista
    }

    /**
     * Método para ver el último registro de la tabla contactos
     */
    private void jbtn_ultimo_actionPerformed() {
        modelAgenda.moverUltimoRegistro();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_correo.setText(modelAgenda.getCorreo());
        System.out.println("Action del boton jbtn_ultimo");
        //invocar al metodo moverUltimoRegistro
        //mostrar nombre en la vista
        //mostar email en la vista
    }

    /**
     * Método para ver el siguiente registro de la tabla contactos
     */
    private void jbtn_siguiente_actionPerformed() {
        modelAgenda.moverSiguienteRegistro();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_correo.setText(modelAgenda.getCorreo());
        System.out.println("Action del boton jbtn_siguiente");
        //invocar al metodo moverSiguienteRegistro
        //mostrar nombre en la vista
        //mostar email en la vista
    }
    public void limpiar(){
        viewAgenda.jtf_nombre.setText("");
        viewAgenda.jtf_correo.setText("");
    }
    private void jbtn_insertar(){
        modelAgenda.setNombre(viewAgenda.jtf_nombre.getText());
        modelAgenda.setCorreo(viewAgenda.jtf_correo.getText()); 
        modelAgenda.guardarRegistro();
    }
    private void jbtn_guardarCambio(){
        modelAgenda.setNombre(viewAgenda.jtf_nombre.getText());
        modelAgenda.setCorreo(viewAgenda.jtf_correo.getText()); 
        modelAgenda.guardarCambios();
        modelAgenda.conectarDB();
    }
    private void jbtn_eliminar(){
        modelAgenda.setNombre(viewAgenda.jtf_nombre.getText());
        modelAgenda.setCorreo(viewAgenda.jtf_correo.getText()); 
        modelAgenda.borrarRegistro();
        modelAgenda.conectarDB();
    }
}
