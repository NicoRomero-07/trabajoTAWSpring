<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : datosEstudio
    Created on : 20-abr-2022, 20:09:51
    Author     : Alfonso 100 %
--%>

<%@page import="es.trabajotaw.trabajotaw.dto.DatosEstudioUsuarioDTO"%>
<%@page import="es.trabajotaw.trabajotaw.dto.DatosEstudioProductoDTO"%>
<%@page import="es.trabajotaw.trabajotaw.dto.EstudioDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Datos de Estudio</title>
    </head>
    <%
        EstudioDTO estudio = (EstudioDTO) request.getAttribute("estudio");
        DatosEstudioProductoDTO estudioProducto = (DatosEstudioProductoDTO) request.getAttribute("estudioProducto");
        DatosEstudioUsuarioDTO estudioUsuario = (DatosEstudioUsuarioDTO) request.getAttribute("estudioUsuario");
        String errorCantidad = (String) request.getAttribute("error");
    %>
    <body>
        <%
        if(estudio.getProducto()){
        %>

        <form:form method="POST" action="save" modelAttribute="estudioProducto">
            <input type="hidden" name="idEstudio" value="<%= estudio == null ? "" : estudio.getIdEstudio()%>" />
            <input:hidden path="id" />
                <h1>Estudio de productos</h1>
                Ordenar por:<br>
                <form:checkbox  path="categorias" value="categorias" />Categorias<br>
                <form:checkbox path="vendidos" value="vendidos" />Vendidos<br>
                <form:checkbox path="promocion" value="promocion" />En promoción<br><br>
                Precio de Salida: &nbsp;&nbsp;
                <form:input path="precioSalida" size="20" /><br><br>
                Precio Actual: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <form:input path="precioActual" size="20" /><br>
                
                <%
                    if(errorCantidad != null && errorCantidad.equals("1")){
                        %> <br> ERROR: El precio actual no puede ser mayor que el precio de salida <%
                    }
                %>
            <form:button>Enviar</form:button>
        </form:form>

        <%
            }else{
        %>
        <form:form method="POST" action="save" modelAttribute="estudioUsuario">
              <input type="hidden" name="idEstudio" value="<%= estudio == null ? "" : estudio.getIdEstudio()%>" />
              <input:hidden path="id"/>
                    <h1>Estudio de <%= estudio.getVendedor() ? "Vendedores" : "Compradores" %></h1>
                    Ordenar por:<br>
                    <form:checkbox path="nombre" /> Nombre<br>
                    <form:checkbox path="apellidos"/> Apellidos<br>
                    <form:checkbox path="ingresos"/> <%= estudio.getVendedor() ? "Ingresos" : "Gastos" %> <br>
                    <form:checkbox path="ascendente"/>Ascendente<br>
            <br>
            <form:button>Enviar</form:button>
        </form:form>
        <%   
            }
        %>

    </body>
</html>
