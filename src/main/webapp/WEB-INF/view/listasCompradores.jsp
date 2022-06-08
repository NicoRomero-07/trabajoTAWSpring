<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <body>
        <jsp:include page="cabeceraMarketing.jsp" /> 
        <h1>Listado de listas de compradores</h1>
        <c:choose>
            <c:when test="${listasCompradores!= null && !listasCompradores.isEmpty()}">
        <form method="POST" action="/marketing">
            Nombre: <input type="text" name="filtroNombre"/>
            <input type="submit" value="Filtrar"/>
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
            <c:forEach var="lista" items="${listasCompradores}">
            <tr>
                <td>${lista.idListaUsuario}</td>
                <td>${lista.nombre}</td>
                <td><a href="/marketing/${lista.idListaUsuario}/edit">Editar</a></td>
                <td><a href="/marketing/${lista.idListaUsuario}/delete">Borrar</a></td>
                <td><a href="/marketing/${lista.idListaUsuario}/send">Notificar promociones</a></td>
                <td><a href="/marketing/${lista.idListaUsuario}/purcharsers">Ver compradores</a></td>
            </tr>
            </c:forEach>
        </table>
        </c:when>
        <c:otherwise>
        <h2>NO HAY LISTAS</h2>
        </c:otherwise>
        </c:choose>
        <br>
        <a href="/marketing/-1/edit">Crear nueva lista ...</a>
    </body>
</html>
