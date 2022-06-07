<%-- 
    Document   : listasCompradores
    Created on : 17-abr-2022, 14:49:02
    Author     : Nicolás Zhao (100%)
--%>

<%@page import="java.util.List"%>
<%@ page import="es.trabajotaw.trabajotaw.entity.ListaUsuario" %>
<%@ page import="es.trabajotaw.trabajotaw.dto.ListaUsuarioDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de listas de compradores</title>
    </head>
    <%
        List<ListaUsuarioDTO> listas = (List)request.getAttribute("listasCompradores");
    %>
    <body>
        <jsp:include page="cabeceraMarketing.jsp" /> 
        <h1>Listado de listas de compradores</h1>
        
        <%
            if (listas!= null && !listas.isEmpty()){
        %>
        <form method="POST" action="/marketing/filtro">
            Nombre: <input type="text" name="filtroNombre" value="" />
            <input type="submit" value="Filtrar" />
        </form>
        <br>
        <table border="1">
            <tr>
                <th>ID_LISTA</th>
                <th>NOMBRE_LISTA</th>                  
                <th></th>                     
                <th></th>              
                <th></th>
                <th></th>
            </tr>
            <%
                for (ListaUsuarioDTO lista: listas) {
            %>
            <tr>
                <td><%= lista.getIdListaUsuario() %></td>
                <td><%= lista.getNombre()%></td>        
                <td><a href="<%= lista.getIdListaUsuario() %>/edit">Editar</a></td>
                <td><a href="<%= lista.getIdListaUsuario() %>/delete">Borrar</a></td>
                <td><a href="<%= lista.getIdListaUsuario() %>/send">Notificar promociones</a></td>
                <td><a href="<%= lista.getIdListaUsuario() %>/purcharsers">Ver compradores</a></td>
            </tr>
            <%
                }
            %>
        </table>
        <%
            }else{
        %>
        <h2>NO HAY LISTAS</h2>
        <%
            }
        %>
        <br>
        <a href="-1/edit">Crear nueva lista ...</a>
    </body>
</html>
