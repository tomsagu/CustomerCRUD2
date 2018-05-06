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
@WebServlet(name = "CustomerCrearActualizarServlet", urlPatterns = {"/CustomerCrearActualizarServlet"})
public class CustomerCrearActualizarServlet extends HttpServlet {

    @EJB
    private DiscountCodeFacade discountCodeFacade;    
    @EJB
    private MicroMarketFacade microMarketFacade;
    @EJB
    private CustomerFacade customerFacade;
    
        

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
        
        String nombre, email, domicilio1, domicilio2, ciudad, telefono;
        String fax, supermercado, descuento, id;
        Customer cliente;
        
        nombre = request.getParameter("nombre");
        email = request.getParameter("email");               
        domicilio1 = request.getParameter("domicilio1");        
        domicilio2 = request.getParameter("domicilio2");                
        ciudad = request.getParameter("ciudad");       
        telefono = request.getParameter("telefono");                
        fax = request.getParameter("fax");
        supermercado = request.getParameter("supermercado");
        descuento = request.getParameter("descuento");
        id = request.getParameter("id");
        
        if ("".equals(id)) { // Crear
            cliente = new Customer();
        } else { // Editar
            cliente = this.customerFacade.find(new Integer(id));
        }
                        
        cliente.setName(nombre);
        cliente.setEmail(email);
        cliente.setAddressline1(domicilio1);
        cliente.setAddressline2(domicilio2);
        cliente.setCity(ciudad);
        cliente.setPhone(telefono);
        cliente.setFax(fax);
        
        MicroMarket mm = this.microMarketFacade.find(supermercado);
        cliente.setZip(mm);
        
        DiscountCode dc = this.discountCodeFacade.find(descuento);
        cliente.setDiscountCode(dc);
        
        if ("".equals(id)) { // Crear
            cliente.setCustomerId(this.customerFacade.buscarCustomerIdSiguiente());        
            this.customerFacade.create(cliente);
        } else { // Editar
            this.customerFacade.edit(cliente);
        }        
        
        
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/CustomerServlet");
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
