<%-- 
    Document   : cabeceraVendedor
    Created on : May 15, 2022, 12:38:22 PM
    Author     : Pablo
--%>

<%--Pablo (100%)--%>

<%@page import="es.trabajotaw.trabajotaw.dto.UsuarioDTO"%>
<%@ page import="es.trabajotaw.trabajotaw.entity.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath());
    }
%>

<table width="80%">
    <tr width="80%">
        <td>Bienvenido, <%= user.getEmail() %></td>
        <td><a href="ListaVendedorServlet">Tus Productos</a></td>        
        <td><a href="/">Salir</a></td>
    </tr>
</table>
