<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Document   : usuarios
    Created on : 20-abr-2022, 15:18:27
    Author     : Nicolás Zhao (100%)
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%--
<%@page import="ListaUsuario"%>
<%@page import="Usuario"%>
--%>
<%@page import="java.util.List"%>
<%@ page import="es.trabajotaw.trabajotaw.entity.ListaUsuario" %>
<%@ page import="es.trabajotaw.trabajotaw.entity.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Compradores</title>
    </head>
    <body>
        <jsp:include page="cabeceraMarketing.jsp" /> 
        <h1>Compradores</h1>
    <table border="1">
        <tr>
            <th>NOMBRE_USUARIO</th>
            <th>CONTRASEÑA</th>
            <th>EMAIL</th>
            <th>NOMBRE</th>
            <th>PRIMER_APELLIDO</th>
            <th>SEGUNDO_APELLIDO</th>
            <th>FECHA_NACIMIENTO</th>
            <th>SEXO</th>
            <th>TIPO_USUARIO</th>
            <th></th>                                                     
        </tr>
        <c:forEach var="user" items="${compradores}">
    <tr>
        <td>${user.nombreUsuario}</td>
        <td>${user.contrasenya}</td>
        <td>${user.email}</td>
        <td>${user.nombre}</td>
        <td>${user.primerApellido}</td>
        <td>${user.segundoApellido}</td>
        <td>${user.fechaNacimiento}</td>
        <td>${user.sexo}</td>
        <td>${user.tipoUsuario.tipo}</td>
        <td><a href="${user.idUsuario}/messages">Ver bandeja de mesajes</a></td>
        
    </tr>
        </c:forEach>
    </table>
    <br/>
    <a href="/marketing/return"><input type="button" value="Volver"/></a>
    </body>
</html>
