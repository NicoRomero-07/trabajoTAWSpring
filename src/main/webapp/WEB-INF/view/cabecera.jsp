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
%>
<jsp:forward page="/" />
<%
    }
%>
<table width="80%">
    <tr width="80%">
        <td>Bienvenido, <%= user.getEmail() %></td>
        <td>Session ID: <%= session.getId() %></td>
        <td><a href="/administrador/administrarUsuarios">Listado de usuarios</a></td>
        <td><a href="/salir">Salir</a></td>
    </tr>
</table>