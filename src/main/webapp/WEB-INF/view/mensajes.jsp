<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<%@ page import="es.trabajotaw.trabajotaw.dto.NotificacionDTO" %>
<%@ page import="es.trabajotaw.trabajotaw.dto.ListaUsuarioDTO" %>
<%@ page import="es.trabajotaw.trabajotaw.dto.UsuarioDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bandeja de mensajes</title>
    </head>
    <body>
        <jsp:include page="cabeceraMarketing.jsp" /> 
        <h1>Bandeja de mensajes</h1>
        <c:choose>
        <c:when test="${notificaciones != null && !notificaciones.isEmpty()}">
        <table border="1">
        <tr>
            <th>FECHA ENVIO</th>
            <th>CONTENIDO</th>
            <th></th>                                                     
        </tr>
        <c:forEach var="notificacion" items="${notificaciones}">
         <tr>
             <td>${notificacion.fechaEnvio}</td>
             <td>${notificacion.contenido}</td>
             <td><a href="/marketing/${lista.idListaUsuario}/${comprador.idUsuario}/${notificacion.idNotificacion}/deleteMessage">Borrar</a></td>
         </tr>
        </c:forEach>
        </table>
        </c:when>
        <c:otherwise>
        <h2>NO TIENE MENSAJES</h2>
        </c:otherwise>
        </c:choose>
        <br/>
        <a href="/marketing/${lista.idListaUsuario}/purcharsers"><input type="button" value="Volver"/></a>
    </body>
</html>
