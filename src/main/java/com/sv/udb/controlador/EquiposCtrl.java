/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Equipos;
import com.sv.udb.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JMagoSV
 */
public class EquiposCtrl {
    private final Connection conn;
    
    public EquiposCtrl()
    {
        this.conn = new Conexion().getConn();
    }
    
    public Equipos guar(String nombEqui, String descEqui)
    {
        Equipos resp = null;
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("INSERT INTO equipos VALUES(NULL, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            cmd.setString(1, nombEqui);
            cmd.setString(2, descEqui);
            cmd.executeUpdate();
            ResultSet rs = cmd.getGeneratedKeys();
            if(rs.next()){
               resp = new Equipos(rs.getInt(1), nombEqui, descEqui);
            }
        }
        catch (Exception e)
        {
            System.err.println("Error al guardar Equipos: " + e.getMessage());
        }
        finally
        {
            try
            {
                if(this.conn != null)
                {
                    if(!this.conn.isClosed())
                    {
                        this.conn.close();
                    }
                }
            }
            catch(SQLException e)
            {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return resp;
    }
    
    public boolean modi(int codiEqui, String nombEqui, String descEqui)
    {
        boolean resp = false;
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("UPDATE equipos SET nomb_equi = ?, desc_equi = ? WHERE codi_equi = ?");
            cmd.setString(1, nombEqui);
            cmd.setString(2, descEqui);
            cmd.setInt(3, codiEqui);
            cmd.executeUpdate();
            resp = true;
        }
        catch (Exception e)
        {
            System.err.println("Error al modificar Equipos: " + e.getMessage());
        }
        finally
        {
            try
            {
                if(this.conn != null)
                {
                    if(!this.conn.isClosed())
                    {
                        this.conn.close();
                    }
                }
            }
            catch(SQLException e)
            {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return resp;
    }
    
    public List<Equipos> consTodo()
    {
       List<Equipos> resp = new ArrayList<>();
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM equipos");
            ResultSet rs = cmd.executeQuery();
            while(rs.next())
            {
                resp.add(new Equipos(rs.getInt(1),rs.getString(2), rs.getString(3)));
            }
        }
        catch(SQLException ex)
        {
            System.err.println("Error al consultar Equipos: " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(this.conn != null)
                {
                    if(!this.conn.isClosed())
                    {
                        this.conn.close();
                    }
                }
            }
            catch(SQLException ex)
            {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }
    
    public Equipos cons(int codiEqui)
    {
       Equipos resp = null;
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM equipos WHERE codi_equi = ?");
            cmd.setInt(1, codiEqui);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
                resp = new Equipos(rs.getInt(1),rs.getString(2), rs.getString(3));
            }
        }
        catch(SQLException ex)
        {
            System.err.println("Error al consultar Equipos: " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(this.conn != null)
                {
                    if(!this.conn.isClosed())
                    {
                        this.conn.close();
                    }
                }
            }
            catch(SQLException ex)
            {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }
}
