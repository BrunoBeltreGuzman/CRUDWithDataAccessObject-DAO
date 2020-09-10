/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Conexion;
import Modelo.ModelTable;
import Vista.Ventana;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class IEstudiantesImpl extends Conexion implements IEstudiantes<Estudiante> {

    
    private Ventana ventana;
    private Estudiante estudiante;
    private ModelTable modelTable;
            

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    private DefaultTableModel modelo;
    private int cantidadDeCulumnas;
    private int cantidadDeRegistros;

    public IEstudiantesImpl(Ventana ventana, Estudiante estudiante, ModelTable modelTable) {
        this.ventana = ventana;
        this.estudiante = estudiante;
        this.modelTable = modelTable;
    }
    
    public void registrar(Estudiante estudiante) throws Exception {
        super.conectar();
        setDatos(ventana);
        try {
            connection = getConexion();
            String sql = "INSERT INTO Estudiantes (nombre, apellido, edad, curso) Values (?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, estudiante.getNombre());
            preparedStatement.setString(2, estudiante.getApellido());
            preparedStatement.setString(3, estudiante.getEdad());
            preparedStatement.setString(4, estudiante.getCurso());
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos registrados satisfactoriamente ");
            deleteDatos();
            limpiar(ventana);
            modelTable.setDataTable(ventana, estudiante);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.desConectar();
        }
    }

    public void actualizar(Estudiante estudiante) throws Exception {
        super.conectar();
        setDatos(ventana);
        try {

                String sql = "Update Estudiantes set nombre = ?, apellido = ?, edad = ?, "
                        + "curso = ? where id = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, estudiante.getNombre());
                preparedStatement.setString(2, estudiante.getApellido());
                preparedStatement.setString(3, estudiante.getEdad());
                preparedStatement.setString(4, estudiante.getCurso());
                preparedStatement.setString(5, estudiante.getId());
                preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos registrados satisfactoriamente ");
            deleteDatos();
            limpiar(ventana);
            modelTable.setDataTable(ventana, estudiante);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.desConectar();
        }
    }

    public void eliminar(Estudiante estudiante) throws Exception {
        super.conectar();
        setDatos(ventana);
        try {
            String sql = "delete from Estudiantes where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, estudiante.getId());
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos registrados satisfactoriamente ");
            deleteDatos();
            limpiar(ventana);
            modelTable.setDataTable(ventana, estudiante);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.desConectar();
        }
    }

    public List<Estudiante> listar() throws Exception {

        List<Estudiante> ListaEstudiantes = null;

        super.conectar();
        try {
            String sql = "select * from Estudiantes";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            ListaEstudiantes = new ArrayList();
            while (resultSet.next()) {
                Estudiante e = new Estudiante();
                e.setId(resultSet.getString(1));
                e.setNombre(resultSet.getString(2));
                e.setApellido(resultSet.getString(3));
                e.setEdad(resultSet.getString(4));
                e.setCurso(resultSet.getString(5));
                ListaEstudiantes.add(e);
            }
            preparedStatement.close();
            resultSet.close();
            limpiar(ventana);
            deleteDatos();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.desConectar();
        }
        return ListaEstudiantes;
    }

    public void setDatos(Ventana ventana) {
        estudiante.setId(ventana.getTexId().getText().trim().toString());
        estudiante.setNombre(ventana.getTexNombre().getText().trim().toString());
        estudiante.setApellido(ventana.getTexApellido().getText().trim().toString());
        estudiante.setEdad(ventana.getTexEdad().getText().trim().toString());
        estudiante.setCurso(ventana.getTexCurso().getText().trim().toString());
    }

    public void deleteDatos() {
        Estudiante estudiante = new Estudiante();
        estudiante.setId("");
        estudiante.setNombre("");
        estudiante.setApellido("");
        estudiante.setEdad("");
        estudiante.setCurso("");
    }

    public void limpiar(Ventana ventana) {
        ventana.getTexId().setText("");
        ventana.getTexNombre().setText("");
        ventana.getTexApellido().setText("");
        ventana.getTexEdad().setText("");
        ventana.getTexCurso().setText("");
    }

    
}
