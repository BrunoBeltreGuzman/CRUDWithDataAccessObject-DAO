/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import DAO.Estudiante;
import DAO.IEstudiantes;
import DAO.IEstudiantesImpl;
import Vista.Colores;
import Vista.Ventana;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BRUNO BELTRE GUZMAN
 */
public class ModelTable extends Conexion {

    private Ventana ventana;
    private Estudiante estudiante;

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private DefaultTableModel modelo;
    private int cantidadDeCulumnas;
    private int cantidadDeRegistros;

    public ModelTable(Ventana ventana, Estudiante estudiante) {
        this.ventana = ventana;
        this.estudiante = estudiante;
    }

    public void BuscarEstudiante(Estudiante est, Ventana v) {
        cantidadDeRegistros = 0;
        super.conectar();

        String where = "";

        if (!"".equals(v.getTexBuscar().getText().trim())) {

            if (v.getCbBuscar().getSelectedIndex() == 0) {
                where = "WHERE id LIKE '%" + v.getTexBuscar().getText().trim() + "%'";
            }

            if (v.getCbBuscar().getSelectedIndex() == 1) {
                where = "WHERE nombre LIKE '%" + v.getTexBuscar().getText().trim() + "%'";
            }

            if (v.getCbBuscar().getSelectedIndex() == 2) {
                where = "WHERE apellido LIKE '%" + v.getTexBuscar().getText().trim() + "%'";
            }

            if (v.getCbBuscar().getSelectedIndex() == 3) {
                where = "WHERE edad LIKE '%" + v.getTexBuscar().getText().trim() + "%'";
            }
            if (v.getCbBuscar().getSelectedIndex() == 4) {
                where = "WHERE curso LIKE '%" + v.getTexBuscar().getText().trim() + "%'";
            }
        }

        try {
            List<Estudiante> listaEstudiantes = null;
            String sql = "SELECT * FROM Estudiantes " + where;
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            listaEstudiantes = new ArrayList();
            while (resultSet.next()) {
                Estudiante e = new Estudiante();
                e.setId(resultSet.getString(1));
                e.setNombre(resultSet.getString(2));
                e.setApellido(resultSet.getString(3));
                e.setEdad(resultSet.getString(4));
                e.setCurso(resultSet.getString(5));
                listaEstudiantes.add(e);
            }
            preparedStatement.close();
            resultSet.close();

            try {
                setModelTable(ventana);

                for (int i = 0; i < listaEstudiantes.size(); i++) {
                    Object[] row = {
                        listaEstudiantes.get(i).getId(),
                        listaEstudiantes.get(i).getNombre(),
                        listaEstudiantes.get(i).getApellido(),
                        listaEstudiantes.get(i).getEdad(),
                        listaEstudiantes.get(i).getCurso()};
                    modelo.addRow(row);
                    cantidadDeRegistros++;
                }
                ventana.getTexResultado().setText(String.valueOf(cantidadDeCulumnas));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ventana.getTexResultado().setText(ventana.getCbBuscar().getSelectedItem()
                    + ": registros encontrados: " + cantidadDeRegistros);
            limpiar(ventana);
            deleteDatos();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.desConectar();
        }
    }

    public void setModelTable(Ventana ventana) {
        super.conectar();
        try {
            modelo = new DefaultTableModel();
            ventana.getTable().setModel(modelo);

            String sql = "SELECT * FROM Estudiantes";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            ResultSetMetaData rsmd = resultSet.getMetaData();
            cantidadDeCulumnas = rsmd.getColumnCount();

            modelo.addColumn("Id");
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Edad");
            modelo.addColumn("Curso");
            //...

            Colores colores = new Colores();
            ventana.getTable().getTableHeader().setFont(new java.awt.Font("Arial", 0, 16));
            ventana.getTable().getTableHeader().setOpaque(false);
            ventana.getTable().getTableHeader().setBackground(colores.getColorPrymary());
            ventana.getTable().getTableHeader().setForeground(Color.WHITE);
            ventana.getTable().setRowHeight(18);
            ventana.getTable().setBorder(null);
            ventana.getTable().setFont(new java.awt.Font("Arial", 0, 16));
            ventana.getTable().setForeground(Color.WHITE);
            ventana.getTable().setBackground(colores.getGris1());
            ventana.getTable().setRowHeight(18);
            ventana.getTable().setSelectionBackground(colores.getColorPrymary());
            ventana.getTable().setSelectionForeground(Color.WHITE);

            int[] Ancho = {5, 100, 100, 100, 100};

            for (int x = 0; x < cantidadDeCulumnas; x++) {
                ventana.getTable().getColumnModel().getColumn(x).setPreferredWidth(Ancho[x]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setDataTable(Ventana ventana, Estudiante estudiante) {
        cantidadDeRegistros = 0;
        try {
            setModelTable(ventana);

            List<Estudiante> listaEstudiantes = new ArrayList();
            IEstudiantes<Estudiante> modelEstudiante = new IEstudiantesImpl(ventana, estudiante, this);
            listaEstudiantes = modelEstudiante.listar();

            for (int i = 0; i < listaEstudiantes.size(); i++) {
                Object[] row = {
                    listaEstudiantes.get(i).getId(),
                    listaEstudiantes.get(i).getNombre(),
                    listaEstudiantes.get(i).getApellido(),
                    listaEstudiantes.get(i).getEdad(),
                    listaEstudiantes.get(i).getCurso()};
                modelo.addRow(row);
                cantidadDeRegistros++;
            }
            ventana.getTexResultado().setText(ventana.getCbBuscar().getSelectedItem()
                    + ": registros encontrados: " + cantidadDeRegistros);
            limpiar(ventana);
            deleteDatos();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void MouseClickedTabla(Ventana v) {
        cantidadDeRegistros = 0;
        ResultSet rs = null;
        super.conectar();
        try {

            int fila = v.getTable().getSelectedRow();
            String codigo = v.getTable().getValueAt(fila, 0).toString();

            preparedStatement = connection.prepareStatement("SELECT * FROM Estudiantes WHERE id = ?");
            preparedStatement.setString(1, codigo);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                v.getTexId().setText(rs.getString(1));
                v.getTexNombre().setText(rs.getString(2));
                v.getTexApellido().setText(rs.getString(3));
                v.getTexEdad().setText(rs.getString(4));
                v.getTexCurso().setText(rs.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.desConectar();
        }
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
