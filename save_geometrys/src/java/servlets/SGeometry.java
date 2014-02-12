/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import bean.BGeometry;
import bean.BPoint;
import datasource.BDConnecion;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager.ManagerGeometry;

/**
 *
 * @author ruben
 */
public class SGeometry extends HttpServlet {

    ManagerGeometry managergeometry = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        ServletContext ctx = this.getServletConfig().getServletContext();
        BDConnecion conexion = new BDConnecion(ctx);
        managergeometry = new ManagerGeometry(conexion);

        BGeometry bGeometry = new BGeometry();

        try {

            String id = managergeometry.optener_ultimo() + "g";
            String tipo = request.getParameter("tipo_geometry");
            
            
            for (int i = 0; i < 10; i++) {
        
                
            }
            
            
            
            String coordenadas = request.getParameter("geometry0");
            System.out.println("cordenada" + request.getParameter("geometry0"));
            bGeometry.setId(id);
            bGeometry.setTipo(tipo);
            bGeometry.setCordenadas(coordenadas);

            managergeometry.insert_geometry(bGeometry);
            response.sendRedirect("index.jsp");

        } finally {
            out.close();
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
