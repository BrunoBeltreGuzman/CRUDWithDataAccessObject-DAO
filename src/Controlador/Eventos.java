/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Estudiante;
import Modelo.ModelTable;
import Vista.Ventana;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 *
 * @author BRUNO BELTRE GUZMAN
 */
public class Eventos implements MouseListener, KeyListener {

    private Ventana ventana;
    private Estudiante estudiante;
   
    Color color = new Color(255, 50, 21);
    
    public Eventos(Ventana ventana) {

        this.ventana = ventana;

        ventana.getBtnBruscar().addMouseListener(this);
        ventana.getBtnRegistrar().addMouseListener(this);
        ventana.getBtnModificar().addMouseListener(this);
        ventana.getBtnEliminar().addMouseListener(this);
        ventana.getBtnLimpiar().addMouseListener(this);
        
        ventana.getTexBuscar().addKeyListener(this);
        ventana.getTexApellido().addKeyListener(this);
        ventana.getTexEdad().addKeyListener(this);
        ventana.getTexCurso().addKeyListener(this);
        ventana.getTexNombre().addKeyListener(this);
        
        ventana.getTable().addMouseListener(this);
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == ventana.getTable()) {
                    ModelTable  modelTable = new ModelTable(ventana, estudiante);
            modelTable.MouseClickedTabla(ventana);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if (e.getSource() == ventana.getBtnBruscar()) {
            ventana.getBtnBruscar().setBackground(color.brighter());
        }
        if (e.getSource() == ventana.getBtnRegistrar()) {
            ventana.getBtnRegistrar().setBackground(color.brighter());
        }
        if (e.getSource() == ventana.getBtnModificar()) {
            ventana.getBtnModificar().setBackground(color.brighter());
        }
        if (e.getSource() == ventana.getBtnEliminar()) {
            ventana.getBtnEliminar().setBackground(color.brighter());
        }
        if (e.getSource() == ventana.getBtnLimpiar()) {
            ventana.getBtnLimpiar().setBackground(color.brighter());
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == ventana.getBtnBruscar()) {
            ventana.getBtnBruscar().setBackground(color);
        }
        if (e.getSource() == ventana.getBtnRegistrar()) {
            ventana.getBtnRegistrar().setBackground(color);
        }
        if (e.getSource() == ventana.getBtnEliminar()) {
            ventana.getBtnEliminar().setBackground(color);
        }
        if (e.getSource() == ventana.getBtnModificar()) {
            ventana.getBtnModificar().setBackground(color);
        }
        if (e.getSource() == ventana.getBtnLimpiar()) {
            ventana.getBtnLimpiar().setBackground(color);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        if (e.getSource() == ventana.getTexBuscar()) {
            ModelTable  modelTable = new ModelTable(ventana, estudiante);
            modelTable.BuscarEstudiante(estudiante, ventana);
           
            if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
                   ventana.getBtnBruscar().doClick();
            }
        }
        
        if(e.getSource() == ventana.getTexNombre()){
            if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
                  ventana.getTexApellido().requestFocus();                  
            }
            
        }
         if(e.getSource() == ventana.getTexApellido()){
            if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
                  ventana.getTexEdad().requestFocus();                  
            }
            
        }
          if(e.getSource() == ventana.getTexEdad()){
            if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
                  ventana.getTexCurso().requestFocus();                  
            }
            
        }
    }

}
