<%-- 
    Document   : cabeceraMarketing
    Created on : 15-may-2022, 9:57:20
    Author     : Nicolás Zhao (100%)
--%>
<%@ page import="es.trabajotaw.trabajotaw.entity.Usuario" %>
<%@ page import="es.trabajotaw.trabajotaw.dto.UsuarioDTO" %>
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
        <td><a href="/salir">Salir</a></td>
    </tr>
</table>
