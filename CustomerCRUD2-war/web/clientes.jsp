<%-- 
    Document   : clientes
    Created on : 09-abr-2018, 12:16:51
    Author     : guzman
--%>

<%@page import="customercrud.entity.Customer"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Customer> lista = (List)request.getAttribute("listac");
%>    

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Lista de clientes</h1>
        <table border="1">
            <tr>
            <th>IDCUSTOMER</th>                
            <th>NAME</th>                            
            <th>EMAIL</th>
            <th></th>            
            <th></th>                        
            </tr>
            <% 
                for (Customer cliente:lista) {
            %>    
            <tr>
                <td>
                <%= cliente.getCustomerId() %>
                </td>
                <td>
                <%= cliente.getName() %>
                </td>                
                <td>
                <%= cliente.getEmail() %>
                </td>                       
                <td>
                    <a href="CustomerBorrarServlet?id=<%= cliente.getCustomerId() %>">Borrar</a>
                </td>                       
                <td>
                    <a href="CustomerEditarServlet?id=<%= cliente.getCustomerId() %>">Editar</a>
                </td>                                       
            </tr>
            <% 
                }
            %>    
            
        </table>
            <a href="CustomerEditarServlet" >Añadir cliente</a>
    </body>
</html>
