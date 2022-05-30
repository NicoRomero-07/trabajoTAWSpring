<%-- 
    Document   : mensajes
    Created on : 02-may-2022, 9:49:28
    Author     : Nicolás Zhao (100%)
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%-- 
<%@page import="ListaUsuario"%>
<%@page import="Usuario"%>
<%@page import="Notificacion"%>
--%>
<%@page import="java.util.List"%>
<%@ page import="es.trabajotaw.trabajotaw.entity.Notificacion" %>
<%@ page import="es.trabajotaw.trabajotaw.entity.ListaUsuario" %>
<%@ page import="es.trabajotaw.trabajotaw.entity.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bandeja de mensajes</title>
    </head>
    <%
        List<Notificacion> notificaciones = (List)request.getAttribute("notificaciones");
        Usuario comprador = (Usuario)request.getAttribute("comprador");
        ListaUsuario lista = (ListaUsuario) request.getAttribute("lista");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    %>
    <body>
        <jsp:include page="cabeceraMarketing.jsp" /> 
        <h1>Bandeja de mensajes</h1>
        <%
            
        if (notificaciones != null && !notificaciones.isEmpty()){
        %>
        <table border="1">
        <tr>
            <th>FECHA ENVIO</th>
            <th>CONTENIDO</th>
            <th></th>                                                     
        </tr>
        <%
                for (Notificacion notificacion : notificaciones) {
        %> 
         <tr>
             <td><%= formatter.format(notificacion.getFechaEnvio())%></td>
             <td><%= notificacion.getContenido() %></td>
             <td><a href="/marketing/<%=lista.getIdListaUsuario()%>/<%=comprador.getIdUsuario()%>/<%=notificacion.getIdNotificacion()%>/deleteMessage">Borrar</a></td>
         </tr>
        <%
             }
        %>
        </table>
        <%
            }else{
        %>
        <h2>NO TIENE MENSAJES</h2>
        <%
            }
        %>
        <br/>
        <a href="/marketing/<%=lista.getIdListaUsuario()%>/purcharsers"><input type="button" value="Volver"/></a>
    </body>
</html>
