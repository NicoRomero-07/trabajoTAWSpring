<%-- 
    Document   : producto
    Created on : 15-may-2022, 12:23:59
    Author     : nicor
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@page import="es.trabajotaw.trabajotaw.entity.Categoria"%>
<%@page import="java.util.List"%>
<%@ page import="es.trabajotaw.trabajotaw.entity.Producto" %>
<%@ page import="es.trabajotaw.trabajotaw.dto.ProductoDTO" %>
<%@ page import="es.trabajotaw.trabajotaw.dto.CategoriaDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Producto</title>
</head>
<body>
<h1>Producto</h1>
<a href="/administrador/administrarProductos">Volver</a>
<%
    ProductoDTO producto = (ProductoDTO) request.getAttribute("producto");
%>
<form:form method="POST" action="/administrador/guardarProducto" modelAttribute="producto">
    <form:hidden path="idProducto" />
    Nombre del Producto: <form:input path="nombre" type="text" /><br><br>
    Descripción: <br><br><form:textarea path="descripcion" rows="10" cols="50"/><br><br>
    Precio Salida: <form:input type="number" path="precioSalida"/><br><br>
    URL Imagen: <form:input type="text" path="urlFoto" /><br><br>
    Fecha inicio de subasta: <form:input type = "date" path="fechaInicioSubasta"/><br><br>
    Fecha fin de subasta: <form:input type = "date" path="fechaFinSubasta" /><br><br>
    Comprador: <form:select path="comprador.idUsuario" items="${usuarios}" itemLabel="nombreUsuario" itemValue="idUsuario"/><br><br>
    Promocion: <form:checkbox path="enPromocion" /><br><br>
    Publicador: <form:select path="publicador.idUsuario" items="${usuarios}" itemLabel="nombreUsuario" itemValue="idUsuario"/><br><br>
    Categoría:
    <form:select path="categoria" items="${categorias}" itemValue="idCategoria" itemLabel="nombre"/>

    <form:button>Guardar</form:button>
</form:form>
</body>
</html>