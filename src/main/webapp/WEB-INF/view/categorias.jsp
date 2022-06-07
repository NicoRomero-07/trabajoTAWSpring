<%-- 
    Document   : categorias
    Created on : 30-abr-2022, 14:03:29
    Author     : nicor
--%>

<%@page import="es.trabajotaw.trabajotaw.entity.Categoria"%>
<%@page import="java.util.List"%>
<%@ page import="es.trabajotaw.trabajotaw.dto.CategoriaDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categorias</title>
    </head>
    <body>
        <jsp:include page="cabecera.jsp" /> 
        <a href="/administrador/vistaAdministrador">Volver</a>
        <h1>Categorias</h1>
    <form method="post" action="/administrador/administrarCategorias">
            Nombre de Categoria: <input type="text" name="filtroNombre" value="" />
            <input type="submit" value="Filtrar" />
    </form>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>NOMBRE</th>
            <th></th>                                                     
            <th></th>
        </tr>
    <%
            List<CategoriaDTO> categorias = (List)request.getAttribute("categorias");
                for (CategoriaDTO categoria: categorias) {
    %> 
    
    <tr>
        <td><%= categoria.getIdCategoria() %></td>
        <td><%= categoria.getNombre() %></td>
        
        <td><a href="/administrador/borrarCategoria/<%= categoria.getIdCategoria() %>">Borrar</a></td>
        <td><a href="/administrador/administrarCategoria/<%= categoria.getIdCategoria() %>">Editar</a></td>
        
    </tr>
    <%
                }
    %>
    </table>
    <a href="/administrador/administrarCategoria/>">Crear nueva categoria ... </a>
    </body>
</html>
