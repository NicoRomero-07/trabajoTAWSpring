<%-- 
    Document   : listaComprador
    Created on : 22-abr-2022, 10:29:30
    Author     : Nicolás Zhao (100%)
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page import="es.trabajotaw.trabajotaw.entity.ListaUsuario" %>
<%@ page import="es.trabajotaw.trabajotaw.entity.Usuario" %>

<%--
<%@page import="Usuario"%>
<%@page import="ListaUsuario"%>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nueva lista</title>
    </head>
    <%
            Boolean error = (Boolean) request.getAttribute("error");
    %>
    <body>
        <jsp:include page="cabeceraMarketing.jsp" /> 
        <h1>Datos de la lista</h1>
        <%
            if (error == null || !error){
        %>
        <%--@elvariable id="listaComprador" type="es.trabajotaw.trabajotaw.dto.ListaUsuarioDTO"--%>
        <form:form method="POST" action="/marketing/save" modelAttribute="listaComprador">
            <form:hidden path="idListaUsuario"/>
            <table border="1">
                <tr>
                    <th>Nombre</th>
                    <td><form:input path="nombre"  required="required"/></td>
                </tr>
                <tr>
                    <th>Compradores:</th>
                    <td><form:checkboxes path="usuarioDTOList" items="${compradores}" itemLabel="nombreUsuario"
                                         itemValue="idUsuario" delimiter="<br/>"/></td>
                </tr>
            </table>
                <br/>
            <input type="submit" value="Confirmar" />
            <a href="/marketing/return"><input type="button" value="Volver"/></a>
        </form:form>
            <%
            }else{
            %>
            <h2>NO SE HA ELEGIDO NINGUN COMPRADOR</h2><br/>
            <a href="/marketing/return"><input type="button" value="Volver"/></a>
        <%
            }
        %>

    </body>
</html>
