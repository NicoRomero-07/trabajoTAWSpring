<%@ page import="es.trabajotaw.trabajotaw.entity.Usuario" %><%--
    Document   : cabecera
    Created on : 11-may-2022, 19:44:09
    Author     : nicor
--%>

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
        <td><a href="UsuariosServlet">Listado de usuarios</a></td>        
        <td><a href="LogoutServlet">Salir</a></td>        
    </tr>
</table>