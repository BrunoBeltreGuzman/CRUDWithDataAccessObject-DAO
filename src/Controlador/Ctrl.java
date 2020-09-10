/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Estudiante;
import DAO.IEstudiantes;
import DAO.IEstudiantesImpl;
import Modelo.ModelTable;
import Vista.Ventana;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/**
 *
 * @author BRUNO BELTRE GUZMAN
 */
public class Ctrl implements ActionListener{

    private Ventana ventana;
    private Estudiante estudiante;
    private IEstudiantes<Estudiante> iEstudiantesImpl;
    private ModelTable modelTable;

    public Ctrl(Estudiante est, IEstudiantesImpl estudiantesImpl, Ventana vent, ModelTable modelTable) {

        this.estudiante = est;
        this.ventana = vent;
        this.iEstudiantesImpl = estudiantesImpl;
        this.modelTable = modelTable;
        
        this.ventana.getBtnRegistrar().addActionListener(this);
        this.ventana.getBtnModificar().addActionListener(this);
        this.ventana.getBtnEliminar().addActionListener(this);
        this.ventana.getBtnBruscar().addActionListener(this);
        this.ventana.getBtnLimpiar().addActionListener(this);

        iniciarVentana();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         * Action Registar
         */
        if (e.getSource() == ventana.getBtnRegistrar()) {
            try {
                iEstudiantesImpl.registrar(estudiante);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        /**
         * Action Mofificar
         */
        if (e.getSource() == ventana.getBtnModificar()) {
            try {
                iEstudiantesImpl.actualizar(estudiante);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        /**
         * Action Eliminar
         */
        if (e.getSource() == ventana.getBtnEliminar()) {
            try {
                iEstudiantesImpl.eliminar(estudiante);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        /**
         * Action Buscar
         */
        if (e.getSource() == ventana.getBtnBruscar()) {
            try {
                modelTable.BuscarEstudiante(estudiante, ventana);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        /**
         * Action Limpiar
         */
        if (e.getSource() == ventana.getBtnLimpiar()) {
            IEstudiantesImpl estudiantesImpl = new IEstudiantesImpl(ventana, estudiante, modelTable);  
            estudiantesImpl.deleteDatos();
            estudiantesImpl.limpiar(ventana);
        }
    }

    public void iniciarVentana() {
        modelTable.setDataTable(ventana, estudiante);
    }

}
