/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.vistas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.sv.udb.controlador.EquiposCtrl;
import com.sv.udb.modelo.Equipos;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JMagoSV
 */
@WebServlet(name = "EquiposServ", urlPatterns = {"/EquiposServ"})
public class EquiposServ extends HttpServlet {

    private static final long serialVersionUID = 8265873876680656160L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json"); //Modificar el response
        boolean esValido = request.getMethod().equals("POST");
        Map<String, Object> data = new HashMap<>(); //Set de respuestas (Este se usa en los reportes)
        data.put("estaModi", false);
        if(!esValido)
        {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
        else
        {
            String CRUD = request.getParameter("btonEqui");
            if(CRUD.equals("Guardar"))
            {
                Equipos objeTemp = new EquiposCtrl().guar(request.getParameter("nombEqui"), request.getParameter("descEqui"));
                if(objeTemp != null)
                {
                    data.put("resp", true);
                    data.put("objeEqui", objeTemp);
                    data.put("mens", "Información guardada");
                    data.put("esNuevo", true);
                    data.put("estaModi", true);
                }
                else
                {
                    data.put("resp", false);
                    data.put("mens", "Error al guardar");
                }
            }
            else if(CRUD.equals("Modificar"))
            {
                int codi = Integer.parseInt(request.getParameter("codiEquiRadi") == null ? "-1" : 
                        request.getParameter("codiEquiRadi"));
                if(new EquiposCtrl().modi(codi, request.getParameter("nombEqui"), request.getParameter("descEqui")))
                {
                    data.put("resp", true);
                    data.put("mens", "Información modificada");
                }
                else
                {
                    data.put("resp", false);
                    data.put("mens", "Error al modificar");
                }
            }
            else if(CRUD.equals("Consultar"))
            {
                int codi = Integer.parseInt(request.getParameter("codiEquiRadi") == null ? "-1" : 
                        request.getParameter("codiEquiRadi"));
                Equipos objeTemp = new EquiposCtrl().cons(codi);
                if(objeTemp != null)
                {
                    data.put("resp", true);
                    data.put("objeEqui", objeTemp);
                    data.put("mens", "Información consultada");
                    data.put("estaModi", true);
                }
                else
                {
                    data.put("resp", false);
                    data.put("mens", "Error al consultar");
                }
            }
            else if(CRUD.equals("Nuevo"))
            {
                Equipos objeTemp = new Equipos();
                data.put("resp", true);
                data.put("objeEqui", objeTemp);
                data.put("mens", "Puede ingresar información nueva");
                data.put("limp", true);
            }
            out.print(new Gson().toJson(data));
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
