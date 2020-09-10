/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.Estudiante;
import java.util.List;

/**
 *
 * @author diosl
 * @param <Estudiante>
 */
public interface IEstudiantes<Estudiante> {
    public void registrar(Estudiante estudiante) throws Exception;
    public void actualizar(Estudiante estudiante) throws Exception;
    public void eliminar(Estudiante estudiante) throws Exception;
    public List<Estudiante> listar() throws Exception;
}
