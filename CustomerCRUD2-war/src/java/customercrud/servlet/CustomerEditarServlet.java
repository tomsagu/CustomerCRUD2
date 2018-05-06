/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customercrud.servlet;

import customercrud.ejb.CustomerFacade;
import customercrud.ejb.DiscountCodeFacade;
import customercrud.ejb.MicroMarketFacade;
import customercrud.entity.Customer;
import customercrud.entity.DiscountCode;
import customercrud.entity.MicroMarket;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author guzman
 */
@WebServlet(name = "CustomerEditarServlet", urlPatterns = {"/CustomerEditarServlet"})
public class CustomerEditarServlet extends HttpServlet {

    @EJB
    private CustomerFacade customerFacade;

    @EJB
    private DiscountCodeFacade discountCodeFacade;

    @EJB
    private MicroMarketFacade microMarketFacade;
                

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
    
        List<MicroMarket> listaSupermercados;
        List<DiscountCode> listaDescuentos;
        Customer cliente;
        String id;
        
        listaSupermercados = this.microMarketFacade.findAll();
        request.setAttribute("listaSupers", listaSupermercados);
        
        listaDescuentos = this.discountCodeFacade.findAll();
        request.setAttribute("listaDescs", listaDescuentos);
        
        id = request.getParameter("id");
        if (id != null) { // Caso de uso editar
            cliente = this.customerFacade.find(new Integer(id));
            request.setAttribute("cliente", cliente);            
        }
                        
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/nuevoEditarCliente.jsp");
        rd.forward(request, response);
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
