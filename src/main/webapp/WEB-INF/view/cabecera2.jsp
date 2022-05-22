<%-- 
    Document   : cabecera2
    Created on : 15-may-2022, 12:00:32
    Author     : Alfon
--%>

<%@ page import="es.trabajotaw.trabajotaw.entity.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Usuario user = (Usuario)session.getAttribute("usuario");
    if (user == null) {
        response.sendRedirect(request.getContextPath());
    }
%>

<table width="80%">
    <tr width="80%">
        <td>Bienvenido, <%= user.getEmail() %></td>
        <td>Session ID: <%= session.getId() %></td>
        <td><a href="LogoutServlet">Salir</a></td>        
    </tr>
</table>
