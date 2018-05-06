<%-- 
    Document   : nuevoCliente
    Created on : 23-abr-2018, 8:59:06
    Author     : guzman
--%>

<%@page import="customercrud.entity.Customer"%>
<%@page import="customercrud.entity.DiscountCode"%>
<%@page import="customercrud.entity.MicroMarket"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<MicroMarket> listaSupermercados;
    List<DiscountCode> listaDescuentos;
    Customer cliente;
    String nombre, email, domicilio1, domicilio2, ciudad, telefono, fax,
           supermercado, descuento, id;
    
    listaSupermercados = (List)request.getAttribute("listaSupers");
    listaDescuentos = (List)request.getAttribute("listaDescs");
    cliente = (Customer)request.getAttribute("cliente");
    if (cliente != null) { // Editar customer
        id = cliente.getCustomerId().toString();
        nombre = cliente.getName();
        email =  cliente.getEmail();
        domicilio1 = cliente.getAddressline1();
        domicilio2 = cliente.getAddressline2();
        ciudad = cliente.getCity();
        telefono = cliente.getPhone();
        fax = cliente.getFax();
        supermercado = cliente.getZip().getZipCode();
        descuento = cliente.getDiscountCode().getDiscountCode();
    } else { // Crear customer
        id = "";
        nombre = "";
        email = "";
        domicilio1 = "";
        domicilio2 = "";
        ciudad = "";
        telefono = "";
        fax = "";
        supermercado = "";
        descuento = "";
    }
    
%>    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Crear cliente nuevo</h1>
        <form action="CustomerCrearActualizarServlet" method="post">
            <input type="hidden" name="id" value="<%= id %>" />
            <table>
                <tr>
                    <td>NAME:</td>
                    <td><input type="text" name="nombre" max="30" maxlength="30" value="<%= nombre %>"/></td>                    
                </tr>
                <tr>
                    <td>EMAIL:</td>
                    <td><input type="text" name="email" value="<%= email %>"/></td>                    
                </tr>
                <tr>
                    <td>ADDRESS:</td>
                    <td><input type="text" name="domicilio1" value="<%= domicilio1 %>"/>
                        <input type="text" name="domicilio2" value="<%= domicilio2 %>"/>
                    </td>                    
                </tr>
                <tr>
                    <td>CITY:</td>
                    <td><input type="text" name="ciudad" value="<%= ciudad %>"/></td>                    
                </tr>
                <tr>
                    <td>PHONE:</td>
                    <td><input type="text" name="telefono" value="<%= telefono %>"/></td>                    
                </tr>
                <tr>
                    <td>FAX:</td>
                    <td><input type="text" name="fax" value="<%= fax %>"/></td>                    
                </tr>                
                <tr>
                    <td>MICROMARKET:</td>
                    <td><select name="supermercado">
                    <%
                        for (MicroMarket mm: listaSupermercados) {
                            String seleccionado = "";
                            if (!"".equals(supermercado) && mm.getZipCode().equals(supermercado)) {
                                seleccionado = "selected";
                            }
                            
                    %>
                      <option <%= seleccionado %> value="<%= mm.getZipCode() %>"><%= mm.getZipCode() %></option>                    
                    <%        
                        }
                    %>          
                        </select>
                    </td>                    
                </tr>                
                <tr>
                    <td>DISCOUNT CODE:</td>
                    <td><select name="descuento">
                    <%
                        for (DiscountCode dc: listaDescuentos) {
                            String seleccionado = "";
                            if (!"".equals(descuento) && dc.getDiscountCode().equals(descuento)) {
                                seleccionado = "selected";
                            }
                            
                    %>
                      <option <%= seleccionado %> value="<%= dc.getDiscountCode() %>"><%= dc.getRate() %></option>                    
                    <%        
                        }
                    %>          
                        </select>
                    </td>                    
                </tr>                
                
            </table>
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
