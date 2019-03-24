/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import grafico.*;
import model.*;
import util.*;
import engine.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author glaubergad
 */
@WebServlet(name = "AppServlet", urlPatterns = {"/AppServlet"})
public class AppServlet extends HttpServlet {
    
    private TipoGrafico tipo1,tipo2,tipo3;
    private Grafico graf1,graf2,graf3;
    private List<String> rows;
    private DataSet dataSet;
    private ModeloDashboard modeloDashboard;
    private MotorTemplate motorTemplate;
    

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AppServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AppServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        return "App gerador de Dashboards";
    }// </editor-fold>
    
    
    public void alimentaTipoGrafico(){
        this.tipo1 = new TipoGrafico("areaChart", 4);
        this.tipo2 = new TipoGrafico("pieChart", 4);
        this.tipo3 = new TipoGrafico("rowChart", 4);
    }
    
    public void alimentaGraficos(){
        this.rows = carregaLista();
        alimentaTipoGrafico();
        this.graf1 = new Grafico(tipo1,"ano",this.rows);
        this.graf2 = new Grafico(tipo2,"ano",this.rows);
        this.graf3 = new Grafico(tipo2,"ano",this.rows);
    }
    
    public List<String> carregaLista(){
        List<String> lista = new ArrayList<String>();
        lista.add("sexo");
        lista.add("idade");
        lista.add("escolaridade");
        return lista;
    }
}
