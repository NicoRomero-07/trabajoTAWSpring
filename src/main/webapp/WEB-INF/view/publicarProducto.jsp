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
            Integer isNew = (Integer) request.getAttribute("isNew");
        %>
        <form:form method="post" modelAttribute="producto" action="/vendedor/guardarProducto/${isNew}">
            <form:hidden path="idProducto"/>
            Nombre del Producto: <form:input path="nombre"/><br/><br/>
            Descripción: <form:textarea path="descripcion"/><br/><br/>
            Precio Salida: <form:input path="precioSalida"/><br/><br/>
            URL Imagen: <form:input path="urlFoto"/><br/><br/>
            Fecha inicio de subasta: <form:input path="fechaInicioSubasta" type="date"/><br/><br/>
            Fecha fin de subasta: <form:input path="fechaFinSubasta" type="date"/><br/><br/>
            Categoria: <form:select path="categoria">
                            <form:options items="${categorias}" itemLabel="nombre" itemValue="idCategoria"/>
                       </form:select><br/><br/>
            <%
                Integer isVendedor = (Integer) request.getAttribute("isVendedor");
                if(isVendedor != 1) {
            %>
            Comprador: <form:input path="comprador"/><br/><br/>
            Publicador: <form:input path="publicador"/><br/><br/>
            <%} else {%>
            <form:hidden path="comprador"/><br/><br/>
            <form:hidden path="publicador"/><br/><br/>
            <%
                }
                List<CategoriaDTO> categorias = (List) request.getAttribute("categorias");
            %>
            <form:button>Enviar</form:button>
        </form:form>

        </form>
    </body>
</html>
