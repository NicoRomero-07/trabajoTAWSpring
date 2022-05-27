<<<<<<< HEAD
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
=======
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
>>>>>>> 71a343530470d070538feb24bdfa57a238b9d4dc
<%--
    Document   : estudio
    Created on : 17-abr-2022, 20:57:42
    Author     : Alfonso 100%
--%>

<%@page import="es.trabajotaw.trabajotaw.dto.EstudioDTO"%>
<%@page import="es.trabajotaw.trabajotaw.dto.UsuarioDTO"%>
<%@page import="es.trabajotaw.trabajotaw.dto.EstudioDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Estudio </title>
    </head>
    <body>
<<<<<<< HEAD
        <%
            EstudioDTO estudio = (EstudioDTO) request.getAttribute("estudio");
        %>
    <h1>Datos del estudio</h1>
        <form:form method="POST" action="/analista/save" modelAttribute="estudio">
            <form:hidden  path="idEstudio" />
=======
        <h1>Datos del estudio</h1>
        <form method="POST" action="saveEstudio">
            <input type="hidden" name="id" value="<%= estudio == null ? "" : estudio.getIdEstudio()%>" />
            
>>>>>>> main
            Nombre: 
            <form:input size="20"  maxlength="20" path="nombre" required="true" /> <br><br>
            
            Analista: 
            <form:select path="analista">
                <form:options items="${usuarios}" itemLabel="nombreUsuario" itemValue="idUsuario"/>
            </form:select><br><br>
            
            Descripcion:
            <br><form:textarea cols="100" rows="5" maxlength="100" path="descripcion"></form:textarea><br><br>
            Elementos a estudiar:<br>
            <input type="radio" name="element" value="comprador" <%= estudio == null || estudio.getComprador() == Boolean.FALSE ? "" : "checked" %>/>Comprador<br>
            <input type="radio" name="element" value="vendedor"  <%= estudio == null || estudio.getVendedor() == Boolean.FALSE ? "" : "checked" %>/>Vendedor<br>
            <input type="radio" name="element" value="producto" <%= estudio == null || estudio.getProducto() == Boolean.FALSE ? "" : "checked" %> />Producto<br><br>
<<<<<<< HEAD
            <form:button>Enviar</form:button>
        </form:form>
=======
            
            <button type="submit" value="Enviar"><a href="datosEstudio/<%= estudio == null || estudio.getIdEstudio() == null ? "" : estudio.getIdEstudio() %>">Editar</a></button>
        </form>
>>>>>>> main
    </body>
</html>
