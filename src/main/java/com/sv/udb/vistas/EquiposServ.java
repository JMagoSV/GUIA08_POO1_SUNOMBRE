/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.vistas;

import com.sv.udb.controlador.EquiposCtrl;
import com.sv.udb.modelo.Equipos;
import java.io.IOException;
import java.io.PrintWriter;
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
        boolean esValido = request.getMethod().equals("POST");
        String mens = "";
        boolean resp = false;
        boolean estaModi = false;
        boolean estaProcesado = false;
        if(!esValido)
        {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
        else
        {
            String CRUD = request.getParameter("btonEqui");
            if(CRUD.equals("Guardar"))
            {
                if(request.getAttribute("estaProcesado") != null)
                {
                    if((boolean)request.getAttribute("estaProcesado"))
                    {
                        Equipos objeTemp = (Equipos)request.getAttribute("objeEqui");
                        if(new EquiposCtrl().guar(objeTemp))
                        {
                            request.setAttribute("objeEqui", objeTemp);
                            mens = "Datos guardados";
                            estaModi = true;
                            resp = true;
                        }
                        else
                        {
                            mens = "Error al guardar";
                        }
                        estaProcesado = true;
                    }
                }
            }
            else if(CRUD.equals("Modificar"))
            {
                if(request.getAttribute("estaProcesado") != null)
                {
                    if((boolean)request.getAttribute("estaProcesado"))
                    {
                        Equipos objeTemp = (Equipos)request.getAttribute("objeEqui");
                        if(new EquiposCtrl().modi(objeTemp))
                        {
                            mens = "Datos Modificados";
                            estaModi = true;
                            resp = true;
                        }
                        else
                        {
                            mens = "Error al modificar";
                        }
                        estaProcesado = true;
                    }
                }
            }
            else if(CRUD.equals("Consultar"))
            {
                int codi = Integer.parseInt(request.getParameter("codiEquiRadi") == null ? "-1" : 
                        request.getParameter("codiEquiRadi"));
                Equipos objeTemp = new EquiposCtrl().cons(codi);
                if(objeTemp != null)
                {
                    request.setAttribute("objeEqui", objeTemp);
                    mens = "Información consultada";
                    estaModi = true;
                    resp = true;
                }
                else
                {
                    mens = "Error al consultar";
                }
                estaProcesado = true;
            }
            else if(CRUD.equals("Nuevo"))
            {
                Equipos obje = new Equipos();
                request.setAttribute("objeEqui", obje);
                mens = null;
                estaProcesado = true;
            }
            request.setAttribute("estaModi", estaModi); //Esta modificando
            request.setAttribute("mensAler", mens);
            request.setAttribute("resp", resp);
            request.setAttribute("estaProcesado", estaProcesado);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
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
