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
        <a href="/vendedor/listaProductos">Volver</a><br/>
        <%
            ProductoDTO producto = (ProductoDTO) request.getAttribute("producto");
        %>
        <form:form method="POST" modelAttribute="producto" action="/vendedor/guardarProducto">
            <form:hidden path="idProducto"/>
            Nombre del Producto: <form:input type="text" path="nombre"/><br/><br/>
            Descripción: <br/><br/><form:textarea type="text" path="descripcion" rows="10" cols="50"/><br/><br/>
            Precio Salida: <form:input type="number" path="precioSalida"/><br/><br/>
            URL Imagen: <form:input type="text" path="urlFoto"/><br/><br/>
            Fecha inicio de subasta: <form:input  path="fechaInicioSubasta" type="date"/><br/><br/>
            Fecha fin de subasta: <form:input path="fechaFinSubasta" type="date"/><br/><br/>
            Categoria: <form:select path="categoria">
                            <form:options items="${categorias}" itemLabel="nombre" itemValue="idCategoria"/>
                       </form:select><br/><br/>

            <%
                List<CategoriaDTO> categorias = (List) request.getAttribute("categorias");
            %>
            <form:button>Enviar</form:button>
            <form:hidden path="comprador.nombreUsuario"/><br/><br/>
            <form:hidden path="publicador.nombreUsuario"/><br/><br/>
        </form:form>

        </form>
    </body>
</html>
