<%-- 
    Document   : publicarProducto
    Created on : Apr 25, 2022, 9:04:48 AM
    Author     : Pablo
--%>

<%--Pablo (100%)--%>

<%@page import="es.trabajotaw.trabajotaw.dto.ProductoDTO"%>
<%@page import="es.trabajotaw.trabajotaw.entity.Categoria"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="java.util.List"%>
<%@ page import="es.trabajotaw.trabajotaw.dto.CategoriaDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Publicar Producto</title>
    </head>
    <body>
        <h1>Producto</h1>
        <a href="/vendedor/listaProductos">Volver</a>
        <spring:url value="/vendedor/guardarProducto"/>
        <%
            ProductoDTO producto = (ProductoDTO) request.getAttribute("producto");
        %>
        <form:form method="post" modelAttribute="producto">
            Nombre del Producto: <form:input path="nombre" type="text" />
            Descripción: <form:input path="descripcion" type="text" />
            Precio Salida: <form:input path="precioSalida" type="text" />
            URL Imagen: <form:input path="urlFoto" type="text" />
            Fecha inicio de subasta: <form:input path="fechInicioSubasta" type="text" />
            Fecha fin de subasta: <form:input path="fechFinSubasta" type="text" />
            Comprador: <form:input path="comprador" type="text" />
            Publicador: <form:input path="publicador" type="text" />
            Categoria: <form:select path="categoria">
                            <%
                                List<CategoriaDTO> categorias = (List) request.getAttribute("categorias");
                            %>
                            <form:options items="&{categorias}" itemLabel="nombre" itemValue="idCategoria"/>
            </form:select>
            <form:button>Enviar</form:button>
        </form:form>

        </form>
    </body>
</html>
